package ca.ualberta.cs.iali1.iali1_fueltrack;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by iali1 on 2/1/16.
 */
public class LogsTest extends ActivityInstrumentationTestCase2{
    public LogsTest() {super(MyActivity.class);}

    public void testGetUnitCost() {
        String date = "2016-01-18";
        String station = "Esso";
        float odemeter = 2005;
        String grade = "regular";
        float unitCost = 99;
        float amount = 40;
        float newUnitCost;

        Logs latestLog = new Logs(date,station,odemeter,grade,unitCost,amount);

        newUnitCost = latestLog.getUnitCost();

        assertEquals(newUnitCost, unitCost);
    }

    public void testGetAmount() {
        String date = "2016-01-18";
        String station = "Esso";
        float odemeter = 2005;
        String grade = "regular";
        float unitCost = 99;
        float amount = 40;
        float newAmount;

        Logs latestLog = new Logs(date,station,odemeter,grade,unitCost,amount);

        newAmount = latestLog.getAmount();

        assertEquals(newAmount,amount);
    }
}
