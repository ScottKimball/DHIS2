package org.motechproject.dhis2.service.it;

import com.google.inject.Inject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.motechproject.dhis2.service.SendIndividualRecordService;
import org.motechproject.event.MotechEvent;
import org.motechproject.testing.osgi.BasePaxIT;
import org.motechproject.testing.osgi.container.MotechNativeTestContainerFactory;
import org.ops4j.pax.exam.ExamFactory;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerClass;

import java.util.UUID;

/**
 * Created by scott on 8/27/14.
 */

@RunWith(PaxExam.class)
@ExamReactorStrategy(PerClass.class)
@ExamFactory(MotechNativeTestContainerFactory.class)
public class SendIndividualRecordServiceIT extends BasePaxIT {

    @Inject
    SendIndividualRecordService sendIndividualRecordService;

    private MotechEvent event;


    @Test
    public void testSend () throws Exception {



    }
}
