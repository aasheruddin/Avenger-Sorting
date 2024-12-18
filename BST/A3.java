
import java.util.Scanner;
import java.util.Iterator;


/**
 * COMP 2503 Fall 2023 Assignment 3 Avenger Statistics
 * 
 * This program reads a input stream and keeps track of the statistics for avengers
 * mentioned by name, alias, or performer's last name.
 * The program uses a BST
 * for storing the data. Multiple BSTs with alternative orderings are
 * constructed to show the required output.
 * 
 * @author Maryam Elahi
 * @date Fall 2023
 */

public class A3 {

	public String[][] avengerRoster = { { "captainamerica", "rogers", "evans" }, { "ironman", "stark", "downey" },
			{ "blackwidow", "romanoff", "johansson" }, { "hulk", "banner", "ruffalo" },
			{ "blackpanther", "tchalla", "boseman" }, { "thor", "odinson", "hemsworth" },
			{ "hawkeye", "barton", "renner" }, { "warmachine", "rhodes", "cheadle" },
			{ "spiderman", "parker", "holland" }, { "wintersoldier", "barnes", "stan" } };

	private int topN = 4;
	private int totalwordcount = 0;
	private Scanner input = new Scanner(System.in);
	private BST<Avenger> alphabeticalBST = new BST<>();
	private BST<Avenger> mentionBST = new BST<Avenger>(new AvengerComparatorMentionOrder());
	private BST<Avenger> mostPopularAvengerBST = new BST<Avenger>(new AvengerComparatorFreqDesc());
	private BST<Avenger> mostPopularPerformerBST = new BST<Avenger>(new AvengerPerformerComparatorFreqDesc());
	

	/**
     * Main method to instantiate the A3 class and run the program.
     * 
     * @param args Command line arguments (not used).
     */
	public static void main(String[] args) {
		A3 a3 = new A3();
		a3.run();
	}


	/**
     * Run the avenger statistics program.
     */
	public void run() {
		readInput();
		createdAlternativeOrderBSTs();
		printResults();
	}


	 /**
     * Create alternative order BSTs based on the alphabetical tree.
     */
	private void createdAlternativeOrderBSTs() {
		/* TODO:
		 *   - delete the following two avengers (if they exist) from the alphabetical tree
		 *   	- barton
		 *   	- banner
		 *   use the tree iterator to do an in-order traversal of the alphabetical tree,
		 *   and add avengers to the other trees with alternative ordering
		 */
		 
		 String[] avengersToDelete = { "barton", "banner" };

		 // Delete specified avengers from the alphabetical tree
		 for (String avengerName : avengersToDelete) {
			 Avenger avengerToDelete = createAvengerObject(avengerName);
			 alphabeticalBST.remove(avengerToDelete);
		 }
	 
	// Use iterator for in-order traversal of the alphabetical tree
		Iterator<Avenger> iterator = alphabeticalBST.iterator();
		while (iterator.hasNext()) {
			Avenger avenger = iterator.next();

		// Add avengers to other trees with alternative orderings
		mentionBST.addInOrder(avenger);
		mostPopularAvengerBST.addInOrder(avenger);
		mostPopularPerformerBST.addInOrder(avenger);
	}
		
	}
	 
		 
		
	

	

	/**
	 * read the input stream and keep track how many times avengers are mentioned by
	 * alias or last name or performer name.
	 */
	private void readInput() {
		/* Create a mention index counter and initialize it to 1
		 * While the scanner object has not reached end of stream, 
		 * 	- read a word. 
		 * 	- clean up the word 
		 * 	- if the word is not empty, add the word count. 
		 * 	- Check if the word is either an avenger alias or last name, or performer last name then
		 *		- Create a new avenger object with the corresponding alias and last name and performer last name.
		 *		- check if this avenger has already been added to the alphabetically ordered tree
		 *			- if yes, increase the corresponding frequency count for the object already in the tree.
		 *			- if no, add newly created avenger to the alphabetically ordered BST, 
		 *				- remember set the frequency and the mention index.
		 * You need to think care about how you are keeping track of the mention order by setting the mention order for each new avenger.
		 */
		//
		int mentionIndex = 1;
		while(input.hasNext()){
			//read and clean word
			String word = cleanWord(input.next());

			
			//if word not empty add word count
			if(word.length()> 0){

				//add word count
				totalwordcount++; 
			
			//create an object based on clean word
			Avenger newAvengerObject = createAvengerObject(word);
			
			// Check if the Avenger object is successfully created
			if(newAvengerObject != null){

				if(alphabeticalBST.contains(newAvengerObject)){
					Avenger exisAvenger = alphabeticalBST.find(newAvengerObject);
					exisAvenger.addFrequency(word);
					exisAvenger.setMentionIndex(mentionIndex);

				} else{
					newAvengerObject.addFrequency(word);
					newAvengerObject.setMentionIndex(mentionIndex);
					alphabeticalBST.add(newAvengerObject);
				}

				mentionIndex++;
			}

		
			

				
			}
		}
		

	}

	 /**
     * Create an Avenger object based on the given word.
     * 
     * @param word The word to create the Avenger object.
     * @return The Avenger object created from the word, or null if not found in the roster.
     */
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
     * Calculate the optimal height for a given BST based on its size.
     * 
     * @param list The BST for which to calculate the optimal height.
     * @return The optimal height for the given BST.
     */
	private int getOptimalHeight(BST<Avenger> list){
		if (list.size() <= 1){
			return list.height();
		}
		return (int) Math.floor((Math.log(list.size()) / Math.log(2)));
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
		// Todo: Print the total number of words (this total should not include words that are all digits or punctuation.)
		System.out.println("Total number of words: " + totalwordcount);
		// TODO: Print the number of mentioned avengers after deleting "barton" and "banner".
		System.out.println("Number of Avengers Mentioned: " + mentionBST.size());
		System.out.println();

		System.out.println("All avengers in the order they appeared in the input stream:");
		// TODO: Print the list of avengers in the order they appeared in the input
		printList(mentionBST);
		// Make sure you follow the formatting example in the sample output
		System.out.println();
		
		System.out.println("Top " + topN + " most popular avengers:");

		// TODO: Print the most popular avengers, see the instructions for tie breaking
		printTopN(mostPopularAvengerBST);
		// Make sure you follow the formatting example in the sample output
		System.out.println();

		System.out.println("Top " + topN + " most popular performers:");
		// TODO: Print the most popular performers, see the instructions for tie breaking
		printTopN(mostPopularPerformerBST);
		// Make sure you follow the formatting example in the sample output
		System.out.println();

		System.out.println("All mentioned avengers in alphabetical order:");
		// TODO: Print the list of avengers in alphabetical order
		alphabeticalBST.printInOrder();
		System.out.println();

		// TODO: Print the actual height and the optimal height for each of the four trees.
	System.out.println("Height of the mention order tree is : " + mentionBST.height() 
				+ " (Optimal height for this tree is : " + getOptimalHeight(mentionBST) + ")");
	System.out.println("Height of the alphabetical tree is : " + alphabeticalBST.height() 
	+ " (Optimal height for this tree is : " + getOptimalHeight(alphabeticalBST) + ")" );
		System.out.println("Height of the most frequent tree is : " + mostPopularAvengerBST.height()
				+ " (Optimal height for this tree is : " + getOptimalHeight(mostPopularAvengerBST) + ")");
		System.out.println("Height of the most frequent performer tree is : " + mostPopularPerformerBST.height()
		+ " (Optimal height for this tree is : " + getOptimalHeight(mostPopularPerformerBST) + ")");
	}

	  /**
     * Print the top N elements from the given BST using its iterator.
     * 
     * @param list The BST from which to print the top N elements.
     */
	private void printTopN(BST<Avenger> list) {
		Iterator<Avenger> i = list.iterator();
		int count = 0;
		
		while(i.hasNext() && count < topN) {
			System.out.println(i.next());
			count++;
		}
	}

	private void printList(BST<Avenger> list) {
		for (Avenger hero : list) {
		   System.out.println(hero);
		}
	 }

	
	
}
