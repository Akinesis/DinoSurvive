package game;

public abstract class Entiter {
	private Caracteristique caracteristique; //contient les pv, pvMax, pm, pmMax, pvg, pvgMax
	private String nom; //certainement inutile;
	
	public Entiter(Caracteristique caracteristique, String nom) {
		this.caracteristique = caracteristique;
		this.nom = nom;
	}

	public String getNom() {
		return this.nom;
	}

	public Caracteristique getStatsistique() {
		return this.caracteristique;
	}

}
