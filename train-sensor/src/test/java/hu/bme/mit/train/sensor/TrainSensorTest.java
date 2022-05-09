package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainUser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class TrainSensorTest {
    TrainController testTC;
    TrainUser testTU;
    TrainSensorImpl testTS;

    @Before
    public void before() {
        testTC = mock(TrainController.class);
        testTU = mock(TrainUser.class);
        testTS = new TrainSensorImpl(testTC, testTU);
    }

    @Test
    public void getSpeedLimitTest() {
        Assert.assertEquals(5, testTS.getSpeedLimit());
    }

    @Test
    public void AbsMinMarginTest() {
        testTS.overrideSpeedLimit(-1);
        verify(testTU, times(1)).setAlarm(true);
    }

    @Test
    public void AbsMaxMarginTest() {
        testTS.overrideSpeedLimit(501);
        verify(testTU, times(1)).setAlarm(true);
    }

    @Test
    public void RelMarginTest() {
        testTS.overrideSpeedLimit(4);
        verify(testTU, times(1)).setAlarm(true);
    }

    @Test
    public void BetweenMarginTest() {
        testTS.overrideSpeedLimit(300);
        verify(testTU, times(0)).setAlarm(false);
    }
}
