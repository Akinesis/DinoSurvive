package game;

import game.inventaire.Inventaire;


//peut-�tre une Class Entiter dont EtreVivant et Item heriterai point a debattre
public abstract class EtreVivant {
	private String nom; //certainement inutile;
	private Inventaire inventaire;
	private Caracteristique caracteristique; //contient les pv, pvMax, pm, pmMax, pvg, pvgMax

	public EtreVivant(String nom, Inventaire inventaire, Caracteristique caracteristique) {
		this.caracteristique = caracteristique;
		this.nom = nom;
		this.inventaire = inventaire;
	}

	public String getNom() {
		return this.nom;
	}

	public Inventaire getInventaire() {
		return this.inventaire;
	}

	public Caracteristique getStatsistique() {
		return this.caracteristique;
	}
	
	
	
}
