package puzzle07;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class PartOne {

	public static void main(String[] args) throws IOException {
		File file = new File("src/puzzle07/input.txt");

		BufferedReader br = new BufferedReader(new FileReader(file));
		
		Map<String,Tache> tacheMap = new HashMap<String,Tache>();
		
		String lineRead;
		while((lineRead = br.readLine()) != null) {//Generation du graphe
			String idPred = lineRead.substring(5, 6);
			String idSucc = lineRead.substring(36, 37);
			
			if(!tacheMap.containsKey(idPred)) {//TachePred n'existe pas encore
				tacheMap.put(idPred, new Tache(idPred));
			}
			if(!tacheMap.containsKey(idSucc)) {//TacheSucc n'existe pas encore
				tacheMap.put(idSucc, new Tache(idSucc));
			}
			
			tacheMap.get(idPred).addSucc(tacheMap.get(idSucc));
			tacheMap.get(idSucc).addPred(tacheMap.get(idPred));
		}
		br.close();
		
		Tache start = new Tache("Start");//Generation de la tache de départ
		for(Tache t : tacheMap.values()) {
			if(t.pred.size() == 0) {
				t.addPred(start);
				start.addSucc(t);
			}
		}
		
		Map<String,Tache> availableTache = new TreeMap<String,Tache>();
		availableTache.put(start.id,start);
		String res = "";
		boolean fini = false;
		
		while(!fini) {
			Tache actualTache = null;
			
			Set<Entry<String, Tache>> avtSet = availableTache.entrySet();
			for(Entry<String, Tache> ent : avtSet) {//Première tache dans l'ordre alphabétique parmi les taches possibles
				actualTache = ent.getValue();
				break;
			}
			
			/*Update du parcours*/
			availableTache.remove(actualTache.id);//On retire la tache actuelle des taches possibles
			res += actualTache.id;//et on l'ajoute à la réponse
			
			for(Tache t : actualTache.succ) {//On regarde les taches potentiellement libérées
				t.pred.remove(actualTache);
				if(t.pred.size() == 0) {//Celle ci est libre
					availableTache.put(t.id, t);
				}
			}
			
			if(actualTache.succ.size() == 0) {//Cas fin du graphe
				fini = true;
			}
//			System.out.println(actualTache);
//			System.out.println(availableTache);
		}
		
		System.out.println(res.substring(5));

//		for(Tache t : tacheMap.values()) {
//			System.out.print(t.id + "[ ");
//			for(Tache tb : t.succ) {
//				System.out.print(tb.id + ",");
//			}
//			System.out.println(" ]" + t.succ.size());
//		}
	}

}
