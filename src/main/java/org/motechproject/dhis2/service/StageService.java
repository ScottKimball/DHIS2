package org.motechproject.dhis2.service;

import org.motechproject.dhis2.dto.impl.StageDto;

/**
 * Created by scott on 10/8/14.
 */
public interface StageService {

    void send(StageDto stageDto);
}
