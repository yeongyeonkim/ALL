package a0605;

import java.util.Stack;

public class Main_2019_KAKAO_크레인인형뽑기게임 {

	public static void main(String[] args) {
		int[][] board = { 
				{ 0, 0, 0, 0, 0 }, 
				{ 0, 0, 1, 0, 3 }, 
				{ 0, 2, 5, 0, 1 }, 
				{ 4, 2, 4, 4, 2 },
				{ 3, 5, 1, 3, 1 } 
				};
		int[] moves = { 1, 5, 3, 5, 1, 2, 1, 4 };
		System.out.println(solution(board, moves));
	}

	public static int solution(int[][] board, int[] moves) {
		int answer = 0;
		int n = board.length;
		Stack<Integer> st = new Stack<>();
		for(int i=0; i<moves.length; i++) {
			int idx = moves[i] - 1;
			for(int j=0; j<n; j++) {
				int doll = board[j][idx];
				if(doll != 0) {
					if(st.size() == 0) {
						st.add(doll);
					} else {
						int tmp = st.pop();
						if(tmp != doll) {
							st.add(tmp);
							st.add(doll);
						} else {
							answer+=2;
						}
					}
					board[j][idx] = 0;
					break;
				}
			}
		}
		return answer;
	}
}
