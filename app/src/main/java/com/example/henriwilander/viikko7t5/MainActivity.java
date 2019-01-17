package com.example.henriwilander.viikko7t5;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends Activity {

    TextView text;
    EditText userInput;
    EditText saveName;
    EditText printName;

    Context context = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;

        text = (TextView) findViewById(R.id.textView2);
        userInput = (EditText) findViewById(R.id.save);
        saveName = (EditText) findViewById(R.id.editText7);
        printName = (EditText) findViewById(R.id.editText4);
}

    public void readFile(View v) {
        try {
            String fileName = printName.getText().toString();
            InputStream ins = context.openFileInput(fileName);

            BufferedReader br = new BufferedReader(new InputStreamReader(ins));
            String s = "";
            String printText = "";

            while ((s = br.readLine()) != null) {
                printText = printText + s + "\n";
            }
            ins.close();
            text.setText(printText);
        } catch (FileNotFoundException e) {
            Log.e("FileNotFoundException", "Tiedostoa ei löydy.");
        } catch (IOException e) {
            Log.e("IOException", "Virhe syötteessä.");
        }
    }

    public void writeFile(View v) {
        try {
            String fileName = saveName.getText().toString();
            OutputStreamWriter ows = new OutputStreamWriter(context.openFileOutput(fileName, Context.MODE_PRIVATE));
            String s = userInput.getText().toString();
            ows.write(s);
            ows.close();
        } catch (FileNotFoundException e) {
            Log.e("FileNotFoundException", "Tiedostoa ei löydy.");
        } catch (IOException e) {
            Log.e("IOException", "Virhe syötteessä.");
        }
    }



}

