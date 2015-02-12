package org.motechproject.dhis2.service.impl;

import org.motechproject.dhis2.service.Dhis2SchemaService;
import org.motechproject.dhis2.service.TasksService;
import org.motechproject.dhis2.tasks.ChannelRequestBuilder;
import org.motechproject.tasks.service.ChannelService;
import org.osgi.framework.BundleContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * Created by scott on 1/15/15.
 */

@Service
public class TasksServiceImpl implements TasksService {

    private BundleContext bundleContext;
    private Dhis2SchemaService schemaService;
    private ChannelService channelService;
    private ChannelRequestBuilder channelRequestBuilder;

    @Autowired
    public TasksServiceImpl(BundleContext bundleContext, Dhis2SchemaService schemaService, ChannelService channelService) {
        this.bundleContext = bundleContext;
        this.schemaService = schemaService;
        this.channelService = channelService;
    }

    @Override
    public void updateChannel() {
        channelRequestBuilder = new ChannelRequestBuilder(schemaService, bundleContext);
        channelService.registerChannel(channelRequestBuilder.build());
    }



}
