package application.sysmar.prototipo.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import application.sysmar.prototipo.R;

public class CadastroActivity extends AppCompatActivity {

    private TextInputEditText campoNome, campoEmail, campoSenha;
    private Switch switchTipoUsuario;
    private FirebaseAuth autenticacao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        campoNome         = findViewById(R.id.editCadastroNome);
        campoEmail        = findViewById(R.id.editCadastroSenha);
        campoSenha        = findViewById(R.id.editCadastroSenha);
        switchTipoUsuario = findViewById(R.id.switchTipoUsuario);
    }


    public void validarCadastroUsuario(View view){
        String textoNome  = campoEmail.getText().toString();
        String textoEmail = campoEmail.getText().toString();
        String textoSenha = campoSenha.getText().toString();

        if ( !textoNome.isEmpty() ) {
            if (!textoEmail.isEmpty()) {
                if (!textoSenha.isEmpty()) {
                    Usuario usuario = new Usuario();
                    usuario.setNome( textoNome);
                    usuario.setEmail( textoEmail);
                    usuario.setSenha( textoSenha);
                    usuario.setTipo(verificaTipoUsuario());

                    cadastrarUsuario(usuario);

                } else {
                    Toast.makeText(CadastroActivity.this, "Preencha a Senha!", Toast.LENGTH_SHORT).show();
                }

            }else {
                Toast.makeText(CadastroActivity.this, "Preencha a E-Mail!", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(CadastroActivity.this, "Preencha o Nome!", Toast.LENGTH_SHORT).show();
        }
    }

    public void cadastrarUsuario(Usuario usuario){
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()
        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if ( task.isSuccessful() ){
                    Toast.makeText(CadastroActivity.this, "Sucesso ao cadastrar Usuário", Toast.LENGTH_SHORT).show();                    
                }else{
                    Toast.makeText(CadastroActivity.this, "erro ao castrar Usuário", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public String verificaTipoUsuario() {
        return switchTipoUsuario.isChecked() ? "P" : "M";
    }
}
