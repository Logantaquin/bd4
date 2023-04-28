package com.example.bd4_2022.modele;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.example.bd4_2022.utilitaire.EventAsync;


public abstract class  ViticulteurDAO implements EventAsync<Viticulteur> {
	private static final String serveur="etu.btssiobayonne.fr";
	private static final String chemin="/~pinheirol/android/";
	private Context contexte;
	private RequestQueue queue;

	public ViticulteurDAO(Context ctx){
		this.contexte=ctx;
		queue = Volley.newRequestQueue(this.contexte);
	}
	
	public void getViticulteurs(){
		String url = "http://"+serveur+chemin+"getViticulteurs.php";

		StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String reponse) {
						Log.d("logTest",reponse);
						onTacheTerminee(jsonStringToViticulteurArrayList(reponse));
					}
				},
				new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						// Prise en charge de l'erreur de reception
						Log.d("logTest","erreur de reception");
					}
				});
		queue.add(stringRequest);
	}
	
	public void getViticulteurByIdV(final Long idV){
		String url = "http://"+serveur+chemin+"getViticulteurByIdV.php";

		StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String reponse) {
						Log.d("logTest",reponse);
						Log.d("logTest","onPostExecute JureDAO2");
						onTacheTerminee(jsonStringToViticulteur(reponse));
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
				params.put("idV", idV.toString());
				return params;
			}
		};

		queue.add(stringRequest);
	}

	
	private ArrayList<Viticulteur> jsonStringToViticulteurArrayList(String jsonString){
		Log.d("logTest","conversion jsonToViticulteurArray : "+jsonString);

		ArrayList<Viticulteur> listeViticulteur = new ArrayList<Viticulteur>();
		long idV;
		String nomV;
			
		try {
			JSONArray tabJson = new JSONArray(jsonString);
			for(int i=0;i<tabJson.length();i++){
				idV = Long.parseLong(tabJson.getJSONObject(i).getString("idV"));
				nomV = tabJson.getJSONObject(i).getString("nomV");
				listeViticulteur.add(new Viticulteur(idV,nomV));
			}
		}
		catch (JSONException e){
			Log.d("logTest","pb decodage JSON");
		}
		return listeViticulteur;
	}
	
	private Viticulteur jsonStringToViticulteur(String jsonString){
		Log.d("logTest","conversion jsonToViticulteur : "+jsonString);
		
		Viticulteur unViticulteur=null;
		long idV;
		String nomV;
			
		try {
			JSONObject objJson = new JSONObject(jsonString);
			idV = Long.parseLong(objJson.getString("idV"));
			nomV = objJson.getString("nomV");
			unViticulteur = new Viticulteur(idV,nomV);
			
		}
		catch (JSONException e){
			Log.d("logTest","pb decodage JSON");
		}
		return unViticulteur;
	}
}
