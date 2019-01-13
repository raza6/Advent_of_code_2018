package puzzle08;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class PartOneAndTwo {
	public static void main(String[] args) throws IOException {
		File file = new File("src/puzzle08/input.txt");

		BufferedReader br = new BufferedReader(new FileReader(file));
		String lineRead = br.readLine();
		br.close();

		Noeud root = new Noeud(lineRead);
		
		System.out.println(root.sumMeta());
		
		/*PART TWO*/
		
		System.out.println(root.sumAlpha());
		
		/*Debug*/
		
//		System.out.println(root.res);
//		for(int k : root.meta) {
//			System.out.println(k);
//		}		
		
//		Noeud test = new Noeud("2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2");
//		
//		for(int k : test.meta) {
//			System.out.println(k);
//		}
//		
//		System.out.println(test.child[0].res);
//		System.out.println(test.child[1].child[0].meta[0]);
		
//		System.out.println(test.sumAlpha());
		
	}
}
