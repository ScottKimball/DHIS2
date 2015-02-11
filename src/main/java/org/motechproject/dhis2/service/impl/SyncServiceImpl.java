package org.motechproject.dhis2.service.impl;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.HttpResponse;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.motechproject.dhis2.domain.DataElement;
import org.motechproject.dhis2.domain.OrgUnit;
import org.motechproject.dhis2.domain.Program;
import org.motechproject.dhis2.domain.Settings;
import org.motechproject.dhis2.domain.Stage;
import org.motechproject.dhis2.domain.TrackedEntity;
import org.motechproject.dhis2.domain.TrackedEntityAttribute;
import org.motechproject.dhis2.http.HttpQuery;
import org.motechproject.dhis2.http.Request;
import org.motechproject.dhis2.repository.DataElementDataService;
import org.motechproject.dhis2.repository.OrgUnitDataService;
import org.motechproject.dhis2.repository.ProgramDataService;
import org.motechproject.dhis2.repository.StageDataService;
import org.motechproject.dhis2.repository.TrackedEntityAttributeDataService;
import org.motechproject.dhis2.repository.TrackedEntityDataService;
import org.motechproject.dhis2.service.SettingsService;
import org.motechproject.dhis2.service.SyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by scott on 12/15/14.
 */

@Service
public class SyncServiceImpl implements SyncService {

    @Autowired
    @Qualifier("dhisSettingsService")
    private SettingsService settingsService;

    @Autowired
    private HttpQuery httpQuery;

    @Autowired
    private TrackedEntityAttributeDataService attributeDataService;

    @Autowired
    private TrackedEntityDataService trackedEntityDataService;

    @Autowired
    private ProgramDataService programDataService;

    @Autowired
    private OrgUnitDataService orgUnitDataService;

    @Autowired
    private DataElementDataService dataElementDataService;

    @Autowired
    private StageDataService stageDataService;


    private Logger logger = LoggerFactory.getLogger(SyncServiceImpl.class);

    /*Path Strings for Json Path*/
    private static final String NAME = "$.name";
    private static final String ID = "$.id";
    private static final String TRACKED_ENTITIES = "$.trackedEntities";
    private static final String TRACKED_ENTITY_ATTRIBUTES = "$.trackedEntityAttributes";
    private static final String PROGRAMS = "$.programs";
    private static final String TRACKED_ENTITY_ID = "$.trackedEntity.id";
    private static final String SINGLE_EVENT = "$.singleEvent";
    private static final String REGISTRATION = "$.registration";
    private static final String PROGRAM_STAGES = "$.programStages";
    private static final String PROGRAM_TRACKED_ENTITY_ATTRIBUTES = "$.programTrackedEntityAttributes[*].trackedEntityAttribute";
    private static final String PROGRAM_STAGE_DATA_ELEMENTS = "$.programStageDataElements[*].dataElement";
    private static final String ORG_UNITS = "$.organisationUnits";
    private static final String DATA_ELEMENTS = "$.dataElements";

    private static final String NO_PAGING_NO_LINKS = "?paging=false&links=false";

    private static final String DEBUG_ID = " ID: ";

    @Override
    public boolean sync() {




        logger.debug("Starting Sync");
        long startTime = System.nanoTime();
        Settings settings = settingsService.getSettings();

        if (!testConnection(settings)) {
            return false;
        }



        programDataService.deleteAll();
        attributeDataService.deleteAll();
        trackedEntityDataService.deleteAll();
        orgUnitDataService.deleteAll();
        dataElementDataService.deleteAll();
        stageDataService.deleteAll();

        try {

            addDataElements(settings);
            addAttributes(settings);
            addTrackedEntities(settings);
            addPrograms(settings);
            addOrgUnits(settings);

            long endTime = System.nanoTime();


            logger.debug("Sync Successful");
            logger.debug("Time for sync: " + TimeUnit.SECONDS.convert(endTime - startTime,
                    TimeUnit.NANOSECONDS) + "seconds");

            return true;


        } catch (PathNotFoundException e) {

            programDataService.deleteAll();
            attributeDataService.deleteAll();
            trackedEntityDataService.deleteAll();
            orgUnitDataService.deleteAll();
            dataElementDataService.deleteAll();
            stageDataService.deleteAll();

            logger.error("Problem with DHIS2 application Schema.Sync Unsuccessful");
            logger.error(e.toString());
            return false;
        }


    }


    private void addDataElements(Settings settings) {

        Request dataElementRequest = new Request(settings.getDataElementsURI() + NO_PAGING_NO_LINKS);
        logger.debug("In Add Data Elements");

        List<Object> dataElements = JsonPath.read(httpQuery.send(dataElementRequest, settings.getUsername(), settings.getPassword()), DATA_ELEMENTS);

        for (Object o : dataElements) {
            String name = JsonPath.read(o, NAME);
            String id = JsonPath.read(o, ID);
            logger.debug("DataElement name:" + name +  DEBUG_ID + id);
            dataElementDataService.create(new DataElement(name, id));
        }

    }


    private void addAttributes(Settings settings)  {

        Request attributeRequest = new Request(settings.getTrackedEntityAttributesURI() + NO_PAGING_NO_LINKS);


        List<Object> attributes = JsonPath.read(httpQuery.send(attributeRequest, settings.getUsername(), settings.getPassword()), TRACKED_ENTITY_ATTRIBUTES);

        for (Object o : attributes) {
            String name = JsonPath.read(o, NAME);
            String id = JsonPath.read(o, ID);

            logger.debug("Tracked Entity Attribute name: " + name  + DEBUG_ID + id);
            attributeDataService.create(new TrackedEntityAttribute(name, id));

        }

    }

    private void addTrackedEntities(Settings settings) {

        Request trackedEntityRequest = new Request(settings.getTrackedEntityURI() + NO_PAGING_NO_LINKS);

        List<Object> trackedEntities = JsonPath.read(httpQuery.send(trackedEntityRequest, settings.getUsername(), settings.getPassword()), TRACKED_ENTITIES);

        for (Object o : trackedEntities) {
            String name = JsonPath.read(o, NAME);
            String id = JsonPath.read(o, ID);

            logger.debug("Tracked Entity Name: " + name + " ID: " + id);
            trackedEntityDataService.create(new TrackedEntity(name, id));

        }
    }

    private void addPrograms(Settings settings)  {


        Request programsRequest = new Request(settings.getProgramURI() + NO_PAGING_NO_LINKS);
        List<Object> programs = JsonPath.read(httpQuery.send(programsRequest, settings.getUsername(), settings.getPassword()), PROGRAMS);

        for (Object o : programs) {
            String id = JsonPath.read(o, ID);
            Program program = buildProgram(settings, id);

            if (program.hasRegistration()) {
                logger.debug("Program name: " + program.getName() + DEBUG_ID + program.getUuid());
                programDataService.create(program);
            }
        }
    }

    private Program buildProgram(Settings settings, String id) {

        Program program = new Program();

        Request programRequest = new Request(settings.getProgramURI() + "/" + id);
        Object programInfo = httpQuery.send(programRequest, settings.getUsername(), settings.getPassword());

        boolean registration = JsonPath.parse(programInfo).read(REGISTRATION);
        boolean singleEvent = JsonPath.parse(programInfo).read(SINGLE_EVENT);

        String name = JsonPath.read(programInfo, NAME);

         /*Build program stages list*/
        List<Stage> programStages = new ArrayList<>();
        List<Object> stages = JsonPath.read(programInfo, PROGRAM_STAGES);

        for (Object o : stages) {
            String stageId = JsonPath.read(o, ID);
            programStages.add(buildStage(settings, stageId , id, registration));
        }

        List<TrackedEntityAttribute> programTrackedEntityAttributes = new ArrayList<>();

        /*If program has a tracked entity*/
        if (registration) {

            /*Setting tracked Entity*/
            String trackedEntityId = JsonPath.read(programInfo, TRACKED_ENTITY_ID);
            TrackedEntity trackedEntity = trackedEntityDataService.findByUuid(trackedEntityId);
            program.setTrackedEntity(trackedEntity);

            try {
                List<Object> attributes = JsonPath.read(programInfo, PROGRAM_TRACKED_ENTITY_ATTRIBUTES);


        /*Build program attributes list*/
                for (Object o : attributes) {
                    String attributeId = JsonPath.read(o, ID);

                    TrackedEntityAttribute attribute = attributeDataService.findByUuid(attributeId);

                    if (attribute == null) {
                        String attributeName = JsonPath.read(o, NAME);
                        attribute = new TrackedEntityAttribute(attributeName, attributeId);
                    }
                    programTrackedEntityAttributes.add(attribute);
                }


            } catch (PathNotFoundException e) {
                logger.debug("No path for programTrackedEntityAttributes for program : " + name);
            }

        }

        program.setUuid(id);
        program.setName(name);
        program.setStages(programStages);
        program.setAttributes(programTrackedEntityAttributes);
        program.setSingleEvent(singleEvent);
        program.setRegistration(registration);

        return program;
    }

    private Stage buildStage(Settings settings, String id, String programId, boolean registration)  {

        Request stageRequest = new Request(settings.getStagesURI() + "/" + id);
        Object stageInfo = httpQuery.send(stageRequest, settings.getUsername(), settings.getPassword());

        String name = JsonPath.read(stageInfo, NAME);


        List<DataElement> programStageDataElements = new ArrayList<>();

        try {
            List<Object> dataElements = JsonPath.read(stageInfo, PROGRAM_STAGE_DATA_ELEMENTS);
            for (Object o : dataElements) {
                String elementId = JsonPath.read(o, ID);
                String elementName = JsonPath.read(o, NAME);

                DataElement dataElement = dataElementDataService.findByUuid(elementId);

                if (dataElement == null) {
                    dataElement = new DataElement();
                    dataElement.setUuid(elementId);
                    dataElement.setName(elementName);
                    dataElementDataService.create(dataElement);
                }

                programStageDataElements.add(dataElement);
            }
        } catch (PathNotFoundException e) {
            logger.debug("No path for programStageDataElements for stage : " + name);
        }


        Stage stage = new Stage();
        stage.setName(name);
        stage.setUuid(id);
        stage.setDataElements(programStageDataElements);
        stage.setProgram(programId);
        stage.setRegistration(registration);

        logger.debug("Stage name: " + stage.getName() + DEBUG_ID + stage.getUuid());
        stageDataService.create(stage);

        return stage;

    }

    private boolean testConnection(Settings settings) {
        HttpGet get = new HttpGet(settings.getProgramURI());
        get.addHeader(BasicScheme.authenticate(
                new UsernamePasswordCredentials(settings.getUsername(), settings.getPassword()),
                "UTF-8",
                false));

        try {
            HttpResponse httpResponse = new DefaultHttpClient().execute(get);

            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                return true;
            }

        } catch (Exception e) {
            logger.error(e.toString());
            return false;
        }

        return false;


    }

    private void addOrgUnits(Settings settings) {

        Request orgUnitRequest = new Request(settings.getOrgUnitsURI() + NO_PAGING_NO_LINKS);

        List<Object> orgUnits = JsonPath.read(httpQuery.send(orgUnitRequest, settings.getUsername(), settings.getPassword()), ORG_UNITS);

        for (Object o : orgUnits) {
            String name = JsonPath.read(o, NAME);
            String id = JsonPath.read(o, ID);

            logger.debug("Org Unit name: " + name + DEBUG_ID + id);
            orgUnitDataService.create(new OrgUnit(name, id));

        }
    }

}
