package puzzle10;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataBuilder {
	public Map<Integer,Coordinate> speedPoint = new HashMap<Integer,Coordinate>();
	
	public DataBuilder() throws IOException {
		File file = new File("src/puzzle10/input.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		String lineRead;
		Pattern p = Pattern.compile("position=<\\s*([\\-0-9]+), \\s*([\\-0-9]+)> velocity=<\\s*([\\-0-9]+), \\s*([\\-0-9]+)>");//group 1 : point x, group 2 : point y, group 3 : vel x, group 4 : vel y
		
		int id = 0;
		
		while((lineRead = br.readLine()) != null) {
			Matcher m = p.matcher(lineRead);
			m.find();
			Coordinate point = new Coordinate(Integer.parseInt(m.group(1)),Integer.parseInt(m.group(2)),Integer.parseInt(m.group(3)),Integer.parseInt(m.group(4)));
			speedPoint.put(id,point);
			id++;
		}
		
		br.close();
	}
	
	public void update() {
		update(0);
	}
	
	public void update(int nb) {
		Set<Entry<Integer, Coordinate>> spSet = speedPoint.entrySet();
		for(Entry<Integer, Coordinate> ent : spSet) {
			speedPoint.replace(ent.getKey(), ent.getValue().update(nb));
		}
	}
	
	//Optimum :  tickUnit="10" lowerBound="300" upperBound="100"  tickUnit="10" lowerBound="100" upperBound="300" 
	
	/*DEBUG*/
	
	public void export() throws IOException{
		File filew = new File("src/puzzle10/result.txt");
		BufferedWriter bw = new BufferedWriter(new FileWriter(filew));
		
		for(Coordinate c : speedPoint.values()) {
			bw.write(c.toString());
			bw.newLine();
		}
		
		bw.close();
	}
}
