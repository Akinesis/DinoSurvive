package game;

import game.inventaire.Inventaire;


//peut-�tre une Class Entiter dont EtreVivant et Item heriterai point a debattre
public abstract class EtreVivant extends Entiter {
	private Inventaire inventaire;
	private Equipement equipement;
	
	public EtreVivant(String nom, Inventaire inventaire, Caracteristique caracteristique) {
		super(caracteristique, nom);
		this.inventaire = inventaire;
		this.equipement = null;
	}

	public Inventaire getInventaire() {
		return this.inventaire;
	}

	public void attaquer(Entiter cible){
		
	}
	
}
