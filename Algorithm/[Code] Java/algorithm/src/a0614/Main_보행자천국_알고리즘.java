package a0614;

public class Main_보행자천국_알고리즘 {
	public static void main(String[] args) {
		System.out.println(solution(3, 3, new int[][] { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } }));
		System.out.println(
				solution(3, 6, new int[][] { { 0, 2, 0, 0, 0, 2 }, { 0, 0, 2, 0, 1, 0 }, { 1, 0, 0, 2, 2, 0 } }));
	}
	public static int MOD = 20170805;
	public static int solution(int m, int n, int[][] cityMap) {
		int[][][] dp = new int[m + 1][n + 1][2]; // 이전 값을 갱신해주어야 해서 인덱스 참조 상 + 1
		dp[1][1][0] = dp[1][1][1] = 1; // dp[][][0] = 아래, dp[][][1] = 오른쪽
		for(int i=1; i<=m; i++) {  
			for(int j=1; j<=n; j++) {
				if(cityMap[i-1][j-1] == 0) { // 자유롭게 통행
					dp[i][j][0] = dp[i][j][1] += (dp[i][j - 1][0] + dp[i - 1][j][1]) % MOD;
				} else if(cityMap[i-1][j-1] == 2) { // 오던 방향으로 만 (이전 값 +)
					dp[i][j][0] = dp[i][j - 1][0];
					dp[i][j][1] = dp[i - 1][j][1];
				} else { // 통행 금지
					dp[i][j][0] = dp[i][j][1] = 0;
				}
			}
		}
		return dp[m][n][0];
	}
}
