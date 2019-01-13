package puzzle15;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class PartOne {

	public static void main(String[] args) throws IOException {
		File file = new File("src/puzzle15/input.txt");
//		File file = new File("src/puzzle15/test.txt");

		BufferedReader br = new BufferedReader(new FileReader(file));
		String lineRead;

		/*****Generation du battleField*****/
		
		Node[][] battleField = new Node[32][32];//Creation des nodes
//		Node[][] battleField = new Node[7][7];
		List<Fighter> fighters = new ArrayList<Fighter>();
		int jy = 0;
		int nbFighter = 0;

		while((lineRead = br.readLine()) != null) {//Generation du champ de bataille, chaque case est une node avec potentiellement un fighter
			for(int ix = 0; ix<lineRead.length(); ix++) {
				battleField[ix][jy] = new Node(ix*battleField.length + jy,lineRead.charAt(ix),ix,jy);
				if(lineRead.charAt(ix) == 'E') {//Cas d'une case Elf
					battleField[ix][jy].fighter = new Fighter(nbFighter, ix, jy, FType.Elf);
					fighters.add(battleField[ix][jy].fighter);
					nbFighter++;
				}else if(lineRead.charAt(ix) == 'G'){//Cas d'une case Goblin
					battleField[ix][jy].fighter = new Fighter(nbFighter, ix, jy, FType.Goblin);
					fighters.add(battleField[ix][jy].fighter);
					nbFighter++;
				}else {//Cas sans fighter
					battleField[ix][jy].fighter = null;
				}
			}
			jy++;
		}
		br.close();

		for(int i = 0; i<battleField.length; i++) {//Creations des liens entre nodes
			for(int j = 0; j<battleField.length; j++) {
				if(battleField[i][j].value != '#') {
					if(battleField[i-1][j].value != '#' && !battleField[i][j].linkedTo.contains(battleField[i-1][j])) {
						battleField[i][j].addNode(battleField[i-1][j]);
					}
					if(battleField[i][j-1].value != '#' && !battleField[i][j].linkedTo.contains(battleField[i][j-1])) {
						battleField[i][j].addNode(battleField[i][j-1]);
					}
					if(battleField[i][j+1].value != '#' && !battleField[i][j].linkedTo.contains(battleField[i][j+1])) {
						battleField[i][j].addNode(battleField[i][j+1]);
					}
					if(battleField[i+1][j].value != '#' && !battleField[i][j].linkedTo.contains(battleField[i+1][j])) {
						battleField[i][j].addNode(battleField[i+1][j]);
					}
				}
			}
		}
		
		//System.out.println(fighters.get(13).ftype);
		//System.out.println(battleField[15][4].parcoursDFSMark());
		
		/*****Gestion du jeu*****/
		
		int nbTurn = 0;
		boolean enemyStillExist = true;
		int sumHP = 0;
		
		while(enemyStillExist) {//On itere tant que les deux camps existent
			List<Fighter> tormFighters = new ArrayList<Fighter>();
			sumHP = 0;
			enemyStillExist = true;
			
			for(Fighter fg : fighters) {//On determine l'action de tout les fighters un par un
				boolean canAct = false;
				
				if(fg.hitPoint > 0) {//On ne prend que les fighters vivants
					
					Fighter enemy;
					if((enemy = canAttack(fg, battleField)) == null){//S'il ne peut attaquer, il essaye de bouger
						
						//Determine les cases de destination
						List<Node> enemyTile = new ArrayList<Node>();
						for(Fighter fgen : fighters) {
							if(fgen.ftype != fg.ftype && fgen.hitPoint > 0) {//Parmi tout les autres fighters vivants on choisit les ennemis
								canAct = true;//Le fighter peut bouger, même s'il ne va pas forcément le faire
								if(battleField[fgen.coor.x-1][fgen.coor.y].value == '.') {
									enemyTile.add(battleField[fgen.coor.x-1][fgen.coor.y]);
								}
								if(battleField[fgen.coor.x][fgen.coor.y-1].value == '.') {
									enemyTile.add(battleField[fgen.coor.x][fgen.coor.y-1]);
								}
								if(battleField[fgen.coor.x][fgen.coor.y+1].value == '.') {
									enemyTile.add(battleField[fgen.coor.x][fgen.coor.y+1]);
								}
								if(battleField[fgen.coor.x+1][fgen.coor.y].value == '.') {
									enemyTile.add(battleField[fgen.coor.x+1][fgen.coor.y]);
								}
							}
						}

						//A la recherche de la case avec la plus petite distance
						Map<Node,Travel> bestNodes = new TreeMap<Node,Travel>();//Node dest
						
						for(Node nden : enemyTile) {
							for(Node[] bftab : battleField) {//ReInitialisation du battleField
								for(Node bf : bftab) {
									bf.marked = false;
									bf.dist = Integer.MAX_VALUE;
								}
							}
							pathDijkstra(nden);//dijkstra sur toutes ces cases pour trouver la case de destination
							
							//Contruction de la map avec pour chaque case de destination la case de depart préférentielle et la distance minimale associé
							bestNodes.put(nden, new Travel(nden, null, Integer.MAX_VALUE));
							
							if(bestNodes.get(nden).dist > battleField[fg.coor.x][fg.coor.y+1].dist) {
								bestNodes.replace(nden, new Travel(nden, battleField[fg.coor.x][fg.coor.y+1],battleField[fg.coor.x][fg.coor.y+1].dist));
							}
							if(bestNodes.get(nden).dist >= battleField[fg.coor.x+1][fg.coor.y].dist) {
								bestNodes.replace(nden, new Travel(nden, battleField[fg.coor.x+1][fg.coor.y], battleField[fg.coor.x+1][fg.coor.y].dist));
							}
							if(bestNodes.get(nden).dist >= battleField[fg.coor.x-1][fg.coor.y].dist) {
								bestNodes.replace(nden, new Travel(nden, battleField[fg.coor.x-1][fg.coor.y], battleField[fg.coor.x-1][fg.coor.y].dist));
							}						
							if(bestNodes.get(nden).dist >= battleField[fg.coor.x][fg.coor.y-1].dist) {
								bestNodes.replace(nden, new Travel(nden, battleField[fg.coor.x][fg.coor.y-1],battleField[fg.coor.x][fg.coor.y-1].dist));
							}
						}
						Set<Entry<Node, Travel>> bnSet = bestNodes.entrySet();
						int bestDist = Integer.MAX_VALUE-1;
						Node bestNode = null;
//						for(Entry<Node,Integer> ent : bnSet) {
//							System.out.println(ent.getKey().coor.x + " " + ent.getKey().coor.y + " : " + ent.getValue());
//						}
						for(Entry<Node,Travel> ent : bnSet) {//On choisi la case avec la plus petite distance ET la case de destination préférentielle
							if(ent.getValue().dist < bestDist) {
								bestDist = ent.getValue().dist;
								bestNode = ent.getValue().dep;
							}
						}//Si une case est impossible a atteindre -> bestDist = MAXINT-1 et bestNode = null

						//On déplace le fighter sur cette case de destination
						if(bestNode != null) {//Une case a été trouvé
							battleField[fg.coor.x][fg.coor.y].value = '.';
							battleField[fg.coor.x][fg.coor.y].fighter = null;
							fg.coor.x = bestNode.coor.x;
							fg.coor.y = bestNode.coor.y;
							battleField[fg.coor.x][fg.coor.y].fighter = fg;
							if(fg.ftype == FType.Elf) {
								battleField[fg.coor.x][fg.coor.y].value = 'E';
							}else {
								battleField[fg.coor.x][fg.coor.y].value = 'G';
							}
						}
					}
					if((enemy = canAttack(fg, battleField)) != null) {//Le fighter peut attaquer, on fournit l'ennemi
						canAct = true;
//						System.out.println("Fight " + fg.ftype + " : " + enemy.x + " " + enemy.y);
						enemy.hitPoint -= fg.attackPower;
						if(enemy.hitPoint <= 0) {//L'ennemi meurt
							tormFighters.add(enemy);
							battleField[enemy.coor.x][enemy.coor.y].value = '.';
							battleField[enemy.coor.x][enemy.coor.y].fighter = null;
						}
					}
					if(!canAct) {//Si ce fighter ne peux agir, le camp ennemi n'existe plus, on sort des iterations
						enemyStillExist = false;
						break;
					}
				}
			}
			nbTurn++;
			
			for(Fighter tormfg : tormFighters) {//On supprime les fighters morts
				fighters.remove(tormfg);
			}			
			
			Collections.sort(fighters);//On reordonne les fighters selon l'ordre préférentiel
			
			for(Fighter fg : fighters) {
				sumHP += fg.hitPoint;
				System.out.print(fg.hitPoint + " ");
			}
			
			/*Debug*/
			
			System.out.println(nbTurn);
			for(int i = 0; i<battleField.length;i++) {
				for(int j = 0; j<battleField.length;j++) {
					System.out.print(battleField[j][i].value);
				}
				System.out.print("\n");
			}
		}
		
		System.out.println((nbTurn-1)*sumHP);
		
		/*****Debug*****/
		
//		for(int i = 0; i<battleField.length; i++) {
//			for(int j = 0; j<battleField.length; j++) {
//				System.out.print(battleField[i][j].value);
//			}
//			System.out.print("\n");
//		}
	}

	private static Fighter canAttack(Fighter fg, Node[][] bf) {//Choix et détection de l'ennemi
		Map<Fighter,Integer> enemies = new TreeMap<Fighter,Integer>();//Map des ennemis potentiels avec les HPs associés
		if(fg.ftype == FType.Elf) {//Cas elf
			if(bf[fg.coor.x][fg.coor.y-1].value == 'G') {//En haut
				enemies.put(bf[fg.coor.x][fg.coor.y-1].fighter, bf[fg.coor.x][fg.coor.y-1].fighter.hitPoint);
			}
			if(bf[fg.coor.x+1][fg.coor.y].value == 'G') {//A gauche
				enemies.put(bf[fg.coor.x+1][fg.coor.y].fighter,bf[fg.coor.x+1][fg.coor.y].fighter.hitPoint);
			}
			if(bf[fg.coor.x-1][fg.coor.y].value == 'G') {//A droite
				enemies.put(bf[fg.coor.x-1][fg.coor.y].fighter,bf[fg.coor.x-1][fg.coor.y].fighter.hitPoint);
			}
			if(bf[fg.coor.x][fg.coor.y+1].value == 'G') {//En bas
				enemies.put(bf[fg.coor.x][fg.coor.y+1].fighter,bf[fg.coor.x][fg.coor.y+1].fighter.hitPoint);
			}
		}else if(fg.ftype == FType.Goblin){//Cas goblin
			if(bf[fg.coor.x][fg.coor.y-1].value == 'E') {//En haut
				enemies.put(bf[fg.coor.x][fg.coor.y-1].fighter, bf[fg.coor.x][fg.coor.y-1].fighter.hitPoint);
			}
			if(bf[fg.coor.x+1][fg.coor.y].value == 'E') {//A gauche
				enemies.put(bf[fg.coor.x+1][fg.coor.y].fighter,bf[fg.coor.x+1][fg.coor.y].fighter.hitPoint);
			}
			if(bf[fg.coor.x-1][fg.coor.y].value == 'E') {//A droite
				enemies.put(bf[fg.coor.x-1][fg.coor.y].fighter,bf[fg.coor.x-1][fg.coor.y].fighter.hitPoint);
			}
			if(bf[fg.coor.x][fg.coor.y+1].value == 'E') {//En bas
				enemies.put(bf[fg.coor.x][fg.coor.y+1].fighter,bf[fg.coor.x][fg.coor.y+1].fighter.hitPoint);
			}
		}
		Set<Entry<Fighter, Integer>> enSet = enemies.entrySet();
		int bestHP = Integer.MAX_VALUE;
		Fighter bestFighter = null; 
		for(Entry<Fighter, Integer> ent : enSet) {
//			System.out.println(fg.ftype + " " + fg.coor.x + " " + fg.coor.y + " target " + ent.getKey().ftype + " " + ent.getKey().coor.x + " " + ent.getKey().coor.y +" : "+ ent.getValue());
			if(ent.getValue() < bestHP) {//On choisi le fighter avec le moins d'HP ET selon l'ordre préférentiel
				bestHP = ent.getValue();
				bestFighter = ent.getKey();
			}
		}
		return bestFighter;
	}
	
	public static void pathDijkstra(Node dep) {
		Deque<Node> dqnd = new ArrayDeque<Node>();
		dqnd.addLast(dep);
		dep.marked = true;
		dep.dist = 0;
		while(!dqnd.isEmpty()) {
			Node actualNode = dqnd.removeFirst();
//			System.out.println(actualNode.coor.x + " " + actualNode.coor.y + " : " + actualNode.dist);
			for(Node nd : actualNode.linkedTo) {
				if(!nd.marked && nd.value == '.') {
					dqnd.addLast(nd);
					nd.marked = true;
					nd.dist = actualNode.dist + 1;
				}
			}
		}
	}

}
