package puzzle09;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("unused")
public class PartOne {
	public static void main(String[] args) throws IOException {
		List<Integer> marbles = new ArrayList<Integer>();
		marbles.add(0);
		marbles.add(1);

		int nbPlayer = 479;//Input 1
		int actualPlayer = 0;
		long[] scores = new long[nbPlayer];
		
		int maxTurn = 71035;//Input 2 : Part 1 = 71035, Part 2 = 71035*100
		int nbTurn = 2;
		int currentIndex = 0;
		
//		File file = new File("src/puzzle09/test.txt");
//
//		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		
		while(nbTurn <= maxTurn) {
			if(nbTurn%10000 == 0) {
				System.out.println(nbTurn);
			}
			if(actualPlayer == nbPlayer) {//Gestion du joueur actuel
				actualPlayer = 0;
			}

			if(nbTurn%23 == 0) {//Cas des marbles multiple de 23		
				scores[actualPlayer] += nbTurn;
				if(currentIndex-7 < 0) {//Boucle nécessaire
					scores[actualPlayer] += marbles.remove((marbles.size()) + (currentIndex-7));
					currentIndex = (marbles.size()+1) + (currentIndex-7);
				}else{
					scores[actualPlayer] += marbles.remove(currentIndex - 7);
					currentIndex -= 7;
				}
			}else {//Cas classique
				if(currentIndex == marbles.size()-1) {//Dernier élément (fin du cercle)
					marbles.add(1, nbTurn);
					currentIndex = 1;
				}else{
					marbles.add(currentIndex+2, nbTurn);
					currentIndex += 2;
				}
			}
			nbTurn++;
			actualPlayer++;
		}
		
		System.out.println("Max : " + Arrays.stream(scores).map(x -> x).max().orElse(-1));
		
//		for(int k : scores) {
//			System.out.println(k);
//		}
//		
//		bw.close();
	}
}
