package game.item;

/**
 * Les identifiant seront sous la forme suivante IXXX, les items en 0XXX serons des armes ex : 0000 epee longue, 00BZ fusil � plasma 
 * en 1XXX les armures, en 2XXX les consommable, en 3XXX les cubes et en 4XXX les outils.
 * Le second caractere XIXX represente le Tiers ex 0000 epee longue T1, 0300 epee longue T4. 
 * Le troisieme caractere XXIX represente le type ex 0000 epee longue T1, 0010 epee courte T1.
 * Le quatrieme caractere XXXI represente "enchantement" ex 0001 epee longue de feu T1, 000W epee longue a plasma, OOOZ epee longue a petit chaton tout fluffy.
 */

public abstract class Item {
	protected String identifiant; 
	protected String nom;
	protected SousType sousType;
	protected int stack;
	protected int durabiliteMax;
	protected int durabiliteActuelle;
	protected float coordonnéesTextureX; 
	protected float coordonnéesTextureY; 
	
	public Item(String identifiant, String nom, SousType sousType, int stack, float coordonnéesTextureX, float coordonnéesTextureY, int durabiliteMax) {
		this.identifiant = identifiant.toLowerCase();
		this.stack = stack;
		this.sousType = sousType;
		this.nom = nom;
		this.durabiliteMax = durabiliteMax;
		this.durabiliteActuelle = this.durabiliteMax;
	}
	
	public Item(String id){
		identifiant = id;
	}
	
	
	
	public String getIdentifiant() {
		return this.identifiant;
	}
	public String getNom() {
		return this.nom;
	}

	public SousType getSousType() {
		return sousType;
	}

	public float getCoordonnéesTextureX() {
		return coordonnéesTextureX;
	}

	public float getCoordonnéesTextureY() {
		return coordonnéesTextureY;
	}

}
