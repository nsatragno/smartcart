package ar.com.smartcart.smartcart.communication;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URLEncoder;

import ar.com.smartcart.smartcart.modelo.LoginResponse;
import ar.com.smartcart.smartcart.modelo.SignupResponse;

public class LoginManager {
    private final static String SERVICIO_LOGIN = "usuarios_app/login?email={email}&password={pass}";
    private final static String SERVICIO_SIGNUP = "usuarios_app/signup";

    public static Boolean login(String email, String password) throws IOException {
        String response = HTTPHelper.request(SERVICIO_LOGIN.replace("{email}", URLEncoder.encode(email))
                        .replace("{pass}", URLEncoder.encode(password)), HTTPHelper.METODO_POST);
        Log.d("smartcart", "Login: " + response);
        LoginResponse loginOK = new Gson().fromJson(response, LoginResponse.class);
        return loginOK.ok;
    }

    public static SignupResponse signup(String usuario, String nombre, String apellido, String password) throws IOException {
        SignupRequest request = new SignupRequest(usuario, nombre, apellido, password);
        String response = HTTPHelper.request(SERVICIO_SIGNUP, HTTPHelper.METODO_POST, new Gson().toJson(request));
        Log.d("smartcart", "SignUp: " + response);
        return new Gson().fromJson(response, SignupResponse.class);
    }

    private static class SignupRequest {
        private final UsuarioApp usuario;

        SignupRequest(String usuario, String nombre, String apellido, String password) {
            this.usuario = new UsuarioApp(usuario, nombre, apellido, password);
        }
    }

    private static class UsuarioApp {
        private final String email;
        private final String nombre;
        private final String apellido;
        private final String password;

        UsuarioApp(String email, String nombre, String apellido, String password) {
            this.email = email;
            this.nombre = nombre;
            this.apellido = apellido;
            this.password = password;
        }
    }
}