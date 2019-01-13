package puzzle22;

public class Node{
	public Coordinate coor;
	public char type;
	public Node northN;
	public Node eastN;
	public Node southN;
	public Node westN;
	public boolean marked;
	public int dist;
	
	
	public Node(int x, int y, char type) {
		this.coor = new Coordinate(x,y);
		this.type = type;
		northN = null;
		eastN = null;
		southN = null;
		westN = null;
		marked = false;
		dist = 0;
	}
	
	public String parcoursDFSMark() {
		String res = this.coor.x + " "+ this.coor.y + " : " + this.dist + " ";
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
}
