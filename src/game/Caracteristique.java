package game;

public class Caracteristique {
	//voir pour un tableau a 2 dimension pour la Vie, la Mana, la Vigeur 
	private int pointDeVieMax;
	private int pointDeAntimatiereMax;
	private int pointDEnduranceMax;
	private int pointDeVieActuel;
	private int pointDeAntimatiereActuel; //pour lancer des competences speciales( genre tir puissant, double tir, ...), utiliter a debattre
	private int pointDEnduranceActuel; //pour Sprinter et peut-etre pour d'autres competences(coup puissant ?). Devra peut-�tre renommee en pointDEndurance
	private int niveau;
	private int experienceActuelle;
	
	//voir pour un tableau de int pour les attributs suivant
	
	private int force; //pour les armes de CaC
	private int dexterite; // pour les armes a distance
	private int vitalite; // influence les degat subit et/ou les pvMax et les pvgMax
	private int astuce; //pour le craft peut-etre
	private int intelligence; //pour les craft plus technologique ex : arme a plasma
	private int vitesse;//deplacement et vitesse attaque
	
	public Caracteristique(int pointDeVieMax, int pointDeAntimatiereMax, int pointDEnduranceMax, int pointDeVieActuel, int pointDeAntimatiereActuel, int pointDEnduranceActuel, int niveau, int experienceActuelle, int force, int dexterite, int vitalite, int astuce, int intelligence, int vitesse) {
		this.pointDeVieMax = pointDeVieMax;
		this.pointDeAntimatiereMax = pointDeAntimatiereMax;
		this.pointDEnduranceMax = pointDEnduranceMax;
		this.pointDeVieActuel = pointDeVieActuel;
		this.pointDeAntimatiereActuel = pointDeAntimatiereActuel;
		this.pointDEnduranceActuel = pointDEnduranceActuel;
		this.niveau = niveau;
		this.experienceActuelle = experienceActuelle;
		this.force = force;
		this.dexterite = dexterite;
		this.vitalite = vitalite;
		this.astuce = astuce;
		this.intelligence = intelligence;
		this.vitesse = vitesse;
	}

	public Caracteristique(int force, int dexterite, int vitalite, int astuce, int intelligence, int vitesse) {
		this.pointDeVieMax = 100;
		this.pointDeAntimatiereMax = 100;
		this.pointDEnduranceMax = 100;
		this.pointDeVieActuel = 100;
		this.pointDeAntimatiereActuel = 100;
		this.pointDEnduranceActuel = 100;
		this.niveau = 1;
		this.experienceActuelle = 0;
		this.force = force;
		this.dexterite = dexterite;
		this.vitalite = vitalite;
		this.astuce = astuce;
		this.intelligence = intelligence;
		this.vitesse = vitesse;
	}

	public void augmentationNiveau(int pointDeVie, int pointDeMana, int pointDeVigueur, int pointAttribut){
		this.niveau = this.niveau + 1;
		this.experienceActuelle = 0;
		this.pointDeVieMax = this.pointDeVieMax + pointDeVie;
		this.pointDeAntimatiereMax = this.pointDeAntimatiereMax + pointDeMana;
		this.pointDEnduranceMax = this.pointDEnduranceMax + pointDeVigueur;
		this.pointDeVieActuel = this.pointDeVieActuel + pointDeVie;
		this.pointDeAntimatiereActuel = this.pointDeAntimatiereActuel + pointDeMana;
		this.pointDEnduranceActuel = this.pointDEnduranceActuel +pointDeVigueur;
		
	}

	public int getPointDeVieMax() {
		return this.pointDeVieMax;
	}

	public int getPointDeAntimatiereMax() {
		return this.pointDeAntimatiereMax;
	}

	public int getPointDEnduranceMax() {
		return this.pointDEnduranceMax;
	}

	public int getPointDeVieActuel() {
		return this.pointDeVieActuel;
	}

	public int getPointDeAntimatiereActuel() {
		return this.pointDeAntimatiereActuel;
	}

	public int getPointDEnduranceActuel() {
		return this.pointDEnduranceActuel;
	}

	public int getNiveau() {
		return this.niveau;
	}

	public int getExperienceActuelle() {
		return this.experienceActuelle;
	}

	public int getForce() {
		return this.force;
	}

	public int getDexterite() {
		return this.dexterite;
	}

	public int getVitalite() {
		return this.vitalite;
	}

	public int getAstuce() {
		return this.astuce;
	}

	public int getIntelligence() {
		return this.intelligence;
	}

	public int getVitesse() {
		return this.vitesse;
	}

	
}
