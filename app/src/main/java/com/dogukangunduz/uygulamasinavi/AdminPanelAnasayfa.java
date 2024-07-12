package com.dogukangunduz.uygulamasinavi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dogukangunduz.uygulamasinavi.databinding.ActivityAdminPanelAnasayfaBinding;

public class AdminPanelAnasayfa extends AppCompatActivity {

    private ActivityAdminPanelAnasayfaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminPanelAnasayfaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    public void onayBeklemeEkrani(View v){
        Intent intent = new Intent(this, OnayBekleyen.class);
        startActivity(intent);
    }
    public void aktifMusteri(View v){
        Intent intent = new Intent(this, AktifMusteri.class);
        startActivity(intent);
    }
    public void urunlerListesi(View v){
        Intent intent = new Intent(this, UrunlerListesi.class);
        startActivity(intent);
    }
    public void mainMenu(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}