package game.item;

public class ItemSimpleFactory {
	public Item itemMaker(String identifiant){
		Item item = null;
		String type = identifiant.substring(0, 0);
		if(type.compareTo("4") <= 0){
			System.out.println("ARME");
			//arme
			item = armeMaker(identifiant);
		}else if(type.compareTo("9") <= 0){
			System.out.println("ARMURE");
			//armure
			item = armureMaker(identifiant);
		}else if(type.compareTo("D") <= 0){
			System.out.println("CONSO");
			//consommables
			item = consommableMaker(identifiant);
		}else if(type.compareTo("P") <= 0){
			System.out.println("CRAFT");
			//craft
			item = craftMaker(identifiant);
		}else if(type.compareTo("R") <= 0){
			System.out.println("BLOC");
			//bloc
			item = blocMaker(identifiant);
		}else if(type.compareTo("Z") <= 0){
			System.out.println("JUNK");
			//junk
			item = junkMaker(identifiant);
		}
		return item;
	}

	private Armes armeMaker(String identifiant) {
		Armes arme;
		
		return null;
	}

	private Armures armureMaker(String identifiant) {
		// TODO Auto-generated method stub
		return null;
	}

	private Consommables consommableMaker(String identifiant) {
		// TODO Auto-generated method stub
		return null;
	}

	private Crafts craftMaker(String identifiant) {
		// TODO Auto-generated method stub
		return null;
	}

	private CubesInventaire blocMaker(String identifiant) {
		// TODO Auto-generated method stub
		return null;
	}

	private DiversItem junkMaker(String identifiant) {
		// TODO Auto-generated method stub
		return null;
	}

}
