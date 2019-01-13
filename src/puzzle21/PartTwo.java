package puzzle21;

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
		int[] register = {0,0,0,0,0,0};
		int ipReg = 0;
		int actualInstr = 0;
		
		List<Instruction> allInstr = new ArrayList<Instruction>();
		
		File file = new File("src/puzzle21/input.txt");
//		File file = new File("src/puzzle19/test.txt");

		BufferedReader br = new BufferedReader(new FileReader(file));
		String lineRead;
		
		Pattern pinpoint = Pattern.compile("^#ip (\\d)");
		Pattern pinstr = Pattern.compile("^(\\w+) (\\d+) (\\d+) (\\d+)$");//intrc = opcode valA valB output
		while((lineRead = br.readLine()) != null) {
			Matcher m = pinpoint.matcher(lineRead);//ip
			if(m.find()) {
				ipReg = Integer.parseInt(m.group(1));
				actualInstr = 0;
			}
			m = pinstr.matcher(lineRead);
			if(m.find()) {//instr
				allInstr.add(new Instruction(m.group(1),Integer.parseInt(m.group(2)),Integer.parseInt(m.group(3)),Integer.parseInt(m.group(4))));
			}
		}
		br.close();
		
		/*Debug de la generation des instructions*/
//		for(Instruction in : allInstr) {
//			System.out.println(in.instrName + " " + in.valA + " " + in.valB + " " + in.output);
//		}
//		System.out.println(allInstr.size());
		
		List<Integer> valStop = new ArrayList<Integer>();
		boolean stop = true;
		
		while(actualInstr < allInstr.size() && stop) {
			register[ipReg] = actualInstr;

			/*Recherche de la valeur permettant l'arret de la boucle*/
			if(actualInstr == 28) {
				if(valStop.contains(register[3])) {//Cas où la boucle se repete (infini)
					System.out.println(valStop.get(valStop.size()-1));
					stop = false;
				}else {
					valStop.add(register[3]);
				}
			}

//			for(int in : register) {
//				System.out.print(in + " ");
//			}
//			System.out.print("\n");
//			System.out.println(allInstr.get(actualInstr).instrName + " " + allInstr.get(actualInstr).valA + " " + allInstr.get(actualInstr).valB + " " + allInstr.get(actualInstr).output);

			switch (allInstr.get(actualInstr).instrName) {
			case "addr":
				register[allInstr.get(actualInstr).output] = register[allInstr.get(actualInstr).valA] + register[allInstr.get(actualInstr).valB];
				break;
			case "addi":
				register[allInstr.get(actualInstr).output] = register[allInstr.get(actualInstr).valA] + allInstr.get(actualInstr).valB;
				break;
			case "mulr":
				register[allInstr.get(actualInstr).output] = register[allInstr.get(actualInstr).valA] * register[allInstr.get(actualInstr).valB];
				break;
			case "muli":
				register[allInstr.get(actualInstr).output] = register[allInstr.get(actualInstr).valA] * allInstr.get(actualInstr).valB;
				break;
			case "banr":
				register[allInstr.get(actualInstr).output] = register[allInstr.get(actualInstr).valA] & register[allInstr.get(actualInstr).valB];
				break;
			case "bani":
				register[allInstr.get(actualInstr).output] = register[allInstr.get(actualInstr).valA] & allInstr.get(actualInstr).valB;
				break;
			case "borr":
				register[allInstr.get(actualInstr).output] = register[allInstr.get(actualInstr).valA] | register[allInstr.get(actualInstr).valB];
				break;
			case "bori":
				register[allInstr.get(actualInstr).output] = register[allInstr.get(actualInstr).valA] | allInstr.get(actualInstr).valB;
				break;
			case "setr":
				register[allInstr.get(actualInstr).output] = register[allInstr.get(actualInstr).valA];
				break;
			case "seti":
				register[allInstr.get(actualInstr).output] = allInstr.get(actualInstr).valA;
				break;
			case "gtir":
				register[allInstr.get(actualInstr).output] = (allInstr.get(actualInstr).valA>register[allInstr.get(actualInstr).valB]) ? 1:0;
				break;
			case "gtri":
				register[allInstr.get(actualInstr).output] = (register[allInstr.get(actualInstr).valA]>allInstr.get(actualInstr).valB) ? 1:0;
				break;
			case "gtrr":
				register[allInstr.get(actualInstr).output] = (register[allInstr.get(actualInstr).valA]>register[allInstr.get(actualInstr).valB]) ? 1:0;
				break;
			case "eqir":
				register[allInstr.get(actualInstr).output] = (allInstr.get(actualInstr).valA==register[allInstr.get(actualInstr).valB]) ? 1:0;
				break;
			case "eqri":
				register[allInstr.get(actualInstr).output] = (register[allInstr.get(actualInstr).valA]==allInstr.get(actualInstr).valB) ? 1:0;
				break;
			case "eqrr":
				register[allInstr.get(actualInstr).output] = (register[allInstr.get(actualInstr).valA]==register[allInstr.get(actualInstr).valB]) ? 1:0;
				break;
			}
			actualInstr = register[ipReg];
			actualInstr++;
		}

		//Cas où la boucle n'est pas infini
		System.out.print("Yes : ");
		for(int in : register) {
			System.out.print(in + " ");
		}
		System.out.print("\n");
	}
}
