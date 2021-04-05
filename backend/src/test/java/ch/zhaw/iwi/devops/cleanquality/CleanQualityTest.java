package ch.zhaw.iwi.devops.cleanquality;
import org.junit.Test;
import org.junit.Assert;
public class CleanQualityTest {
    
    @Test
    public void cleanquality() {

        CleanQuality cq = new CleanQuality();
        Assert.assertEquals("Clean", cq.getAlertCleanQuality(1));

    }
}
