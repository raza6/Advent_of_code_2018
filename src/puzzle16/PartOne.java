package puzzle16;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PartOne {
	public static void main(String[] args) throws IOException {
		int[] registerb = new int[4];
		int[] instruction = new int[4];
		int[] registera = new int[4];
		int nbinstr = 0;
		
		File file = new File("src/puzzle16/input.txt");
//		File file = new File("src/puzzle16/test.txt");

		BufferedReader br = new BufferedReader(new FileReader(file));
		String lineRead;
		
		Pattern pbef = Pattern.compile("Before: \\[(\\d), (\\d), (\\d), (\\d)\\]");
		Pattern pinstr = Pattern.compile("(\\d+) (\\d) (\\d) (\\d)");//intrc = opcode valA valB output
		Pattern paft = Pattern.compile("After:  \\[(\\d), (\\d), (\\d), (\\d)\\]");
		while((lineRead = br.readLine()) != null) {
			int nbvalid = 0;
			Matcher m = pbef.matcher(lineRead);//Cas ligne before
			if(m.find()) {
				registerb[0] = Integer.parseInt(m.group(1));
				registerb[1] = Integer.parseInt(m.group(2));
				registerb[2] = Integer.parseInt(m.group(3));
				registerb[3] = Integer.parseInt(m.group(4));
			}
			m = pinstr.matcher(lineRead);
			if(m.find()) {//Cas ligne instr
				instruction[0] = Integer.parseInt(m.group(1));//Opcode
				instruction[1] = Integer.parseInt(m.group(2));//ValA
				instruction[2] = Integer.parseInt(m.group(3));//ValB
				instruction[3] = Integer.parseInt(m.group(4));//Output
			}
			m = paft.matcher(lineRead);
			if(m.find()) {//Cas ligne after
				registera[0] = Integer.parseInt(m.group(1));
				registera[1] = Integer.parseInt(m.group(2));
				registera[2] = Integer.parseInt(m.group(3));
				registera[3] = Integer.parseInt(m.group(4));
				for(int i = 0; i<16;i++) {//On teste toutes les instructions possibles
					int[] registertemp = registerb.clone();
					switch (i) {
						case 0://addr
							registertemp[instruction[3]] = registerb[instruction[1]] + registerb[instruction[2]];
							break;
						case 1://addi
							registertemp[instruction[3]] = registerb[instruction[1]] + instruction[2];
							break;
						case 2://mulr
							registertemp[instruction[3]] = registerb[instruction[1]] * registerb[instruction[2]];
							break;
						case 3://muli
							registertemp[instruction[3]] = registerb[instruction[1]] * instruction[2];
							break;
						case 4://banr
							registertemp[instruction[3]] = registerb[instruction[1]] & registerb[instruction[2]];
							break;
						case 5://bani
							registertemp[instruction[3]] = registerb[instruction[1]] & instruction[2];
							break;
						case 6://borr
							registertemp[instruction[3]] = registerb[instruction[1]] | registerb[instruction[2]];
							break;
						case 7://bori
							registertemp[instruction[3]] = registerb[instruction[1]] | instruction[2];
							break;
						case 8://setr
							registertemp[instruction[3]] = registerb[instruction[1]];
							break;
						case 9://seti
							registertemp[instruction[3]] = instruction[1];
							break;
						case 10://gtir
							registertemp[instruction[3]] = (instruction[1]>registerb[instruction[2]]) ? 1:0;
							break;
						case 11://gtri
							registertemp[instruction[3]] = (registerb[instruction[1]]>instruction[2]) ? 1:0;
							break;
						case 12://gtrr
							registertemp[instruction[3]] = (registerb[instruction[1]]>registerb[instruction[2]]) ? 1:0;
							break;
						case 13://eqir
							registertemp[instruction[3]] = (instruction[1]==registerb[instruction[2]]) ? 1:0;
							break;
						case 14://eqri
							registertemp[instruction[3]] = (registerb[instruction[1]]==instruction[2]) ? 1:0;
							break;
						case 15://eqrr
							registertemp[instruction[3]] = (registerb[instruction[1]]==registerb[instruction[2]]) ? 1:0;
							break;
					}
					if(registertemp[0] == registera[0] && registertemp[1] == registera[1] && registertemp[2] == registera[2] && registertemp[3] == registera[3]) {//L'instruction testée fonctionne
						nbvalid++;
					}
				}
				if(nbvalid >= 3) {
					nbinstr++;
				}
			}
		}
		br.close();

		System.out.println(nbinstr);

	}

}
