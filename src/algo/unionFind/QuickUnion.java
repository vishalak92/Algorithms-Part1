package algo.unionFind;

public class QuickUnion {

private int[] id;
private int[] size; //only for weighted Union
	
	public QuickUnion(int N)
	{
		id = new int[N];
		size = new int[N];
		for (int i =0;i<N;i++)
		{
			id[i] = i;
			size[i] = 1;
		}
	}
	
	private int root(int i)
	{
		while(i!=id[i])
		{
			i = id[i];
		}
		return i;
	}
	
	public boolean connected (int p, int q)
	{
		return root(p) == root(q); 
	}
	
	public void union (int p, int q)
	{
		int pid = root(p);
		int qid = root(q); 
		id[pid] = qid;
				
	}
	
	public void unionWeighted(int p, int q)
	{
		int pid = root(p);
		int qid = root(q); 
		if (pid == qid)
		{
			return;
		}
		if(size[pid] < size[qid])
		{
			id[pid] = qid;
			size[qid] += size[pid];
		}
		else
		{
			id[qid] = pid;
			size[pid] += size[qid];
		}
	}
}
