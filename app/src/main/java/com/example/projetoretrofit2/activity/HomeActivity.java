package com.example.projetoretrofit2.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.projetoretrofit2.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void show(View view) {

        switch (view.getId()){

            case R.id.btnAlbum:

                Intent intent = new Intent(this,AlbumActivity.class);
                startActivity(intent);

                break;
                default:
                Toast.makeText(this,
                        "Erro ao selecionar opção",
                        Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
