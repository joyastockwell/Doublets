import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author Jutin Eccles and Joy Stockwell
 */
public class Doublets {
	private static LinksInterface links;

	public Doublets(LinksInterface links) {
		this.links = links;
	}

	public static void main(String[] args) {
		String end, start, type;
		Scanner scanner = new Scanner(System.in);
		Links startLinks = new Links("english.cleaned.all.35.txt");
		links = startLinks;
		System.out.println("Welcome to Doublets, a game of \"verbal torture.\"");

		while (true) {
			System.out.print("Enter starting word: ");
			start = scanner.nextLine();

			System.out.print("Enter ending word: ");
			end = scanner.nextLine();
			
			if(start.length() != end.length())
			{
				System.out.println("The word pair " + start + " and " + end + " is invalid.");
				continue;
			}

			System.out.print("Enter chain manager (s: stack, q: queue, p: priority queue, x: exit): ");
			type = scanner.nextLine();

			if (type.startsWith("x")) {
				break;
			}


			ChainManager manager = null;
			
			if (type.startsWith("s")) {
				manager = new StackChainManager();
			} else if (type.startsWith("q")) {
				manager = new QueueChainManager();
			} else if (type.startsWith("p")) {
				manager = new PriorityQueueChainManager(end);	
			} else {
				System.out.println("invalid selection");
				continue;
			}

			try {
			Chain ch = findChain(start, end, manager);
			System.out.println("Chain: " + ch.toString());
			System.out.println("Length: " + ch.length());
			System.out.println("Candidates: " + manager.getNumberOfNexts());
			System.out.println("Max Size: " + manager.maxSize());
			} catch (NullPointerException e){
				System.out.println("The word pair " + start + " and " + end + " is invalid.");
			}
		}
		scanner.close();
	}

	public static Chain findChain(String start, String end, ChainManager manager) {
		try {
			Set<String> startCandidates = links.getCandidates(start);
			for (String candidate : startCandidates) {
				LinkedHashSet<String> chainList = new LinkedHashSet<>();
				chainList.add(start);
				chainList.add(candidate);
				Chain chain = new Chain(chainList);
				manager.add(chain);
			}
		} catch (RuntimeException e) {
			return null;
		}

		manager.incrementNumNexts();

		while (!manager.isEmpty()) {
			Chain currentChain = manager.next();
			manager.incrementNumNexts();
			String lastInChain = currentChain.getLast();
			if (lastInChain.equals(end)) {
				return currentChain;
			}
			Set<String> candidates = links.getCandidates(lastInChain);

			if (candidates != null) {
				for (String candidate : candidates) {
					if (!currentChain.contains(candidate)) {
						LinkedHashSet<String> chainList = new LinkedHashSet<>();
						for (String inChain : currentChain) {
							chainList.add(inChain);
						}
						chainList.add(candidate);
						Chain chain = new Chain(chainList);

						manager.add(chain);
						manager.updateMax(manager.size());
					}
				}
			}
		}
		return null;

	}

}
