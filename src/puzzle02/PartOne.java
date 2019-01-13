package puzzle02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PartOne {
	public static void main(String[] args) throws IOException{
		File file = new File("src/puzzle02/input.txt");

		BufferedReader br = new BufferedReader(new FileReader(file));
		
		String actualBox;
		int nbP = 0, nbT = 0;
		while((actualBox = br.readLine()) != null) {
			List<Character> actualCharSet = new ArrayList<Character>();
			for(int i = 0; i<actualBox.length(); i++) {//On stocke les lettres uniques du mot
				if(!actualCharSet.contains(actualBox.charAt(i))) {
					actualCharSet.add(actualBox.charAt(i));
				}
			}
			boolean containsP = false, containsT = false;
			for(char c : actualCharSet) {//On cherche les paires et les triples dans le mot parmi la liste
				int occ = (int) actualBox.chars().filter(ch -> ch ==c).count();
				if (occ == 2) {
					containsP = true;
				}else if(occ == 3) {
					containsT = true;		
				}
				if(containsP && containsT) {
					break;
				}
			}
			
			nbP += (containsP) ? 1:0;//On incrémente les compteurs
			nbT += (containsT) ? 1:0;
		}
		System.out.println(nbP*nbT);
		br.close();
	}
}
