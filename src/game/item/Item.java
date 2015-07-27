package game.item;

/**
 * Les identifiant seront sous la forme suivante IXXX, les items en 0XXX serons des armes ex : 0000 epee longue, 00BZ fusil � plasma 
 * en 1XXX les armures, en 2XXX les consommable, en 3XXX les cubes et en 4XXX les outils.
 * Le second caractere XIXX represente le Tiers ex 0000 epee longue T1, 0300 epee longue T4. 
 * Le troisieme caractere XXIX represente le type ex 0000 epee longue T1, 0010 epee courte T1.
 * Le quatrieme caractere XXXI represente "enchantement" ex 0001 epee longue de feu T1, 000W epee longue a plasma, OOOZ epee longue a petit chaton tout fluffy.
 */

public abstract class Item {
	protected int identifiant; 
	protected String nom;
		
	public Item(int identifiant, String nom) {
		this.identifiant = identifiant;
		this.nom = nom;
	}
	
	public int getIdentifiant() {
		return this.identifiant;
	}
	public String getNom() {
		return this.nom;
	}

}
