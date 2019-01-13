package puzzle15;

enum FType {Goblin, Elf};

public class Fighter implements Comparable<Fighter>{
	public int id;
	public Coordinate coor;
	public FType ftype;
	public int hitPoint;
	public int attackPower;
	
	public Fighter(int id, int coordx, int coordy, FType ftype) {
		this.id = id;
		this.coor = new Coordinate(coordx, coordy);
		this.ftype = ftype;
		this.hitPoint = 200;
		this.attackPower = 3;
	}
	
	public Fighter(int id, int coordx, int coordy, FType ftype, int power) {
		this.id = id;
		this.coor = new Coordinate(coordx, coordy);
		this.ftype = ftype;
		this.hitPoint = 200;
		this.attackPower = power;
	}
	
	@Override
	public int compareTo(Fighter f) {//Tri des fighters, haut-gauche vers bas-droite
		if(this.coor.y > f.coor.y) {
			return 1;
		}else if(this.coor.y < f.coor.y) {
			return -1;
		}else{
			if(this.coor.x > f.coor.x) {
				return 1;
			}else if(this.coor.x < f.coor.x){
				return -1;
			}else{
				return 0;
			}
		}
	}
}
