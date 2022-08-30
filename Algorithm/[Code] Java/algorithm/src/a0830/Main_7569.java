package a0830;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_7569 {

	public static class Node {
		int x;
		int y;
		int z;
		int value;

		Node(int x, int y, int z, int value) {
			this.x = x;
			this.y = y;
			this.z = z;
			this.value = value;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		int[] dx = { 0, 1, 0, -1, 0, 0 }, dy = { 1, 0, -1, 0, 0, 0 }, dz = { 0, 0, 0, 0, -1, 1 };
		int[][][] map = new int[h][n][m];
		Queue<Node> q = new LinkedList<>();
		for (int k = 0; k < h; k++) {
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < m; j++) {
					int num = Integer.parseInt(st.nextToken());
					map[k][i][j] = num;
					if (num == 1) {
						q.add(new Node(i, j, k, 0));
					}
				}
			}
		}
		int result = 0;
		while (!q.isEmpty()) {
			Node tmp = q.poll();
			for (int i = 0; i < 6; i++) {
				int nx = tmp.x + dx[i];
				int ny = tmp.y + dy[i];
				int nz = tmp.z + dz[i];
				// 범위에 벗어나지 않으며, 0인 경우에만 탐색
				if (0 <= nx && nx < n && 0 <= ny && ny < m && 0 <= nz && nz < h && map[nz][nx][ny] == 0) {
					map[nz][nx][ny] = tmp.value + 1;
					q.add(new Node(nx, ny, nz, tmp.value + 1));
				}
			}
			result = tmp.value;
		}
		for (int k = 0; k < h; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (map[k][i][j] == 0) {
						System.out.println("-1");
						return;
					}
				}
			}
		}
		System.out.println(result);
	}

}
