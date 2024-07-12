package com.dogukangunduz.uygulamasinavi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.dogukangunduz.uygulamasinavi.databinding.ActivityMusteriPanelGirisBinding;

public class MusteriPanelGiris extends AppCompatActivity {
    private ActivityMusteriPanelGirisBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMusteriPanelGirisBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    public void goToRegister(View view) {
        Intent intent = new Intent(MusteriPanelGiris.this, MusteriKayit.class);
        startActivity(intent);
    }

    public void goToLogin(View view) {
        String userName = binding.musteriKullaniciAdiText.getText().toString();
        String password = binding.musteriSifreText.getText().toString();
        if (userName.equals("") || password.equals("")) {
            Toast.makeText(MusteriPanelGiris.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
        } else {
            try {
                SQLiteDatabase db = this.openOrCreateDatabase("magaza_db", MODE_PRIVATE, null);
                Cursor cursor = db.rawQuery("SELECT * FROM musteri WHERE kullaniciAdi = '" + userName + "' AND sifre = '" + password + "'", null);
                if (cursor.getCount() > 0) {
                    SQLiteDatabase db2 = this.openOrCreateDatabase("magaza_db", MODE_PRIVATE, null);
                    Cursor cursor2 = db2.rawQuery("SELECT * FROM musteri WHERE onayDurumu =" + 0 + "", null);
                    if (cursor2.getCount() > 0) {
                        Toast.makeText(MusteriPanelGiris.this, "You are not approved yet.", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        Intent intent = new Intent(MusteriPanelGiris.this, MusteriAnaSayfa.class);
                        /*intent.putExtra("usernName", userName);
                        intent.putExtra("password", password);*/
                        startActivity(intent);
                        finish();
                    }
                } else {
                    Toast.makeText(MusteriPanelGiris.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                }
                cursor.close();
                db.close();
            } catch (Exception e) {
                Toast.makeText(MusteriPanelGiris.this, "Failed to login: ", Toast.LENGTH_SHORT).show();
            }
        }
    }
}