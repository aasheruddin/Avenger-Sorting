
//import java.util.ArrayList;
import java.util.Scanner;

/** 
 * COMP 2503 Fall 2023 Assignment 2 
 * 
 * This program reads a input stream and keeps track of the statistics for avengers
 * mentioned by name, alias, or performer's last name.
 *
 * @author Maryam Elahi
 * @date Fall 2023
*/

public class A2 {

	public String[][] avengerRoster = { { "captainamerica", "rogers", "evans" }, { "ironman", "stark", "downey" },
			{ "blackwidow", "romanoff", "johansson" }, { "hulk", "banner", "ruffalo" },
			{ "blackpanther", "tchalla", "boseman" }, { "thor", "odinson", "hemsworth" },
			{ "hawkeye", "barton", "renner" }, { "warmachine", "rhodes", "cheadle" },
			{ "spiderman", "parker", "holland" }, { "wintersoldier", "barnes", "stan" } };

	private int topN = 4;
	private int totalwordcount = 0;
	private Scanner input = new Scanner(System.in);
	//private ArrayList<Avenger> avengersArrayList = new ArrayList<Avenger>();
	
	private SLL<Avenger> mentionList = new SLL<Avenger>();
	private SLL<Avenger> alphabticalList = new SLL<Avenger>();
	private SLL<Avenger> mostPopularAvenger = new SLL<Avenger>(new AvengerComparatorFreqDesc());
	private SLL<Avenger> mostPopularPerformer = new SLL<Avenger>(new AvengerPerformerComparatorFreqDesc());
	
	public static void main(String[] args) {
		A2 a1 = new A2();
		a1.run();
	}

	public void run() {
		readInput();
		createdOrderedLists();
		printResults();
	}

	private void createdOrderedLists() {
		// TODO: 
		/* Create a mover and traverse through the mentionList.
		// Add each avenger to the other three lists. 
		// Hint: For this assignment you can expose the reference 
		// 	     to the first element in the linked list, and create a mover to traverse the list.
		//       (The better solution is to create an iterator, but we haven't learned about them, 
		// 		  will talk about iterators later.)
		*/ 
		Node<Avenger> mover = mentionList.getHead();
		 
		while (mover != null) {
			Avenger avenger = mover.getData();

			alphabticalList.addInOrder(avenger);

			mostPopularAvenger.addInOrder(avenger);

			mostPopularPerformer.addInOrder(avenger);

			mover = mover.getNext();
		}
		
	}

	

	/**
	 * read the input stream and keep track  
	 * how many times avengers are mentioned by alias or last name.
	 */
	private void readInput() {
		/*
		 In a loop, while the scanner object has not reached end of stream,
		 	- read a word.
		 	- clean up the word
		    - if the word is not empty, add the word count. 
		    - Check if the word is either an avenger alias or last name, or performer last name then
				- Create a new avenger object with the corresponding alias and last name and performer last name.
				- if this avenger has already been mentioned, increase the corresponding frequency count for the object already in the mentionList.
				- if this avenger has not been mentioned before, add the newly created avenger to the mentionList, remember to update the corresponding frequency.
		*/
		while (input.hasNext()) {
			String word = cleanWord(input.next());
	
			if (!word.isEmpty()) {
				totalwordcount++;
	
				for (String[] avengerInfo : avengerRoster) {
					String alias = avengerInfo[0].toLowerCase() ; // Convert to lowercase for case-insensitive comparison
					String lastName = avengerInfo[1].toLowerCase();
					String performer = avengerInfo[2].toLowerCase();

					
	
					if (word.equals(alias) || word.equals(lastName) || word.equals(performer)) {
						Avenger avenger = new Avenger(alias, lastName, performer);
						// Check if the Avenger is already in mentionList
						Avenger existingAvenger = mentionList.find(avenger);
	
						if (existingAvenger != null) {
							// Increment the frequency counts
							//existingAvenger.incrementCounts();
							if(word.equals(alias)) {
								existingAvenger.incrementAliasFreq();
							}
							if(word.equals(lastName)) {
								existingAvenger.incrementNameFreq();
							}
							
							if(word.equals(performer)) {
								existingAvenger.incrementPerformerFreq();
							}
						} else {
							// The Avenger is not in the mentionList, add it
							if(word.equals(alias)) {
								existingAvenger.incrementAliasFreq();
							}
							if(word.equals(lastName)) {
								existingAvenger.incrementNameFreq();
							}
							
							if(word.equals(performer)) {
								existingAvenger.incrementPerformerFreq();
							}
							mentionList.add(avenger);
						}
						break;  // No need to continue searching once a match is found
					}
				}
			}
		}
        
	}

	
	
	


	
	
	
	
	private String cleanWord(String next) {
		// First, if there is an apostrophe, the substring
		// before the apostrophe is used and the rest is ignored.
		// Words are converted to all lowercase.
		// All other punctuation and numbers are skipped.
		String ret;
		int inx = next.indexOf('\'');
		if (inx != -1)
			ret = next.substring(0, inx).toLowerCase().trim().replaceAll("[^a-z]", "");
		else
			ret = next.toLowerCase().trim().replaceAll("[^a-z]", "");
		return ret;
	}

	
	


	private void printResults() {
		System.out.println("Total number of words: " + totalwordcount);
		System.out.println("Number of Avengers Mentioned: " + mentionList.size()); // Use size() to get the number of avengers mentioned
		System.out.println();
	
		System.out.println("All avengers in the order they appeared in the input stream:");
		Node<Avenger> mentionMover = mentionList.getHead();
		while (mentionMover != null) {
			Avenger avenger = mentionMover.getData();
			System.out.println(avenger.toString());
			mentionMover = mentionMover.getNext();
		}
		System.out.println();
	
		System.out.println("Top " + topN + " most popular avengers:");
		Node<Avenger> popularAvengerMover = mostPopularAvenger.getHead();
		for (int i = 0; i < topN && popularAvengerMover != null; i++) {
			Avenger avenger = popularAvengerMover.getData();
			System.out.println(avenger.toString());
			popularAvengerMover = popularAvengerMover.getNext();
		}
		System.out.println();
	
		System.out.println("Top " + topN + " most popular performers:");
		Node<Avenger> popularPerformerMover = mostPopularPerformer.getHead();
		for (int i = 0; i < topN && popularPerformerMover != null; i++) {
			Avenger avenger = popularPerformerMover.getData();
			System.out.println(avenger.toString());
			popularPerformerMover = popularPerformerMover.getNext();
		}
		System.out.println();
	
		System.out.println("All mentioned avengers in alphabetical order:");
		Node<Avenger> alphabeticalMover = alphabticalList.getHead();
		while (alphabeticalMover != null) {
			Avenger avenger = alphabeticalMover.getData();
			System.out.println(avenger.toString());
			alphabeticalMover = alphabeticalMover.getNext();
		}
		System.out.println();
	}
	
}
