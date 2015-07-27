package game.inventaire;

import java.util.ArrayList;


/*
 * utilit� de la classe sac reside dans la limitation de la taille i.e lors de l'utilisation de la 
 * methode add on doit checked si le sac n'est pas remplit.
 */
public class Sac<T> {
	private ArrayList<T> sac; //ArrayList ou Tableau ? apres avoir teste je ne pense pouvoir utiliser de tableau A verifier
	private int taille;
	
	public Sac(int taille) {
		this.taille = taille;
		this.sac = new ArrayList<T>(this.taille);
	}
	
	/**
	 * @return true si l'element a ete ajouter, false si le sac est deja plein
	 */
	public boolean add(T t){
		if(this.sac.size() >= this.taille){
			return false;
		}else{
			this.sac.add(t);
			return true;
		}
	}
	
	public ArrayList<T> getSac() {
		return this.sac;
	}
	
}
