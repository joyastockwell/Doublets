import java.util.Iterator;
import java.util.LinkedHashSet;

public class Chain implements Iterable<String> {

	private LinkedHashSet linked;
	//idea: make binary nodes, then make last thing be first thing
	//in list of binary nodes, and have the last thing point to the rest

	Chain() {
		this.linked = new LinkedHashSet<String>();
	}

	Chain(LinkedHashSet set) {
		this.linked = set;
	}

	public Chain addLast(String str) {
		LinkedHashSet adder = new LinkedHashSet(this.linked);
		adder.add(str);
		return new Chain(adder);
	}
	
	public String getLast()
	{
		Iterator<String> iter = iterator();
		String lastWord = "";
		while(iter.hasNext())
		{
			lastWord = iter.next();
		}
		return lastWord;
	}
	
	public int length()
	{
		Iterator<String> iter = iterator();
		int count = 0;
		while(iter.hasNext())
		{
			count++;
			iter.next();
		}
		return count;
	}

	public boolean contains(String str) {
		return this.linked.contains(str);
	}
	
	public String toString()
	{
		return this.linked.toString();
	}

	@Override
	public Iterator<String> iterator() {
		return this.linked.iterator();
	}

}
