package puzzle06;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PartOne {
	public static void main(String[] args) throws IOException {
		File file = new File("src/puzzle06/input.txt");

		BufferedReader br = new BufferedReader(new FileReader(file));
		
		String lineRead;
		Map<Integer,Coordinate> baseId = new HashMap<Integer,Coordinate>(); //Id, coor
		int id = 1;
		
		Pattern p = Pattern.compile("^(\\d+), (\\d+)");//group 1 x, group 2 y
		while((lineRead = br.readLine()) != null) {
			Matcher m = p.matcher(lineRead);
			m.find();
			
			Coordinate coor = new Coordinate(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)));
			baseId.put(id, coor);
			id++;
		}
		br.close();
		
		int[][] grid = new int[400][400];//grid vide
		for(int[] gri : grid) {
			Arrays.fill(gri, 0);
		}
		
//Doublon : effectue dans le bloc suivant
//		for(int i = 1; i<=baseId.size();i++) {//Remplissage de la grid avec les id
//			grid[baseId.get(i).x][baseId.get(i).y] = i;
//		}
		
		for(int i = 0; i<grid.length;i++) {//Remplissage de la grid
			for(int j = 0; j<grid.length;j++) {
				int[] min = new int[baseId.size()];//Generation des distances minimales pour une case, dman at index id-1
				for(int k = 1; k<=baseId.size();k++) {
					int dman = Math.abs(i-baseId.get(k).x)+Math.abs(j-baseId.get(k).y);
					min[k-1] = dman;
				}
				
				int minDman = Arrays.stream(min).min().orElse(-1);//Choix de la plus petite distance
				if(Arrays.stream(min).filter(x -> x == minDman).count() > 1) {//Equidistance
					grid[i][j] = 0;
				}else {//Sinon, on cherche l'id à mettre dans la case
					int iminDman = 0;
					for(int l = 0; l<min.length; l++) {
						if(min[l] == minDman) {
							iminDman = l+1;
							break;
						}
					}
					grid[i][j] = iminDman;
				}
			}
		}
		
		List<Integer> idEnclave = new ArrayList<Integer>(baseId.keySet());
		
		//On parcours les bords de la grid pour trouver les aires enclavées
		for(int i = 0; i<grid.length; i++) {
			if(idEnclave.contains(grid[i][0])) {
				idEnclave.remove(new Integer(grid[i][0]));
			}
		}
		for(int i = 0; i<grid.length; i++) {
			if(idEnclave.contains(grid[i][grid.length-1])) {
				idEnclave.remove(new Integer(grid[i][grid.length-1]));
			}
		}
		for(int j = 0; j<grid.length; j++) {
			if(idEnclave.contains(grid[0][j])) {
				idEnclave.remove(new Integer(grid[0][j]));
			}
		}
		for(int j = 0; j<grid.length; j++) {
			if(idEnclave.contains(grid[grid.length-1][j])) {
				idEnclave.remove(new Integer(grid[grid.length-1][j]));
			}
		}
		
		List<Integer> maxOccu = new ArrayList<Integer>();//Calcul de la taille des aires restantes
		for(int id1 : idEnclave) {
			maxOccu.add((int) Arrays.stream(grid).flatMapToInt(x -> Arrays.stream(x)).filter(y -> y == id1).count());
		}
		
		System.out.println(maxOccu.stream().mapToInt(x -> x).max().orElse(0));
		
		/*HARD DEBUG*/
		
		File filew = new File("src/puzzle06/debug.txt");
		BufferedWriter bw = new BufferedWriter(new FileWriter(filew));
		
		for(int[] gri : grid) {
			for(int gr : gri) {
				bw.write(gr + "|");
			}
			bw.newLine();
		}
		
		bw.close();
		
		/*Debug*/
//		for(int[] gri : grid) {
//			for(int gr : gri) {
//				System.out.print(gr + "|");
//			}
//			System.out.print("\n");
//		}
		
		System.out.println(idEnclave);
	}
}
