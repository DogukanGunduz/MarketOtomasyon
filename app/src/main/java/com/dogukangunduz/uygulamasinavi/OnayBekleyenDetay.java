package com.dogukangunduz.uygulamasinavi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.dogukangunduz.uygulamasinavi.databinding.ActivityOnayBekleyenDetayBinding;

public class OnayBekleyenDetay extends AppCompatActivity {

    int id;

    private ActivityOnayBekleyenDetayBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOnayBekleyenDetayBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

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
                binding.nameText.setText(cursor.getString(nameX));
                binding.surnnameText.setText(cursor.getString(surnameX));
                binding.usernameText.setText(cursor.getString(userNameX));
                binding.genderText.setText(cursor.getString(genderX));
            }
        } catch (Exception e) {
            Toast.makeText(OnayBekleyenDetay.this, "Database failed", Toast.LENGTH_SHORT).show();
        }
    }

    public void onayla(View view) {
        try {
            SQLiteDatabase db = this.openOrCreateDatabase("magaza_db", MODE_PRIVATE, null);
            db.execSQL("UPDATE musteri SET onayDurumu = '1' WHERE id = " + id);
            Toast.makeText(OnayBekleyenDetay.this, "Onaylandı", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,OnayBekleyen.class);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(OnayBekleyenDetay.this, "Database failed", Toast.LENGTH_SHORT).show();
        }
    }
}