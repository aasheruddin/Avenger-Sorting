import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * COMP 2503 Fall 2023 Assignment 1
 * 
 * This program reads a input stream and keeps track of the statistics for avengers
 * mentioned by name, alias, or performer's last name.
 *
 * @author Maryam Elahi
 * @date Fall 2023
 */

public class A1 {

	public String[][] avengerRoster = { { "captainamerica", "rogers", "evans" }, { "ironman", "stark", "downey" },
			{ "blackwidow", "romanoff", "johansson" }, { "hulk", "banner", "ruffalo" },
			{ "blackpanther", "tchalla", "boseman" }, { "thor", "odinson", "hemsworth" },
			{ "hawkeye", "barton", "renner" }, { "warmachine", "rhodes", "cheadle" },
			{ "spiderman", "parker", "holland" }, { "wintersoldier", "barnes", "stan" } };

	private int topN = 4;
	private Scanner input = new Scanner(System.in);
	private int totalwordcount = 0;
	private ArrayList<Avenger> avengersArrayList = new ArrayList<Avenger>();

	public static void main(String[] args) {
		A1 a1 = new A1();
		a1.run();
	}

	public void run() {
		readInput();
		printResults();
	}

	/**
	 * read the input stream and keep track  
	 * how many times avengers are mentioned by alias or last name or performer's last name.
	 */
	private void readInput() {
		/*
		In a loop, while the scanner object has not reached end of stream,
		 	- read a word.
		 	- clean up the word
		    - if the word is not empty, add the word count. 
		    - Check if the word is either an avenger alias or last name, or performer last name then
				- Create a new avenger object with the corresponding alias and last name and performer last name.
				- if this avenger has already been mentioned, increase the corresponding frequency count for the object already in the list.
				- if this avenger has not been mentioned before, add the newly created avenger to the list, remember to update the corresponding frequency.
		*/ 
		
        while (input.hasNext()) {
            String word = input.next();

            word = preprocessWord(word);
            

            if (!word.isEmpty()) {
                
            	totalwordcount++;
            	
                for (String[] avengerInfo : avengerRoster) {
                    String alias = avengerInfo[0];
                    String lastName = avengerInfo[1];
                    String performer = avengerInfo[2];

                    if (word.equals(alias) || word.equals(lastName) || word.equals(performer)) {
                        
                    	boolean avengerFound = false;
                        for (Avenger avenger : avengersArrayList) {
                            if (avenger.getHeroAlias().equals(word)) {
                            	avengerFound = true;
                            	avenger.setAliasFreq(avenger.getAliasFreq() + 1);
                                break;
                            }
                            else if (avenger.getHeroName().equals(word)) {
                            	avengerFound = true;
                            	avenger.setNameFreq(avenger.getNameFreq() + 1);
                                break;
                            }
                            else if (avenger.getPerformer().equals(word)) {
                            	avengerFound = true;
                            	avenger.setPerformerFreq(avenger.getPerformerFreq() + 1);
                                break;
                            }
                        }
                        if (!avengerFound) {
                            Avenger newAvenger = new Avenger(alias, lastName, performer);
                            if (word.equals(alias)) {
                            	newAvenger.setAliasFreq(1);
                            }
                            else if (word.equals(performer)) {
                            	newAvenger.setPerformerFreq(1);
                            }
                            else if (word.equals(lastName)) {
                            	newAvenger.setNameFreq(1);
                            }
                            avengersArrayList.add(newAvenger);
                        }
                        break;
                        
                    }
                }
            }
        }
        
    }
	
	/**
	 * Clean up each word from input.
	 * @param word
	 * @return Cleaned up word.
	 */
    private String preprocessWord(String word) {
    	if (word.contains("'")) {
         	String[] parts = word.split("'");
         	word = parts[0];
        }
    	word = word.trim();
        word = word.toLowerCase();
        word = word.replaceAll("[^a-zA-Z]+", "");
        return word;
    }

	
	/*
	 * Create helper functions as needed
	 */


	/**
	 * print the results
	 */
	private void printResults() {
		/*
		 * Make sure your format matches the sample output exactly. 
		 * No extra empty lines, or text. Use the diff command to check if your output matches the sample outputs.
		 */
		System.out.println("Total number of words: " + totalwordcount);
		System.out.println("Number of Avengers Mentioned: " + avengersArrayList.size());
		System.out.println();

		// Print all ordered by appearance
		System.out.println("All avengers in the order they appeared in the input stream:");
		for (Avenger avenger : avengersArrayList) {
			System.out.println(avenger.toString());
		}
		System.out.println();

		// Print all ordered by AvengerComparatorFreqDesc
		System.out.println("Top " + topN + " most popular avengers:");
		Collections.sort(avengersArrayList, new AvengerComparatorFreqDesc());
		int avengerIndex = 0;
		while (avengerIndex < 4 && avengerIndex < avengersArrayList.size()) {
			System.out.println(avengersArrayList.get(avengerIndex).toString());
			avengerIndex++;
		}
		System.out.println();

		// Print top n ordered by AvengerPerformerComparatorFreqDesc
		System.out.println("Top " + topN + " most popular performers:");
		Collections.sort(avengersArrayList, new AvengerPerformerComparatorFreqDesc());
		avengerIndex = 0;
		while (avengerIndex < 4 && avengerIndex < avengersArrayList.size()) {
			System.out.println(avengersArrayList.get(avengerIndex).toString());
			avengerIndex++;
		}
		System.out.println();

		// Print all ordered by alias alphabetically
		System.out.println("All mentioned avengers in alphabetical order:");
		Collections.sort(avengersArrayList);
		for (Avenger avenger : avengersArrayList) {
			System.out.println(avenger.toString());
		}
		System.out.println();
	}
}
