package puzzle22;

public class PartOne {
	public static void main(String[] args){
		int depth = 11109;
		Coordinate target = new Coordinate(9,731);
		int[][] eroLvl = new int[1000][1000];
		
//		depth = 510;
//		target = new Coordinate(10,10);
//		eroLvl = new int[15][15];
		
		/*Génération de la map d'érosion*/

		eroLvl[0][0] = (0+depth)%20183;
		eroLvl[target.x][target.y] = (0+depth)%20183;
		for(int i = 1; i<eroLvl.length;i++) {
			eroLvl[0][i] = ((i*48271)+depth)%20183;//x = 0
			eroLvl[i][0] = ((i*16807)+depth)%20183;//y = 0
		}
		
		for(int ix = 1; ix<eroLvl.length; ix++) {
			for(int jy = 1; jy<eroLvl.length; jy++) {
				if(ix!=target.x || jy!=target.y) {
					eroLvl[ix][jy] = ((eroLvl[ix-1][jy]*eroLvl[ix][jy-1])+depth)%20183;
				}
			}
		}
		
		/*Génération de la map de type*/
		
		char[][] typeReg = new char[1000][1000];
		
		for(int ix = 0; ix<eroLvl.length; ix++) {
			for(int jy = 0; jy<eroLvl.length; jy++) {
				int eroLvlAux = eroLvl[ix][jy]%3;
				if(eroLvlAux == 0) {//Rocky
					typeReg[ix][jy] = '.';
				}else if (eroLvlAux == 1) {//Wet
					typeReg[ix][jy] = '=';
				}else if (eroLvlAux == 2) {//Narrow
					typeReg[ix][jy] = '|';
				}
			}
		}
		
		/*Calcul du riskLvl*/
		
		int riskLvl = 0;
		
		for(int ix = 0; ix<=target.x; ix++) {
			for(int jy = 0; jy<=target.y; jy++) {
				if(typeReg[ix][jy] == '.') {//Rocky
					riskLvl += 0;
				}else if (typeReg[ix][jy] == '=') {//Wet
					riskLvl += 1;
				}else if (typeReg[ix][jy] == '|') {//Narrow
					riskLvl += 2;
				}
			}
		}
		
		System.out.println(riskLvl);
		
		/*DEBUG*/
		
//		for(int i = 0; i<eroLvl[0].length; i++) {
//			for(int j = 0; j<eroLvl.length; j++) {
//				System.out.print(typeReg[j][i]);
//			}
//			System.out.print("\n");
//		}
	}
}
