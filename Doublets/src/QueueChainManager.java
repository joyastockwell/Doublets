import java.util.LinkedList;

public class QueueChainManager extends ChainManager {
	
	LinkedList<Chain> chainList;
	
	public QueueChainManager()
	{
		this.chainList = new LinkedList<>();
	}

	@Override
	public void add(Chain chain) {
		this.chainList.addLast(chain);

	}

	@Override
	public Chain next() {
		return this.chainList.removeFirst();
	}

	@Override
	public boolean isEmpty() {
		return this.chainList.isEmpty();
	}
	
	@Override
	public int size()
	{
		return this.chainList.size();
	}

}
