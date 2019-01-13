package puzzle14;

import puzzle09.CircleList;
import puzzle09.Maillon;

public class PartTwo {
	public static void main(String[] args) {
		Maillon<Integer> r1 = new Maillon<Integer>(3);
		Maillon<Integer> r2 = new Maillon<Integer>(7);
		r1.pred = r2;
		r1.succ = r2;
		r2.pred = r1;
		r2.succ = r1;
		CircleList<Integer> recipeList = new CircleList<Integer>(r2);

		Maillon<Integer> elf1 = r1;
		Maillon<Integer> elf2 = r2;

		String pattern = "047801";//INPUT
		int ListLength = 2;
		boolean isNotMatched = true;

		while(isNotMatched) {
			int newRecipe = elf1.value + elf2.value;//Generation des nouvelles recettes
			String newRecipeString = Integer.toString(newRecipe);
			for(int i = 0; i<newRecipeString.length();i++) {//Insertion des recettes dans la circleList
				recipeList.ajouterMaillon(new Maillon<Integer>(newRecipeString.charAt(i)-48));//L'élément courant est déplacé en queue de liste
				String matcher = recipeList.current.pred.pred.pred.pred.pred.value.toString()//On cherche le motif
						+recipeList.current.pred.pred.pred.pred.value.toString()
						+recipeList.current.pred.pred.pred.value.toString()
						+recipeList.current.pred.pred.value.toString()
						+recipeList.current.pred.value.toString()
						+recipeList.current.value.toString();
				if(matcher.equals(pattern)) {//Si le motif est trouvé, on sort de la boucle
					isNotMatched = false;
					ListLength++;
					break;
				}
				ListLength++;
			}
			int nelf1 = elf1.value + 1;//Placement des lutins sur leurs nouvelles recettes
			int nelf2 = elf2.value + 1;
			for(int i = 0; i<nelf1; i++) {
				elf1 = elf1.succ;
			}
			for(int i = 0; i<nelf2; i++) {
				elf2 = elf2.succ;
			}
//			if(ListLength%1000000 == 0) {
//				System.out.println(ListLength);
//			}
		}
		
		System.out.println(ListLength-6);
	}
}
