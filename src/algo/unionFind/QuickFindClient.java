package algo.unionFind;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class QuickFindClient {

	public static void main(String[] args) {

		Scanner stdIn = null;
		try {
			stdIn = new Scanner(new File("QuickFindInput.txt"));
			int N = stdIn.nextInt();
			QuickFindUF quf = new QuickFindUF(N);
			
			while(stdIn.hasNextInt())
			{
				int p = stdIn.nextInt();
				int q = stdIn.nextInt();
				if(!quf.connected(p, q))
				{
					quf.union(p, q);
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
