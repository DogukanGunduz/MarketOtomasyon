package com.dogukangunduz.uygulamasinavi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.dogukangunduz.uygulamasinavi.databinding.ActivityUrunlerListesiMusteriDetayBinding;

public class UrunlerListesiMusteriDetay extends AppCompatActivity {

    String[] satinAlmaSayisi = {"Adet Seçiniz...", "1", "2", "3", "4", "5"};

    int id;
    int stok;
    private ActivityUrunlerListesiMusteriDetayBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUrunlerListesiMusteriDetayBinding.inflate(getLayoutInflater());
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
                binding.nameTextt.setText(cursor.getString(nameX));
                binding.surnnameTextt.setText(cursor.getString(priceX));
                binding.usernameTextt.setText(cursor.getString(stokX));
            }
        } catch (Exception e) {
            Toast.makeText(UrunlerListesiMusteriDetay.this, "Database failed", Toast.LENGTH_SHORT).show();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, satinAlmaSayisi);
        binding.sayi.setAdapter(adapter);

        if (id == 1) {
            binding.imageView.setImageResource(R.drawable.kitap);
        } else if (id == 2) {
            binding.imageView.setImageResource(R.drawable.canta);
        } else if (id == 3) {
            binding.imageView.setImageResource(R.drawable.gozluk);
        } else if (id == 4) {
            binding.imageView.setImageResource(R.drawable.saat);
        } else if (id == 5) {
            binding.imageView.setImageResource(R.drawable.elbise);
        } else if (id == 6) {
            binding.imageView.setImageResource(R.drawable.ayakkabi);
        } else if (id == 7) {
            binding.imageView.setImageResource(R.drawable.bileklik);
        } else if (id == 8) {
            binding.imageView.setImageResource(R.drawable.terlik);
        } else if (id == 9) {
            binding.imageView.setImageResource(R.drawable.suluk);
        } else if (id == 10) {
            binding.imageView.setImageResource(R.drawable.anahtarlik);
        }


    }

    public void buy(View v) {
        stok = Integer.parseInt(binding.usernameTextt.getText().toString());
        if (stok >= Integer.parseInt(binding.sayi.getSelectedItem().toString())) {
        try {
                int yeniStok = Integer.parseInt(binding.usernameTextt.getText().toString()) - Integer.parseInt(binding.sayi.getSelectedItem().toString());
                SQLiteDatabase db = this.openOrCreateDatabase("magaza_db", MODE_PRIVATE, null);
                String sql = "UPDATE urunler SET urunStogu=? WHERE id=?";
                SQLiteStatement sqlStatus = db.compileStatement(sql);
                sqlStatus.bindLong(1, yeniStok);
                sqlStatus.bindLong(2, id);
                sqlStatus.execute();
                Toast.makeText(this, "Product bought succesfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, UrunlerListesi.class);
                startActivity(intent);
            } catch(Exception e){
                Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void goBack(View v) {
        Intent intent = new Intent(this, UrunlerListesi.class);
        startActivity(intent);
        finish();
    }
}