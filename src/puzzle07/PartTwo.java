package puzzle07;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

public class PartTwo {
	public static void main(String[] args) throws IOException {
		File file = new File("src/puzzle07/input.txt");

		BufferedReader br = new BufferedReader(new FileReader(file));
		
		Map<String,Tache> tacheMap = new HashMap<String,Tache>();
		
		String lineRead;
		while((lineRead = br.readLine()) != null) {//Generation du graphe
			String idPred = lineRead.substring(5, 6);
			String idSucc = lineRead.substring(36, 37);
			
			if(!tacheMap.containsKey(idPred)) {//TachePred n'existe pas encore
				int dur = (int) idPred.charAt(0) -4;
				tacheMap.put(idPred, new Tache(idPred, dur));
			}
			if(!tacheMap.containsKey(idSucc)) {//TacheSucc n'existe pas encore
				int dur = (int) idSucc.charAt(0) -4;
				tacheMap.put(idSucc, new Tache(idSucc, dur));
			}
			
			tacheMap.get(idPred).addSucc(tacheMap.get(idSucc));
			tacheMap.get(idSucc).addPred(tacheMap.get(idPred));
		}
		br.close();
		
		Tache start = new Tache("Start", 0);//Generation de la tache de départ
		for(Tache t : tacheMap.values()) {
			if(t.pred.size() == 0) {
				t.addPred(start);
				start.addSucc(t);
			}
		}
		
		Map<String,Tache> availableTache = new TreeMap<String,Tache>();//Taches en attente d'être traitées
		Map<String,Tache> ongoingTache = new TreeMap<String,Tache>();//Taches en train d'être traitées
		
		ongoingTache.put(start.id,start);//Initialisation
		int nbWorker = 1;
		boolean fini = false;
		int totalSecond = 0;
		
		while(!fini) {
			Tache actualTache = null;
			List<String> rmAvailable = new ArrayList<String>();
			List<String> rmOngoing = new ArrayList<String>();
			
			/*Gestion des taches en cours*/	
			Set<Entry<String, Tache>> ongSet = ongoingTache.entrySet();
			for(Entry<String, Tache> ent : ongSet) {
				actualTache = ent.getValue();
				if(ent.getValue().duration == 0) {//Cette tache est fini, on libère les succs
					for(Tache t : actualTache.succ) {
						t.pred.remove(actualTache);
						if(t.pred.size() == 0) {//Celle ci est libre, on la rajoute aux taches en attente d'être traitées
							availableTache.put(t.id, t);
						}
					}
					rmOngoing.add(actualTache.id);//On supprime la tache fini
					nbWorker--;//On libère l'ouvrier
				}else{//Sinon on décrémente
					actualTache.duration--;
					//System.out.println(actualTache.id + " " + actualTache.duration);
				}
			}
			
			for(String ts : rmOngoing) {//On enlève les taches finies (lourdeur)
				ongoingTache.remove(ts);
			}
			
			//System.out.println(ongoingTache);
			
			/*Gestion des nouvelles taches*/
			Set<Entry<String, Tache>> avtSet = availableTache.entrySet();
			for(Entry<String, Tache> ent : avtSet) {//Premières taches dans l'ordre alphabétique parmi les taches possibles
				if(nbWorker < 5) {//Il y a encore des ouvriers
					actualTache = ent.getValue();
					ongoingTache.put(actualTache.id, actualTache);//On ajoute cette taches aux taches en train d'être traité
					rmAvailable.add(actualTache.id);//On supprime la tache des taches en attente d'être traité
					actualTache.duration--;//On commence à décrémenter
					nbWorker++;//On affecte un ouvrier
				}
			}
			
			for(String ts : rmAvailable) {//On enlève les taches en train d'être traité (lourdeur)
				availableTache.remove(ts);
			}
			
			totalSecond++;
//			System.out.println(availableTache);
//			System.out.println(totalSecond);
			
			if(ongoingTache.size() == 0) {//Cas fin du graphe (un tour en trop, il faut décrementer le compteur)
				fini = true;
				totalSecond--;
			}
		}
		
		System.out.println(totalSecond);

//		for(Tache t : tacheMap.values()) {
//			System.out.print(t.id + "[ ");
//			for(Tache tb : t.succ) {
//				System.out.print(tb.id + ",");
//			}
//			System.out.println(" ]" + t.duration);
//		}
	}
}
