package puzzle15;

public class Travel {
	public Node dest;
	public Node dep;
	public int dist;
	
	public Travel(Node dest, Node dep, int dist) {
		this.dest = dest;
		this.dep = dep;
		this.dist = dist;
	}
}
