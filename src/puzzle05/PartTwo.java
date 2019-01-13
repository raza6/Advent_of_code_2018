package puzzle05;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class PartTwo {
	public static void main(String[] args) throws IOException {

		File file = new File("C:\\Users\\Antonin Gontier\\Documents\\Workspaaaace\\AdventOfCode_2018\\src\\puzzle05\\input.txt");
		
		BufferedReader br = new BufferedReader(new FileReader(file));//Stockage du string
		String sequence = br.readLine();
		br.close();
	
		int[] lengthTab = new int[26];
		String seqTemp = "";
		
		char min, max;
		
		for(int i = 97; i < 123; i++) {
			min = (char) i;
			max = Character.toUpperCase(min);
			
			seqTemp = sequence.replaceAll(Character.toString(min), "").replaceAll(Character.toString(max), "");			
					
			boolean isTotallyReduced;//On réduit la séquence après avoir éliminé une lettre
			do{
				isTotallyReduced = true;
				for(int j = 0; j<seqTemp.length()-1; j++){
					if(Math.abs((int) seqTemp.charAt(j) - (int) seqTemp.charAt(j+1)) == 32) {//Même lettre mais cap diff 
						isTotallyReduced = false;
						seqTemp = seqTemp.replaceAll(Character.toString(seqTemp.charAt(j))+Character.toString(seqTemp.charAt(j+1)),"");
						//seqTemp = seqTemp.replaceAll(Character.toString(seqTemp.charAt(j+1))+Character.toString(seqTemp.charAt(j)),"");
					}
				}
			}while(!isTotallyReduced);
			
			lengthTab[i-97] = seqTemp.length();
		}
		
		System.out.println(Arrays.stream(lengthTab).min().orElse(0));
		
//		int minln = Integer.MAX_VALUE;
//		for(int i = 0; i<lengthTab.length; i++) {
//			if(minln>lengthTab[i]) {
//				minln = lengthTab[i];
//			}
//			System.out.print(lengthTab[i] + "|");
//		}
//		System.out.println(minln);
	}
}
