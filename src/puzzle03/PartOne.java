package puzzle03;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PartOne {
	public static void main(String[] args) throws IOException{
		File file = new File("src/puzzle03/input.txt");

		BufferedReader br = new BufferedReader(new FileReader(file));
	
		Boolean[][] fabric = new Boolean[1500][1500];
		String actualPlan;
		int offsetLeft = 0, offsetTop = 0, width = 0, height = 0; 
		Pattern p = Pattern.compile("@ ([0-9]+),([0-9]+): ([0-9]+)x([0-9]+)$");
		while((actualPlan = br.readLine()) != null) {
			Matcher m = p.matcher(actualPlan);//On récupère les infos
			
			while(m.find()) {
				offsetLeft = Integer.parseInt(m.group(1));
				offsetTop = Integer.parseInt(m.group(2));
				width = Integer.parseInt(m.group(3));
				height = Integer.parseInt(m.group(4));
			}
			
			for(int i = offsetLeft; i<offsetLeft+width;i++) {//On remplit le tableau
				for(int j = offsetTop; j<offsetTop+height;j++) {
 					fabric[i][j] = (fabric[i][j] == null) ? true:false ;
				}
			}
		}
		br.close();
		
		int res = (int) Arrays.stream(fabric).flatMap(x -> Arrays.stream(x)).filter(x -> x != null && x != true).count();
		
//		for(Boolean[] bll : fabric) {
//			res += (int) Arrays.stream(bll).filter(x -> x != null).filter(x -> x != true).count();
//		}
		System.out.println(res);
	}
}
