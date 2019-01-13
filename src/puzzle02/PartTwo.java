package puzzle02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PartTwo {
	public static void main(String[] args) throws IOException {

		File file = new File("src/puzzle02/input.txt");

		BufferedReader br = new BufferedReader(new FileReader(file));
		
		List<String> boxList = new ArrayList<String>();
		String boxRead;
		while((boxRead = br.readLine()) != null) {//On crée une liste d'id
			boxList.add(boxRead);
		}
		br.close();
		
		List<String> boxList2 = new ArrayList<String>(boxList);
		String res1 = "", res2 = "";
		
		for(String boxBase : boxList) {//On cherche les ids
			boxList2 = new ArrayList<String>(boxList2.subList(1, boxList2.size()));
			for(String boxComp : boxList2) {
				int nbDiff = 0;
				for(int i = 0; i<boxBase.length(); i++) {//On compare les strings
					if(boxBase.charAt(i) != boxComp.charAt(i)) {
						nbDiff++;
					}
				}
				if(nbDiff == 1) {//Strings trouvés
					res1 = boxBase;
					res2 = boxComp;
					System.out.println("base : " + boxBase + " comp : " + boxComp);
				}
			}
		}
		
		String res = "";
		for(int i = 0; i<res1.length(); i++) {//On genere la solution
			if(res1.charAt(i) == res2.charAt(i)) {
				res += res1.charAt(i);
			}
		}
		System.out.println(res);
	}
}
