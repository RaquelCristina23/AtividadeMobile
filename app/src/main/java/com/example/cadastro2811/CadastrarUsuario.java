package com.example.cadastro2811;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CadastrarUsuario extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    EditText email, senha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_usuario);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();


        email = findViewById(R.id.editTextEmail);
        senha = findViewById(R.id.editTxtSenha);
    }

    public void CadastrarUsuario(View view) {
    if((! email.getText().toString().trim().equals("")&&(! senha.getText().toString().trim().equals("")))){
        firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(),senha.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "usuário cadastrado", Toast.LENGTH_SHORT).show();
                }
                if (task.isCanceled()){
                    Toast.makeText(getApplicationContext(), "tente novamente", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    }
}
