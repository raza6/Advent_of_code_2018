package puzzle18;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class PartOneAndTwo {

	public static void main(String[] args) throws IOException {
		File file = new File("src/puzzle18/input.txt");
//		File file = new File("src/puzzle18/test.txt");

		BufferedReader br = new BufferedReader(new FileReader(file));
		String lineRead;
		
		/*Génération du field*/
		
		char[][] field = new char[52][52];
//		char[][] field = new char[12][12];
		
		for(char[] ftab : field) {//Introduction d'un offset sur les bords du field
			Arrays.fill(ftab,' ');
		}
		
		int ix = 0;
		while((lineRead = br.readLine()) != null) {
			for(int jy = 0; jy<lineRead.length();jy++) {
				field[ix+1][jy+1] = lineRead.charAt(jy);
			}
			ix++;
		}
		br.close();
		
		/*Gestion de la simulation*/
		
		int nbTurn = 0;
		char[][] nfield = new char[field.length][field.length];
		while(nbTurn < 10) {//PART ONE : 10, PART TWO : 1000
			
			//Copie du field
			for(int ic = 0; ic<field.length; ic++) {
				for(int jc = 0; jc<field.length; jc++) {
					nfield[ic][jc] = field[ic][jc];
				}
			}
			
			//Centre du field
			for(int i = 1; i<field[0].length-1; i++) {
				for(int j = 1; j<field.length-1; j++) {
					
					if(field[i][j] == '.') {//Cas open
						int nbTrees = 0;
						nbTrees += (field[i-1][j-1] == '|') ? 1:0;
						nbTrees += (field[i-1][j] == '|') ? 1:0;
						nbTrees += (field[i-1][j+1] == '|') ? 1:0;
						nbTrees += (field[i][j+1] == '|') ? 1:0;
						nbTrees += (field[i+1][j+1] == '|') ? 1:0;
						nbTrees += (field[i+1][j] == '|') ? 1:0;
						nbTrees += (field[i+1][j-1] == '|') ? 1:0;
						nbTrees += (field[i][j-1] == '|') ? 1:0;
						if(nbTrees >= 3) {
							nfield[i][j] = '|';
						}
//						System.out.println(field[i][j] + " -> " + nfield[i][j]);
					}
					
					if(field[i][j] == '|') {//Cas trees
						int nbLumberyard = 0;
						nbLumberyard += (field[i-1][j-1] == '#') ? 1:0;
						nbLumberyard += (field[i-1][j] == '#') ? 1:0;
						nbLumberyard += (field[i-1][j+1] == '#') ? 1:0;
						nbLumberyard += (field[i][j+1] == '#') ? 1:0;
						nbLumberyard += (field[i+1][j+1] == '#') ? 1:0;
						nbLumberyard += (field[i+1][j] == '#') ? 1:0;
						nbLumberyard += (field[i+1][j-1] == '#') ? 1:0;
						nbLumberyard += (field[i][j-1] == '#') ? 1:0;
						if(nbLumberyard >= 3) {
							nfield[i][j] = '#';
						}
//						System.out.println(field[i][j] + " -> " + nfield[i][j]);
					}
					
					if(field[i][j] == '#') {//Cas lumberyard
						int nbLumberyard = 0;
						int nbTrees = 0;
						nbLumberyard += (field[i-1][j-1] == '#') ? 1:0;
						nbLumberyard += (field[i-1][j] == '#') ? 1:0;
						nbLumberyard += (field[i-1][j+1] == '#') ? 1:0;
						nbLumberyard += (field[i][j+1] == '#') ? 1:0;
						nbLumberyard += (field[i+1][j+1] == '#') ? 1:0;
						nbLumberyard += (field[i+1][j] == '#') ? 1:0;
						nbLumberyard += (field[i+1][j-1] == '#') ? 1:0;
						nbLumberyard += (field[i][j-1] == '#') ? 1:0;
						
						nbTrees += (field[i-1][j-1] == '|') ? 1:0;
						nbTrees += (field[i-1][j] == '|') ? 1:0;
						nbTrees += (field[i-1][j+1] == '|') ? 1:0;
						nbTrees += (field[i][j+1] == '|') ? 1:0;
						nbTrees += (field[i+1][j+1] == '|') ? 1:0;
						nbTrees += (field[i+1][j] == '|') ? 1:0;
						nbTrees += (field[i+1][j-1] == '|') ? 1:0;
						nbTrees += (field[i][j-1] == '|') ? 1:0;
						if(nbLumberyard >= 1 && nbTrees >= 1) {
							nfield[i][j] = '#';
						}else{
							nfield[i][j] = '.';
						}
//						System.out.println(field[i][j] + " -> " + nfield[i][j]);
					}
				}
			}
			
			//Recopie du field
			for(int ic = 0; ic<field.length; ic++) {
				for(int jc = 0; jc<field.length; jc++) {
					field[ic][jc] = nfield[ic][jc];
				}
			}
			
//			int nbTrees = 0;
//			int nbLumberyard = 0;
//			
//			for(int ic = 0; ic<field.length; ic++) {
//				for(int jc = 0; jc<field.length; jc++) {
//					if(field[ic][jc] == '|') {
//						nbTrees++;
//					}else if(field[ic][jc] == '#') {
//						nbLumberyard++;
//					}
//				}
//			}
//			
//			System.out.println(nbTurn + " : " + (nbTrees * nbLumberyard));
			
			nbTurn++;
		}
		
		int nbTrees = 0;
		int nbLumberyard = 0;
		
		for(int ic = 0; ic<field.length; ic++) {
			for(int jc = 0; jc<field.length; jc++) {
				if(field[ic][jc] == '|') {
					nbTrees++;
				}else if(field[ic][jc] == '#') {
					nbLumberyard++;
				}
			}
		}
		
		/*PART ONE*/
		
		System.out.println(nbTurn + " : " + (nbTrees * nbLumberyard));
		
		/*PART TWO*/
		
		//Boucle modulo 28 :
		//Valeur à 1000 = 189168
		//Valeur à 1028 = 189168
		//Valeur 1000000000 = valeur 1000 + (1000000000 - 1000)%28 = valeur 1000 = 189168
		
		System.out.println("1000000000 : " + (nbTrees * nbLumberyard));
		
		/*DEBUG*/
		
		for(int i = 0; i<field[0].length; i++) {
			for(int j = 0; j<field.length; j++) {
				System.out.print(field[i][j]);
			}
			System.out.print("\n");
		}

	}
}
