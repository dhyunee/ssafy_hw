package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class swea_5643_키순서 {

	static int n, m;
	static boolean[][] mapmax, mapmin;
	static boolean[] visited;
	static int cnt = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		StringBuilder sb=new StringBuilder();
		for (int t = 1; t <= tc; t++) {
			n = Integer.parseInt(br.readLine());
			m = Integer.parseInt(br.readLine());
			mapmax = new boolean[n][n];
			mapmin = new boolean[n][n];
			visited = new boolean[n];

			for (int i = 0; i < m; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int y = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				mapmin[y-1][x-1] = true;
				mapmax[x-1][y-1] = true;
			}
			
			int ans = 0;
			for (int i = 0; i < n; i++) {
				cnt=0;
				Arrays.fill(visited, false);
				dfs(i, mapmax);
				Arrays.fill(visited, false);
				dfs(i, mapmin);
				if (cnt == n - 1)
					ans++;
			}
			sb.append("#").append(t).append(" ").append(ans).append("\n");

		}
		System.out.println(sb);

	}

	static void dfs(int x, boolean[][] arr) {
		visited[x] = true;
		for (int i = 0; i < n; i++) {
			if (arr[x][i] && !visited[i]) {
				cnt++;
				dfs(i, arr);
			}
		}
	}

}
