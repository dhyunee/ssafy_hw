package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_17406 {

	static int[][] arr;
	static int n,m,k;
	static int []dy= {0,1,0,-1},dx= {1,0,-1,0};
	static int[][]pure;
	static boolean [] visited;
	static int[]numbers;
	
	static int[]r,c,s;
	static int sum;
	static int min=Integer.MAX_VALUE;
	public static void main(String[] args)throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		k=Integer.parseInt(st.nextToken());
		
		arr=new int[n+1][m+1];
		pure=new int[n+1][m+1];
		
		for(int i=1;i<n+1;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=1;j<m+1;j++) {
				arr[i][j]=Integer.parseInt(st.nextToken());
				pure[i][j]=arr[i][j];
			}	
		}
	
		
		r=new int[k];
		c=new int[k];
		s=new int[k];
		
		for(int i=0;i<k;i++) {
			st=new StringTokenizer(br.readLine());
			r[i]=Integer.parseInt(st.nextToken());
			c[i]=Integer.parseInt(st.nextToken());
			s[i]=Integer.parseInt(st.nextToken());
				
		}
		
		visited=new boolean[k];
		numbers=new int[k];
		perm(0);
		
		System.out.println(min);
	}

	static void perm(int cnt) {
		if(cnt==k) {
			
			for(int k=1;k<n+1;k++) {
				for(int j=1;j<m+1;j++) {
					arr[k][j]=pure[k][j];
				}
			}
			for(int i=0;i<k;i++) {
				int x=numbers[i];
				solve(r[x],c[x],s[x]);
			}
			
			for(int k=1;k<n+1;k++) {
				sum=0;
				for(int j=1;j<m+1;j++) {
					sum+=arr[k][j];
				}
				min=Math.min(min, sum);
			}
			
			return;
		}
		
		for(int i=0;i<k;i++) {
			if(visited[i])continue;
			numbers[cnt]=i;
			visited[i]=true;
			perm(cnt+1);
			visited[i]=false;
		}
		
	}

	
	static void solve(int r,int c,int s) {
		for(int l=1;l<=s;l++) {
			int x=c-l;
			int y=r-l;
			int idx=0;
			int tmp = arr[y][x];
			
			while(idx<4) {
	//			System.out.println(y+" "+x);
				int nx = x + dx[idx];
				int ny = y + dy[idx];
				
				if(nx>=c-l&&ny>=r-l&&nx<=c+l&&ny<=r+l) {
					int tmp2=arr[ny][nx];
					arr[ny][nx]=tmp;
					tmp=tmp2;
					x = nx;
					y = ny;
					
				}
				
				else {
	//				System.out.println(y+" "+x);
					idx++;
				}

			}
		}
		
	}
		
}


