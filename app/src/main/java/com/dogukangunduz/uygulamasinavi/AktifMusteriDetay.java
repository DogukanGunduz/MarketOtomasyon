package com.dogukangunduz.uygulamasinavi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.dogukangunduz.uygulamasinavi.databinding.ActivityAktifMusteriDetayBinding;

public class AktifMusteriDetay extends AppCompatActivity {

    private ActivityAktifMusteriDetayBinding binding;

    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAktifMusteriDetayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        id = intent.getIntExtra("id", -1);

        try {
            SQLiteDatabase db = this.openOrCreateDatabase("magaza_db", MODE_PRIVATE, null);
            Cursor cursor = db.rawQuery("SELECT * FROM musteri WHERE id = " + id, null);
            int idX = cursor.getColumnIndex("id");
            int nameX = cursor.getColumnIndex("isim");
            int surnameX = cursor.getColumnIndex("soyisim");
            int userNameX = cursor.getColumnIndex("kullaniciAdi");
            int genderX = cursor.getColumnIndex("cinsiyet");
            while (cursor.moveToNext()) {
                binding.nameTextt.setText(cursor.getString(nameX));
                binding.surnnameTextt.setText(cursor.getString(surnameX));
                binding.usernameTextt.setText(cursor.getString(userNameX));
                binding.genderTextt.setText(cursor.getString(genderX));
            }
        } catch (Exception e) {
            Toast.makeText(AktifMusteriDetay.this, "Database failed", Toast.LENGTH_SHORT).show();
        }
    }

    public void onayKaldir(View view){
        try {
            SQLiteDatabase db = this.openOrCreateDatabase("magaza_db", MODE_PRIVATE, null);
            db.execSQL("UPDATE musteri SET onayDurumu = 0 WHERE id = " + id);
            Toast.makeText(AktifMusteriDetay.this, "Onay Kaldırıldı", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, AktifMusteri.class);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(AktifMusteriDetay.this, "Database failed", Toast.LENGTH_SHORT).show();
        }
    }
}