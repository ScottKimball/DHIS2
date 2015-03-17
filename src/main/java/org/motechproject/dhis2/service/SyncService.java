package org.motechproject.dhis2.service;

/**
 * Used to synchronize this module with the DHIS2 server schema
 */
public interface SyncService {

    /**
     * Queries the DHIS2 server to get the current schema for that instance and then persists that
     * information in MDS
     * @return true if successful; false otherwise
     */
    boolean sync();
}
