package ca.ualberta.cs.iali1.iali1_fueltrack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
//Alot of the code was copied from lonely twitter activity
public class MyActivity extends Activity {
    private static final String FILENAME = "file.sav";
    private ListView oldEntryLogs;

    private ArrayList<Logs> oldLogs = new ArrayList<Logs>();
    private ArrayAdapter<Logs> adapter;

    private TextView totalCostPlace;
    private int requestCode = 1;
    private int clickedPos;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        oldEntryLogs = (ListView) findViewById(R.id.oldLogList);
        totalCostPlace = (TextView) findViewById(R.id.overallFuelCost);

        //took from http://stackoverflow.com/questions/3889994/android-list-view-on-click
        //When items in the listview are clicked
        oldEntryLogs.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView parent, View view, int position, long id){
                requestCode = 2;
                clickedPos = position;
                //serialzation
                // http://stackoverflow.com/questions/10100705/android-getserializable
                //this sends data to the editing class so that I can autofill the edittexts
                Intent data = new Intent(MyActivity.this, Editing.class);
                data.putExtra("toEdit",(oldLogs.get(position)));
                //starts the activity
                startActivityForResult(data,requestCode);
            }
        });
}

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        loadFromFile();
        float totalCost = 0;
        //Recalculates the total cost
        for (int i = 0; i < oldLogs.size(); i++){
            float unitCost;
            float litres;
            unitCost = ((oldLogs.get(i)).getUnitCost())/100;
            litres = (oldLogs.get(i)).getAmount();
            totalCost += (unitCost * litres);
        }
        String output = "Total Cost = " + totalCost;
        output = String.format("Total Cost = %.2f", totalCost);
        totalCostPlace.setText(output);

        adapter = new ArrayAdapter<Logs>(this,
                R.layout.list_item, oldLogs);
        oldEntryLogs.setAdapter(adapter);
    }
    //Reads from the file --taken from lonelyTwitter and changed some values
    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();

            // Took from https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html 01-19 2016
            Type listType = new TypeToken<ArrayList<Logs>>() {}.getType();
            oldLogs = gson.fromJson(in, listType);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            oldLogs = new ArrayList<Logs>();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }
    //Saves into the file -- taken from lonely twitter and changed some values
    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME, 0);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(oldLogs, out);
            out.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }
    //When they click the add button a new Activity pops open to enter the values.
    public void addButton(View view){
        requestCode = 1;
        Intent intent = new Intent(this, Adding.class);
        //took from http://developer.android.com/training/basics/intents/result.html
        startActivityForResult(intent,requestCode);
    }

    //when they click done on either adding or editing it comes here
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        float totalCost = 0;
        //this request code is for adding
        if (requestCode == 1){
            //If it ran successfully
            if (resultCode == RESULT_OK) {
                //serialzation
                // http://stackoverflow.com/questions/10100705/android-getserializable
                //I add the data returned into the old logs
                oldLogs.add((Logs) data.getSerializableExtra("data"));
            }
        //This is the request for editing
        } else if(requestCode == 2){
            if (resultCode == RESULT_OK){
                //changes the data for the log that they wanted to edit.
                oldLogs.set(clickedPos, ((Logs) data.getSerializableExtra("data")));
            }
        }
        //Recalculates the total cost
        for (int i = 0; i < oldLogs.size(); i++) {
            float unitCost;
            float litres;
            unitCost = (oldLogs.get(i)).getUnitCost();
            litres = (oldLogs.get(i)).getAmount();
            totalCost += (unitCost * litres);
        }
        //Prints the total cost into the place
        String output = "Total Cost = " + totalCost;
        output = String.format("Total Cost = %.2f", totalCost);
        totalCostPlace.setText(output);
        adapter.notifyDataSetChanged();
        saveInFile();
    }

    }
