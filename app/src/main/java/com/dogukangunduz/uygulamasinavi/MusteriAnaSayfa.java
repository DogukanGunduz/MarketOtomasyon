package com.dogukangunduz.uygulamasinavi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dogukangunduz.uygulamasinavi.databinding.ActivityMusteriAnaSayfaBinding;

public class MusteriAnaSayfa extends AppCompatActivity {

    private ActivityMusteriAnaSayfaBinding binding;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMusteriAnaSayfaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        id = intent.getIntExtra("id", -1);
    }

    public void urunlereGit(View view) {
        Intent intent = new Intent(this, UrunListeMusteri.class);
        startActivity(intent);
        finish();
    }

    public void profileGirl(View view){
        Intent intent = new Intent(this, MusteriProfil.class);
        intent.putExtra("id", id);
        startActivity(intent);
        finish();
    }

}