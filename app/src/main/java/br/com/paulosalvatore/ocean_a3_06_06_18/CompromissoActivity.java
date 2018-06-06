package br.com.paulosalvatore.ocean_a3_06_06_18;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class CompromissoActivity extends AppCompatActivity {

    private TextView tvNome;
    private TextView tvEmail;

    private Spinner spLocalAula;
    private EditText etInformacoes;

    private String nome = "";
    private String email = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compromisso);

        tvNome = (TextView) findViewById(R.id.tvNome);
        tvEmail = (TextView) findViewById(R.id.tvEmail);

        spLocalAula = (Spinner) findViewById(R.id.spLocalAula);
        etInformacoes = (EditText) findViewById(R.id.etInformacoes);

        Intent i = getIntent();

        nome = i.getStringExtra("nome");
        email = i.getStringExtra("email");

        tvNome.setText(nome);
        tvEmail.setText(email);
    }

    public void cadastrar(View view) {
        Intent i = new Intent();
        i.putExtra("local_aula", spLocalAula.getSelectedItem().toString());
        setResult(RESULT_OK, i);
        this.finish();
    }

    public void limpar(View view) {
        etInformacoes.setText("");
        spLocalAula.setSelection(0);
    }
}
