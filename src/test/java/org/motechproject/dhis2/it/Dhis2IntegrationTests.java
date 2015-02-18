package org.motechproject.dhis2.it;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by scott on 2/12/15.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({EventHandlerBundleIT.class,SyncBundleIT.class})
public class Dhis2IntegrationTests {
}
