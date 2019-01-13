package puzzle13;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PartTwo {
	public static void main(String[] args) throws IOException {
		
		/*INITIALISATION*/
		
		File file = new File("src/puzzle13/input.txt");
//		File file = new File("src/puzzle13/retest.txt");


		BufferedReader br = new BufferedReader(new FileReader(file));
		String lineRead;

		int ix = 0;
		int jy = 0;
		char[][] gridCart = new char[150][150];//150,150, 13,6
		List<Cart> cartList = new ArrayList<Cart>();
		int nbCart = 0;

		while((lineRead = br.readLine()) != null) {//On genere la Liste des carts
			for(ix = 0; ix<gridCart.length; ix++) {
//				System.out.println(lineRead.length());
//				System.out.println(lineRead.charAt(ix) + " " + jy + " " + ix);
				if(lineRead.charAt(ix) == '^') {
					cartList.add(new Cart(nbCart,ix,jy,Card.Nord));
					nbCart++;
				}else if(lineRead.charAt(ix) == '>') {
					cartList.add(new Cart(nbCart,ix,jy,Card.Est));
					nbCart++;
				}else if(lineRead.charAt(ix) == 'v') {
					cartList.add(new Cart(nbCart,ix,jy,Card.Sud));
					nbCart++;
				}else if(lineRead.charAt(ix) == '<') {
					cartList.add(new Cart(nbCart,ix,jy,Card.Ouest));
					nbCart++;
				}
			}
			jy++;
		}
		br.close();

		file = new File("src/puzzle13/input2.txt");
//		file = new File("src/puzzle13/retest2.txt");
		br = new BufferedReader(new FileReader(file));

		jy = 0;
		while((lineRead = br.readLine()) != null) {//On genere la grille des chemins
			for(ix = 0; ix<gridCart.length; ix++) {
				gridCart[ix][jy] = lineRead.charAt(ix);
			}
			jy++;
		}
		br.close();

		/*PARCOURS*/
		
		while(cartList.size() > 1) {//On itere jusqu'à ce qu'il ne reste plus qu'un cart
			List<Cart> tormCart = new ArrayList<Cart>();
			
			for(Cart c : cartList) {
				if(c.orientation == Card.Nord) {//Nouvelle position ^
					c.cordy--;
					if(gridCart[c.cordx][c.cordy] == '/') {//Nouvelle orientation, Cas courbe
						c.orientation = Card.Est;
					}else if(gridCart[c.cordx][c.cordy] == '\\') {
						c.orientation = Card.Ouest;
					}else if(gridCart[c.cordx][c.cordy] == '+') {//Nouvelle orientation, Cas intersection
						if(c.state == StateInter.First) {
							c.orientation = Card.Ouest;
							c.state = StateInter.Second;
						}else if(c.state == StateInter.Second) {
							c.state = StateInter.Third;
						}else if(c.state == StateInter.Third) {
							c.orientation = Card.Est;
							c.state = StateInter.First;
						}
					}
				}else if(c.orientation == Card.Est) {//Nouvelle position >
					c.cordx++;
					if(gridCart[c.cordx][c.cordy] == '/') {
						c.orientation = Card.Nord;
					}else if(gridCart[c.cordx][c.cordy] == '\\') {
						c.orientation = Card.Sud;
					}else if(gridCart[c.cordx][c.cordy] == '+') {
						if(c.state == StateInter.First) {
							c.orientation = Card.Nord;
							c.state = StateInter.Second;
						}else if(c.state == StateInter.Second) {
							c.state = StateInter.Third;
						}else if(c.state == StateInter.Third) {
							c.orientation = Card.Sud;
							c.state = StateInter.First;
						}
					}
				}else if(c.orientation == Card.Sud) {//Nouvelle position v
					c.cordy++;
					if(gridCart[c.cordx][c.cordy] == '/') {
						c.orientation = Card.Ouest;
					}else if(gridCart[c.cordx][c.cordy] == '\\') {
						c.orientation = Card.Est;
					}else if(gridCart[c.cordx][c.cordy] == '+') {
						if(c.state == StateInter.First) {
							c.orientation = Card.Est;
							c.state = StateInter.Second;
						}else if(c.state == StateInter.Second) {
							c.state = StateInter.Third;
						}else if(c.state == StateInter.Third) {
							c.orientation = Card.Ouest;
							c.state = StateInter.First;
						}
					}
				}else if(c.orientation == Card.Ouest) {//Nouvelle position <
					c.cordx--;
					if(gridCart[c.cordx][c.cordy] == '/') {
						c.orientation = Card.Sud;
					}else if(gridCart[c.cordx][c.cordy] == '\\') {
						c.orientation = Card.Nord;
					}else if(gridCart[c.cordx][c.cordy] == '+') {
						if(c.state == StateInter.First) {
							c.orientation = Card.Sud;
							c.state = StateInter.Second;
						}else if(c.state == StateInter.Second) {
							c.state = StateInter.Third;
						}else if(c.state == StateInter.Third) {
							c.orientation = Card.Nord;
							c.state = StateInter.First;
						}
					}
				}
				
//				System.out.println(c.cordx + " " + c.cordy + " " + c.orientation + " " + c.id);
				
				for(Cart c2 : cartList) {//Les carts avancent un par un, si celui ci crash avec un de ses camarades, on les retire
					if(c != c2 && c.cordx == c2.cordx && c.cordy == c2.cordy && c2.crashable) {
//						System.out.println(c.cordx + "," + c.cordy);
						c.crashable = false;//On retire les carts crashés aux yeux des autres carts
						c2.crashable = false;
						tormCart.add(c);//On ajoute les carts crashés à la liste des cartes à supprimer
						tormCart.add(c2);	
					}
				}
			}
			
			for(Cart c : tormCart) {//On retire les carts crashés
				cartList.remove(c);
			}
//			System.out.println(cartList);
			Collections.sort(cartList);//On trie les carts, le plus haut à gauche est prioritaire pour la prochaine iteration
		}
		System.out.println(cartList.get(0).cordx + "," + cartList.get(0).cordy);
	}
}
