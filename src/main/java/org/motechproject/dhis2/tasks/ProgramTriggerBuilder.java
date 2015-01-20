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



    public List<ActionEventRequest> build(List<Program> programs) {

        List<ActionEventRequest> actionEventRequests = new ArrayList<>();

        for (Program program : programs) {

            SortedSet<ActionParameterRequest> actionParameters = new TreeSet<>();

            ActionEventRequestBuilder builder = new ActionEventRequestBuilder();
            builder.setDisplayName(DisplayNames.PROGRAM_ENROLLMENT + "[" + program.getName() + "]");
            builder.setSubject(EventSubjects.ENROLL_IN_PROGRAM);
            builder.setName(program.getName());

            /*Add External ID field*/
            ActionParameterRequestBuilder actionParameterBuilder = new ActionParameterRequestBuilder();
            actionParameterBuilder.setDisplayName(DisplayNames.EXTERNAL_ID);
            actionParameterBuilder.setKey(EventParams.EXTERNAL_ID);
            actionParameterBuilder.setType(UNICODE);
            actionParameterBuilder.setRequired(true);
            actionParameterBuilder.setOrder(Integer.valueOf(1));


            actionParameters.add(actionParameterBuilder.createActionParameterRequest());


            /*Add Tracked Entity Type field with value*/
            actionParameterBuilder = new ActionParameterRequestBuilder();
            actionParameterBuilder.setDisplayName(DisplayNames.TRACKED_ENTITY_TYPE);
            actionParameterBuilder.setKey(EventParams.ENTITY_TYPE);
            actionParameterBuilder.setValue(program.getTrackedEntity().getName());
            actionParameterBuilder.setType(UNICODE);
            actionParameterBuilder.setOrder(Integer.valueOf(2));
            actionParameterBuilder.setHidden(true);

            actionParameters.add(actionParameterBuilder.createActionParameterRequest());

            /*Adds all tracked entity attributes for program*/
            actionParameters.addAll(buildRequestForProgram(program));

            builder.setActionParameters(actionParameters);


            actionEventRequests.add(builder.createActionEventRequest());

        }




        return actionEventRequests;

    }

    private List<ActionParameterRequest> buildRequestForProgram(Program program) {

        List<ActionParameterRequest> parameterRequests = new ArrayList<>();
        ActionParameterRequestBuilder actionParameterBuilder;

        for (TrackedEntityAttribute attribute : program.getAttributes()) {

            actionParameterBuilder = new ActionParameterRequestBuilder();
            actionParameterBuilder.setDisplayName(attribute.getName());

                /*TODO Might want to change to use UUID as key rather than name*/
            actionParameterBuilder.setKey(attribute.getName());
            actionParameterBuilder.setType(UNICODE);
            actionParameterBuilder.setOrder(Integer.valueOf(2));
            parameterRequests.add(actionParameterBuilder.createActionParameterRequest());

        }

        return parameterRequests;

    }



}
