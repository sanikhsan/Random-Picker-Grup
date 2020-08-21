package com.san.randompicker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class GrupCepat extends AppCompatActivity {
    //Deklarasi Edit Text dan Button
    EditText inputpesertacepat;
    Button buttoninputpesertacepat;
    String[] teams = new String[100000];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grup_cepat);

        inputpesertacepat = findViewById(R.id.inputpesertacepat);
        buttoninputpesertacepat = findViewById(R.id.buttoninputpesertacepat);

        buttoninputpesertacepat.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Mengambil value yang ada di EditText inputjarak dan dimasukkan ke variabel stringinputjarak
                String stringinputjarak = inputpesertacepat.getText().toString();
                //Convert tipe data String menjadi Integer
                int dinputjarak = Integer.parseInt(stringinputjarak);

                //define ArrayList to hold Integer objects
                final ArrayList numbers = new ArrayList();
                for(int i = 0; i < dinputjarak; i++)
                {
                    numbers.add(i+1);
                }
                Collections.shuffle(numbers);
                for(int j =0; j < dinputjarak; j++)
                {
                    StringBuffer sb = new StringBuffer();
                    for (Object s : numbers){
                        sb.append(s);
                        sb.append("\n");
                    }
                    String str = sb.toString();
                    teams = str.split("\n");
                }
                LayoutInflater layoutInflater = LayoutInflater.from(GrupCepat.this);
                View promptView = layoutInflater.inflate(R.layout.input_dialog_peserta,null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(GrupCepat.this);
                alertDialogBuilder.setView(promptView);


                final EditText jumlah_team = (EditText) promptView.findViewById(R.id.jumlah_team_edit_text);
                //Jendela Dialog
                alertDialogBuilder.setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(GrupCepat.this, AcakGrupCepat.class);
                                intent.putExtra("Jumlah Anggota tiap Tim",jumlah_team.getText().toString());
                                intent.putExtra("Tim",teams);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton("Batalkan", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id ) {
                                dialog.cancel();
                            }
                        });
                //Buat Alert Dialog
                AlertDialog alert = alertDialogBuilder.create();
                alert.show();
            }
        });
    }
}