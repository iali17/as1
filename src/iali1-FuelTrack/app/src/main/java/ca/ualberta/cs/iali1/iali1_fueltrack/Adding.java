package ca.ualberta.cs.iali1.iali1_fueltrack;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;

public class Adding extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding);

        Intent intent = getIntent();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_adding, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    //The cancel button just finishes the acitivity
    public void cancelButton(View view){
        finish();
    }

    public void doneButton(View view){
        //starts an intent so we can return data
        Intent intent = new Intent();
        //gets the data from the edit texts
        String date = ((EditText) findViewById(R.id.date)).getText().toString();
        String station = ((EditText)findViewById(R.id.station)).getText().toString();
        //to get numbers from edit text
        // http://stackoverflow.com/questions/4903515/how-do-i-return-an-int-from-edittext-android
        float odemeter = Float.parseFloat(((EditText) findViewById(R.id.odemeter)).getText().toString());
        //Formats the string into only 1 sig dig
        odemeter = Float.parseFloat((String.format("%.1f",odemeter)));
        String grade = ((EditText)findViewById(R.id.grade)).getText().toString();
        float unitCost = Float.parseFloat(((EditText) findViewById(R.id.unitCost)).getText().toString());
        //Formats the string into only 1 sig dig
        unitCost = Float.parseFloat((String.format("%.1f",unitCost)));
        float amount = Float.parseFloat(((EditText) findViewById(R.id.amount)).getText().toString());
        //Formats the string into only 3 sig dig
        amount = Float.parseFloat((String.format("%.3f",amount)));

        //makes a log with all the info
        Logs latestLog = new Logs(date,station,odemeter,grade,unitCost,amount);
        //puts the data into an extra to return it
        intent.putExtra("data", latestLog);
        //sets the result to OK
        setResult(Activity.RESULT_OK, intent);
        //finishes the activity
        finish();
    }
}
