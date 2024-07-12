package com.dogukangunduz.uygulamasinavi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import com.dogukangunduz.uygulamasinavi.databinding.ActivityMusteriProfilBinding;

public class MusteriProfil extends AppCompatActivity {

    private ActivityMusteriProfilBinding binding;
    int id;
    String userName;
    String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMusteriProfilBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        /*Intent intent = getIntent();
        id = intent.getIntExtra("id", -1);
        System.out.println(id);*/

        try {
            SQLiteDatabase db = this.openOrCreateDatabase("magaza_db", MODE_PRIVATE, null);
            Cursor cursor = db.rawQuery("SELECT * FROM musteriler WHERE id = " + id, null);
            int idX = cursor.getColumnIndex("id");
            int nameX = cursor.getColumnIndex("isim");
            int surnameX = cursor.getColumnIndex("soyisim");
            int usernameX = cursor.getColumnIndex("kullaniciAdi");
            int passwordX = cursor.getColumnIndex("sifre");

            while (cursor.moveToNext()) {
                binding.musteriAdiText.setText(cursor.getString(nameX));
                binding.musteriSoyadiText.setText(cursor.getString(surnameX));
                binding.kullaniciAdiText.setText(cursor.getString(usernameX));
                binding.sifreText.setText(cursor.getString(passwordX));
            }
        } catch (Exception e) {
            Toast.makeText(MusteriProfil.this, "Database failed", Toast.LENGTH_SHORT).show();
        }
    }
}