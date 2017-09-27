package com.paivadeveloper.clubdollar.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.paivadeveloper.clubdollar.R;
import com.paivadeveloper.clubdollar.util.BaseActivity;

public class ClienteLogin extends BaseActivity {
private Button btn_entrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_login);

        Button btn_entrar = findViewById(R.id.btn_entrar);
        btn_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        TextView tv_novaconta = findViewById(R.id.tv_novaconta);
        tv_novaconta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClienteLogin.this, ClienteNovaConta.class);

                startActivity(intent);
            }
        });

        btn_entrar = findViewById(R.id.btn_entrar);
        btn_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClienteLogin.this, MainActivity.class);

                startActivity(intent);
            }
        });
    }
}
