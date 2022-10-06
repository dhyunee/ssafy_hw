package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj_9205_맥주마시면서걸어가기 {
	static int n;
	static List<int[]>list;
	static int []start=new int[2];
	static int []dest=new int[2];
	public static void main(String[] args)throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		int tc=Integer.parseInt(br.readLine());
		StringBuilder sb=new StringBuilder();
		for(int t=1;t<=tc;t++) {
			n=Integer.parseInt(br.readLine());
			list=new ArrayList<int[]>();
			for(int i=0;i<n+2;i++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				int y=Integer.parseInt(st.nextToken());
				int x=Integer.parseInt(st.nextToken());
				list.add(new int[] {y,x});
			}
			
			boolean [][]map=new boolean[n+2][n+2];
			for(int i=0;i<n+2;i++) {
				for(int j=0;j<n+2;j++) {
					int dist=Math.abs(list.get(i)[0]-list.get(j)[0])+Math.abs(list.get(i)[1]-list.get(j)[1]);
					if(dist<=1000)map[i][j]=true;
				
				}
			}
			
			
			for(int i=0;i<n+2;i++) {
				for(int j=0;j<n+2;j++) {
					if(i==j)continue;
					for(int k=0;k<n+2;k++) {
						if(j==k||i==k)continue;
						if(map[i][k]&&map[j][i]) {
							map[j][k]=true;
						}
					}
				}
			}
			System.out.println(map[0][n+1]?"happy":"sad");
			System.out.println();
		}
	}

}
