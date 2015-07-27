package game;

import game.item.Armes;
import game.item.Armures;
import game.item.Outils;

public class Equipement {
	private Armures casque;
	private Armures torse;
	private Armures jambiere;
	private Armures mainDroite;
	private Armures mainGauche;
	private Outils outil;
	private Armes unPlanUn;
	private Armes unPlanDeux;
	
	public Equipement(Armures casque, Armures torse, Armures jambiere, Armures mainDroite, Armures mainGauche,
			Outils outil, Armes unPlanUn, Armes unPlanDeux) {
		this.casque = casque;
		this.torse = torse;
		this.jambiere = jambiere;
		this.mainDroite = mainDroite;
		this.mainGauche = mainGauche;
		this.outil = outil;
		this.unPlanUn = unPlanUn;
		this.unPlanDeux = unPlanDeux;
	}

	public Armures getCasque() {
		return casque;
	}

	public Armures getTorse() {
		return torse;
	}

	public Armures getJambiere() {
		return jambiere;
	}

	public Armures getMainDroite() {
		return mainDroite;
	}

	public Armures getMainGauche() {
		return mainGauche;
	}

	public Outils getOutil() {
		return outil;
	}

	public Armes getUnPlanUn() {
		return unPlanUn;
	}

	public Armes getUnPlanDeux() {
		return unPlanDeux;
	}
	
	
}
