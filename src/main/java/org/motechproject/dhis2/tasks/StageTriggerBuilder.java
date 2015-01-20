package org.motechproject.dhis2.tasks;

import org.motechproject.dhis2.domain.DataElement;
import org.motechproject.dhis2.domain.Stage;
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
public class StageTriggerBuilder  {

    private static final String UNICODE = "UNICODE";
    private int counter;

    public List<ActionEventRequest> build(List<Stage> stages) {

        List<ActionEventRequest> actionEventRequests = new ArrayList<>();

        for (Stage stage : stages) {

            counter = 0;

            SortedSet<ActionParameterRequest> actionParameters = new TreeSet<>();
            ActionEventRequestBuilder builder = new ActionEventRequestBuilder();


            /*External ID*/
            ActionParameterRequestBuilder actionParameterBuilder = new ActionParameterRequestBuilder()
                    .setDisplayName(DisplayNames.EXTERNAL_ID)
                    .setKey(EventParams.EXTERNAL_ID)
                    .setType(UNICODE)
                    .setRequired(true)
                    .setOrder(Integer.valueOf(counter++));

            actionParameters.add(actionParameterBuilder.createActionParameterRequest());

            /*Program details*/
            actionParameterBuilder = new ActionParameterRequestBuilder()
                    .setKey(EventParams.PROGRAM)
                    .setValue(stage.getProgram())
                    .setHidden(true)
                    .setOrder(Integer.valueOf(counter++))
                    .setDisplayName(stage.getProgram());

            actionParameters.add(actionParameterBuilder.createActionParameterRequest());

            /*Date*/
            actionParameterBuilder = new ActionParameterRequestBuilder()
                    .setDisplayName(DisplayNames.DATE)
                    .setOrder(Integer.valueOf(counter++))
                    .setKey(EventParams.DATE)
                    .setType(UNICODE);

            actionParameters.add(actionParameterBuilder.createActionParameterRequest());


            actionParameters.addAll(buildRequestForStage(stage));

            builder.setActionParameters(actionParameters)
                    .setDisplayName(DisplayNames.STAGE_EVENT + "[" + stage.getName() + "]")
                    .setName(stage.getName())
                    .setSubject(EventSubjects.UPDATE_PROGRAM_STAGE);

            actionEventRequests.add(builder.createActionEventRequest());


        }


        return actionEventRequests;
    }

    private List<ActionParameterRequest> buildRequestForStage(Stage stage) {

        List<ActionParameterRequest> parameterRequests = new ArrayList<>();
        ActionParameterRequestBuilder actionParameterBuilder;

        for (DataElement element : stage.getDataElements()) {
            actionParameterBuilder = new ActionParameterRequestBuilder()
                    .setDisplayName(element.getName())
                    .setKey(element.getUuid())
                    .setType(UNICODE)
                    .setOrder(Integer.valueOf(counter++));

            parameterRequests.add(actionParameterBuilder.createActionParameterRequest());

        }


        return parameterRequests;
    }
}
