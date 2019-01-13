package puzzle16;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PartTwo {

	public static void main(String[] args) throws IOException {
		int[] register = {0,0,0,0};
		int[] instruction = new int[4];
		
		File file = new File("src/puzzle16/input2.txt");
//		File file = new File("src/puzzle16/test.txt");

		BufferedReader br = new BufferedReader(new FileReader(file));
		String lineRead;
		
		Pattern pinstr = Pattern.compile("(\\d+) (\\d) (\\d) (\\d)");//intrc = opcode valA valB output
		while((lineRead = br.readLine()) != null) {
			Matcher m = pinstr.matcher(lineRead);
			if(m.find()) {//Cas ligne instr
				instruction[0] = Integer.parseInt(m.group(1));//Opcode
				instruction[1] = Integer.parseInt(m.group(2));//ValA
				instruction[2] = Integer.parseInt(m.group(3));//ValB
				instruction[3] = Integer.parseInt(m.group(4));//Output
				switch (instruction[0]) {
					case 0://gtri
						register[instruction[3]] = (register[instruction[1]]>instruction[2]) ? 1:0;
						break;
					case 1://bani
						register[instruction[3]] = register[instruction[1]] & instruction[2];
						break;
					case 2://eqrr
						register[instruction[3]] = (register[instruction[1]]==register[instruction[2]]) ? 1:0;						
						break;
					case 3://gtir
						register[instruction[3]] = (instruction[1]>register[instruction[2]]) ? 1:0;						
						break;
					case 4://eqir
						register[instruction[3]] = (instruction[1]==register[instruction[2]]) ? 1:0;						
						break;
					case 5://bori
						register[instruction[3]] = register[instruction[1]] | instruction[2];						
						break;
					case 6://seti
						register[instruction[3]] = instruction[1];						
						break;
					case 7://setr
						register[instruction[3]] = register[instruction[1]];						
						break;
					case 8://addr
						register[instruction[3]] = register[instruction[1]] + register[instruction[2]];						
						break;
					case 9://borr
						register[instruction[3]] = register[instruction[1]] | register[instruction[2]];						
						break;
					case 10://muli
						register[instruction[3]] = register[instruction[1]] * instruction[2];						
						break;
					case 11://banr
						register[instruction[3]] = register[instruction[1]] & register[instruction[2]];					
						break;
					case 12://addi
						register[instruction[3]] = register[instruction[1]] + instruction[2];						
						break;
					case 13://eqri
						register[instruction[3]] = (register[instruction[1]]==instruction[2]) ? 1:0;						
						break;
					case 14://mulr
						register[instruction[3]] = register[instruction[1]] * register[instruction[2]];						
						break;
					case 15://gtrr
						register[instruction[3]] = (register[instruction[1]]>register[instruction[2]]) ? 1:0;						
						break;
				}
				System.out.println(register[0] + " " + register[1] + " " + register[2] + " " + register[3]);
			}
		}
		br.close();
		
		System.out.println(register[0]);	
		
		//opcode 0 -> gtri (11)
		//opcode 1 -> bani (5)
		//opcode 2 -> eqrr (15)
		//opcode 3 -> gtir (10)
		//opcode 4 -> eqir (13)
		//opcode 5 -> bori (7)
		//opcode 6 -> seti (9)
		//opcode 7 -> setr (8)
		//opcode 8 -> addr (0)
		//opcode 9 -> borr (6)
		//opcode 10 -> muli (3)
		//opcode 11 -> banr (4)
		//opcode 12 -> addi (1)
		//opcode 13 -> eqri (14)
		//opcode 14 -> mulr (2)
		//opcode 15 -> gtrr (12)
	}

}
