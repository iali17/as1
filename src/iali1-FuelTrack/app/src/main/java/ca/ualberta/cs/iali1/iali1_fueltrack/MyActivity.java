package ca.ualberta.cs.iali1.iali1_fueltrack;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

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

public class MyActivity extends Activity {
    private static final String FILENAME = "file.sav";
    private EditText bodyText;
    private ListView oldEntryLogs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button addButton = (Button) findViewById(R.id.add);
        oldEntryLogs = (ListView) findViewById(R.id.oldLogList);

    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        //String[] tweets = loadFromFile();
        //loadFromFile();
    }
    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            //Gson gson = new Gson();

            // Took from https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html 01-19 2016
            //Type listType = new TypeToken<ArrayList<NormalTweet>>() {}.getType();
            //tweets = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            //tweets = new ArrayList<Tweet>();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }

    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    0);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            //Gson gson = new Gson();
            //gson.toJson(tweets, out);
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
        Intent intent = new Intent(this, Adding.class);
        startActivity(intent);
    }

}
