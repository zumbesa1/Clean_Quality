package ch.zhaw.iwi.devops.cleanquality;

public class CleanQuality {


    public String getAlertCleanQuality(int i) {

        if (i == 1) {
            return "Clean";
        } else if (i == 2) {
            return "Semi Clean";
        } else if (i == 3) {
            return "Not Clean";
        }
        return "The input has to be the number 1, 2 or 3.";
    }
}
