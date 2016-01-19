package info.androidhive.parsenotifications.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import info.androidhive.parsenotifications.R;
import info.androidhive.parsenotifications.app.AppConfig;
import info.androidhive.parsenotifications.helper.ParseUtils;
import info.androidhive.parsenotifications.helper.PrefManager;

public class LoginActivity extends ActionBarActivity implements View.OnClickListener {

    private EditText inputEmail;
    private EditText inputPassword;
    private Button btnLogin;
    private PrefManager pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Verifying parse configuration. This is method is for developers only.
        ParseUtils.verifyParseConfiguration(this);

        pref = new PrefManager(getApplicationContext());
        if (pref.isLoggedIn()) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);

            finish();
        }

        setContentView(R.layout.activity_login);

        inputEmail = (EditText) findViewById(R.id.email);
        //inputPassword = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(this);
    }


    private void login() {
        String email = inputEmail.getText().toString();
       // String pass  = inputPassword.getText().toString();

        if (isValidEmail(email)) {


            pref.createLoginSession(email);

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);

            startActivity(intent);

            finish();
        } else {
            Toast.makeText(getApplicationContext(), "Please enter valid email address!", Toast.LENGTH_LONG).show();
        }
        // Validation of the pattern
        //Pattern p= Pattern.compile("^[A-Za-z0-9]{4,20}$");
        //Match start of string as long as it is not followed by a space
        //Match 4 to 20 occurrences of either a space or a non space provided it is not prefixed by a space
        //Match end of string
        //Matcher m = p.matcher(pass);


    }

    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                login();
                break;
            default:
        }
    }
}
