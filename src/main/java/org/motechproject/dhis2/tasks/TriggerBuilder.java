package org.motechproject.dhis2.tasks;

import org.motechproject.tasks.contract.TriggerEventRequest;

import java.util.List;

/**
 * Created by scott on 1/15/15.
 */
public interface TriggerBuilder {

    List<TriggerEventRequest> buildTriggers();


}
