package puzzle19;

public class Instruction {
	public String instrName;
	public int valA;
	public int valB;
	public int output;
	
	public Instruction(String instrName, int valA, int valB, int output) {
		this.instrName = instrName;
		this.valA = valA;
		this.valB = valB;
		this.output = output;
	}
}
