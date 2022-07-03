package a0703;

public class Main_Dev_Matching_3 {
	public static int min, max, cnt;
	public static int[] dx = { 0, 1, 0, -1 }, dy = { 1, 0, -1, 0 };
	public static int[][] map;

	// 겉면은 무조건 바다이며 (1<x, 1<y) 육지가 lands 배열로 나타나고, 육지의 바깥은 바다 안쪽은 호수
	// 호수 중에서 최소, 최대 크기를 출력하는 문제. 호수가 없을 경우 -1, -1 리턴
	// 100점
	public static void main(String[] args) {
		int[][] lands = { { 2, 2 }, { 2, 3 }, { 2, 4 }, { 3, 2 }, { 3, 5 }, { 4, 3 }, { 4, 4 } };
		System.out.println(solution(5, 6, lands));
		lands = new int[][] { { 2, 5 }, { 3, 3 }, { 3, 4 }, { 3, 5 }, { 4, 3 } };
		System.out.println(solution(5, 7, lands));
		lands = new int[][] { { 2, 2 }, { 2, 3 }, { 2, 5 }, { 3, 2 }, { 3, 4 }, { 3, 5 }, { 3, 6 }, { 4, 3 }, { 4, 6 },
				{ 5, 2 }, { 5, 5 }, { 6, 2 }, { 6, 3 }, { 6, 4 }, { 6, 6 }, { 7, 2 }, { 7, 6 }, { 8, 3 }, { 8, 4 },
				{ 8, 5 } };
		System.out.println(solution(9, 7, lands));
	}

	public static int[] solution(int rows, int columns, int[][] lands) {
		int[] answer = new int[2];
		// 
		min = Integer.MAX_VALUE / 2;
		max = -1;
		map = new int[rows][columns];
		for (int i = 0; i < lands.length; i++) {
			map[lands[i][0] - 1][lands[i][1] - 1] = 1; // 
		}
		cnt = -1;
		dfs(0, 0, rows, columns);
		for(int i=0; i<rows; i++) {
			for(int j=0; j<columns; j++) {
				if(map[i][j] == 0) {
					cnt = 0;
					lake(i, j, rows, columns);
					// 
					min = cnt > min ? min : cnt;
					max = cnt > max ? cnt : max;
				}
			}
		}
		if(cnt == - 1) {
			answer[0] = -1;
			answer[1] = -1;
		} else {
			answer[0] = min;
			answer[1] = max;
		}
		return answer;
	}

	public static void dfs(int x, int y, int r, int c) {
		map[x][y] = 1;
		for (int i = 0; i < 4; i++) {
			int nx = dx[i] + x;
			int ny = dy[i] + y;
			if (0 <= nx && nx < r && 0 <= ny && ny < c && map[nx][ny] == 0) {
				dfs(nx, ny, r, c);
			}
		}
	}
	public static void lake(int x, int y, int r, int c) {
		map[x][y] = 1;
		cnt++;
		for(int i=0; i<4; i++) {
			int nx = dx[i] + x;
			int ny = dy[i] + y;
			if (0<=nx&&nx<r&&0<=ny&&ny<c&&map[nx][ny] == 0) {
				lake(nx, ny, r, c);
			}
		}
	}
}
