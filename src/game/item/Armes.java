package game.item;

public class Armes extends Item {
	private int materiaux;
	private int degat;
	private double vitesseAttaque;
	private AlterationArme alteration;
	
	
	public Armes(String identifiant, String nom, SousType sousType, int stack, float coordonnéesTextureX, float coordonnéesTextureY, int durabiliteMax, int materiaux, int degat, double vitesseAttaque, AlterationArme alterationArme) {
		super(identifiant, nom, sousType, stack, coordonnéesTextureX, coordonnéesTextureX, durabiliteMax);
		this.materiaux = materiaux;
		this.degat = degat;
		this.vitesseAttaque = vitesseAttaque;
		this.alteration = alterationArme;
	}

	

}
