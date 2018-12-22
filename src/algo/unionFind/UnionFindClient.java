package algo.unionFind;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class UnionFindClient {

	public static void main(String[] args) {

		Scanner stdIn = null;
		try {
			stdIn = new Scanner(new File("QuickFindInput.txt"));
			int N = stdIn.nextInt();
			UF uf = new UF(N);
			
			while(!stdIn.hasNext())
			{
				int p = stdIn.nextInt();
				int q = stdIn.nextInt();
				if(!uf.connected(p, q))
				{
					uf.union(p, q);
					System.out.println(p + " " + q);
				}
			}
			
			stdIn.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
