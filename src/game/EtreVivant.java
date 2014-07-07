package game;

import game.inventaire.Inventaire;


//peut-être une Class Entiter dont EtreVivant et Item heriterai point a debattre
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
		return this.nom;
	}

	public Inventaire getInventaire() {
		return this.inventaire;
	}

	public Statistique getStatsistique() {
		return this.statsistique;
	}

}