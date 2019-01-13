package puzzle15;

import java.util.ArrayList;
import java.util.List;

public class Node implements Comparable<Node>{
	public int id;
	public char value;
	public Coordinate coor;
	public List<Node> linkedTo;
	public boolean marked;
	public int dist;
	public Fighter fighter;
	
	public Node(int id) {
		this.id = id;
		linkedTo = new ArrayList<Node>();
		marked = false;
		dist = Integer.MAX_VALUE;
	}
	
	public Node(int id, char value, int coorx, int coory) {
		this.id = id;
		this.value = value;
		this.coor = new Coordinate(coorx, coory);
		linkedTo = new ArrayList<Node>();
		marked = false;
		dist = Integer.MAX_VALUE;
	}
	
	public void addNode(Node nd) {//Double lien
		linkedTo.add(nd);
		nd.linkedTo.add(this);
	}
	
	public String parcoursDFSListe(List<Node> markedNode) {
		String res = this.id + " ";
		markedNode.add(this);
		for(Node nd : linkedTo) {
			if(!markedNode.contains(nd)) {
				res += nd.parcoursDFSListe(markedNode);
			}
		}
		return res;
	}
	
	public String parcoursDFSMark() {
		String res = this.id + " ";
		this.marked = true;
		for(Node nd : linkedTo) {
			if(!nd.marked) {
				res += nd.parcoursDFSMark();
			}
		}
		return res;
	}

	@Override
	public int compareTo(Node n) {//Tri des node, haut-gauche vers bas-droite
		if(this.coor.y > n.coor.y) {
			return 1;
		}else if(this.coor.y < n.coor.y) {
			return -1;
		}else{
			if(this.coor.x > n.coor.x) {
				return 1;
			}else if(this.coor.x < n.coor.x){
				return -1;
			}else{
				return 0;
			}
		}
	}
}
