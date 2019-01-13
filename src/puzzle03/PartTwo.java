package puzzle03;

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
		File file = new File("src/puzzle03/input.txt");

		BufferedReader br = new BufferedReader(new FileReader(file));

		String[][] fabric = new String[1500][1500];//Structure des ateliers
		List<String> idList = new ArrayList<String>();
		
		String actualPlan;//Lecture du fichier
		Pattern p = Pattern.compile("^#([0-9]+) @ ([0-9]+),([0-9]+): ([0-9]+)x([0-9]+)$");
		int offsetLeft = 0, offsetTop = 0, width = 0, height = 0;
		String id = "";
		
		while ((actualPlan = br.readLine()) != null) {
			Matcher m = p.matcher(actualPlan);// On récupère les infos

			while (m.find()) {
				id = m.group(1);
				offsetLeft = Integer.parseInt(m.group(2));
				offsetTop = Integer.parseInt(m.group(3));
				width = Integer.parseInt(m.group(4));
				height = Integer.parseInt(m.group(5));
			}
			
			idList.add(id);

			for (int i = offsetLeft; i < offsetLeft + width; i++) {// On remplit le tableau
				for (int j = offsetTop; j < offsetTop + height; j++) {
					if(fabric[i][j] == null) {//Cas sans overlap
						fabric[i][j] = id;
					}else {//Cas avec overlap
						idList.remove(id);
						if(idList.contains(fabric[i][j])) {
							idList.remove(fabric[i][j]);
						}
					}
				}
			}
		}
		br.close();
		
		for(String s : idList) {
			System.out.println(s);
		}
	}

}
