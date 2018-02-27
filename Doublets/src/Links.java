import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Links implements LinksInterface {

	// All of the words in the user's chosen file are sorted by length and put
	// into an ArrayList.
	// Then those ArrayLists are stored in the HashMap "dictionary".
	// The lengths of the words are their keys.
	public HashMap<Integer, ArrayList<String>> dictionary = new HashMap<>();
	private HashMap<String, Set<String>> candidates = new HashMap<>();

	Links(String str) {
		FileReader reads;
		try {
			reads = new FileReader(str);
			Scanner scan = new Scanner(reads);

			// puts all dictionary in a dictionary
			while (scan.hasNextLine()) {
				String word = scan.nextLine();
				if (this.dictionary.get(word.length()) != null) {
					this.dictionary.get(word.length()).add(word);
				} else {
					ArrayList<String> ar = new ArrayList<>();
					ar.add(word);
					this.dictionary.put(word.length(), ar);
				}
			}

			// outer for loop goes through every key (words' lengths)
			// and selects the ArrayList of words of that length
			for (Integer num : this.dictionary.keySet()) {
				ArrayList<String> ofInterest = this.dictionary.get(num);
				// The candidates HashMap consists of keys and possible plays
				// from those keys (The possible plays are the values.)
				// This for loop selects the value that will be the key in the
				// candidates HashMap.
				// All values in the dictionary are eventually selected.
				for (String candKey : ofInterest) {
					Set<String> entries = new HashSet<String>();
					for (int j = 0; j < ofInterest.size(); j++) {
						int differences = 0;
						// counts the number of differences between the word1
						// seleced above and another word word2
						// if there is only one difference, adds the word2 to
						// the word1's set
						for (int i = 0; i < num; i++)
							if (candKey.charAt(i) != ofInterest.get(j).charAt(i)) {
								differences++;
							}

						if (differences == 1) {
							entries.add(ofInterest.get(j));
						}
					}
					if(!entries.isEmpty())
						this.candidates.put(candKey, entries);
					else
						this.candidates.put(candKey, null);
				}
			}
		} catch (

		FileNotFoundException exception) {
			System.out.println("Sorry but your dictionary selection is invalid.");
		}
	}

	public Set<String> getCandidates(String word) {
		return (Set<String>) this.candidates.get(word);
	}

	public boolean exists(String word) {
		return false;
	}
}
