package com.example.bd4_2022.modele;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bd4_2022.utilitaire.EventAsync;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class NoterDAO {
    private static final String serveur="etu.btssiobayonne.fr";
    private static final String chemin="/~pinheirol/android/";
    private Context contexte;
    private RequestQueue queue;

    public NoterDAO(Context ctx){
        this.contexte=ctx;
        queue = Volley.newRequestQueue(this.contexte);
    }

    public void addNoter(final Noter uneNote) {
        String url = "http://"+serveur+chemin+"addNoter.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String reponse) {
                        Log.d("logTest",reponse);
                        Log.d("logTest","onPostExecute NoterDAO2");
                        Log.d("logTest",uneNote.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Prise en charge de l'erreur de reception
                        Log.d("logTest","erreur de reception");
                    }
                })
        {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("idJ", uneNote.getIdJ()+"");
                params.put("idV", uneNote.getIdV()+"");
                params.put("note", uneNote.getNote()+"");
                return params;
            }
        };

        queue.add(stringRequest);
    }
}
