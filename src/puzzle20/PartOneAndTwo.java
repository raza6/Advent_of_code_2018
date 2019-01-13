package puzzle20;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class PartOneAndTwo {
	public static void main(String[] args) throws IOException {
		File file = new File("src/puzzle20/input.txt");

		BufferedReader br = new BufferedReader(new FileReader(file));
		String lineRead = br.readLine();
		lineRead = lineRead.substring(1);
		br.close();
		
		List<Node> facility = new ArrayList<Node>();

		Node start = new Node(0);
		Node actualNode = start;
		int id = 1;
		Deque<Node> branch = new ArrayDeque<Node>();
		
		//lineRead = "ENWWW(NEEE|SSE(EE|N))$";
		
		/*Generation de la facility*/
		
		while(lineRead.charAt(0) != '$') {
			/*Nouvelle node*/
			if(lineRead.charAt(0) == 'N' && actualNode.northN == null) {
				actualNode.northN = new Node();
				facility.add(actualNode.northN);
				actualNode.northN.southN = actualNode;
				actualNode.northN.id = id + "N";
				
				lineRead = lineRead.substring(1);
				id++;
				actualNode = actualNode.northN;
			}else if(lineRead.charAt(0) == 'E' && actualNode.eastN == null) {
				actualNode.eastN = new Node();
				facility.add(actualNode.eastN);
				actualNode.eastN.westN = actualNode;
				actualNode.eastN.id = id + "E";
				
				lineRead = lineRead.substring(1);
				id++;
				actualNode = actualNode.eastN;
			}else if(lineRead.charAt(0) == 'S' && actualNode.southN == null) {
				actualNode.southN = new Node();
				facility.add(actualNode.southN);
				actualNode.southN.northN = actualNode;
				actualNode.southN.id = id + "S";
				
				lineRead = lineRead.substring(1);
				id++;
				actualNode = actualNode.southN;
			}else if(lineRead.charAt(0) == 'W' && actualNode.westN == null) {
				actualNode.westN = new Node();
				facility.add(actualNode.westN);
				actualNode.westN.eastN = actualNode;
				actualNode.westN.id = id + "W";
				
				lineRead = lineRead.substring(1);
				id++;
				actualNode = actualNode.westN;
			}
			/*Node existante*/
			else if(lineRead.charAt(0) == 'N' && actualNode.northN != null) {
				lineRead = lineRead.substring(1);
				actualNode = actualNode.northN;
			}else if(lineRead.charAt(0) == 'E' && actualNode.eastN != null) {
				lineRead = lineRead.substring(1);
				actualNode = actualNode.eastN;
			}else if(lineRead.charAt(0) == 'S' && actualNode.southN != null) {
				lineRead = lineRead.substring(1);
				actualNode = actualNode.southN;
			}else if(lineRead.charAt(0) == 'W' && actualNode.westN != null) {
				lineRead = lineRead.substring(1);
				actualNode = actualNode.westN;
			}
			/*Plusieurs chemins*/
			else if(lineRead.charAt(0) == '(') {//Début de branch
				branch.addFirst(actualNode);
				lineRead = lineRead.substring(1);
			}else if(lineRead.charAt(0) == '|') {
				actualNode = branch.peekFirst();
				lineRead = lineRead.substring(1);
			}else if(lineRead.charAt(0) == ')') {
				branch.removeFirst();
				lineRead = lineRead.substring(1);
			}
		}
		
		//On definit la distance de chaque pièce
		for(Node n : facility) {
			n.marked = false;
		}
		pathDijkstra(start);
		
		/*Calcul*/
		
		int distMax = 0;
		int nbFarRoom = 0;
		for(Node n : facility) {
			n.marked = false;
			if(n.dist > distMax) {
				distMax = n.dist;
			}
			if(n.dist >= 1000) {
				nbFarRoom++;
			}
		}
		
		/*PART ONE*/
		
		System.out.println(distMax);
		
		/*PART TWO*/

		System.out.println(nbFarRoom);
		
//		System.out.println(start.parcoursDFSMark());
	}
	
	public static void pathDijkstra(Node dep) {
		Deque<Node> dqnd = new ArrayDeque<Node>();
		dqnd.addLast(dep);
		dep.marked = true;
		dep.dist = 0;
		while(!dqnd.isEmpty()) {
			Node actualNode = dqnd.removeFirst();
//			System.out.println(actualNode.coor.x + " " + actualNode.coor.y + " : " + actualNode.dist);
			if(actualNode.northN != null && !actualNode.northN.marked) {
				dqnd.addLast(actualNode.northN);
				actualNode.northN.marked = true;
				actualNode.northN.dist = actualNode.dist + 1;
			}
			if(actualNode.eastN != null && !actualNode.eastN.marked) {
				dqnd.addLast(actualNode.eastN);
				actualNode.eastN.marked = true;
				actualNode.eastN.dist = actualNode.dist + 1;
			}
			if(actualNode.southN != null && !actualNode.southN.marked) {
				dqnd.addLast(actualNode.southN);
				actualNode.southN.marked = true;
				actualNode.southN.dist = actualNode.dist + 1;
			}
			if(actualNode.westN != null && !actualNode.westN.marked) {
				dqnd.addLast(actualNode.westN);
				actualNode.westN.marked = true;
				actualNode.westN.dist = actualNode.dist + 1;
			}
		}
	}

}
