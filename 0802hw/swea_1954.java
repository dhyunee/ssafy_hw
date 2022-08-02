package java_study;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class swea_1954 {

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int d = 0;
	static int[][] map;
	static int n;

	static int change_d(int d) {
		d++;
		if (d == 4)
			d = 0;
		return d;
	}

	static void write() {
		int x = 0, y = 0;
		int cnt = 1;
		map[y][x] = cnt;
		while (cnt < n * n) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			if (ny >= 0 && nx >= 0 && ny < n && nx < n && map[ny][nx] == 0) {
				cnt++;
				map[ny][nx] = cnt;
				y = ny;
				x = nx;
			} else {
				d = change_d(d);
			}

		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());

		for (int t = 1; t < tc + 1; t++) {
			n = Integer.parseInt(br.readLine());
			map = new int[n][n];

			write();

			System.out.println("#" + (t));
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}

		}

	}

}
