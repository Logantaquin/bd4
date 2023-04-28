package com.example.bd4_2022.modele;

public class Jure {
	private long idJ;
	private String nomJ;
	private String prenomJ;
	
	public Jure(long idJ, String nomJ, String prenomJ) {
		this.idJ = idJ;
		this.nomJ = nomJ;
		this.prenomJ = prenomJ;
	}
	
	public Jure(String nomJ, String prenomJ) {
		this.idJ = -1;
		this.nomJ = nomJ;
		this.prenomJ = prenomJ;
	}
	
	public long getIdJ() {
		return idJ;
	}
	
	public void setIdJ(long idJ) {
		this.idJ = idJ;
	}
	
	public String getNomJ() {
		return nomJ;
	}
	
	public void setNomJ(String nomJ) {
		this.nomJ = nomJ;
	}

	public String getPrenomJ() {
		return prenomJ;
	}
	
	public void setPrenomJ(String prenomJ) {
		this.prenomJ = prenomJ;
	}

	public String toString(){
		return "idJ="+idJ+",nomJ="+nomJ+",prenomJ="+prenomJ;
	}
}
