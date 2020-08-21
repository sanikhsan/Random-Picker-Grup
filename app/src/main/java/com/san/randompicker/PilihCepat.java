package com.san.randompicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.lang.reflect.Array;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class PilihCepat extends AppCompatActivity {

    EditText inputjarak;
    TextView outputhasil;
    Button buttoninputjarak, buttonoutputhasil,buttonulangi,buttonsemuahasil;
    int index;
    String[] teams = new String[100000];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_cepat);
        index = 1;

        inputjarak = findViewById(R.id.inputjarak);
        outputhasil = findViewById(R.id.outputhasil);
        buttoninputjarak = findViewById(R.id.buttoninputjarak);
        buttonoutputhasil = findViewById(R.id.buttonoutputhasil);
        buttonulangi = findViewById(R.id.buttonulangi);
        buttonsemuahasil = findViewById(R.id.buttonsemuahasil);



        final LinearLayout i_layout = findViewById(R.id.input);
        final LinearLayout o_layout = findViewById(R.id.output);

        buttoninputjarak.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                //Mengambil value yang ada di EditText inputjarak dan dimasukkan ke variabel stringinputjarak
                String stringinputjarak = inputjarak.getText().toString();
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
                    outputhasil.setText(""+numbers.get(0));
                    o_layout.setVisibility(View.VISIBLE);
                    i_layout.setVisibility(View.GONE);

                    if (index <numbers.size()){
                        buttonoutputhasil.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                outputhasil.setText(""+numbers.get(index));
                                index++;
                                if (index==numbers.size()){
                                    buttonoutputhasil.setVisibility(View.GONE);
                                    buttonsemuahasil.setVisibility(View.VISIBLE);
                                    buttonulangi.setVisibility(View.VISIBLE);

                                    for(int j =0; j < numbers.size(); j++)
                                    {
                                        StringBuffer sb = new StringBuffer();
                                        for (Object s : numbers){
                                            sb.append(s);
                                            sb.append("\n");
                                        }
                                        String str = sb.toString();
                                        teams = str.split("\n");
                                    }

                                    buttonsemuahasil.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent intent = new Intent(PilihCepat.this, AnRiwayatPilihCepat.class);
                                            intent.putExtra("key",teams);
                                            startActivity(intent);
                                        }
                                    });
//                                    Toast.makeText(getApplicationContext(), "Tidak Ada Data Lagi",Toast.LENGTH_LONG).show();
                                }else{
                                }
                            }
                        });
                    }else{

                    }

                    }
                }
        });

        buttonulangi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(getIntent());
            }
        });
    }
}