package org.motechproject.dhis2.tasks;

import org.motechproject.dhis2.domain.Program;
import org.motechproject.dhis2.domain.TrackedEntityAttribute;
import org.motechproject.dhis2.event.EventParams;
import org.motechproject.dhis2.event.EventSubjects;
import org.motechproject.tasks.contract.ActionEventRequest;
import org.motechproject.tasks.contract.ActionEventRequestBuilder;
import org.motechproject.tasks.contract.ActionParameterRequest;
import org.motechproject.tasks.contract.ActionParameterRequestBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;


/**
 * Created by scott on 1/15/15.
 */
public class ProgramTriggerBuilder {

    private static final String UNICODE = "UNICODE";
    private int counter;



    public List<ActionEventRequest> build(List<Program> programs) {

        List<ActionEventRequest> actionEventRequests = new ArrayList<>();

        for (Program program : programs) {
            counter = 0;

            SortedSet<ActionParameterRequest> actionParameters = new TreeSet<>();

            ActionEventRequestBuilder builder = new ActionEventRequestBuilder();


            /*Add External ID field*/
            ActionParameterRequestBuilder actionParameterBuilder = new ActionParameterRequestBuilder()
                    .setDisplayName(DisplayNames.EXTERNAL_ID)
                    .setKey(EventParams.EXTERNAL_ID)
                    .setType(UNICODE)
                    .setRequired(true)
                    .setOrder(counter++);


            actionParameters.add(actionParameterBuilder.createActionParameterRequest());

            actionParameterBuilder = new ActionParameterRequestBuilder()
                    .setDisplayName(DisplayNames.DATE)
                    .setKey(EventParams.DATE)
                    .setOrder(counter++)
                    .setType(UNICODE);



            /*Add Tracked Entity Type field with value*/
            actionParameterBuilder = new ActionParameterRequestBuilder()
                    .setDisplayName(DisplayNames.TRACKED_ENTITY_TYPE)
                    .setKey(EventParams.ENTITY_TYPE)
                    .setValue(program.getTrackedEntity().getName())
                    .setType(UNICODE)
                    .setOrder(counter++)
                    .setHidden(true);

            actionParameters.add(actionParameterBuilder.createActionParameterRequest());

            /*Adds all tracked entity attributes for program*/
            actionParameters.addAll(buildRequestForProgram(program));

            builder.setActionParameters(actionParameters)
                    .setDisplayName(DisplayNames.PROGRAM_ENROLLMENT + " [" + program.getName() + "]")
                    .setSubject(EventSubjects.ENROLL_IN_PROGRAM)
                    .setName(program.getName());


            actionEventRequests.add(builder.createActionEventRequest());

        }




        return actionEventRequests;

    }

    private List<ActionParameterRequest> buildRequestForProgram(Program program) {

        List<ActionParameterRequest> parameterRequests = new ArrayList<>();
        ActionParameterRequestBuilder actionParameterBuilder;

        for (TrackedEntityAttribute attribute : program.getAttributes()) {

            actionParameterBuilder = new ActionParameterRequestBuilder()
                    .setDisplayName(attribute.getName())
                    .setKey(attribute.getUuid())
                    .setType(UNICODE)
                    .setOrder(counter++);

            parameterRequests.add(actionParameterBuilder.createActionParameterRequest());

        }

        return parameterRequests;

    }



}
