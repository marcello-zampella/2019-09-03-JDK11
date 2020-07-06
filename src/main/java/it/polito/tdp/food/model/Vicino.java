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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((vicino == null) ? 0 : vicino.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vicino other = (Vicino) obj;
		if (vicino == null) {
			if (other.vicino != null)
				return false;
		} else if (!vicino.equals(other.vicino))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Vicino [vicino=" + vicino + ", peso=" + peso + "]";
	}
	
	

}
