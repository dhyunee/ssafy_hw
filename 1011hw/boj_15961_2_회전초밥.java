package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class boj_15961_2_회전초밥 {

	static int []sushilist;
	static int []sushi;
	static List<Integer>klist=new ArrayList<Integer>();
	
	static HashSet<Integer>set=new HashSet<>();
	static int n,d,k,c;
	
	public static void main(String[] args)throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());

		n=Integer.parseInt(st.nextToken());
		d=Integer.parseInt(st.nextToken());
		k=Integer.parseInt(st.nextToken());
		c=Integer.parseInt(st.nextToken());
		
		sushilist=new int[n+k-1];
		sushi=new int[d+1];
		
		for(int i=0;i<n;i++) {
			sushilist[i]=Integer.parseInt(br.readLine());
		}
		
		
		int cnt=0;
		int max=0;
		for(int i=0;i<k;i++) {
			int x=sushilist[i];
			if(sushi[x]==0) {
				cnt++;
			}
			
			sushi[x]++;
			
			if(sushi[c]==0) {
				cnt++;
			}
			sushi[c]++;
		}
		max=cnt;
		
		for(int i=0;i<n-1;i++) {
			
			sushi[sushilist[i]]--;
			if(sushi[sushilist[i]]==0)cnt--;
			
			if(sushi[sushilist[(i+k)%n]]==0)cnt++;
			sushi[sushilist[(i+k)%n]]++;
			
			max=Math.max(max, cnt);
		}
		
		System.out.println(max);
	}
	
}
