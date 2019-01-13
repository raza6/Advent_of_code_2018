package puzzle10;

public class Coordinate {
	public int pointx;
	public int pointy;
	public int velx;
	public int vely;
	
	public Coordinate(int pointx, int pointy, int velx, int vely) {
		super();
		this.pointx = pointx;
		this.pointy = pointy;
		this.velx = velx;
		this.vely = vely;
	}

	@Override
	public String toString() {
		return "Coordinate [pointx=" + pointx + ", pointy=" + pointy + ", velx=" + velx + ", vely=" + vely + "]";
	}
	
	public Coordinate update() {
		pointx += velx;
		pointy += vely;
		return this;
	}
	
	public Coordinate update(int nb) {
		pointx += nb*velx;
		pointy += nb*vely;
		return this;
	}

}
