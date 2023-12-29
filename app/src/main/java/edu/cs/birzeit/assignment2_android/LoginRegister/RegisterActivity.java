package edu.cs.birzeit.assignment2_android.LoginRegister;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import edu.cs.birzeit.assignment2_android.R;
import edu.cs.birzeit.assignment2_android.data.UserPreferencesManager;


public class RegisterActivity extends AppCompatActivity {

    private EditText ed_firstName;
    private EditText ed_lastName;
    private EditText ed_email;
    private EditText ed_password;
    private Button btn_register;
    private UserPreferencesManager preferencesManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        preferencesManager = UserPreferencesManager.getInstance(this);

        setupViews();
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnRegisterOnClick(view);
                Toast.makeText(RegisterActivity.this, "Register successfully!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }


    private void setupViews(){
       ed_firstName = findViewById(R.id.ed_firstName);
       ed_lastName = findViewById(R.id.ed_lastName);
       ed_email = findViewById(R.id.ed_email);
       ed_password = findViewById(R.id.ed_password);
       btn_register = findViewById(R.id.btn_register);

    }

    public void btnRegisterOnClick(View view) {
        String firstName = ed_firstName.getText().toString().trim();
        String lastName = ed_lastName.getText().toString().trim();
        String email = ed_email.getText().toString().trim();
        String pass = ed_password.getText().toString();

        preferencesManager.saveUserDetails(firstName, lastName, email, pass);

        preferencesManager.setRegistered(true);

    }
}
