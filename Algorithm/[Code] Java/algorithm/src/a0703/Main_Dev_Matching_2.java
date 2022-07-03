package a0703;

public class Main_Dev_Matching_2 {
	public static int cnt, idx;
	public static int[] dx = { 0, -1, 0, 1 };
	public static int[] dy = { 1, 0, -1, 0 };
	public static int[][] map;


	public static void main(String[] args) {
		System.out.println(solution(4, true));
//		System.out.println(solution(5, false));
		/*
		 * 1 2 9 10 
		 * 4 3 8 11 
		 * 5 6 7 12 
		 * 16 15 14 13
		 */
	}
	// n x n의 범위의 맵에서 그려나가는 문제 (horizontal은 시작 방향을 나타내는 것)
	// -> 런타임 에러도 발생하며 50점 나온 문제 -> 시간초과가 나는듯..
	public static int[][] solution(int n, boolean horizontal) {
		map = new int[n][n];
		cnt = 2;
		idx = 2;
		map[0][0] = 1;
		if (horizontal) {
			search(0, 1, n);
		} else {
			search(1, 0, n);
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		return map;
	}

	public static void search(int x, int y, int n) {
		map[x][y] = cnt++;
		if (cnt > n*n)
			return; 
		boolean flag = false;
		for (int i = 0; i < 4; i++) {
			if (flag)
				return;
			int nx = dx[i] + x;
			int ny = dy[i] + y;
			if (0 <= nx && nx < idx && 0 <= ny && ny < idx && map[nx][ny] == 0) {
				search(nx, ny, n);
				flag = true;
			}
		}
		if (!flag) {
			idx++; 
			for (int i = 0; i < 4; i++) {
				if (flag)
					return;
				int nx = dx[i] + x;
				int ny = dy[i] + y;
				if (0 <= nx && nx < idx && 0 <= ny && ny < idx && map[nx][ny] == 0) {
					search(nx, ny, n);
					flag = true;
				}
			}
		}
	}
}
