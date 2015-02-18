package org.motechproject.dhis2.it;

import com.google.inject.Inject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.motechproject.testing.osgi.container.MotechNativeTestContainerFactory;
import org.ops4j.pax.exam.ExamFactory;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerSuite;

/**
 * Created by scott on 2/16/15.
 */

@RunWith(PaxExam.class)
@ExamReactorStrategy(PerSuite.class)
@ExamFactory(MotechNativeTestContainerFactory.class)
public class SyncBundleIT {

    @Test
    public void emptyTest () throws Exception {

    }


}
