package puzzle23;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PartOne {
	public static void main(String[] args) throws IOException {
		File file = new File("src/puzzle23/input.txt");
		List<Nano> allNano = new ArrayList<Nano>();
		
		/*Generation de la liste des nanobots*/
		
		Pattern p = Pattern.compile("^pos=<(-?\\d+),(-?\\d+),(-?\\d+)>, r=(-?\\d+)$");

		BufferedReader br = new BufferedReader(new FileReader(file));
		String lineRead;
		while((lineRead = br.readLine()) != null) {
			Matcher m = p.matcher(lineRead);
			if(m.find()) {
				allNano.add(new Nano(Long.parseLong(m.group(1)),Long.parseLong(m.group(2)),Long.parseLong(m.group(3)),Long.parseLong(m.group(4))));
			}
		}
		br.close();
		
		/*A la recherche du plus fort nanobot*/
		
		long maxRadius = 0;
		Nano maxNano = null;
		
		for(Nano n : allNano) {
			if(maxRadius < n.radius) {
				maxNano = n;
				maxRadius = n.radius;
			}
		}
		
		/*Calcul des nanobots in range*/
		
		int nbNanoInRange = 0;
		
		for(Nano n : allNano) {
			long manDist = Math.abs(n.coor.x - maxNano.coor.x) + Math.abs(n.coor.y - maxNano.coor.y) + Math.abs(n.coor.z - maxNano.coor.z);
			if(manDist <= maxNano.radius) {
				nbNanoInRange++;
			}
		}

		System.out.println(nbNanoInRange);
	}
}
