package com.san.randompicker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class AcakGrupCepat extends AppCompatActivity {
    FloatingActionButton share_fab;
    FloatingActionButton share_fab2;
    ListView list_acak_grup_cepat;
    String share_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acak_grup_cepat);

        share_fab          = (FloatingActionButton) findViewById(R.id.share_fab);
        share_fab2         = (FloatingActionButton) findViewById(R.id.share_fab2);
        Intent intent      = getIntent();
        String jumlah_team = intent.getStringExtra("Jumlah Anggota tiap Tim");
        String[] peserta   = intent.getStringArrayExtra("Tim");

        if (jumlah_team.length() > 0 ){
            list_acak_grup_cepat = (ListView) findViewById(R.id.list_acak_grup_cepat);

            int size = peserta.length;
            Random rand = new Random();

            ArrayList<String> list = new ArrayList<>(size);
            for(int i = 0; i < size; i++) {
                list.add(peserta[i]);
            }

            String[] hasilRandom = new String[size];
            int x  = 0;
            int n  = Integer.valueOf(jumlah_team);
            int jumlah_team_min_satu = n -1;
            int angka = 1;
            share_text ="";

            while(list.size() > 0) {
                int index = rand.nextInt(list.size());
                int hasil_mod = x % n;
                hasilRandom[x] =" ";
                if (hasil_mod == 0 ){
                    hasilRandom[x] += "Tim "+angka+" : ";//list.remove(index);
                    angka++;
                }
                hasilRandom[x] += list.remove(index);
                share_text += hasilRandom[x]+"\n";
                x++;
            }
            if (hasilRandom.length > 1){
                // Buat daftar dari larik element bertipe String
                final List<String> tim_list = new ArrayList<String>(Arrays.asList(hasilRandom));
                // Buat sebuah Adapter Larik
                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                        (this, android.R.layout.simple_list_item_1, tim_list);
                // DataBind ListView with items from ArrayAdapter
                list_acak_grup_cepat.setAdapter(arrayAdapter);
            }else{
                Toast.makeText(this,"Peserta tidak boleh kosong, Minimal 2 inputan",Toast.LENGTH_SHORT).show();
                finish();
            }
        }else{
            Toast.makeText(this,"Input Jumlah Anggota Setiap Tim Tidak Boleh kosong",Toast.LENGTH_SHORT).show();
            finish();
        }
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

        //klik untuk menghubungkan aplikasi berbagi
        share_fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, share_text);
                sendIntent.setType("text/plain");

                //Digunakan untuk membuat dan menulis File/Data pada Penyimpanan
                FileOutputStream fileOutputStream;
                try {
                    //Membuat Berkas Baru dengan mode Private
                    fileOutputStream = openFileOutput("DataKelompok", Context.MODE_PRIVATE);

                    //Menulis Data Baru dan Mengkonversinya kedalam bentuk byte
                    fileOutputStream.write(share_text.getBytes());

                    //Menutup FileOutputStream
                    fileOutputStream.close();

                    Toast.makeText(getApplicationContext(), "Data Disimpan di Internal", Toast.LENGTH_SHORT).show();
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
    }
}
