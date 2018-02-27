import java.util.PriorityQueue;

public class PriorityQueueChainManager extends ChainManager {

	String tail;
	PriorityQueue<Entry> pq = new PriorityQueue<>();

	public PriorityQueueChainManager(String tail) {
		this.tail = tail;
	}

	public int diff(String curr) {
		int retVal = 0;
		for (int i = 0; i < this.tail.length(); i++) {
			if (curr.charAt(i) != this.tail.charAt(i)) {
				retVal++;
			}
		}
		return retVal;
	}

	@Override
	public void add(Chain chain) {
		Entry en = new Entry(chain);
		this.pq.add(en);
	}

	@Override
	public Chain next() {
		return this.pq.remove().getCh();
	}

	@Override
	public boolean isEmpty() {
		return this.pq.isEmpty();
	}

	@Override
	public int size() {
		return this.pq.size();
	}

	private class Entry implements Comparable {

		int value;
		Chain ch;

		public Entry(Chain ch) {
			this.ch = ch;
			this.value = ch.length() + diff(ch.getLast());
		}

		public int getValue() {
			return this.value;
		}
		
		public Chain getCh() {
			return this.ch;
		}

		@Override
		public int compareTo(Object o) {
			return this.value - ((Entry)o).value;
		}

	}

}
