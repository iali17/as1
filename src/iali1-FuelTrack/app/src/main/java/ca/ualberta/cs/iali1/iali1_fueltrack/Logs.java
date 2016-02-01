package ca.ualberta.cs.iali1.iali1_fueltrack;

import java.io.Serializable;

/**
 * Created by ALI on 2016-01-30.
 */
public class Logs implements Serializable{
    protected String date;
    protected String station;
    protected float odemeter;
    protected String grade;
    protected float unitCost;
    protected float amount;
    //makes a new log with all the specified data
    public Logs(String date, String station, float odemeter, String grade, float unitCost, float amount){
        this.date = date;
        this.station = station;
        this.odemeter = odemeter;
        this.grade = grade;
        this.unitCost = unitCost;
        this.amount = amount;
    }
    //returns the unit cost
    public float getUnitCost(){
        return unitCost;
    }
    //returns the amount of fuel
    public float getAmount(){
        return amount;
    }

    //when printing this gives the format.
    @Override
    public String toString(){
        return "Date: " +date + "\nStation: " + station + "\nOdemeter Value: " + odemeter + "\nFuel Grade: " + grade + "\nUnitCost: " + unitCost + "\nLiters Filled: " + amount;
    }
}
