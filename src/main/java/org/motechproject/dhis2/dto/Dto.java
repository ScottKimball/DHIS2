package org.motechproject.dhis2.dto;

import java.io.IOException;

/**
 * Created by scott on 12/2/14.
 */
public interface Dto {

    String toJson()  throws IOException;
}
