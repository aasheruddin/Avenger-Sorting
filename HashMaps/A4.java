
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Collection;

/**
 * COMP 2503 Fall 2023 Assignment 4
 * 
 * This program must read a input stream and keeps track of the frequency at
 * which an avenger is mentioned either by name or alias or performer's last name. The program must use HashMaps
 * for keeping track of the Avenger Objects, and it must use TreeMaps
 * for storing the data. 
 * 
 * @author Maryam Elahi
 * @date Fall 2023
 */

public class A4 {

	public String[][] avengerRoster = { { "captainamerica", "rogers", "evans" }, { "ironman", "stark", "downey" },
			{ "blackwidow", "romanoff", "johansson" }, { "hulk", "banner", "ruffalo" },
			{ "blackpanther", "tchalla", "boseman" }, { "thor", "odinson", "hemsworth" },
			{ "hawkeye", "barton", "renner" }, { "warmachine", "rhodes", "cheadle" },
			{ "spiderman", "parker", "holland" }, { "wintersoldier", "barnes", "stan" } };

	private int topN = 4;
	private int totalwordcount = 0;
	private int mentionIndex = 0;
	private Scanner input = new Scanner(System.in);

	/* TODO:
	 * Create the necessary hashMap and treeMap objects to keep track of the Avenger objects 
	 * Remember that a hashtable does not keep any inherent ordering for its contents.
	 * But for this assignment we want to be able to create the sorted lists of avenger objects.
	 * Use TreeMap objects (which are binary search trees, and hence have an
	 * ordering) for creating the following orders: alphabetical, mention order, most popular avenger, and most popular performer
	 * The alphabetical order TreeMap must be constructed with the natural order of the Avenger objects.
	 * The other three orderings must be created by passing the corresponding Comparators to the 
	 * TreeMap constructor. 
	 */

	 private Map<String, Avenger> avengerHashMap = new HashMap<>();
	 private TreeMap<Avenger, Avenger> alphabeticalOrderMap = new TreeMap<>();
	 private TreeMap<Avenger, Avenger> mentionOrderMap = new TreeMap<>(new AvengerComparatorMentionOrder());
	 private TreeMap<Avenger, Avenger> mostPopularAvengerMap = new TreeMap<>(new AvengerComparatorFreqDesc());
	 private TreeMap<Avenger, Avenger> mostPopularPerformer = new TreeMap<>(new AvengerPerformerComparatorFreqDesc());

	
	
	public static void main(String[] args) {
		A4 a4 = new A4();
		a4.run();
	}

	public void run() {
		readInput();
		createdOrderedTreeMaps();
		printResults();
	}

	private void createdOrderedTreeMaps() {
		/* TODO:
		 * Create an iterator over the key set in the HashMap that keeps track of the avengers
		 * Add avenger objects to the treeMaps with different orderings.
		 * 
		 ** Hint: 
		 * Note that the HashMap and the TreeMap classes do not implement
		 * the Iterable interface at the top level, but they have
		 * methods that return Iterable objects, such as keySet() and entrySet().
		 * For example, you can create an iterator object over 
		 * the 'key set' of the HashMap and use the next() method in a loop
		 * to get each word object. 
		 */	
		
		 Iterator<String> iterator = avengerHashMap.keySet().iterator();

		/* Add avenger objects to the treeMaps with different orderings */
		while (iterator.hasNext()) {
			String avengerKey = iterator.next();
			Avenger avenger = avengerHashMap.get(avengerKey);
			

			alphabeticalOrderMap.put(avenger, avenger);
			mentionOrderMap.put(avenger, avenger);
			mostPopularAvengerMap.put(avenger, avenger);
			mostPopularPerformer.put(avenger, avenger);
		}
			
	
	}
	/**
	 * read the input stream and keep track how many times avengers are mentioned by
	 * alias or last name.
	 */
	private void readInput() {
		/*
		 * In a loop, while the scanner object has not reached end of stream, - read a
		 * word. - clean up the word - if the word is not empty, add the word count. -
		 * Check if the word is either an avenger alias or last name then - Create a new
		 * avenger object with the corresponding alias and last name. - if this avenger
		 * has already been mentioned, increase the corresponding frequency count for the object
		 * already in the hashMap. - if this avenger has not been mentioned before, add the
		 * newly created avenger to the hashMap, remember to set the frequency, and 
		 * to keep track of the mention order
		 */

		 while(input.hasNext()){

			//read and clean word
			String word = cleanWord(input.next());

			if (word.length() > 0){
				totalwordcount++;
			

			//create avenger object
			Avenger newAvengerObject = createAvengerObject(word);


			if(newAvengerObject != null){
				String avengerKey = newAvengerObject.getAlias();

				if(avengerHashMap.containsKey(avengerKey)){

					newAvengerObject.addFrequency(word);
					avengerHashMap.get(avengerKey).addFrequency(word);

				} else{

					newAvengerObject.addFrequency(word);
				 	newAvengerObject.setMentionIndex(mentionIndex++);
					avengerHashMap.put(newAvengerObject.getAlias(), newAvengerObject);
				}



				}




		 	}


		}
	}




	private Avenger createAvengerObject(String word) {
		for (int i = 0; i < avengerRoster.length; i++) {
			if (avengerRoster[i][0].equals(word) || avengerRoster[i][1].equals(word)
					|| avengerRoster[i][2].equals(word)) {
				return new Avenger(avengerRoster[i][0], avengerRoster[i][1], avengerRoster[i][2]);
			}
		}
		return null;
	}

	/**
     * Clean the given word by removing unwanted characters.
     * 
     * @param next The word to clean.
     * @return The cleaned word.
     */
	private String cleanWord(String next) {
		
		String ret;
		int inx = next.indexOf('\'');
		if (inx != -1)
			ret = next.substring(0, inx).toLowerCase().trim().replaceAll("[^a-z]", "");
		else
			ret = next.toLowerCase().trim().replaceAll("[^a-z]", "");
		return ret;
	}

	/**
	 * print the results
	 */
	private void printResults() {
		/*
		 * Please first read the documentation for TreeMap to see how to 
		 * iterate over a TreeMap data structure in Java.
		 *  
		 * Hint for printing the required list of avenger objects:
		 * Note that the TreeMap class does not implement
		 * the Iterable interface at the top level, but it has
		 * methods that return Iterable objects.
		 * You must either create an iterator over the 'key set',
		 * or over the values 'collection' in the TreeMap.
		 * 
		 */
		
		
		System.out.println("Total number of words: " + totalwordcount);
		System.out.println("Number of Avengers Mentioned: " + mentionOrderMap.size());
		System.out.println();

		System.out.println("All avengers in the order they appeared in the input stream:");
		printMap(mentionOrderMap);
		// Make sure you follow the formatting example in the sample output
		System.out.println();

		System.out.println("Top " + topN + " most popular avengers:");
		// Todo: Print the most popular avengers, see the instructions for tie breaking
		printTopN(mostPopularAvengerMap.values());
		// Make sure you follow the formatting example in the sample output
		System.out.println();

		System.out.println("Top " + topN + " most popular performers:");
		// Todo: Print the most popular performer, see the instructions for tie breaking
		printTopN(mostPopularPerformer.values());
		// Make sure you follow the formatting example in the sample output
		System.out.println();

		System.out.println("All mentioned avengers in alphabetical order:");
		// Todo: Print the list of avengers in alphabetical order
		printMap(alphabeticalOrderMap);
		System.out.println();
	}

	/**
	 * Print the elements from the given TreeMap using its iterator.
	 *
	 * @param map The TreeMap from which to print the elements.
	 */
	private void printMap(TreeMap<Avenger, Avenger> map) {
		Iterator<Map.Entry<Avenger, Avenger>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<Avenger, Avenger> entry = iterator.next();
			System.out.println(entry.getValue());
		}
	}

	
	private void printTopN(Collection<Avenger> list) {
		Iterator<Avenger> i = list.iterator();
		int count = 0;
		
		while(i.hasNext() && count < topN) {
			System.out.println(i.next());
			count++;
		}
	}
}
