package ar.com.smartcart.smartcart.communication;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URLEncoder;

import ar.com.smartcart.smartcart.modelo.LoginResponse;

public class LoginManager {
    private final static String SERVICIO_LOGIN = "usuarios_app/login?email={email}&password={pass}";

    public static Boolean login(String email, String password) throws IOException {
        String response = HTTPHelper.request(SERVICIO_LOGIN.replace("{email}", URLEncoder.encode(email))
                        .replace("{pass}", URLEncoder.encode(password)), HTTPHelper.METODO_POST);
        Log.d("Login:", response);
        LoginResponse loginOK = new Gson().fromJson(response, LoginResponse.class);
//        return loginOK.ok;
        return Boolean.TRUE;
    }
}