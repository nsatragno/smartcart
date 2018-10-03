package ar.com.smartcart.smartcart;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.net.URI;

public class ConfigurarConexionActivity extends Activity implements View.OnClickListener {

    TextView texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar_conexion);

        Button boton = findViewById(R.id.configurar_conexion_button);
        boton.setOnClickListener(this);

        texto = findViewById(R.id.url_servidor);
        texto.setText(getSharedPreferences("smartcart", Context.MODE_PRIVATE).getString("URL_SERVIDOR", ""));
    }

    @Override
    public void onClick(View v) {
        String url = texto.getText().toString();
        SharedPreferences.Editor edit = getSharedPreferences("smartcart", Context.MODE_PRIVATE).edit();
        Log.d("smartcart", "Guardando nueva URL para servidor: " + url);
        edit.putString("URL_SERVIDOR", url);
        edit.commit();
        Intent myIntent = new Intent(ConfigurarConexionActivity.this, LoginActivity.class);
        startActivity(myIntent);
    }
}