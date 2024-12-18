import java.util.Comparator;

/**
 * This comparator class compares two avengers by checking which one has a higher performer frenquency.
 * Breaks tie if necessary.
 * @author sammy
 *
 */
public class AvengerPerformerComparatorFreqDesc implements Comparator<Avenger> {

	@Override
	public int compare(Avenger o1, Avenger o2) {
		
		int result = 0;
		boolean checkFurther = false;
		int currentChar = 0;
		
		if (o1.getPerformerFreq() > o2.getPerformerFreq()) {
			result = -1;
		}
		else if (o1.getPerformerFreq() < o2.getPerformerFreq()) {
			result = 1;
		}
		
		// First tie break, length of Hero's name.
		else if (o1.getPerformerFreq() == o2.getPerformerFreq()) {
			
			if (o1.getHeroName().length() < o2.getHeroName().length()) {
				result = -1;
			}
			else if (o1.getHeroName().length() > o2.getHeroName().length()) {
				result = 1;
			}
			
			// Second tie break, alphabetical order of alias.
			else if (o1.getHeroName().length() == o2.getHeroName().length()) {
				
				result = o1.compareTo(o2);
			}
			
			
			
		}
		
		return result;
	}

}
