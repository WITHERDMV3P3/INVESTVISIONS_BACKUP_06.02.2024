package com.example.investvisions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button botaoacao;
    private Button botaorprecojusto;
    private Button botaoafiis;
    private Button botaopdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    botaoacao = findViewById(R.id.buttonacao);
    botaorprecojusto = findViewById(R.id.buttonprecojusto);   //declaração das variaveis dos botões e atribuindo
    botaoafiis = findViewById(R.id.buttonfiis);
    botaopdf = findViewById(R.id.buttonpdf);

    botaoacao.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this,acao.class); //sistema de click para ir para a tela desejada da ação
            startActivity(intent);
        }
    });
    botaorprecojusto.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent2 = new Intent(MainActivity.this,precojusto.class); //sistema de click para ir para a tela desejada do preço justo
            startActivity(intent2);
        }
    });
    botaoafiis.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent3 = new Intent(MainActivity.this,fiis.class); //sistema de click para ir para a tela desejada do FIIS
            startActivity(intent3);
        }
    });
  /*  botaopdf.setOnClickListener((new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    }));*/

    }
}