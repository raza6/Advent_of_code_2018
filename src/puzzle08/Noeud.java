package puzzle08;

import java.util.Arrays;

public class Noeud {
	public int headerChild;
	public int headerMeta;
	public Noeud[] child;
	public int[] meta;
	public String res;//Reste du string input
	
	public Noeud(String data) {
		String[] dataSplited = data.split(" ", 3);//hChild, hMeta, String input
		int hC = Integer.parseInt(dataSplited[0]);
		int hM = Integer.parseInt(dataSplited[1]);
		this.headerChild = hC;
		this.headerMeta = hM;
		this.child = new Noeud[headerChild];
		this.meta = new int[headerMeta];		
		
		if(hC == 0) {//Cas feuille
			this.res = this.setMeta(dataSplited[2]);//Les metadata sont juste derrière, la suite du string input est stocké
		}else {
			String resTemp = this.setChild(dataSplited[2]);//On construit les enfants, puis on récupère le string input
			this.res = this.setMeta(resTemp);//On construit les metadatas, la suite du string input est stocké 
		}
	}
	
	public String setMeta(String st){
		String[] metaSplited = st.split(" ", this.headerMeta+1);
		for(int i = 0; i<this.headerMeta; i++) {
			this.meta[i] = Integer.parseInt(metaSplited[i]);
		}
		if(metaSplited.length >= this.headerMeta+1) {
			return metaSplited[this.headerMeta];//On retourne la suite du string input
		}else{//Fin du string input (root node)
			return "nope";
		}
	}
	
	public String setChild(String st){
		String stNoeud = st;
		for(int i = 0; i<this.headerChild; i++) {//Construction des noeuds enfant
			this.child[i] = new Noeud(stNoeud);//On passe le string input en param du constructeur
			stNoeud = this.child[i].res;//On récupère le reste du string input
		}
		return stNoeud;
	}
	
	public int sumMeta() {
		int res = 0;
		for(int i = 0; i<this.headerChild;i++) {
			res += this.child[i].sumMeta();
		}
		if(this.headerMeta != 0) {
			res += Arrays.stream(this.meta).sum();
		}
		return res;
	}
	
	public int sumAlpha() {
		int res = 0;
		if(this.headerChild == 0) {//Cas feuille
			res = this.sumMeta();
		}else{//Cas noeud
			for(int k : this.meta) {
				if(k <= this.child.length) {//l'index k correspond à un noeud enfant
					res += this.child[k-1].sumAlpha();
				}
			}
		}
		return res;
	}
}
