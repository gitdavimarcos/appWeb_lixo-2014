package br.edu.iff.pooa20152.ppi.ppiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
    }

    public void chamarInscricao (View v) {
        startActivity(new Intent(this, Inscricao.class));
    }

    public void chamarProfessor (View v){
        startActivity(new Intent(this, Professor.class));
    }

    public void chamarCurso (View v) {
        startActivity(new Intent(this, Curso.class));
    }

    public void chamarProAlu (View v) {
        startActivity(new Intent(this, AlocarProfessor.class));
    }

    public void chamarNota (View v) {
        startActivity(new Intent(this, LancarNota.class));
    }
}
