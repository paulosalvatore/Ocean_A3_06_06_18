package br.com.paulosalvatore.ocean_a3_06_06_18;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;

    private TextView tvTitulo;

    private EditText etNome;
    private EditText etEmail;

    private TextView tvResultado;

    private Button btLimpar;

    private String nome = "";
    private String email = "";

    private int contador = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTitulo = (TextView) findViewById(R.id.tvTitulo);

        etNome = (EditText) findViewById(R.id.etNome);
        etEmail = (EditText) findViewById(R.id.etEmail);

        tvResultado = (TextView) findViewById(R.id.tvResultado);

        btLimpar = (Button) findViewById(R.id.btLimpar);

        btLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpar();
            }
        });
    }

    private void limpar() {
        etNome.setText("");
        etEmail.setText("");
    }

    public void enviar(View view) {
        // Toast.makeText(this, "Botão clicado.", Toast.LENGTH_LONG).show();

        contador++;

        tvTitulo.setText("Título Alterado: " + contador);

        nome = etNome.getText().toString();
        email = etEmail.getText().toString();

        if (nome.isEmpty()) {
            Toast.makeText(this, getResources().getString(R.string.digitar_nome), Toast.LENGTH_LONG).show();
        }
        else if (email.isEmpty()) {
            Toast.makeText(this, getResources().getString(R.string.digitar_email), Toast.LENGTH_LONG).show();
        }
        else {
            atualizarResultado();

            // Iniciar a nova tela com os dados passados
            Intent i = new Intent(this, CompromissoActivity.class);
            i.putExtra("nome", nome);
            i.putExtra("email", email);
            startActivityForResult(i, REQUEST_CODE);
        }
    }

    private void atualizarResultado() {
        if (nome.isEmpty() || email.isEmpty())
            return;

        tvResultado.setText(
                getResources().getString(R.string.nome) + ": " + nome + "\n" +
                getResources().getString(R.string.email) + ": " + email
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            String localAula = data.getStringExtra("local_aula");

            Toast.makeText(
                    this,
                    "Item cadastrado com sucesso: " + localAula,
                    Toast.LENGTH_LONG
            ).show();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        nome = etNome.getText().toString();
        outState.putString("nome", nome);

        email = etEmail.getText().toString();
        outState.putString("email", email);

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        nome = savedInstanceState.getString("nome");
        email = savedInstanceState.getString("email");

        atualizarResultado();
    }
}
