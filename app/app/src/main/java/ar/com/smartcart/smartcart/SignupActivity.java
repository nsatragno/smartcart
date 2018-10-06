package ar.com.smartcart.smartcart;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.text.Layout;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import ar.com.smartcart.smartcart.communication.LoginManager;
import ar.com.smartcart.smartcart.modelo.SignupResponse;

public class SignupActivity extends Activity {

    final int MIN_PASSWORD_LENGTH = 6;

    SignUpTask task;
    View signupForm;
    ProgressBar progressBar;
    TextView emailView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        signupForm = findViewById(R.id.signup_form);
        progressBar = findViewById(R.id.signup_progress);
        emailView = findViewById(R.id.email);

        findViewById(R.id.sign_up_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView nombreView = findViewById(R.id.nombre);
                TextView apellidoView = findViewById(R.id.apellido);
                TextView passwordView = findViewById(R.id.password);

                boolean cancel = false;
                if (nombreView.getText().toString().isEmpty()) {
                    nombreView.setError("El Nombre no puede estar vacío");
                    cancel = true;
                }
                if (apellidoView.getText().toString().isEmpty()) {
                    apellidoView.setError("El Apellido no puede estar vacío");
                    cancel = true;
                }
                if (passwordView.getText().toString().isEmpty()) {
                    passwordView.setError("El Password no puede estar vacío");
                    cancel = true;
                } else if (passwordView.getText().toString().length() < MIN_PASSWORD_LENGTH) {
                    passwordView.setError("El Password debe tener por lo menos " + MIN_PASSWORD_LENGTH + " caracteres");
                    cancel = true;
                }
                if (emailView.getText().toString().isEmpty()) {
                    emailView.setError("El Correo Electrónico no puede estar vacío");
                    cancel = true;
                }

                if (cancel) {
                    return;
                }
                task = new SignUpTask(emailView.getText().toString(), nombreView.getText().toString(),
                        apellidoView.getText().toString(), passwordView.getText().toString());
                task.execute();
                signupForm.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }

    private class SignUpTask extends AsyncTask<Void, Void, String> {
        private final String usuario;
        private final String nombre;
        private final String apellido;
        private final String password;

        public SignUpTask(String usuario, String nombre, String apellido, String password) {
            this.usuario = usuario;
            this.nombre = nombre;
            this.apellido = apellido;
            this.password = password;
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                SignupResponse signup = LoginManager.signup(usuario, nombre, apellido, password);
                if (signup.ok) {
                    return "ok";
                }
                return signup.mensaje;
            } catch (IOException e) {
                return "Error de Conexión. Por favor, revise la conexión e intente de nuevo.";
            }
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);

            signupForm.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);

            if (response.equals("ok")) {
                SharedPreferences.Editor edit = getSharedPreferences("smartcart", MODE_PRIVATE).edit();
                edit.putString("USUARIO", usuario);
                edit.putString("PASSWORD", password);
                edit.commit();

                Toast.makeText(getApplicationContext(), "Usuario creado con éxito", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
                return;
            }
            emailView.setError(response);
        }
    }
}
