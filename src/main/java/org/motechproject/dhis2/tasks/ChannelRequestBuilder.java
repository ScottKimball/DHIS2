package org.motechproject.dhis2.tasks;

import org.motechproject.dhis2.domain.Program;
import org.motechproject.dhis2.domain.Stage;
import org.motechproject.dhis2.domain.TrackedEntity;
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




    private Dhis2SchemaService dhis2SchemaService;
    private BundleContext bundleContext;

    public ChannelRequestBuilder(Dhis2SchemaService dhis2SchemaService , BundleContext bundleContext) {
        this.dhis2SchemaService = dhis2SchemaService;
        this.bundleContext = bundleContext;
    }

    public ChannelRequest build() {

        ProgramActionBuilder programActionBuilder = new ProgramActionBuilder();
        CreateActionBuilder createActionBuilder = new CreateActionBuilder();
        StageActionBuilder stageActionBuilder = new StageActionBuilder();

        List<ActionEventRequest> actions = new ArrayList<>();

        List<Program> programs = dhis2SchemaService.getPrograms();
        actions.addAll(programActionBuilder.build(programs));


        List<Stage> stages = dhis2SchemaService.getStages();
        actions.addAll(stageActionBuilder.build(stages));

        List<TrackedEntityAttribute> attributes = dhis2SchemaService.getTrackedEntityAttributes();
        List<TrackedEntity> trackedEntities = dhis2SchemaService.getTrackedEntities();
        actions.addAll(createActionBuilder.build(attributes, trackedEntities));

        return new ChannelRequest(DisplayNames.DHIS2_DISPLAY_NAME, bundleContext.getBundle().getSymbolicName(),
                bundleContext.getBundle().getVersion().toString(), null, new ArrayList<TriggerEventRequest>(), actions);

    }
}
