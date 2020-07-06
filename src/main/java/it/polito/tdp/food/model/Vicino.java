package it.polito.tdp.food.model;

public class Vicino {
	
	String vicino;
	double peso;
	public String getVicino() {
		return vicino;
	}
	public void setVicino(String vicino) {
		this.vicino = vicino;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	public Vicino(String vicino, double peso) {
		super();
		this.vicino = vicino;
		this.peso = peso;
	}
	
	

}
