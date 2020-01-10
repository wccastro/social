package application.sysmar.prototipo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import application.sysmar.prototipo.R;

public class MainActivity extends AppCompatActivity {

    private Object View;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
    }
    public void abrirTelaLogin(android.view.View  view){
        startActivity( new Intent(this, LoginActivity.class));
    }

    public void abritTelaCadastro(android.view.View view){
        startActivity( new Intent(this, CadastroActivity.class));
    }
}
