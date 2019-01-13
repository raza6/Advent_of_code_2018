package puzzle04;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PartOneAndTwo {
	public static void main(String[] args) throws IOException {
		File file = new File("src/puzzle04/input2.txt");

		BufferedReader br = new BufferedReader(new FileReader(file));
		
		Pattern pDate = Pattern.compile("^\\[(\\d+)-(\\d+)-(\\d+) (\\d+):(\\d+)\\]");//Group 1 year, 2 month, 3 day, 4 hour, 5 minute
		Pattern pGuard = Pattern.compile("Guard #(\\d+)");//Group 1 = id guard
		Pattern pWakeUp = Pattern.compile("wakes up");
		Pattern pAsleep = Pattern.compile("falls asleep");
		
		String lineRead;
		String lastGuard = "";
		Map<String,int[]> timetable = new HashMap<String,int[]>();
		
		while((lineRead = br.readLine()) != null) {//On genere une map (Garde, [Fréquence de sommeil par minute])
			Matcher mDate = pDate.matcher(lineRead);
			Matcher mActivity = pGuard.matcher(lineRead);
			mDate.find();
			
			if(mActivity.find()) {
				if(!timetable.containsKey(mActivity.group(1))) {//Nouveau garde -> initialisation
					int[] activity = new int[60];//index 0 = minuit, 60 = 1h00
					Arrays.fill(activity,0);
					timetable.put(mActivity.group(1), activity);
				}
				lastGuard = mActivity.group(1);
			}

			mActivity = pAsleep.matcher(lineRead);
			if(mActivity.find()) {//Cas asleep
				int[] activity = timetable.get(lastGuard);
				for(int i = Integer.parseInt(mDate.group(5)); i<activity.length; i++) {
					activity[i] = activity[i]+1;
				}
			}

			mActivity = pWakeUp.matcher(lineRead);
			if(mActivity.find()) {//Cas wakeup
				int[] activity = timetable.get(lastGuard);
				for(int i = Integer.parseInt(mDate.group(5)); i<activity.length; i++) {
					activity[i] = activity[i]-1;
				}
			}
		}
		br.close();
		
		/*The most sleeping guard is*/
		Map<String,Integer> timetable2 = new HashMap<String,Integer>();//Generation of timetable of total sleep
		for(String s : timetable.keySet()) {
			timetable2.put(s, Arrays.stream(timetable.get(s)).sum());
		}
		
		Set<Entry<String, Integer>> ttset = timetable2.entrySet();
		int max = 0;//Max des heures de sommeil total
		String gMax = "";//Garde correspondant
		for(Entry<String, Integer> ent : ttset) {
			if(ent.getValue() > max) {
				max = ent.getValue();
				gMax = ent.getKey();
			}
		}
		System.out.println("Le garde est " + gMax);
		
		/*The most sleeped minute of this guard*/
		int[] timetableGMax = timetable.get(gMax);
		max = 0;//Max de la minute la plus dormi pour le garde particulier
		int minMax = 0;//Index de cette minute
		for(int i = 0; i<timetableGMax.length; i++) {
			if(timetableGMax[i] > max) {
				max = timetableGMax[i];
				minMax = i;
			}
		}
		System.out.println("La minute est " + minMax);
		
		System.out.println("Le code est " + Integer.parseInt(gMax)*minMax);
		
		/**************************************/
		/*PartTwo*/
		/**************************************/
		
		
		Set<Entry<String, int[]>> ttset2 = timetable.entrySet();
		max = 0;//Max de la minute la plus dormi tout garde confondu
		minMax = 0;//Index de cette minute
		gMax = "";//Garde correspondant
		for(Entry<String, int[]> ent : ttset2) {
			int[] tabMin = ent.getValue();
			for(int i = 0; i < tabMin.length; i++) {
				if(tabMin[i] > max) {
					max = tabMin[i];
					minMax = i;
					gMax = ent.getKey();
				}
			}
		}
		System.out.println("Le garde est " + gMax);
		System.out.println("La minute est " + minMax);
		
		System.out.println("Le code est " + Integer.parseInt(gMax)*minMax);
		
//		System.out.println(timetable.toString());
//		List<int[]> test = new ArrayList<int[]>(timetable.values());
//		for(int[] in : test) {
//			System.out.print("[");
//			for(int val : in) {
//				System.out.print(val + ",");
//			}
//			System.out.print("]\n");
//		}
		
//		List<Integer> test2 = new ArrayList<Integer>(timetable2.values());
//		System.out.println(test2);
		
//		int[] res = timetable.get("743");
//		for(int i = 0; i<res.length; i++) {
//			if(res[i] == 15) {
//				System.out.println(i);
//			}
//		}
	}
}
