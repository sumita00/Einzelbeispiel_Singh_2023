package com.example.einzelbeispiel_singh_2023;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView antwortFromServer;
    private TextView berechnungresult;
    private TextView hinweisText;
    private EditText eingabemartikelNr;
    private Button sendButton;
    private Button berechnen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        berechnungresult = (TextView) findViewById(R.id.textView2);

        antwortFromServer = (TextView) findViewById(R.id.textView3);

        eingabemartikelNr = (EditText) findViewById(R.id.editTextNumber);

        sendButton = (Button) findViewById(R.id.button);
        berechnen = (Button) findViewById(R.id.button2);

    }


}