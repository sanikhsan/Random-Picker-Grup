package com.san.randompicker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class AnRiwayatPilihCepat extends Activity {
    FloatingActionButton share_fab;
    ListView hasil_pilih_cepat;
    String share_text;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_pilih_cepat);
        Intent intent      = getIntent();
        share_fab          = (FloatingActionButton) findViewById(R.id.share_fab);

        hasil_pilih_cepat = (ListView) findViewById(R.id.hasil_pilih_cepat);
        String[] peserta = getIntent().getStringArrayExtra("key");
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, peserta);
        CustomAdapter adapter = new CustomAdapter(this,peserta);
        hasil_pilih_cepat.setAdapter(adapter);

        //klik untuk menghubungkan aplikasi berbagi
        share_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, share_text);
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.send_to)));
            }
        });
    }

    class CustomAdapter extends ArrayAdapter<String>
    {
        Context context;
        String[] peserta;

        CustomAdapter(Context c, String[] peserta)
        {
            super(c, R.layout.list,peserta);
            this.context = c;
            this.peserta=peserta;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View row = vi.inflate(R.layout.list, parent, false);
            TextView titlee = (TextView) row.findViewById(R.id.listid);
            int pos = position+1;
            titlee.setText("Urutan Ke-"+pos + ", Absen No : "  + peserta[position]);
            pos++;
            return row;
        }
    }
}
