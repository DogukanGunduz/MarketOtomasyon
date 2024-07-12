package com.dogukangunduz.uygulamasinavi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.dogukangunduz.uygulamasinavi.databinding.ActivityAdminPanelBinding;

public class AdminPanelGiris extends AppCompatActivity {

    private ActivityAdminPanelBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminPanelBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    public void login (View view) {
        String userName = binding.adminKullaniciAdiText.getText().toString();
        String password = binding.adminSifreText.getText().toString();
        if (userName.equals("") || password.equals("")) {
            Toast.makeText(AdminPanelGiris.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
        } else {
            try {
                SQLiteDatabase db = this.openOrCreateDatabase("magaza_db", MODE_PRIVATE, null);
                Cursor cursor = db.rawQuery("SELECT * FROM admin WHERE userName = '" + userName + "' AND password = '" + password + "'", null);
                if (cursor.getCount() > 0) {
                    Intent intent = new Intent(AdminPanelGiris.this, AdminPanelAnasayfa.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(AdminPanelGiris.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                }
                cursor.close();
                db.close();
            } catch (Exception e) {
                Toast.makeText(AdminPanelGiris.this, "Failed to login: ", Toast.LENGTH_SHORT).show();
            }
        }
    }
}