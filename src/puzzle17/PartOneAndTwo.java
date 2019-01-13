package puzzle17;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PartOneAndTwo {
	
	public static void main(String[] args) throws IOException {
		File file = new File("src/puzzle17/input.txt");
//		file = new File("src/puzzle17/test.txt");

		BufferedReader br = new BufferedReader(new FileReader(file));
		String lineRead;
		char[][] ground = new char[300][1817];//Max y
		int xoffset = 400;
		
		/******Génération du ground******/
		
		for(char[] line : ground) {
			Arrays.fill(line, '.');
		}
		ground[500-xoffset][0] = '+';
		
		Pattern px = Pattern.compile("^x=(\\d+), y=(\\d+)\\.\\.(\\d+)$");//Group 1 = x, group 2-3 = y->y
		Pattern py = Pattern.compile("^y=(\\d+), x=(\\d+)\\.\\.(\\d+)$");
		while((lineRead = br.readLine()) != null) {
			Matcher m = px.matcher(lineRead);
			if(m.find()) {//x statique
				for(int i = Integer.parseInt(m.group(2)); i<= Integer.parseInt(m.group(3));i++) {
					ground[Integer.parseInt(m.group(1))-xoffset][i] = '#';
				}
			}else{
				m = py.matcher(lineRead);
				m.find();
				for(int i = Integer.parseInt(m.group(2)); i<= Integer.parseInt(m.group(3));i++) {
					ground[i-xoffset][Integer.parseInt(m.group(1))] = '#';
				}
			}
		}
		br.close();
		
		/*Gestion de la simulation*/
		int nbUpdate = 0;
		
		while (nbUpdate < 1000) {
			for(int i = 0; i<ground[0].length-1; i++) {
				for(int j = 0; j<ground.length; j++) {
					//Cas source
					if(ground[j][i] == '+') {
						ground[j][i+1] = '|';
					}
					//Cas chute
					if(ground[j][i] == '|' && ground[j][i+1] == '.') {
						ground[j][i+1] = '|';
					//Cas fond réservoir
					}else if(ground[j][i] == '|' && ground[j][i+1] == '#') {
						////////
						ground[j][i] = '~';
						////////
						boolean leftBound = true;
						int jbase = j;
						while(leftBound) {
							jbase--;
							if(ground[jbase][i] == '#') {
								leftBound = false;
							}else {
								ground[jbase][i] = '~';
							}							
						}
						////////
						boolean rightBound = true;
						jbase = j;
						while(rightBound) {
							jbase++;
							if(ground[jbase][i] == '#') {
								rightBound = false;
							}else {
								ground[jbase][i] = '~';
							}							
						}
					//Cas remplissage
					}else if(ground[j][i] == '|' && ground[j][i+1] == '~') {
						/////////
						ground[j][i] = '~';
						/////////
						boolean topReached = false;
						int jleft = 0;
						int jright = 0;
						
						boolean leftBound = true;
						int jbase = j;
						while(leftBound) {
							jbase--;
							if(ground[jbase][i] == '#') {
								leftBound = false;
							}else if(ground[jbase][i+1] == '#'){
								/////////
								topReached = true;
								leftBound = false;
								jleft = jbase;
								ground[jbase][i] = '~';
								ground[jbase-1][i] = '|';
								ground[j][i] = '*';
								
							}else{
								ground[jbase][i] = '~';
							}							
						}
						////////
						boolean rightBound = true;
						jbase = j;
						while(rightBound) {
							jbase++;
							if(ground[jbase][i] == '#') {
								rightBound = false;
							}else if(ground[jbase][i+1] == '#'){
								/////////
								topReached = true;
								rightBound = false;
								jright = jbase;
								ground[jbase][i] = '~';
								ground[jbase+1][i] = '|';
								ground[j][i] = '*';
								
							}else{
								ground[jbase][i] = '~';
							}							
						}
						//Cas debordement
						if(topReached) {
							int k = 0;
							boolean filled = false;
							if(jleft != 0) {
								k = jleft;
								while(!filled){
									if(ground[k+1][i] == '#' || ground[k+1][i] == '|') {
										ground[k][i] = '*';
										filled = true;
									}else {
										ground[k][i] = '*';
										k++;
									}
								}
							}else {
								k = jright;
								while(!filled){
									if(ground[k-1][i] == '#' || ground[k-1][i] == '|') {
										ground[k][i] = '*';
										filled = true;
									}else {
										ground[k][i] = '*';
										k--;
									}
								}
							}
						}
					}
					//Cas particuliers du aux boites dans les boites
					if(ground[j][i] == '~' && ground[j][i+1] == '.') {
						ground[j][i+1] = '~';
					}
					if(ground[j][i] == '~' && ground[j+1][i] == '.') {
						 ground[j+1][i] = '~';
					}
					if(ground[j][i] == '*' && ground[j+1][i] == '|' && ground[j+2][i] == '*') {
						ground[j][i] = '~';
						
						boolean leftBound = true;
						int jbase = j;
						while(leftBound) {
							jbase--;
							if(ground[jbase][i] == '#') {
								leftBound = false;
							}else {
								ground[jbase][i] = '~';
							}
						}
						
						boolean rightBound = true;
						jbase = j;
						while(rightBound) {
							jbase++;
							if(ground[jbase][i] == '#') {
								rightBound = false;
							}else {
								ground[jbase][i] = '~';
							}
						}
					}
				}
			}
			nbUpdate++;
		}
		
		int waterTile = 0;
		int waterTileRetained = 0;
		for(int i = 0; i<ground[0].length; i++) {
			for(int j = 0; j<ground.length; j++) {
				if(ground[j][i] == '|' || ground[j][i] == '*' || ground[j][i] == '~') {
					waterTile++;
					if(ground[j][i] == '~') {
						waterTileRetained++;
					}
				}
			}
		}
		
		/*PART ONE*/
		System.out.println(waterTile-2);//Offset du au min y
		
		/*PART TWO*/
		System.out.println(waterTileRetained);//Offset du au min y
		
		/*****Debug*****/
		
		File filew = new File("src/puzzle17/output.txt");
		BufferedWriter bw = new BufferedWriter(new FileWriter(filew));
		
		for(int i = 0; i<ground[0].length; i++) {
			for(int j = 0; j<ground.length; j++) {
				bw.write(ground[j][i]);
			}
			bw.newLine();
			bw.flush();
		}
		
		bw.close();
		
		System.out.println();
	}
	
	
	
}
