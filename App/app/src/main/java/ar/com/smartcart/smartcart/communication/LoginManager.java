package ar.com.smartcart.smartcart.communication;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URLEncoder;

import ar.com.smartcart.smartcart.modelo.LoginResponse;

public class LoginManager {
    private final static String URL_LOGIN =
            "http://192.168.1.109:3000/usuarios_app/login?email={email}&password={pass}";

    public static boolean login(String email, String password) throws IOException {
        String response = HTTPHelper.request(
                URL_LOGIN.replace("{email}", URLEncoder.encode(email))
                        .replace("{pass}", URLEncoder.encode(password)));
        Log.d("Smartcart", response);
        LoginResponse login = new Gson().fromJson(response, LoginResponse.class);
        return login.ok;
    }
}
