package puzzle23;

public class Nano {
	public Coordinate coor;
	public long radius;
	
	public Nano(long x, long y, long z, long radius) {
		this.coor = new Coordinate(x,y,z);
		this.radius = radius;
	}
}
