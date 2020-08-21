package com.san.randompicker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class GrupBaru extends AppCompatActivity {
    //Deklarasi Edit Text dan Button
    EditText inputpeserta;
    Button buttoninputpeserta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grup_baru);

        //Menghubungkan EditText dan Button
        inputpeserta = findViewById(R.id.inputpeserta);
        buttoninputpeserta = findViewById(R.id.buttoninputpeserta);

        buttoninputpeserta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Convert nilai input dari EditText menjadi String
                final String[] teams = inputpeserta.getText().toString().split("\n");

                LayoutInflater layoutInflater = LayoutInflater.from(GrupBaru.this);
                View promptView = layoutInflater.inflate(R.layout.input_dialog_peserta,null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(GrupBaru.this);
                alertDialogBuilder.setView(promptView);


                final EditText jumlah_team = (EditText) promptView.findViewById(R.id.jumlah_team_edit_text);
                //Jendela Dialog
                alertDialogBuilder.setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                Log.d("GrupBaru", String.valueOf(teams.length));
                                Intent intent = new Intent(GrupBaru.this, AcakGrupBaru.class);
                                intent.putExtra("Jumlah Anggota tiap Tim",jumlah_team.getText().toString());
                                intent.putExtra("Tim",teams);
                                startActivity(intent);
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