package game.item;

/**
 * 
 * Les identifiant seront sous la forme suivante XXXX, les items en 10 serons des armes ex : 1000 epee longue, 10BZ fusil à plasma 
 * en 20 les armures, en 30 les consommable, en 40 les cubes et en 50 les outils
 */

public abstract class Item {
	private String identifiant; 
	private String Nom;
		
	public Item(String identifiant, String nom) {
		this.identifiant = identifiant;
		this.Nom = nom;
	}
	
	public String getIdentifiant() {
		return identifiant;
	}
	public String getNom() {
		return Nom;
	}

}
