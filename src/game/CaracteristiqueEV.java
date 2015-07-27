package game;

public class CaracteristiqueEV extends Caracteristique {
	//voir pour un tableau a 2 dimension pour la Vie, la Mana, la Vigeur 
	private int pointDEnduranceMax;
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
	
	public CaracteristiqueEV(int pointDeVieMax, int pointDEnduranceMax, int pointDeVieActuel, int pointDEnduranceActuel, int niveau, int experienceActuelle, int force, int dexterite, int vitalite, int astuce, int intelligence, int vitesse) {
		super(pointDeVieMax, pointDeVieActuel);
		this.pointDEnduranceMax = pointDEnduranceMax;
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

	public CaracteristiqueEV(int force, int dexterite, int vitalite, int astuce, int intelligence, int vitesse) {
		super(100, 100);
		this.pointDEnduranceMax = 100;
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

	public void augmentationNiveau(int pointDeVie, int pointDeVigueur, int pointAttribut){
		this.niveau = this.niveau + 1;
		this.experienceActuelle = 0;
		this.pointDeVieMax = this.pointDeVieMax + pointDeVie;
		this.pointDEnduranceMax = this.pointDEnduranceMax + pointDeVigueur;
		this.pointDeVieActuel = this.pointDeVieActuel + pointDeVie;
		this.pointDEnduranceActuel = this.pointDEnduranceActuel +pointDeVigueur;
		
	}

	public int getPointDeVieMax() {
		return this.pointDeVieMax;
	}

	public int getPointDEnduranceMax() {
		return this.pointDEnduranceMax;
	}

	public int getPointDeVieActuel() {
		return this.pointDeVieActuel;
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
