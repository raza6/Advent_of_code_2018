package puzzle01;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PartOneAndTwo {
	public static void main(String[] args) throws IOException, UnknownOperatorException {
		File file = new File("src/puzzle01/input.txt");

		BufferedReader br = new BufferedReader(new FileReader(file));

		String freqChange;
		int res = 0;
		List<Integer> freqSeen= new ArrayList<Integer>();
		boolean trig1 = true;
		int i = 0;
		
		freqSeen.add(0);
		while(trig1) {
			while ((freqChange = br.readLine()) != null && trig1) {
				if(freqChange.charAt(0) == '+') {
					freqChange = freqChange.replace("+", "");
					res += Integer.parseInt(freqChange);
				}else if(freqChange.charAt(0) == '-') {
					freqChange = freqChange.replace("-", "");
					res -= Integer.parseInt(freqChange);
				}else {
					throw new UnknownOperatorException();
				}
				/*Part 2 from here...*/
				if(freqSeen.contains(res) && trig1) {
					System.out.println("First freq seen twice :" + res);
					trig1 = false;
					freqSeen.add(res);
				}else {
					freqSeen.add(res);
				}
			}
			br.close();
			br = new BufferedReader(new FileReader(file));
			i++;
			System.out.println("loop" + i);
			/*Part 2 ...to here*/
		}
		//System.out.println(freqSeen);
		br.close();
	}
}
