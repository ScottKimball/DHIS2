package org.motechproject.dhis2.tasks;

import org.motechproject.dhis2.domain.Program;
import org.motechproject.dhis2.domain.Stage;
import org.motechproject.dhis2.domain.TrackedEntity;
import org.motechproject.dhis2.domain.TrackedEntityAttribute;
import org.motechproject.dhis2.service.ProgramService;
import org.motechproject.dhis2.service.StageService;
import org.motechproject.dhis2.service.TrackedEntityAttributeService;
import org.motechproject.dhis2.service.TrackedEntityService;
import org.motechproject.tasks.contract.ActionEventRequest;
import org.motechproject.tasks.contract.ChannelRequest;
import org.motechproject.tasks.contract.TriggerEventRequest;
import org.osgi.framework.BundleContext;

import java.util.ArrayList;
import java.util.List;

public class ChannelRequestBuilder  {

    private BundleContext bundleContext;
    private ProgramService programService;
    private StageService stageService;
    private TrackedEntityAttributeService trackedEntityAttributeService;
    private TrackedEntityService trackedEntityService;

    public ChannelRequestBuilder(BundleContext bundleContext,
                                 ProgramService programService,
                                 StageService stageService,
                                 TrackedEntityAttributeService trackedEntityAttributeService,
                                 TrackedEntityService trackedEntityService) {
        this.bundleContext = bundleContext;
        this.programService = programService;
        this.stageService = stageService;
        this.trackedEntityAttributeService = trackedEntityAttributeService;
        this.trackedEntityService = trackedEntityService;
    }

    public ChannelRequest build() {

        ProgramTriggerBuilder programTriggerBuilder = new ProgramTriggerBuilder();
        RegistrationTriggerBuilder registrationTriggerBuilder = new RegistrationTriggerBuilder();
        StageTriggerBuilder stageTriggerBuilder = new StageTriggerBuilder();

        List<ActionEventRequest> actions = new ArrayList<>();

        List<Program> programs = programService.findByRegistration(true);
        actions.addAll(programTriggerBuilder.build(programs));

        List<Stage> stages = stageService.findAll();
        actions.addAll(stageTriggerBuilder.build(stages));

        List<TrackedEntityAttribute> attributes = trackedEntityAttributeService.findAll();
        List<TrackedEntity> trackedEntities = trackedEntityService.findAll();
        actions.addAll(registrationTriggerBuilder.build(attributes, trackedEntities));

        return new ChannelRequest(DisplayNames.DHIS2_DISPLAY_NAME, bundleContext.getBundle().getSymbolicName(),
                bundleContext.getBundle().getVersion().toString(), null, new ArrayList<TriggerEventRequest>(), actions);

    }
}
