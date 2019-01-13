package puzzle09;

import java.io.IOException;
import java.util.Arrays;

public class PartTwo {
	public static void main(String[] args) throws IOException {
		Maillon<Long> m1 = new Maillon<Long>(0L);
		Maillon<Long> m2 = new Maillon<Long>(1L);
		m1.pred = m2;
		m2.pred = m1;
		m1.succ = m2;
		m2.succ = m1;
		CircleList<Long> marbles = new CircleList<Long>(m1);

		int nbPlayer = 479;//Input 1
		int actualPlayer = 0;
		long[] scores = new long[nbPlayer];
		
		int maxTurn = 71035*100;//Input 2 : Part 1 = 71035, Part 2 = 71035*100
		long nbTurn = 2;
		
		long start = System.currentTimeMillis();
		
		while(nbTurn <= maxTurn) {
//			if(nbTurn%10000 == 0) {
//				System.out.println(nbTurn);
//			}
			
			if(actualPlayer == nbPlayer) {//Gestion du joueur actuel
				actualPlayer = 0;
			}
			
			if(nbTurn%23 == 0) {//Cas des marbles multiple de 23
				scores[actualPlayer] += nbTurn;//On ajoute le tour actuel
				marbles.current = marbles.current.pred.pred.pred.pred.pred.pred.pred;//On shift de -7
				scores[actualPlayer] += marbles.retirerMaillon();//On retire le maillon
			}else {//Cas classique
				marbles.current = marbles.current.succ;
				marbles.ajouterMaillon(new Maillon<Long>(nbTurn));
			}
			nbTurn++;
			actualPlayer++;
		}
		
		System.out.println("Max : " + Arrays.stream(scores).map(x -> x).max().orElse(-1));
		
		System.out.println("Exec : " + (System.currentTimeMillis() - start));
	}
}
