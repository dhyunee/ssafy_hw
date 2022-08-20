package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_17135_1 {
	static int[][] map,copy;
	static int n, m, d, h,max=-1;// 궁수가 위로 이동할 때 y축이 h
	static int[] tgt = new int[3];
	static boolean[] selected;
	static Queue<node> q;
	static List<node>enemy=new ArrayList<>();
	static int cnt;
	static class node {
		int y, x;

		node(int y, int x) {
			this.y = y;
			this.x = x;			
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());

		map = new int[n+1][m];
		copy=new int[n+1][m];
		selected = new boolean[m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		comb(0,0);
		System.out.println(max);
	}

	static void comb(int tgt_idx, int src_idx) {//조합(궁수 어디 있을지)
		if (tgt_idx == 3) {
			q = new ArrayDeque<>();
			for (int i = 0; i < 3; i++) {
				q.offer(new node(n, tgt[i]));
			}
			h = n;
			cnt=0;
			cal();

			return;
		}

		for (int i = src_idx; i < m; i++) {
			if (!selected[i]) {
				tgt[tgt_idx] = i;
				selected[i] = true;
				comb(tgt_idx + 1, i + 1);
				selected[i] = false;
			}
		}
	}

	static void cal() {
	
		for(int i=0;i<n;i++)copy[i]=Arrays.copyOf(map[i],m);
		
		while(h>0) {//궁수가 다 올라갈 때가지==적이 다 내려갈 때까지
			enemy.clear();
			for(int i=0;i<3;i++) {//한번에 3개 동시에 빼기
				node nd=q.poll();
				
				int x=nd.x;
				int y=nd.y;
				int tmpd=d;
				int em_x=m,em_y=n;//list에 저장할 적의 위치
				
				for(int k=y-1;k>=0;k--) {
					for(int j=0;j<m;j++) {
						int dis=Math.abs(y-k)+Math.abs(x-j);//거리계산
						if(tmpd>=dis&&copy[k][j]==1) {
							if(tmpd==dis) {//최단거리와 똑같을 때
								if(em_x>j) {//제일 왼쪽 애를 없앰
									em_x=j;
									em_y=k;
								}
	
							}
							else {//최단거리 갱신
								tmpd=dis;
								em_x=j;
								em_y=k;
							}
						}
					}
				}
				q.offer(new node(y-1,x));//궁수 한 칸씩 올리기==적 한 칸씩 내리기
				
				if(em_y!=n&&em_x!=m) {
					enemy.add(new node(em_y,em_x));
				}
				
			}
			for(int i=0;i<enemy.size();i++) {
				if(copy[enemy.get(i).y][enemy.get(i).x]==1) {
					copy[enemy.get(i).y][enemy.get(i).x]=0;
					cnt++;
				}
			}
			h--;
		}

		max=Math.max(max, cnt);
	}
}
