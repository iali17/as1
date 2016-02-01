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
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class MyActivity extends Activity {
    private static final String FILENAME = "file.sav";
    private ListView oldEntryLogs;
    NumberFormat twoDecimal = new DecimalFormat("#0.00");

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
        oldEntryLogs.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView parent, View view, int position, long id){
                requestCode = 2;
                clickedPos = position;
                //serialzation
                // http://stackoverflow.com/questions/10100705/android-getserializable
                Intent data = new Intent(MyActivity.this, Editing.class);
                data.putExtra("toEdit",(oldLogs.get(position)));
                startActivityForResult(data,requestCode);
            }
        });
}

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        //String[] tweets = loadFromFile();
        loadFromFile();
        float totalCost = 0;
        for (int i = 0; i < oldLogs.size(); i++){
            float unitCost;
            float litres;
            unitCost = ((oldLogs.get(i)).getUnitCost())/100;
            litres = (oldLogs.get(i)).getAmount();
            totalCost += (unitCost * litres);
        }
        String output = "Total Cost = " + totalCost;
        output = String.format("%.2f", totalCost);
        totalCostPlace.setText(output);

        adapter = new ArrayAdapter<Logs>(this,
                R.layout.list_item, oldLogs);
        oldEntryLogs.setAdapter(adapter);
    }
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

    public void addButton(View view){
        requestCode = 1;
        Intent intent = new Intent(this, Adding.class);
        //took from http://developer.android.com/training/basics/intents/result.html
        startActivityForResult(intent,requestCode);
    }

    public void editFields(View view){
        requestCode = 2;
        Intent intent = new Intent(this,Adding.class);
        startActivityForResult(intent,requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        float totalCost = 0;
        if (requestCode == 1){
            if (resultCode == RESULT_OK) {
                //serialzation
                // http://stackoverflow.com/questions/10100705/android-getserializable
                oldLogs.add((Logs) data.getSerializableExtra("data"));
            }
        } else if(requestCode == 2){
            if (resultCode == RESULT_OK){
                oldLogs.set(clickedPos, ((Logs) data.getSerializableExtra("data")));
            }
        }

        for (int i = 0; i < oldLogs.size(); i++) {
            float unitCost;
            float litres;
            unitCost = (oldLogs.get(i)).getUnitCost();
            litres = (oldLogs.get(i)).getAmount();
            totalCost += (unitCost * litres);
        }
        String output = "Total Cost = " + totalCost;
        output = String.format("%.2f", totalCost);
        totalCostPlace.setText(output);
        adapter.notifyDataSetChanged();
        saveInFile();
    }

    }
