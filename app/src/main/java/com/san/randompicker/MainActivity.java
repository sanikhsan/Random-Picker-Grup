package com.san.randompicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private CardView grupbaru,pilihcepat,grupcepat,readme,credit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        grupbaru = (CardView) findViewById(R.id.grupbaru);
        pilihcepat = (CardView) findViewById(R.id.pilihcepat);
        grupcepat = (CardView) findViewById(R.id.grupcepat);
        readme = (CardView) findViewById(R.id.readme);
//        credit = (CardView) findViewById(R.id.credit);

        grupbaru.setOnClickListener(this);
        pilihcepat.setOnClickListener(this);
        grupcepat.setOnClickListener(this);
        readme.setOnClickListener(this);
//        credit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        Intent i;

        switch (v.getId()){
            case R.id.grupbaru : i = new Intent(this, GrupBaru.class); startActivity(i); break;
            case R.id.pilihcepat : i = new Intent(this, PilihCepat.class); startActivity(i); break;
            case R.id.grupcepat : i = new Intent(this, GrupCepat.class); startActivity(i); break;
            case R.id.readme : i = new Intent(this, Readme.class); startActivity(i); break;
//            case R.id.credit : i = new Intent(this, Credit.class); startActivity(i); break;
        }
    }
}