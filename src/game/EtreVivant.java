package game;

import game.inventaire.Inventaire;


//peut-être fusionne avec Item en une Class Entiter point a debattre
public abstract class EtreVivant {
	private String nom; //certainement inutile;
	private Inventaire inventaire;
	private Statistique statsistique; //contient les pv, pvMax, pm, pmMax, pvg, pvgMax

	public EtreVivant(String nom, Inventaire inventaire, Statistique statsistique) {
		this.statsistique = statsistique;
		this.nom = nom;
		this.inventaire = inventaire;
	}

	public String getNom() {
		return nom;
	}

	public Inventaire getInventaire() {
		return inventaire;
	}

	public Statistique getStatsistique() {
		return statsistique;
	}
	
	
	
}
