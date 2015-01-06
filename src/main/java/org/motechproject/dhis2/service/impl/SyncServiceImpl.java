package org.motechproject.dhis2.service.impl;

import org.motechproject.dhis2.http.HttpQuery;
import org.motechproject.dhis2.service.SyncService;
import org.springframework.stereotype.Service;

/**
 * Created by scott on 12/15/14.
 */

@Service
public class SyncServiceImpl implements SyncService {


    private HttpQuery httpQuery;


    @Override
    public boolean sync() {
        return false;
    }
}
