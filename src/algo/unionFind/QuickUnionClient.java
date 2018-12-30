package algo.unionFind;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class QuickUnionClient {

	public static void main(String[] args) {

		Scanner stdIn = null;
		try {
			stdIn = new Scanner(new File("QuickFindInput.txt"));
			int N = stdIn.nextInt();
			QuickUnion quf = new QuickUnion(N);
			
			while(stdIn.hasNextInt())
			{
				int p = stdIn.nextInt();
				int q = stdIn.nextInt();
				if(!quf.connected(p, q))
				{
					//Uncomment the line below to Run the Quick Union without weighting 
					//quf.union(p, q);
					//Running the Weighted Quick Union
					quf.unionWeighted(p, q); 
					
					System.out.println(p + " " + q + " are now connected");
				}
				else
				{
					System.out.println(p + " " + q + " already connected");
				}
			}
			
			stdIn.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
