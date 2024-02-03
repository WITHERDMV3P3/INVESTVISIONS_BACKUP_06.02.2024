package com.example.investvisions;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


/** @noinspection ALL*/
public class acao extends AppCompatActivity {
    private SQLiteDatabase bancoacao;
    public EditText editTextTextprecolucro;
    public EditText editTextTextpvp;
    public EditText editTextTextcagr;
    public EditText editTextTextmargem;
    public EditText editTextTextdiv;
    public Button botaosalvaracao;
    public Button vertabela;
    public ListView listview;
    public ArrayList<Integer> arraydeid;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acao);
        editTextTextprecolucro = findViewById(R.id.editTextTextprecolucro);
        editTextTextpvp = findViewById(R.id.editTextTextpvp);
        editTextTextcagr = findViewById(R.id.editTextTextcagr);
        editTextTextmargem = findViewById(R.id.editTextTextmargem);
        editTextTextdiv = findViewById(R.id.editTextTextdiv);
        botaosalvaracao = findViewById(R.id.botaosalvaracao);
        vertabela = findViewById(R.id.vertabela);

        listview = findViewById(R.id.listview1);

        criarbancoacao();

        botaosalvaracao.setOnClickListener(v -> {
            registrar();
        });
        vertabela.setOnClickListener(v -> {
            listardados();
    });
       listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
           @Override
           public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long l) {       //CLICK LONGO PARA APAGAR OS DADOS DO ARQUIVO
               deletardados(i);
               return true;
           }
       });
    }

    public void criarbancoacao() { //INICIO DA CONEXÃO COM O BANCO DE DADOS
        try {
            bancoacao = openOrCreateDatabase("acao", MODE_PRIVATE, null);
            bancoacao.execSQL("CREATE TABLE IF NOT EXISTS bancoacao1 (id INTEGER PRIMARY KEY AUTOINCREMENT, precolucro FLOAT, pvp FLOAT, cagr FLOAT, margemliq FLOAT, divliq FLOAT)"); //CRIAR BANCO DE DADOS NO SISTEMA CASO A TABELA NÃO EXISTIR
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void registrar() {
            try {
                bancoacao = openOrCreateDatabase("acao", MODE_PRIVATE, null);                        //INSERIR OS NOMES NO BANCO DE DADOS CASO TENHA ALGUM ERRO ELE IRÁ SINALIZAR
                String sql = "INSERT INTO bancoacao1(precolucro,pvp,cagr,margemliq,divliq) VALUES (?,?,?,?,?)";
                SQLiteStatement acao = bancoacao.compileStatement(sql);
                acao.bindString(1, editTextTextprecolucro.getText().toString());                             // CADA AÇAO INDICA ONDE CADA DADO SERÁ INSERIDO NO EDITTEXT
                acao.bindString(2, editTextTextpvp.getText().toString());
                acao.bindString(3, editTextTextcagr.getText().toString());
                acao.bindString(4, editTextTextmargem.getText().toString());
                acao.bindString(5, editTextTextdiv.getText().toString());
                acao.executeInsert();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    public void listardados() {
        try {
            arraydeid = new ArrayList<>();
            bancoacao = openOrCreateDatabase("acao", MODE_PRIVATE, null);
            try (Cursor meucursor = bancoacao.rawQuery("SELECT * FROM bancoacao1", null)) { //LISTAR OS DADOS A PARTIR DE UM LISTVIEW PARA APARECER AO USUÁRIO
                ArrayList<String> linha = new ArrayList<>();
                ArrayAdapter<String> meuadaptador = new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_list_item_1,
                        android.R.id.text1,
                        linha
                );
                listview.setAdapter(meuadaptador);

                meucursor.moveToFirst();
                while (!meucursor.isAfterLast()) {
                    linha.add(meucursor.getString(1)); // Recupere os dados da primeira coluna
                    arraydeid.add(meucursor.getInt(0));
                    meucursor.moveToNext();

                }
                meucursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deletardados(Integer i) {
        try {
            Toast.makeText(this, i.toString(), Toast.LENGTH_SHORT).show();
            bancoacao = openOrCreateDatabase("acao", MODE_PRIVATE, null); //DELETAR O BANCO DE DADOS A PARTIR DE UM LISTVIEW
            String sql = "DELETE FROM bancoacao1 WHERE id=?";
            SQLiteStatement deletar = bancoacao.compileStatement(sql);
            deletar.bindLong(1, arraydeid.get(i));
            deletar.executeUpdateDelete();
            listardados();
            bancoacao.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}




