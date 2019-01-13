package puzzle13;

enum Card {Nord, Est, Sud, Ouest};
enum StateInter {First, Second, Third};

public class Cart implements Comparable<Cart>{
	public int id;
	public int cordx;
	public int cordy;
	public Card orientation;
	public StateInter state;
	public boolean crashable;
	
	public Cart(int id, int cordx, int cordy, Card orientation) {
		this.id = id;
		this.cordx = cordx;
		this.cordy = cordy;
		this.orientation = orientation;
		this.state = StateInter.First;
		this.crashable = true;
	}

	@Override
	public int compareTo(Cart c) {//Tri des carts, haut-gauche vers bas-droite
		if(this.cordy > c.cordy) {
			return 1;
		}else if(this.cordy < c.cordy) {
			return -1;
		}else{
			if(this.cordx > c.cordx) {
				return 1;
			}else if(this.cordx < c.cordx){
				return -1;
			}else{
				return 0;
			}
		}
	}
	
	
}
