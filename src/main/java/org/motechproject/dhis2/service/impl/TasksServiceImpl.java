package org.motechproject.dhis2.service.impl;

import org.motechproject.dhis2.service.ProgramService;
import org.motechproject.dhis2.service.StageService;
import org.motechproject.dhis2.service.TasksService;
import org.motechproject.dhis2.service.TrackedEntityAttributeService;
import org.motechproject.dhis2.service.TrackedEntityService;
import org.motechproject.dhis2.tasks.ChannelRequestBuilder;
import org.motechproject.tasks.service.ChannelService;
import org.osgi.framework.BundleContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TasksServiceImpl implements TasksService {

    private BundleContext bundleContext;
    private ProgramService programService;
    private StageService stageService;
    private TrackedEntityService trackedEntityService;
    private TrackedEntityAttributeService trackedEntityAttributeService;
    private ChannelService channelService;

    @Autowired
    public TasksServiceImpl(BundleContext bundleContext,
                            ProgramService programService,
                            StageService stageService,
                            TrackedEntityService trackedEntityService,
                            TrackedEntityAttributeService trackedEntityAttributeService,
                            ChannelService channelService) {
        this.bundleContext = bundleContext;
        this.programService = programService;
        this.stageService = stageService;
        this.trackedEntityService = trackedEntityService;
        this.trackedEntityAttributeService = trackedEntityAttributeService;
        this.channelService = channelService;
    }

    @Override
    public void updateChannel() {
        ChannelRequestBuilder channelRequestBuilder = new ChannelRequestBuilder(bundleContext, programService, stageService, trackedEntityAttributeService, trackedEntityService);
        channelService.registerChannel(channelRequestBuilder.build());
    }
}
