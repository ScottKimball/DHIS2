package org.motechproject.dhis2.tasks;

import org.motechproject.dhis2.service.Dhis2SchemaService;
import org.motechproject.tasks.contract.ChannelRequest;
import org.osgi.framework.BundleContext;

/**
 * Created by scott on 1/15/15.
 */
public class ChannelRequestBuilder {

    private Dhis2SchemaService dhis2SchemaService;

    public ChannelRequestBuilder(Dhis2SchemaService dhis2SchemaService) {
        this.dhis2SchemaService = dhis2SchemaService;
    }

    public ChannelRequest build () {
        return null;
    }
}
