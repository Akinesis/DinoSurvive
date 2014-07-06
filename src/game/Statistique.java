package game;

public class Statistique {
	//voir pour un tableau a 2 dimension pour la Vie, la Mana, la Vigeur 
	private int pointDeVieMax;
	private int pointDeManaMax;
	private int pointDeVigueurMax;
	private int pointDeVieActuel;
	private int pointDeManaActuel; //pour lancer des competences speciales, utiliter a debattre
	private int pointDeVigueurActuel; //pour Sprinter et peut-etre pour d'autres competences. Devra peut-être renommee en pointDEndurance
	private int niveau;
	private int experienceActuelle;
	//voir pour un tableau de int pour les attributs suivant
	private int force; //pour les armes de CaC
	private int dexterite; // pour les armes a distance
	private int vitalite; // influence les degat subit et/ou les pvMax et les pvgMax
	private int astuce; //pour le craft peut-etre
	private int intelligence; //pour les craft plus technologique ex : arme a plasma
	
	public Statistique(int pointDeVieMax, int pointDeManaMax, int pointDeVigueurMax, int pointDeVieActuel, int pointDeManaActuel, int pointDeVigueurActuel, int niveau, int experienceActuelle, int force, int dexterite, int vitalite, int astuce, int intelligence) {
		this.pointDeVieMax = pointDeVieMax;
		this.pointDeManaMax = pointDeManaMax;
		this.pointDeVigueurMax = pointDeVigueurMax;
		this.pointDeVieActuel = pointDeVieActuel;
		this.pointDeManaActuel = pointDeManaActuel;
		this.pointDeVigueurActuel = pointDeVigueurActuel;
		this.niveau = niveau;
		this.experienceActuelle = experienceActuelle;
		this.force = force;
		this.dexterite = dexterite;
		this.vitalite = vitalite;
		this.astuce = astuce;
		this.intelligence = intelligence;
	}

	public Statistique(int force, int dexterite, int vitalite, int astuce, int intelligence) {
		this.pointDeVieMax = 100;
		this.pointDeManaMax = 100;
		this.pointDeVigueurMax = 100;
		this.pointDeVieActuel = this.pointDeVieMax;
		this.pointDeManaActuel = this.pointDeManaMax;
		this.pointDeVigueurActuel = this.pointDeVigueurMax;
		this.niveau = 1;
		this.experienceActuelle = 0;
		this.force = force;
		this.dexterite = dexterite;
		this.vitalite = vitalite;
		this.astuce = astuce;
		this.intelligence = intelligence;
	}

	public void augmentationNiveau(int pointDeVie, int pointDeMana, int pointDeVigueur, int pointAttribut){
		this.niveau = this.niveau + 1;
		this.experienceActuelle = 0;
		this.pointDeVieMax = this.pointDeVieMax + pointDeVie;
		this.pointDeManaMax = this.pointDeManaMax + pointDeMana;
		this.pointDeVigueurMax = this.pointDeVigueurMax + pointDeVigueur;
		this.pointDeVieActuel = this.pointDeVieActuel + pointDeVie;
		this.pointDeManaActuel = this.pointDeManaActuel + pointDeMana;
		this.pointDeVigueurActuel = this.pointDeVigueurActuel +pointDeVigueur;
		
	}

	public int getPointDeVieActuel() {
		return pointDeVieActuel;
	}

	public int getPointDeManaActuel() {
		return pointDeManaActuel;
	}

	public int getPointDeVigueurActuel() {
		return pointDeVigueurActuel;
	}
	
	public int getPointDeVieMax() {
		return pointDeVieMax;
	}

	public int getPointDeManaMax() {
		return pointDeManaMax;
	}

	public int getPointDeVigueurMax() {
		return pointDeVigueurMax;
	}

	public int getNiveau() {
		return niveau;
	}

	public int getExperienceActuelle() {
		return experienceActuelle;
	}
	
	public int getForce() {
		return force;
	}

	public int getDexterite() {
		return dexterite;
	}

	public int getVitalite() {
		return vitalite;
	}

	public int getAstuce() {
		return astuce;
	}

	public int getIntelligence() {
		return intelligence;
	}


}
