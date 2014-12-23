package modeles;


public class Noeud implements Comparable<Noeud>{
	private int key_;
	private int bal_;
	private Noeud gauche_, droit_;
	private Chunk chunk;

	public Noeud(Chunk ch) {
		chunk = ch;
		key_ = chunk.getId();
		bal_ = 0;
		gauche_ = null;
		droit_ = null;
	}

	public int getBal() {
		return bal_;
	}

	public boolean isKey(int key) {
		return key_ == key;
	}

	public int getKey() {
		return key_;
	}

	public Noeud getDroit() {
		return droit_;
	}

	public Noeud getGauche() {
		return gauche_;
	}

	public void majBal(int bal) {
		bal_ += bal;
	}

	public void setGauche(Noeud gauche) {
		gauche_ = gauche;
	}

	public void setDroit(Noeud droit) {
		droit_ = droit;
	}

	public void setBal(int bal) {
		bal_ = bal;
	}

	public Chunk getChunk() {
		// TODO Auto-generated method stub
		return chunk;
	}

	@Override
	public int compareTo(Noeud noeud) {

		if(key_ < noeud.getKey()){
			return -1;
		}else if(key_ > noeud.getKey()){
			return +1;
		}
		return 0;
	}
}
