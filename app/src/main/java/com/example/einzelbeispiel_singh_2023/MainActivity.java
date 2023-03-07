package com.example.einzelbeispiel_singh_2023;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    private TextView antwortFromServer;
    private TextView berechnungresult;
    private EditText eingabemartikelNr;
    private Button sendButton;
    private Button berechnen;
    boolean prim=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        berechnungresult = (TextView) findViewById(R.id.textView2); // Testfeld fuer Berechnungsausgabe

        antwortFromServer = (TextView) findViewById(R.id.textView3); // Testfeld fuer ServerAntwort

        eingabemartikelNr = (EditText) findViewById(R.id.editTextNumber); // Eingabe der MartikelNr

        sendButton = (Button) findViewById(R.id.button2); // Button fuer Server
        berechnen = (Button) findViewById(R.id.button); // Button fuer Berechnung

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                run();
            }
        });

        berechnen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){

                char [] einzelneZiffer = eingabemartikelNr.getText().toString().toCharArray(); //die einzelnen eingegeben Ziffern in Chararray speichern

                String isPrimeZahlErgebnis = "";


                for (int i = 0; i < einzelneZiffer.length; i++) {
                    prim = true;
                    isPrime(Integer.parseInt(String.valueOf(einzelneZiffer[i]))); //Methode isPrime aufrufen ueber charArray darueber laufen lassen
                    if( prim == true){ //wenn prime ture soll an String angehaengt werden von der jeweiligen Stell eim Array
                        isPrimeZahlErgebnis += einzelneZiffer[i];
                    }
                }
                berechnungresult.setText(isPrimeZahlErgebnis); //Ergebnis soll auf Console ausgegeben werden
            }

            public void isPrime(int zahl){ // zahl = Integer.parseInt(String.valueOf(einzelneZiffer[i])
                if(zahl == 1 || zahl == 0){ //Bedingung der Primzahlen
                    prim = false;
                }
                else if (zahl % 2 == 0 && zahl != 2 || zahl % 3 == 0 && zahl != 3){
                    prim = false;
                } /* else {
                    prim = true;
                }*/
            }
        });
    }


    public void run() {
        ExampleThread thread = new ExampleThread();
        thread.start();
    }
    class ExampleThread extends Thread{
        @Override
        public void run() {
            try {
                String inputUser = eingabemartikelNr.getText().toString(); // input vom Eingabefeld ablesen

                Socket clientSocket = new Socket("se2-isys.aau.at", 53212); // client socket mit server verbinden

                DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream()); // output stream zum Socket anhaengen

                BufferedReader inputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); //  input stream zum Socket anhaengen

                outputStream.writeBytes(inputUser + "\n"); // Input zum Server schicken

                String result = inputStream.readLine(); // vom Server Input ablesen

                antwortFromServer.setText(result); //  result in textview schreiben

                clientSocket.close(); //  client socket schließen

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}