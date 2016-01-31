package ca.ualberta.cs.iali1.iali1_fueltrack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class Editing extends AppCompatActivity {
    Logs oldLogs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editing);

        Intent intent = getIntent();
        oldLogs = (Logs) intent.getSerializableExtra("toEdit");
        ((EditText) findViewById(R.id.date)).setText(oldLogs.date);
        ((EditText)findViewById(R.id.station)).setText(oldLogs.station);
        ((EditText)findViewById(R.id.odemeter)).setText(""+oldLogs.odemeter);
        ((EditText)findViewById(R.id.grade)).setText((oldLogs.grade));
        ((EditText)findViewById(R.id.unitCost)).setText((""+ oldLogs.unitCost));
        ((EditText)findViewById(R.id.amount)).setText((""+oldLogs.amount));
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
        float unitCost =  Float.parseFloat(((EditText) findViewById(R.id.unitCost)).getText().toString());
        float amount = Float.parseFloat(((EditText)findViewById(R.id.amount)).getText().toString());

        Logs latestLog = new Logs(date,station,odemeter,grade,unitCost,amount);
        intent.putExtra("data", latestLog);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

}
