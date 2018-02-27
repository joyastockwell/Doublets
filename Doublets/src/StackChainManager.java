import java.util.Stack;

public class StackChainManager extends ChainManager {
	
	private Stack<Chain> chainStack;
	
	public StackChainManager()
	{
		this.chainStack = new Stack();
	}

	@Override
	public void add(Chain chain) {
		this.chainStack.push(chain);

	}

	@Override
	public Chain next() {
		return this.chainStack.pop();
	}

	@Override
	public boolean isEmpty() {
		return this.chainStack.isEmpty();
	}
	
	@Override
	public int size()
	{
		return this.chainStack.size();
	}

}
