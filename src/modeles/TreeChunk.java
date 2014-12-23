package modeles;


public class TreeChunk {
	private Noeud racine_;

	public TreeChunk(){
		racine_ = null;
	}

	public Chunk findChunk(Chunk ck){
		return rechercheRec(ck, racine_);
	}

	private Chunk rechercheRec(Chunk ck, Noeud noeud) {
		if(noeud != null) {
			int compare = ck.getId() - noeud.getKey();
			if(compare == 0){
				return noeud.getChunk();
			}else if(compare<0){
				return rechercheRec(ck, noeud.getGauche());
			}else {
				return rechercheRec(ck, noeud.getDroit());
			}
		}
		return null;
	}

	public void parcourPref(ChunkManager cm){
		parcourPrefRec(cm, racine_);
	}

	private void parcourPrefRec(ChunkManager cm, Noeud noeud){
		if(noeud != null) {
			cm.checkRenderTree(noeud.getChunk());

			if(noeud.getDroit()!=null){
				parcourPrefRec(cm, noeud.getDroit());
			}

			if(noeud.getGauche() != null){
				parcourPrefRec(cm, noeud.getGauche());
			}
		}
	}

	public Noeud ajouter(Chunk ck, Noeud noeud) {
		if(noeud == null) {
			noeud = new Noeud(ck);
			System.out.println(ck.getX() +" "+ ck.getY() +" "+ ck.getZ() );
		}
		else if(!noeud.isKey(ck.getId())){
			// si key > Noeud.key, ajouter à droite
			//int compare = ck.getId() - noeud.getKey();

			if(ck.getId() > noeud.getKey()) {
				noeud.setDroit(ajouter(ck, noeud.getDroit()));
				noeud.majBal(1);
			}
			else {
				noeud.setGauche(ajouter(ck, noeud.getGauche()));
				noeud.majBal(-1);
			}

			if(noeud.getBal()==-2 || noeud.getBal()==2){
				noeud = equilibrer(noeud);
			}
		}
		return noeud;
	}

	private Noeud equilibrer(Noeud noeud) {
		if(noeud.getBal() == 2) {
			if(noeud.getDroit().getBal() >= 0) {
				return RotG(noeud);
			}
			else {
				noeud.setDroit(RotD(noeud.getDroit()));
				return RotG(noeud);
			}
		}
		else if(noeud.getBal() == -2) {
			if(noeud.getGauche().getBal() <= 0) {
				return RotD(noeud);
			}
			else {
				noeud.setGauche(RotG(noeud.getGauche()));
				return RotD(noeud);
			}
		}
		else
			return noeud;
	}

	private Noeud RotD(Noeud noeud) {
		Noeud noeudTemp = noeud.getGauche();
		int a,b;

		a = noeud.getBal();
		b = noeudTemp.getBal();
		noeud.setGauche(noeudTemp.getDroit());
		noeudTemp.setDroit(noeud);
		noeud.setBal(a-Math.min(b, 0)+1);
		noeudTemp.setBal(Math.max(Math.max(a-2, a+b-2), b+1));
		return noeudTemp;
	}

	private Noeud RotG(Noeud noeud) {
		Noeud noeudTemp = noeud.getDroit();
		int a,b;

		a = noeud.getBal();
		b = noeudTemp.getBal();
		noeud.setDroit(noeudTemp.getGauche());
		noeudTemp.setGauche(noeud);
		noeud.setBal(a-Math.max(b, 0)-1);
		noeudTemp.setBal(Math.min(Math.min(a-2, a+b-2), b-1));
		return noeudTemp;
	}

	public Noeud getRacine() {
		// TODO Auto-generated method stub
		return racine_;
	}
	
	public void setRacine(Noeud noeud){
		racine_ = noeud;
	}
}
