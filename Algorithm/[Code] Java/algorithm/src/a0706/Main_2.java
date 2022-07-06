package a0706;

// 런타임 에러, 효율성 좋지 않은 듯.

public class Main_2 {
	public static int min;
	public static long answer;
	public static int[] dx = { 0, 1 }, dy = { 1, 0 }; // 아래쪽, 오른쪽
	public static int[][] map;

	public static void main(String[] args) {
		System.out.println(solution(4, 3, new int[][] { { 2, 2 } }));
	}

	public static int solution(int m, int n, int[][] puddles) {
		answer = 0;
		min = m + n - 2; // 2차원 배열에서 최단 거리
		map = new int[n][m];
		for (int i = 0; i < puddles.length; i++) {
			map[puddles[i][0] - 1][puddles[i][1] - 1] = 1;
		}
		move(0, 0, n, m, 0);
		return (int) (answer % 1000000007);
	}
	public static void move(int x, int y, int n, int m, int cnt) {
		if(x == n - 1 && y == m - 1) {
			answer++;
			return;
		}
		System.out.println(x + " : " + y);
		for(int i=0; i<2; i++) {
			int nx = dx[i] + x;
			int ny = dy[i] + y;
			if(0<=nx&&nx<n&&0<=ny&&ny<m&&map[nx][ny]!=1&&cnt != min) { // 범위 체크, puddle 체크, 최단 거리 체크
				move(nx, ny, n, m, cnt + 1);
			}
		}
	}
}
