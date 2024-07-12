package com.dogukangunduz.uygulamasinavi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.dogukangunduz.uygulamasinavi.databinding.ActivityAktifMusteriBinding;

import java.util.ArrayList;

public class AktifMusteri extends AppCompatActivity {

    private ActivityAktifMusteriBinding binding;

    ArrayList<Integer> ids = new ArrayList<Integer>();
    ArrayList<String> names = new ArrayList<String>();
    ArrayList<String> expressions = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAktifMusteriBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        try {
            SQLiteDatabase db = this.openOrCreateDatabase("magaza_db", MODE_PRIVATE, null);
            Cursor cursor = db.rawQuery("SELECT * FROM musteri WHERE onayDurumu ='1'", null);
            int idX = cursor.getColumnIndex("id");
            int nameX = cursor.getColumnIndex("isim");
            int surnameX = cursor.getColumnIndex("soyisim");
            while (cursor.moveToNext()) {
                ids.add(cursor.getInt(idX));
                names.add(cursor.getString(nameX));
                expressions.add(cursor.getInt(idX) + "-" + cursor.getString(nameX) +" "+cursor.getString(surnameX));
            }
            ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, expressions);
            binding.aktifMusteri.setAdapter(adapter);
            cursor.close();

            binding.aktifMusteri.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String choosen = expressions.get(position);
                    String[] parts = choosen.split("-");
                    int showingProduct = Integer.parseInt(parts[0]);
                    Intent goToDetails = new Intent(AktifMusteri.this, AktifMusteriDetay.class);
                    goToDetails.putExtra("id", showingProduct);
                    startActivity(goToDetails);

                }
            });

        } catch (Exception e) {
            Toast.makeText(this,"failed to create db", Toast.LENGTH_SHORT).show();
        }
    }

    public void adminSayfasi (View view) {
        Intent goToAdmin = new Intent(AktifMusteri.this, AdminPanelAnasayfa.class);
        startActivity(goToAdmin);
    }
}