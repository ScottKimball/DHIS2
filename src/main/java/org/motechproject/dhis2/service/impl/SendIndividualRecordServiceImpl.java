package org.motechproject.dhis2.service.impl;

import org.motechproject.dhis2.domain.IndividualRecord;
import org.motechproject.dhis2.repository.IndividualRecordsDataService;
import org.motechproject.dhis2.service.SendIndividualRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by scott on 8/26/14.
 */

@Service("individualRecordService")
public class SendIndividualRecordServiceImpl implements SendIndividualRecordService {

  private Logger logger = LoggerFactory.getLogger(SendIndividualRecordServiceImpl.class);
  private IndividualRecordsDataService individualRecordsDataService;

    @Autowired
    public SendIndividualRecordServiceImpl (IndividualRecordsDataService individualRecordsDataService) {
        this.individualRecordsDataService = individualRecordsDataService;
    }

    @Override
    public void send(IndividualRecord individualRecord) {
        logger.debug("In SendIndividualRecordServiceImpl.send");

        // TODO:

    }
}
