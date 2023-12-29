package edu.cs.birzeit.assignment2_android.LoginRegister;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import edu.cs.birzeit.assignment2_android.R;
import edu.cs.birzeit.assignment2_android.data.UserPreferencesManager;
import edu.cs.birzeit.assignment2_android.activities.MainActivity;

public class LoginActivity extends AppCompatActivity {
    private boolean flag = false;
    private EditText ed_email;
    private EditText ed_password;
    private CheckBox chkRememberPassword;
    private Button Login;
    private UserPreferencesManager preferencesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        preferencesManager = UserPreferencesManager.getInstance(this);

        setupViews();
        checkPrefs();

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = ed_email.getText().toString().trim();
                String password = ed_password.getText().toString();

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginActivity.this, "Please fill in both email and password", Toast.LENGTH_SHORT).show();
                    return; // Return early if email or password is empty
                }

                if (chkRememberPassword.isChecked() && !preferencesManager.isRegistered()) {
                    preferencesManager.getEditor().putString(UserPreferencesManager.EMAIL, email);
                    preferencesManager.getEditor().putString(UserPreferencesManager.PASSWORD, password);
                    preferencesManager.getEditor().putBoolean(UserPreferencesManager.FLAG, true);
                    preferencesManager.getEditor().apply();
                }

                // Check for registration status after a short delay
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (preferencesManager.isRegistered()) {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(LoginActivity.this, "Incorrect credentials or user not registered", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, 1000);
            }
        });
    }

    private void checkPrefs() {
        flag = preferencesManager.getLoginState();

        if (flag) {
            String email = preferencesManager.getEmail();
            String password = preferencesManager.getPassword();

            ed_email.setText(email);
            ed_password.setText(password);
            chkRememberPassword.setChecked(true);
        }
    }

    private void setupViews(){
        ed_email = findViewById(R.id.ed_email);
        ed_password = findViewById(R.id.ed_password);
        chkRememberPassword = findViewById(R.id.chkRememberPassword);
        Login = findViewById(R.id.btn_login);
    }
}
