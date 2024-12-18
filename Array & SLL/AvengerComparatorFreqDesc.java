import java.util.Comparator;

/**
 * This class compares two Avengers by checking which one has a higher overall mention frequency
 * (alias mentions + name mentions + performer mentions). Breaks tie if necessary.
 * @author sammy
 *
 */
public class AvengerComparatorFreqDesc implements Comparator<Avenger> {

	@Override
	public int compare(Avenger o1, Avenger o2) {
		
		int result = 0;
		int o1Mentions = o1.getAliasFreq() + o1.getNameFreq() +o1.getPerformerFreq();
		int o2Mentions = o2.getAliasFreq() + o2.getNameFreq() +o2.getPerformerFreq();
		boolean checkFurther = false;
		int currentChar = 0;
		
		
		if (o1Mentions > o2Mentions) {
			result = -1;
		}
		else if (o1Mentions < o2Mentions) {
			result = 1;
		}
		
		// Tie break, alphabetical order of performer.
		else if (o1Mentions == o2Mentions) {
			if (o1.getPerformer().toLowerCase().charAt(0) < o2.getPerformer().toLowerCase().charAt(0)) {
				result = -1;
			}
			else if (o1.getPerformer().toLowerCase().charAt(0) > o2.getPerformer().toLowerCase().charAt(0)) {
				result = 1;
			}
			else if (o1.getPerformer().toLowerCase().charAt(0) == o2.getPerformer().toLowerCase().charAt(0)) {
				checkFurther = true;
				while (checkFurther) {
					currentChar ++;
					if (o1.getPerformer().toLowerCase().charAt(currentChar) < o2.getPerformer().toLowerCase().charAt(currentChar)) {
						result = -1;
						checkFurther = false;
					}
					else if (o1.getPerformer().toLowerCase().charAt(currentChar) > o2.getPerformer().toLowerCase().charAt(currentChar)) {
						result = 1;
						checkFurther = false;
					}
					else if (o1.getPerformer().toLowerCase().charAt(currentChar) == o2.getPerformer().toLowerCase().charAt(currentChar)) {
						checkFurther = true;
					}
				}
			}
		}
	
		return result;
		}


}
