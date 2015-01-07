package org.motechproject.dhis2.service.impl;

import org.motechproject.dhis2.http.HttpConstants;
import org.motechproject.dhis2.http.HttpQuery;
import org.motechproject.dhis2.http.Request;
import org.motechproject.dhis2.repository.TrackedEntityAttributeDataService;
import org.motechproject.dhis2.service.SyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by scott on 12/15/14.
 */

@Service
public class SyncServiceImpl implements SyncService {


    private HttpQuery httpQuery;
    private TrackedEntityAttributeDataService attributeDataService;

    @Autowired
    public SyncServiceImpl(HttpQuery httpQuery, TrackedEntityAttributeDataService attributeDataService) {
        this.httpQuery = httpQuery;
        this.attributeDataService = attributeDataService;
    }

    @Override
    public boolean sync() {

        /*Get attributes*/
        Request attributeRequest = new Request(HttpConstants.BASE_URL + HttpConstants.TRACKED_ENTITY_ATTRIBUTES_PATH);
        Object attributes = httpQuery.send(attributeRequest);



        return false;
    }
}
