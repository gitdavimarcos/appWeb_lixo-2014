package br.edu.iff.pooa20152.ppi.ppiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void acessarTelaPrincipal (View v){
        startActivity(new Intent(this, Principal.class));
        finish();
    }
}
