package BlockChainSim;
import java.lang.reflect.Array;

//�� Ʈ���� Sum Tree ������ ���� Generic Tree Type
public abstract class SumTree<T>{
	private T[] nodes;
	private int leaf_length = 0;
	
	public SumTree(Class<T[]> classtype, T[] leafs, T init){
		
		//Tree�� ũ�� ����
		int logValue = (int) (Math.log(leafs.length-1)/Math.log(2));
		int nodesLength = (int) Math.pow(2, logValue +1) -1 + leafs.length;
		
		this.nodes = classtype.cast( Array.newInstance(classtype.getComponentType(), nodesLength));
		this.leaf_length = leafs.length;
		
		//��� �ʱ�ȭ
		for(int i=0; i<this.nodes.length; i++) {
			this.nodes[i] = init;
		}
			
		//�ʱ� leaf�� sum tree�� �ʱ�ȭ
		for(int i=0; i< leafs.length; i++) {
			this.nodes[i + nodesLength - leafs.length] = leafs[i];	
		}
		
		int start_idx = this.nodes.length -1;
		if(this.leaf_length %2 == 1) {
			int tree_idx = (nodesLength)/2 -1;
			this.nodes[tree_idx] = leafs[this.leaf_length -1];
			start_idx -= 1;
		}
		
		for(int i=start_idx; i> 0; i -= 2) {
			this.nodes[i/2-1] = this.add(this.nodes[i], this.nodes[i-1]); 
		}
	
	}
	//Generic��  ������ �Ұ����ؼ�, �߻�޼ҵ带 ������, Generic Ÿ���� �����Ҷ�, add�� �����ؾ���.
	public abstract T add(T a, T b);	
	
	public T getRoot() {
		return nodes[0];
	}

	@Override
	public String toString() {
		String sumtree = "";
		int linePrint = 1;
		int lineCount = 0;
		for(int i=0; i<nodes.length; i++) {
			sumtree += nodes[i].toString();
			sumtree += " ";
			if(i == linePrint - 1) {
				sumtree += "\n";
				int prelinePrint = linePrint;
				linePrint = (int) (Math.pow(2, (lineCount+1))+i+1);
				lineCount += 1;
				
			}
		}
		return sumtree;
	}


}
