package puzzle11;

public class PartOneAndTwo {

	public static void main(String[] args) {
		final int input = 5153;//5153
		int[][] powerGrid = new int[300][300];
		
		for(int i = 0; i<powerGrid.length; i++) {//Génération de la power grid
			for(int j = 0; j<powerGrid.length; j++) {
				int rackID = i+11;
				int powerLvl = rackID*(j+1);
				powerLvl += input;
				powerLvl *= rackID;
				powerLvl = (powerLvl < 100) ? 0:((powerLvl/100)%10) ;
				powerLvl -= 5;
				powerGrid[i][j] = powerLvl;
				
//				if(i == 121 && j == 78) {
//					System.out.println(powerGrid[i][j] + " " + (i+1) + "|" + (j+1));
//				}		
			}
		}
		
		/*PART ONE*/
		
		partOneGen(powerGrid);
		
		/*PART TWO*/
		
		partTwoGen(powerGrid);
	}
	
	public static void partOneGen(int[][] grid) {//Calcul de la sub-grid 3x3 max
		int maxPower = 0;
		int cornerIdX = 0;
		int cornerIdY = 0;
		for(int i = 0; i<grid.length-2; i++) {
			for(int j = 0; j<grid.length-2; j++) {
				int powerSubGrid = sumGrid3(grid, i, j);
				if(maxPower < powerSubGrid) {
					maxPower = powerSubGrid;
					cornerIdX = i+1;
					cornerIdY = j+1;
				}
			}
		}
		System.out.println("(X,Y) " + cornerIdX + "," + cornerIdY);
	}
	
	public static void partTwoGen(int[][] grid) {//Calcul de la sub-grid max
		int maxPower = 0;
		int cornerIdX = 0;
		int cornerIdY = 0;
		int subGridSize = 1;
		for(int size = 0; size<grid.length; size++) {
	
			for(int i = 0; i<grid.length-size; i++) {
				for(int j = 0; j<grid.length-size; j++) {
					
					int powerSubGrid = sumGrid(grid, i, j, size);
					
					if(maxPower < powerSubGrid) {
						maxPower = powerSubGrid;
						cornerIdX = i+1;
						cornerIdY = j+1;
						subGridSize = size+1;
					}
				}
			}
			System.out.println(size);
		}
		System.out.println("(X,Y,size) " + cornerIdX + "," + cornerIdY + "," + subGridSize);
	}
	
	public static int sumGrid(int[][] grid, int ip, int jp, int size) {
		int res = 0;
		
		for(int i = ip; i<=ip+size; i++) {
			for(int j = jp; j<=jp+size; j++) {
				res += grid[i][j];
			}
		}
		
		return res;
	}
	
	public static int sumGrid3(int[][] grid, int i, int j) {
		int res = 0;
		res += grid[i][j] + grid[i][j+1] + grid[i][j+2];
		res += grid[i+1][j] + grid[i+1][j+1] + grid[i+1][j+2];
		res += grid[i+2][j] + grid[i+2][j+1] + grid[i+2][j+2];
		return res;
	}
}
