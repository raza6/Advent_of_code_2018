package puzzle06;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PartTwo {
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
		
		for(int i = 1; i<=baseId.size();i++) {//Remplissage de la grid avec les id
			grid[baseId.get(i).x][baseId.get(i).y] = i;
		}
		
		int totalCase = 0;
		for(int i = 0; i<grid.length;i++) {//Calcul des cases valables
			for(int j = 0; j<grid.length;j++) {
				int dtotal = 0;
				for(int k = 1; k<=baseId.size();k++) {
					dtotal += Math.abs(i-baseId.get(k).x)+Math.abs(j-baseId.get(k).y);
				}
				if(dtotal < 10000) {
					totalCase++;
					grid[i][j] = -1;
				}
			}
		}
		
		System.out.println(totalCase);
		
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
		
		System.out.println();

	}

}
