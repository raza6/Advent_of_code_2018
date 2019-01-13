package puzzle05;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class PartOne {
	public static void main(String[] args) throws IOException {
				
		File file = new File("C:\\Users\\Antonin Gontier\\Documents\\Workspaaaace\\AdventOfCode_2018\\src\\puzzle05\\input.txt");
		
		BufferedReader br = new BufferedReader(new FileReader(file));//Stockage du string
		String sequence = br.readLine();
		br.close();
		
		boolean isTotallyReduced;
		do{
			isTotallyReduced = true;
			for(int i = 0; i<sequence.length()-1; i++){
				if(Math.abs((int) sequence.charAt(i) - (int) sequence.charAt(i+1)) == 32) {//Même lettre mais cap diff 
					isTotallyReduced = false;
					sequence = sequence.replaceAll(Character.toString(sequence.charAt(i))+Character.toString(sequence.charAt(i+1)),"");
					//sequence = sequence.substring(0, i) + sequence.substring(i+2);
				}
			}
		}while(!isTotallyReduced);//Sequence minimale
		
		System.out.println(sequence.length());
	}
}
