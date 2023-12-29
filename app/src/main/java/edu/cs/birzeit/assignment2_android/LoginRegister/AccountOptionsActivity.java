package edu.cs.birzeit.assignment2_android.LoginRegister;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import edu.cs.birzeit.assignment2_android.R;

public class AccountOptionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_options);

        Button btn_register = findViewById(R.id.btn_register);
        Button btn_login = findViewById(R.id.btn_login);


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_register = new Intent(AccountOptionsActivity.this, RegisterActivity.class);
                startActivity(intent_register);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_login = new Intent(AccountOptionsActivity.this, LoginActivity.class);
                startActivity(intent_login);
            }
        });
    }
}
