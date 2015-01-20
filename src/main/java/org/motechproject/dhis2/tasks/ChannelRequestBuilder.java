package org.motechproject.dhis2.tasks;

import org.motechproject.dhis2.domain.Program;
import org.motechproject.dhis2.domain.Stage;
import org.motechproject.dhis2.domain.TrackedEntityAttribute;
import org.motechproject.dhis2.service.Dhis2SchemaService;
import org.motechproject.tasks.contract.ActionEventRequest;
import org.motechproject.tasks.contract.ChannelRequest;
import org.motechproject.tasks.contract.TriggerEventRequest;
import org.osgi.framework.BundleContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by scott on 1/15/15.
 */
public class ChannelRequestBuilder  {

    private static final String DISPLAY_NAME = "DHIS2";


    private Dhis2SchemaService dhis2SchemaService;
    private BundleContext bundleContext;

    public ChannelRequestBuilder(Dhis2SchemaService dhis2SchemaService , BundleContext bundleContext) {
        this.dhis2SchemaService = dhis2SchemaService;
        this.bundleContext = bundleContext;
    }

    public ChannelRequest build() {

        ProgramTriggerBuilder programTriggerBuilder = new ProgramTriggerBuilder();
        RegistrationTriggerBuilder registrationTriggerBuilder = new RegistrationTriggerBuilder();
        StageTriggerBuilder stageTriggerBuilder = new StageTriggerBuilder();

        List<ActionEventRequest> actions = new ArrayList<>();

        List<Program> programs = dhis2SchemaService.getPrograms();
        actions.addAll(programTriggerBuilder.build(programs));

        List<Stage> stages = dhis2SchemaService.getStages();
        actions.addAll(stageTriggerBuilder.build(stages));

        List<TrackedEntityAttribute> attributes = dhis2SchemaService.getTrackedEntityAttributes();
        actions.addAll(registrationTriggerBuilder.build(attributes));



        return new ChannelRequest(DISPLAY_NAME, bundleContext.getBundle().getSymbolicName(),
                bundleContext.getBundle().getVersion().toString(), null, new ArrayList<TriggerEventRequest>(), actions);

    }
}
