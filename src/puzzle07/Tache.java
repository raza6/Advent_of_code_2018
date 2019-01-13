package puzzle07;

import java.util.ArrayList;
import java.util.List;

public class Tache {
	public String id;
	public int duration;
	public List<Tache> pred;
	public List<Tache> succ;
	
	public Tache(String id) {
		this.id = id;
		this.pred = new ArrayList<Tache>();
		this.succ = new ArrayList<Tache>();
	}
	
	public Tache(String id, int dur) {
		this.id = id;
		this.duration = dur;
		this.pred = new ArrayList<Tache>();
		this.succ = new ArrayList<Tache>();
	}
	
	public void addSucc(Tache succ) {
		this.succ.add(succ);
	}
	
	public void addPred(Tache pred) {
		this.pred.add(pred);
	}
	
	public void rmPred(Tache pred) {
		this.pred.remove(pred);
	}
}
