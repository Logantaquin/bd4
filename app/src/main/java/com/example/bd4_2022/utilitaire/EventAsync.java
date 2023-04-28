package com.example.bd4_2022.utilitaire;

import com.example.bd4_2022.modele.Jure;

import java.util.ArrayList;

// Force la classe qui l'implemente a avoir les methodes indiquees ci-dessous
// Cette methode sera appelee lorsque la tache asynchrone sera terminee
public interface EventAsync<T> {
	public void onTacheTerminee(String resultat);
	public void onTacheTerminee(ArrayList<T> resultat);
	public void onTacheTerminee(T resultat);

}
