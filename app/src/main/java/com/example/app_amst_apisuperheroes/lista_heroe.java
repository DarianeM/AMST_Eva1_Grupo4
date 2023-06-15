package com.example.app_amst_apisuperheroes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class lista_heroe extends AppCompatActivity {

    private RequestQueue mQueue;
    ListView listViewHeroes;
    TextView textViewNumero;
    String nombreHeroe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_heroe);

        mQueue = Volley.newRequestQueue(this);

        listViewHeroes = findViewById(R.id.idListViewHeroes);
        List<String> dataList = obtenerDatos(); // Reemplaza "obtenerDatos()" con tu lista de strings
        CustomAdapter adapter = new CustomAdapter(this, dataList);
        listViewHeroes.setAdapter(adapter);

        listViewHeroes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Obtener el elemento seleccionado
                String elementoSeleccionado = (String) parent.getItemAtPosition(position);

                // Iniciar la nueva actividad, pasando el elemento seleccionado si es necesario
                Intent intent = new Intent(lista_heroe.this, DatosHeroe.class);
                intent.putExtra("elemento", elementoSeleccionado);
                startActivity(intent);
            }
        });
    }

    private List<String> obtenerDatos(){
        List<String> listaHeroes = new ArrayList<>();
        nombreHeroe = getIntent().getStringExtra("heroe");



        // Obtener el nombre
        // Buscar en el servidor en el caso de que si
        // https://superheroapi.com/api/3960965630796560/search/name...
        String url_heroe = "https://superheroapi.com/api/3960965630796560/search/" + nombreHeroe;
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET, url_heroe, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response);
                        try {
                            //listaHeroes = response.getJSONArray("name");
                            response = response.toJSONArray();
                            if (response != null) {
                                for (int i=0;i<response.length();i++){
                                    listaHeroes.add(response.getString(i));
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }
        };
        ;

        mQueue.add(request);

        //textViewNumero.setText("Resultado: "+listaHeroes.size());

        return listaHeroes;
    }
}