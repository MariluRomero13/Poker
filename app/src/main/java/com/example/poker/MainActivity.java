package com.example.poker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Integer numero = 0, total = 0;
    ImageView imagen;
    TextView txnumero;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnenviar).setOnClickListener(this);
        findViewById(R.id.btnreiniciar).setOnClickListener(this);
        imagen = findViewById(R.id.imagen);
        txnumero = findViewById(R.id.txnumero);
    }

    @Override
    public void onClick(View v) {
        JsonObjectRequest jar = new JsonObjectRequest(Request.Method.GET, "http://nuevo.rnrsiilge-org.mx/baraja/numero", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try
                        {
                            Log.d("numero", response.toString());
                            switch (response.getInt("numero"))
                            {
                                case 1:
                                    imagen.setImageResource(R.drawable.uno);
                                    break;
                                case 2:
                                    imagen.setImageResource(R.drawable.dos);
                                    break;
                                case 3:
                                    imagen.setImageResource(R.drawable.tres);
                                    break;
                                case 4:
                                    imagen.setImageResource(R.drawable.cuatro);
                                    break;
                                case 5:
                                    imagen.setImageResource(R.drawable.cinco);
                                    break;
                                case 6:
                                    imagen.setImageResource(R.drawable.seis);
                                    break;
                                case 7:
                                    imagen.setImageResource(R.drawable.siete);
                                    break;
                                case 8:
                                    imagen.setImageResource(R.drawable.ocho);
                                    break;
                                case 9:
                                    imagen.setImageResource(R.drawable.nueve);
                                    break;
                                case 10:
                                    imagen.setImageResource(R.drawable.diez);
                                    break;
                                case 11:
                                    imagen.setImageResource(R.drawable.once);
                                    break;
                                case 12:
                                    imagen.setImageResource(R.drawable.doce);
                                    break;
                                case 13:
                                    imagen.setImageResource(R.drawable.trece);
                                    break;
                            }

                            total = total + response.getInt("numero");
                            txnumero.setText(total.toString());


                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                }

                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInstance(MainActivity.this).getRequestQueue().add(jar);

        switch (v.getId())
        {
            case R.id.btnreiniciar:
                numero = 0;
                total = 0;
                imagen.setImageResource(R.drawable.portada);
                txnumero.setText("Total");
                break;

            case R.id.btnenviar:
                JSONObject jsonparams = new JSONObject();
                try {
                    jsonparams.put("nombre", "Marilú");
                    jsonparams.put("numero", total);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest jor = new JsonObjectRequest(Request.Method.POST, "http://nuevo.rnrsiilge-org.mx/baraja/enviar", jsonparams,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try
                                {
                                    Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                                    numero = 0;
                                    total = 0;
                                    imagen.setImageResource(R.drawable.portada);
                                    txnumero.setText("Total");
                                }
                                catch(Exception e)
                                {
                                    e.printStackTrace();
                                }
                            }
                        }

                        , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                VolleySingleton.getInstance(MainActivity.this).getRequestQueue().add(jor);
                break;

        }
    }
}
