package puzzle23;

public class Coordinate {
	public long x;
	public long y;
	public long z;
	
	public int nbNanoInRange;
	
	public Coordinate(long x, long y, long z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Coordinate(long x, long y, long z, int n) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.nbNanoInRange = n;
	}
}
