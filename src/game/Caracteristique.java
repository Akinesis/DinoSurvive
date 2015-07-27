package game;

public abstract class Caracteristique {
	protected int pointDeVieMax;
	protected int pointDeVieActuel;
	
	public Caracteristique(int pointDeVieMax, int pointDeVieActuel) {
		this.pointDeVieMax = pointDeVieMax;
		this.pointDeVieActuel = pointDeVieActuel;
	}
	
	
}
