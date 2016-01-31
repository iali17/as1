package ca.ualberta.cs.iali1.iali1_fueltrack;

import java.io.Serializable;

/**
 * Created by ALI on 2016-01-30.
 */
public class Logs implements Serializable{
    protected String date;
    protected String station;
    protected int odemeter;
    protected String grade;
    protected float unitCost;
    protected float amount;

    public Logs(String date, String station, int odemeter, String grade, float unitCost, float amount){
        this.date = date;
        this.station = station;
        this.odemeter = odemeter;
        this.grade = grade;
        this.unitCost = unitCost;
        this.amount = amount;
    }

    public float getUnitCost(){
        return unitCost;
    }

    public float getAmount(){
        return amount;
    }

    @Override
    public String toString(){
        return "Date: " +date + "\nStation: " + station + "\nOdemeter Value: " + odemeter + "\nFuel Grade: " + grade + "\nUnitCost: " + unitCost + "\nLiters Filled: " + amount;
    }
}
