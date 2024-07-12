package com.dogukangunduz.uygulamasinavi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.dogukangunduz.uygulamasinavi.databinding.ActivityUrunlerListesiDetayBinding;

public class UrunlerListesiDetay extends AppCompatActivity {
    
    private ActivityUrunlerListesiDetayBinding binding;

    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUrunlerListesiDetayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        id = intent.getIntExtra("id", -1);

        try {
            SQLiteDatabase db = this.openOrCreateDatabase("magaza_db", MODE_PRIVATE, null);
            Cursor cursor = db.rawQuery("SELECT * FROM urunler WHERE id = " + id, null);
            int idX = cursor.getColumnIndex("id");
            int nameX = cursor.getColumnIndex("urunAdi");
            int priceX = cursor.getColumnIndex("urunFiyati");
            int stokX = cursor.getColumnIndex("urunStogu");
            
            while (cursor.moveToNext()) {
                binding.nameeText.setText(cursor.getString(nameX));
                binding.surnnameeText.setText(cursor.getString(priceX));
                binding.usernameeText.setText(cursor.getString(stokX));
            }
        } catch (Exception e) {
            Toast.makeText(UrunlerListesiDetay.this, "Database failed", Toast.LENGTH_SHORT).show();
        }
    }

    public void update(View v){
        String urunAdi = binding.nameeText.getText().toString().trim();
        int urunFiyati = Integer.parseInt(binding.surnnameeText.getText().toString().trim());
        int urunStogu = Integer.parseInt(binding.usernameeText.getText().toString().trim());
        try {
            SQLiteDatabase db = this.openOrCreateDatabase("magaza_db", MODE_PRIVATE, null);
            String sql = "UPDATE urunler SET urunAdi=?, urunFiyati=?, urunStogu=? WHERE id=?";
            SQLiteStatement sqlStatus = db.compileStatement(sql);
            sqlStatus.bindString(1, urunAdi);
            sqlStatus.bindLong(2, urunFiyati);
            sqlStatus.bindLong(3, urunStogu);
            sqlStatus.bindLong(4, id);
            sqlStatus.execute();
            Toast.makeText(this, "Product updated successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, UrunlerListesi.class);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(UrunlerListesiDetay.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    public void goBack(View v) {
        Intent intent = new Intent(this, UrunlerListesi.class);
        startActivity(intent);
    }
}