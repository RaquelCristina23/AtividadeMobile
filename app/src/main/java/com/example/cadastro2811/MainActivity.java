package com.example.cadastro2811;

import androidx.annotation.IntegerRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    EditText editTextEmail, editTextSenha;
    Button btnCadastrar, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        editTextEmail = findViewById(R.id.editTxtEmail);
        editTextSenha = findViewById(R.id.editTxtSenha);

    }

    public void login(View view) {
        String email = editTextEmail.getText().toString();
        String senha = editTextSenha.getText().toString();

        if (!email.trim().equals("")) {
            firebaseAuth.signInWithEmailAndPassword(email, senha)
            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
                    }
                    if (task.isCanceled()){
                        Toast.makeText(getApplicationContext(), "tente novamente", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            FirebaseUser usuario = firebaseAuth.getCurrentUser();

            if (usuario != null) {
                Intent intent = new Intent(getApplicationContext(), Principal.class);
                startActivity(intent);
            }

        }

    }

    public void cadastrar(View view) {
            Intent intent = new Intent(getApplicationContext(), CadastrarUsuario.class);
            startActivity(intent);
    }

    public void recuperarSenha(View view){
        String email = editTextEmail.getText().toString();
        String senha = editTextSenha.getText().toString();

    if (!email.trim().equals("")){
        firebaseAuth.sendPasswordResetEmail(email);
    }
    }
}

