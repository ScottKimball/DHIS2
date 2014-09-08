package org.motechproject.dhis2.service;

import org.motechproject.dhis2.domain.DataValue;

import javax.xml.crypto.Data;
import java.io.UnsupportedEncodingException;

/**
 * Created by scott on 8/29/14.
 */
public interface SendAggregateDataService  {

    public void send (DataValue dataValue) throws UnsupportedEncodingException;
}
