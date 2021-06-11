package ch.zhaw.iwi.devops.cleanquality;
import org.junit.Test;
import org.junit.Assert;
public class CleanQualityTest {
    
    @Test
    public void cleanquality() {

        CleanQuality cq = new CleanQuality();
        Assert.assertEquals("Not Clean", cq.getAlertCleanQuality(3));
        Assert.assertEquals("Semi Clean", cq.getAlertCleanQuality(2));
        Assert.assertEquals("Clean", cq.getAlertCleanQuality(1));
        Assert.assertEquals("The input has to be the number 1, 2 or 3.", cq.getAlertCleanQuality(4));       

    }
}
