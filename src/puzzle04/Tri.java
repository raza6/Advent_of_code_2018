package puzzle04;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tri {
	//Genere le fichier trié
	public static void main(String[] args) throws IOException {
		File file = new File("src/puzzle04/input.txt");

		BufferedReader br = new BufferedReader(new FileReader(file));
		
		Map<String,String> thetree = new TreeMap<String,String>();
		String line = "";
		while((line = br.readLine()) != null) {
			thetree.put(line, "");
		}
		
		File filew = new File("src/puzzle04/input2.txt");
		BufferedWriter bw = new BufferedWriter(new FileWriter(filew));
		
		Pattern pDate = Pattern.compile("^\\[(\\d+)-(\\d+)-(\\d+) (\\d+):(\\d+)\\]");//Group 1 year, 2 month, 3 day, 4 hour, 5 minute
		for(String s : thetree.keySet()) {
			Matcher m = pDate.matcher(s);
			m.find();
			if(m.group(4).equals("23")) {//On fixe les heures les plus basses à 23h59
				s = "[" + m.group(1) + "-" + m.group(2) + "-"+ m.group(3) + " " + m.group(4) + ":59] " + s.substring(19);
			}
			bw.write(s);
			bw.newLine();
		}
		
		bw.close();
		br.close();
	}

}
