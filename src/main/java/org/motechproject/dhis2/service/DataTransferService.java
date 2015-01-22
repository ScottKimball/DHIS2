package org.motechproject.dhis2.service;

import org.motechproject.dhis2.dto.Dto;

/**
 * Created by scott on 1/22/15.
 */
public interface DataTransferService {

    void send(Dto dto, String path);
}
