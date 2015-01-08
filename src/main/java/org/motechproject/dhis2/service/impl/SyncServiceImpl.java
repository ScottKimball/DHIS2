package org.motechproject.dhis2.service.impl;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import org.motechproject.dhis2.domain.*;
import org.motechproject.dhis2.http.HttpConstants;
import org.motechproject.dhis2.http.HttpQuery;
import org.motechproject.dhis2.http.Request;
import org.motechproject.dhis2.repository.OrgUnitDataService;
import org.motechproject.dhis2.repository.ProgramDataService;
import org.motechproject.dhis2.repository.TrackedEntityAttributeDataService;
import org.motechproject.dhis2.repository.TrackedEntityDataService;
import org.motechproject.dhis2.service.SyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by scott on 12/15/14.
 */

@Service
public class SyncServiceImpl implements SyncService {


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


    private Logger logger = LoggerFactory.getLogger(SyncServiceImpl.class);


    @Override
    public boolean sync() {

        if (!testConnection())
            return false;

        programDataService.deleteAll();
        attributeDataService.deleteAll();
        trackedEntityDataService.deleteAll();
        orgUnitDataService.deleteAll();

        try {
            addAttributes();
            addTrackedEntities();
            addPrograms();
            addOrgUnits();

        /*TODO: Tracked entity instances*/

            return true;


        } catch (PathNotFoundException e) {
            logger.debug("Problem with DHIS2 application Schema.");
            logger.debug(e.toString());
            return false;
        }


    }

    private void addAttributes () throws PathNotFoundException  {

        Request attributeRequest = new Request(HttpConstants.TRACKED_ENTITY_ATTRIBUTES_PATH + "?" +
                HttpConstants.NO_PAGING_NO_LINKS);
        List<Object> attributes = JsonPath.read(httpQuery.send(attributeRequest),"$.trackedEntityAttributes");

        for (Object o : attributes) {
            String name = JsonPath.read(o, "$.name");
            String id = JsonPath.read(o,"$.id");

            attributeDataService.create(new TrackedEntityAttribute(name,id));

        }

    }

    private void addTrackedEntities() throws PathNotFoundException {

        Request trackedEntityRequest = new Request(HttpConstants.TRACKED_ENTITY_PATH + "?" +
                HttpConstants.NO_PAGING_NO_LINKS);

        List<Object> trackedEntities = JsonPath.read(httpQuery.send(trackedEntityRequest),"$.trackedEntities");

        for (Object o : trackedEntities) {
            String name = JsonPath.read(o, "$.name");
            String id = JsonPath.read(o,"$.id");

            trackedEntityDataService.create(new TrackedEntity(name,id));

        }
    }

    private void addPrograms () throws PathNotFoundException {


        Request programsRequest = new Request(HttpConstants.PROGRAM_PATH + "?" + HttpConstants.NO_PAGING_NO_LINKS);
        List<Object> programs = JsonPath.read(httpQuery.send(programsRequest), "$.programs");

        for (Object o : programs) {
            String id = JsonPath.read(o,"$.id");
            programDataService.create(buildProgram(id));

        }


    }

    private Program buildProgram (String id) throws PathNotFoundException{

        Request programRequest = new Request(HttpConstants.PROGRAM_PATH + "/" + id);
        Object programInfo = httpQuery.send(programRequest);


        String name = JsonPath.read(programInfo,"$.name");

         /*Get associated tracked entity*/
        TrackedEntity trackedEntity = new TrackedEntity(
                (String) JsonPath.read(programInfo, "$.trackedEntity.name"),
                (String) JsonPath.read(programInfo, "$.trackedEntity.id"));


        /*Build program stages list*/
        List<Stage> programStages = new ArrayList<>();
        List<Object> stages = JsonPath.read(programInfo,"$.programStages");

        for (Object o: stages) {
            String stageId = JsonPath.read(o,"$.id");
            programStages.add(buildStage(stageId));
        }


        /*Build program attributes list*/
        List<Object>  attributes = JsonPath.read(programInfo,"$.programTrackedEntityAttributes");
        List<TrackedEntityAttribute> programTrackedEntityAttributes = new ArrayList<>();

        for (Object o: attributes) {
            String attributeName = JsonPath.read(o, "$.attribute.name");
            String attributeId = JsonPath.read(o,"$.attribute.id");
            programTrackedEntityAttributes.add(new TrackedEntityAttribute(attributeName,attributeId));
        }


        Program program = new Program();
        program.setUuid(id);
        program.setName(name);
        program.setTrackedEntity(trackedEntity);
        program.setStages(programStages);
        program.setAttributes(programTrackedEntityAttributes);

        return program;
    }

    private Stage buildStage(String id) throws PathNotFoundException {

        Request stageRequest = new Request(HttpConstants.STAGES_PATH + "/" + id);
        Object stageInfo = httpQuery.send(stageRequest);

        String name = JsonPath.read(stageInfo,"$.name");

        List<Object>  dataElements = JsonPath.read(stageInfo,"$.programStageDataElements");
        List<DataElement> programStageDataElements = new ArrayList<>();

        for (Object o : dataElements) {
            String elementName = JsonPath.read(o, "$.dataElement.name");
            String elementId = JsonPath.read(o,"$.dataElement.id");
            programStageDataElements.add(new DataElement(name,id));

        }


        Stage stage = new Stage();
        stage.setName(name);
        stage.setUuid(id);
        stage.setDataElements(programStageDataElements);

        return stage;

    }

    private boolean testConnection() {
        /*TODO: */
        return true;
    }

    private void addOrgUnits() {

        Request orgUnitRequest = new Request(HttpConstants.ORG_UNITS_PATH + "?" +
                HttpConstants.NO_PAGING_NO_LINKS);

        List<Object> orgUnits = JsonPath.read(httpQuery.send(orgUnitRequest), "$.organisationUnits");

        for (Object o : orgUnits) {
            String name = JsonPath.read(o, "$.dataElement.name");
            String id = JsonPath.read(o,"$.dataElement.id");

            orgUnitDataService.create(new OrgUnit(name,id));


        }






    }


}
