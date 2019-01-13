package puzzle23;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PartTwo {
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
		
		/*A la recherche de la meilleure coordonnée*/
		
		long maxX = 0;
		long minX = 0;
		long maxY = 0;
		long minY = 0;
		long maxZ = 0;
		long minZ = 0;		
		
		for(Nano n : allNano) {
			if(n.coor.x > maxX) {
				maxX = n.coor.x;
			}
			if(n.coor.x < minX) {
				minX = n.coor.x;
			}
			if(n.coor.y > maxY) {
				maxY = n.coor.y;
			}
			if(n.coor.y < minY) {
				minY = n.coor.y;
			}
			if(n.coor.z > maxZ) {
				maxZ = n.coor.z;
			}
			if(n.coor.z < minZ) {
				minZ = n.coor.z;
			}
		}
		
		Coordinate bestCoor = new Coordinate(0,0,0,0);
		
		for(long i = minX; i<=maxX; i++) {
			for(long j = minY; j<=maxY; j++) {
				for(long k = minZ; k<=maxZ; k++) {
					int nbNanoInRange = 0;
					for(Nano n : allNano) {
						long manDist = Math.abs(n.coor.x - i) + Math.abs(n.coor.y - j) + Math.abs(n.coor.z - k);
						if(manDist <= n.radius) {
							nbNanoInRange++;
						}
					}
					if(nbNanoInRange > bestCoor.nbNanoInRange) {
						bestCoor = new Coordinate(i,j,k,nbNanoInRange);
					}else if(nbNanoInRange == bestCoor.nbNanoInRange) {
						long manDistZN = Math.abs(0 - i) + Math.abs(0 - j) + Math.abs(0 - k);
						long manDistZO = Math.abs(bestCoor.x - i) + Math.abs(bestCoor.y - j) + Math.abs(bestCoor.z - k);
						if(manDistZN < manDistZO) {
							bestCoor = new Coordinate(i,j,k,nbNanoInRange);					
						}
					}
				}
			}
		}

		System.out.println(bestCoor.x + " " + bestCoor.y + " " + bestCoor.z);
	}
}
