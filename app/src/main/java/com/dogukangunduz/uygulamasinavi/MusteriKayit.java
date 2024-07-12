package com.dogukangunduz.uygulamasinavi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.dogukangunduz.uygulamasinavi.databinding.ActivityMusteriKayitBinding;

import java.io.ByteArrayOutputStream;

public class MusteriKayit extends AppCompatActivity {

    private ActivityMusteriKayitBinding binding;

    String[] cinsiyetler = {"Cinsiyet Seçiniz...","Erkek","Kadın"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMusteriKayitBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        try {
            SQLiteDatabase db = this.openOrCreateDatabase("magaza_db", MODE_PRIVATE, null);
        } catch (Exception e) {
            Toast.makeText(MusteriKayit.this, "Failed to create database: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cinsiyetler);
        binding.cinsiyet.setAdapter(adapter);
    }

    public void kayitOl(View view) {
        String isim = binding.musteriIsimText.getText().toString();
        String soyIsim = binding.musteriSoyisimText.getText().toString();
        String kullaniciAdi = binding.musteriKullaniciAdiText.getText().toString();
        String sifre = binding.musteriSifreText.getText().toString();
        String onay = "0";
        String cinsiyet = binding.cinsiyet.getSelectedItem().toString();

        if (isim.equals("") || soyIsim.equals("") || sifre.equals("") || kullaniciAdi.equals("")) {
            Toast.makeText(MusteriKayit.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
        }
        else {

            try {
                SQLiteDatabase db = this.openOrCreateDatabase("magaza_db", MODE_PRIVATE, null);
                String sql = "INSERT INTO musteri (isim, soyisim, kullaniciAdi,sifre,onayDurumu,cinsiyet) VALUES(?,?,?,?,?,?)";
                SQLiteStatement sqlStatus = db.compileStatement(sql);
                sqlStatus.bindString(1, isim);
                sqlStatus.bindString(2, soyIsim);
                sqlStatus.bindString(3, kullaniciAdi);
                sqlStatus.bindString(4, sifre);
                sqlStatus.bindString(5, onay);
                sqlStatus.bindString(6, cinsiyet);
                sqlStatus.execute();

                Intent goToProducts = new Intent(MusteriKayit.this, MusteriPanelGiris.class);
                goToProducts.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(goToProducts);

                Toast.makeText(this, "Product saved successfully", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(MusteriKayit.this, "Save product failed", Toast.LENGTH_SHORT).show();
            }
        }
    }
}