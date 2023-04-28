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


public abstract class JureDAO implements EventAsync<Jure> {
	private static final String serveur="etu.btssiobayonne.fr";
	private static final String chemin="/~pinheirol/android/";
	private Context contexte;
	private RequestQueue queue;
	
	public JureDAO(Context ctx){
		this.contexte=ctx;
		queue = Volley.newRequestQueue(this.contexte);
	}
		
	public void getJures(){
		String url = "http://"+serveur+chemin+"getJures.php";
		StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String reponse) {
						Log.d("logTest",reponse);
						onTacheTerminee(jsonStringToJureArrayList(reponse));
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
	
	public void getJureByIdJ(final Long idJ){
		String url = "http://"+serveur+chemin+"getJureByIdJ.php";

		StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String reponse) {
						Log.d("logTest",reponse);
						Log.d("logTest","onPostExecute JureDAO2");
						onTacheTerminee(jsonStringToJure(reponse));
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
				params.put("idJ", idJ.toString());
				return params;
			}
		};

		queue.add(stringRequest);

	}

	
	private ArrayList<Jure> jsonStringToJureArrayList(String jsonString){
		Log.d("logTest","conversion jsonToJureArray : "+jsonString);

		ArrayList<Jure> listeJure = new ArrayList<Jure>();
		long idJ;
		String nomJ;
		String prenomJ;
			
		try {
			JSONArray tabJson = new JSONArray(jsonString);
			for(int i=0;i<tabJson.length();i++){
				idJ = Long.parseLong(tabJson.getJSONObject(i).getString("idJ"));
				nomJ = tabJson.getJSONObject(i).getString("nomJ");
				prenomJ = tabJson.getJSONObject(i).getString("prenomJ");
				listeJure.add(new Jure(idJ,nomJ,prenomJ));
			}
		}
		catch (JSONException e){
			Log.d("logTest","pb decodage JSON");
		}
		return listeJure;
	}
	
	private Jure jsonStringToJure(String jsonString){
		Log.d("logTest","conversion jsonToJure : "+jsonString);
		
		Jure unJure=null;
		long idJ;
		String nomJ;
		String prenomJ;
			
		try {
			JSONObject objJson = new JSONObject(jsonString);
			idJ = Long.parseLong(objJson.getString("idJ"));
			nomJ = objJson.getString("nomJ");
			prenomJ = objJson.getString("prenomJ");
			unJure = new Jure(idJ,nomJ,prenomJ);
			
		}
		catch (JSONException e){
			Log.d("logTest","pb decodage JSON");
		}
		return unJure;
	}
}
