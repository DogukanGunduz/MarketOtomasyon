package com.dogukangunduz.uygulamasinavi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.dogukangunduz.uygulamasinavi.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        try {
            SQLiteDatabase db = this.openOrCreateDatabase("magaza_db", MODE_PRIVATE, null);
            db.execSQL("CREATE TABLE IF NOT EXISTS musteri (id INTEGER PRIMARY KEY AUTOINCREMENT, isim VARCHAR(50), soyisim VARCHAR(50), kullaniciAdi VARCHAR(50), sifre VARCHAR(50), onayDurumu VARCHAR(50), cinsiyet VARCHAR(50)) ");
            db.execSQL("CREATE TABLE IF NOT EXISTS admin (id INTEGER PRIMARY KEY AUTOINCREMENT, userName VARCHAR(50), password VARCHAR(50))");
            db.execSQL("CREATE TABLE IF NOT EXISTS urunler (id INTEGER PRIMARY KEY AUTOINCREMENT, urunAdi VARCHAR(50), urunFiyati INTEGER, urunStogu INTEGER)");

        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "Failed to create database: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        /*try {
            SQLiteDatabase db = this.openOrCreateDatabase("magaza_db", MODE_PRIVATE, null);
            db.execSQL("INSERT INTO urunler (urunAdi, urunFiyati, urunStogu) VALUES ('kitap', 20, 200)");
            db.execSQL("INSERT INTO urunler (urunAdi, urunFiyati, urunStogu) VALUES ('canta', 1500, 100)");
            db.execSQL("INSERT INTO urunler (urunAdi, urunFiyati, urunStogu) VALUES ('gozluk', 150, 25)");
            db.execSQL("INSERT INTO urunler (urunAdi, urunFiyati, urunStogu) VALUES ('saat', 90, 79)");
            db.execSQL("INSERT INTO urunler (urunAdi, urunFiyati, urunStogu) VALUES ('elbise', 400, 200)");
            db.execSQL("INSERT INTO urunler (urunAdi, urunFiyati, urunStogu) VALUES ('ayakkabi', 1250, 500)");
            db.execSQL("INSERT INTO urunler (urunAdi, urunFiyati, urunStogu) VALUES ('bileklik', 70, 50)");
            db.execSQL("INSERT INTO urunler (urunAdi, urunFiyati, urunStogu) VALUES ('terlik', 20, 150)");
            db.execSQL("INSERT INTO urunler (urunAdi, urunFiyati, urunStogu) VALUES ('suluk', 70, 26)");
            db.execSQL("INSERT INTO urunler (urunAdi, urunFiyati, urunStogu) VALUES ('anahtarlik', 12, 500)");
            db.close();
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "Failed to save admin users: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        try {
            SQLiteDatabase db = this.openOrCreateDatabase("magaza_db", MODE_PRIVATE, null);
            db.execSQL("INSERT INTO admin (userName, password) VALUES ('admin', '123456')");
            db.close();
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "Failed to save admin users: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }*/
    }

    public void adminPanelGiris(View v){
        Intent intent = new Intent(MainActivity.this, AdminPanelGiris.class);
        startActivity(intent);
    }
    public void musteriPanelGiris(View v){
        Intent intent = new Intent(MainActivity.this, MusteriPanelGiris.class);
        startActivity(intent);
    }
}