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
import java.util.ArrayList;
import java.util.Date;

public class Adding extends AppCompatActivity  {
    public final static ArrayList<Logs> EXTRA = new ArrayList<Logs>();


    private ArrayList<Logs> logs = new ArrayList<Logs>();

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

    public void cancelButton(View view){
        finish();
    }

    public void doneButton(View view){
        Intent intent = new Intent();

        String date = ((EditText) findViewById(R.id.date)).getText().toString();
        String station = ((EditText)findViewById(R.id.station)).getText().toString();
        //to get numbers from edit text
        // http://stackoverflow.com/questions/4903515/how-do-i-return-an-int-from-edittext-android
        int odemeter = Integer.parseInt(((EditText) findViewById(R.id.odemeter)).getText().toString());
        String grade = ((EditText)findViewById(R.id.grade)).getText().toString();
        float unitCost = Integer.parseInt(((EditText) findViewById(R.id.unitCost)).getText().toString());
        float amount = Integer.parseInt(((EditText)findViewById(R.id.amount)).getText().toString());
        Logs latestLog = new Logs(date,station,odemeter,grade,unitCost,amount);
        //logs.add(latestLog);
        intent.putExtra("data", latestLog);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}
