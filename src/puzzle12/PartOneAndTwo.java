package puzzle12;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PartOneAndTwo {

	public static void main(String[] args) throws IOException {
		File file = new File("src/puzzle12/input.txt");
		String input = ".##..##..####..#.#.#.###....#...#..#.#.#..#...#....##.#.#.#.#.#..######.##....##.###....##..#.####.#";

		BufferedReader br = new BufferedReader(new FileReader(file));
		String lineRead;
		Pattern p = Pattern.compile("([.#]+) => #");//group1
		
//		input = "#..#.#..##......###...###...........";
		int dec = 0;
		int nbGen = 0;

		long res = 0;
		long oldRes = 0;

		while(nbGen < 1000) {//PartOne = 20, PartTwo = 1000
			input = "...." + input + "....";//Gestion des effets de bord
			dec -= 4;
			String temp = input.replace('#', '.');//String vide pour une nouvelle generation

//			System.out.println(input);

			while((lineRead = br.readLine()) != null) {
				Matcher m = p.matcher(lineRead);

				if(m.find()) {//Note pertinante (avec #)
					if(input.contains(m.group(1))) {//Input contient la note
						for(int i = 2; i<input.length()-2; i++) {
							if(input.substring(i-2, i+3).equals(m.group(1))) {//Si le pattern match
								temp = temp.substring(0,i) + "#" + temp.substring(i+1);//On rajoute la plante pour la gen suivante
							}
						}
					}	
				}				
			}
//			System.out.println("Regex : " + m.group(1));
//			System.out.println(temp);

			if(!temp.substring(0,4).contains("#")) {//Gestion des effets de bord
				temp = temp.substring(4);
				dec += 4;
			}
			if(!temp.substring(temp.length()-4).contains("#")) {
				temp = temp.substring(0,temp.length()-4);
			}

			input = temp;//Nouvelle generation
			br.close();
			br = new BufferedReader(new FileReader(file));
			nbGen++;		

			int i = dec;
			oldRes = res;
			res = 0;
			
			for(char c : input.toCharArray()) {//Comptage des plantes
				if(c == '#') {
					res += i;
				}
				i++;
			}

//			System.out.println(input);
			System.out.println("Gen n°" + nbGen + " sum=" + res + " diff=" + (res-oldRes));

		}

//		System.out.println(res); //PartOne

		System.out.println(res+((50000000000L - nbGen)*(res-oldRes)));//PartTwo
	}
}
