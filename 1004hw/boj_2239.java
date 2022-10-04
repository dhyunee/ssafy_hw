package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class boj_2239 {
	static int [][]map=new int[9][9];
	static List<int[]>list=new ArrayList<>();
	public static void main(String[] args)throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

		//입력
		for(int i=0;i<9;i++) {
			char []arr=br.readLine().toCharArray();
			for(int j=0;j<9;j++) {
				map[i][j]=arr[j]-'0';
				if(map[i][j]==0)list.add(new int[] {i,j});
			}
		}
		
		cal(0);
		
		
	}
	static boolean flag=false;
	static void cal(int idx) {
		//System.out.println(idx+" / "+list.size());
		if(list.size()==idx) {
			flag=true;
			for(int i=0;i<9;i++) {
				for(int j=0;j<9;j++) {
					System.out.print(map[i][j]);
				}System.out.println();
			}
			return;
			
		}
		if(flag)return;
		int y=list.get(idx)[0];
		int x=list.get(idx)[1];
		
		boolean[]num=new boolean[10];
		
		for(int i=0;i<9;i++) {
			if(map[y][i]!=0)num[map[y][i]]=true;
			if(map[i][x]!=0)num[map[i][x]]=true;
		}
		
		int sx=(x/3)*3;
		int sy=(y/3)*3;
		
		for(int i=sy;i<sy+3;i++) {
			for(int j=sx;j<sx+3;j++) {
				if(map[i][j]!=0)num[map[i][j]]=true;
			}
		}
		
		for(int i=1;i<10;i++) {
			if(!num[i]) {
				map[y][x]=i;
				cal(idx+1);
				map[y][x]=0;
			}
		}
	}
}
