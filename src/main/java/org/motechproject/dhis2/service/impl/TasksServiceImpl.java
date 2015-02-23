package org.motechproject.dhis2.service.impl;

import org.motechproject.dhis2.service.ProgramService;
import org.motechproject.dhis2.service.StageService;
import org.motechproject.dhis2.service.TasksService;
import org.motechproject.dhis2.service.TrackedEntityAttributeService;
import org.motechproject.dhis2.service.TrackedEntityService;
import org.motechproject.dhis2.tasks.ChannelRequestBuilder;
import org.motechproject.tasks.service.ChannelService;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
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
    private ChannelRequestBuilder channelRequestBuilder;

    @Autowired
    public TasksServiceImpl(BundleContext bundleContext,
                            ProgramService programService,
                            StageService stageService,
                            TrackedEntityService trackedEntityService,
                            TrackedEntityAttributeService trackedEntityAttributeService) {
        this.bundleContext = bundleContext;
        this.programService = programService;
        this.stageService = stageService;
        this.trackedEntityService = trackedEntityService;
        this.trackedEntityAttributeService = trackedEntityAttributeService;
    }

    @Override
    public void updateChannel() {
        ServiceReference serviceReference = bundleContext.getServiceReference("org.motechproject.tasks.service.ChannelService");

        if (serviceReference != null) {

            Object service = bundleContext.getService(serviceReference);
            if (service != null) {

                channelService = (ChannelService) service;

                channelRequestBuilder = new ChannelRequestBuilder(bundleContext, programService, stageService, trackedEntityAttributeService, trackedEntityService);
                channelService.registerChannel(channelRequestBuilder.build());
            }


        }


    }




}
