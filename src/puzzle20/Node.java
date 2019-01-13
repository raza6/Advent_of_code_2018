package puzzle20;

public class Node implements Comparable<Node>{
	public String id;
	public Node northN;
	public Node eastN;
	public Node southN;
	public Node westN;
	public boolean marked;
	public int dist;
	
	
	public Node(int id) {
		this.id = id + "";
		northN = null;
		eastN = null;
		southN = null;
		westN = null;
		marked = false;
		dist = Integer.MAX_VALUE;
	}	
	
	public Node() {
		northN = null;
		eastN = null;
		southN = null;
		westN = null;
		marked = false;
		dist = Integer.MAX_VALUE;
	}	
	
	public String parcoursDFSMark() {
		String res = this.id + " " + this.dist + " ";
		this.marked = true;
		if(northN != null && !northN.marked) {
			res += northN.parcoursDFSMark();
		}
		if(eastN != null && !eastN.marked) {
			res += eastN.parcoursDFSMark();
		}
		if(southN != null && !southN.marked) {
			res += southN.parcoursDFSMark();
		}
		if(westN != null && !westN.marked) {
			res += westN.parcoursDFSMark();
		}
		return res;
	}

	@Override
	public int compareTo(Node n) {//Tri des node, haut-gauche vers bas-droite
		return 0;
	}
}
