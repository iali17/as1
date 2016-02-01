package ca.ualberta.cs.iali1.iali1_fueltrack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class Editing extends AppCompatActivity {
    private Logs oldLogs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editing);

        //Gets the intent which is given to us from the MyActivity
        Intent intent = getIntent();
        oldLogs = (Logs) intent.getSerializableExtra("toEdit");
        //Fills the edit text with the data that was already given to us
        ((EditText) findViewById(R.id.date)).setText(oldLogs.date);
        ((EditText)findViewById(R.id.station)).setText(oldLogs.station);
        ((EditText)findViewById(R.id.odemeter)).setText(""+oldLogs.odemeter);
        ((EditText)findViewById(R.id.grade)).setText((oldLogs.grade));
        ((EditText)findViewById(R.id.unitCost)).setText((""+ oldLogs.unitCost));
        ((EditText)findViewById(R.id.amount)).setText((""+oldLogs.amount));
    }
    //finishes without editing anything when they click cancel
    public void cancelButton(View view){
        finish();
    }

    public void doneButton(View view){
        //makes a new intent so we can return data
        Intent intent = new Intent();
        //gets data from the edit texts
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

        //makes a new log object with the new info
        Logs latestLog = new Logs(date,station,odemeter,grade,unitCost,amount);
        //puts it into the itent so we can recieve it when it finishes
        intent.putExtra("data", latestLog);
        //sets the result to OK
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

}
