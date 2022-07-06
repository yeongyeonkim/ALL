package a0706;

import java.util.Arrays;

public class Main_1 {

	public static void main(String[] args) {
		System.out.println(solution(new int[] {5,1,3,7}, new int[] {2,2,6,8}));
		System.out.println(solution(new int[] {2,2,2,2}, new int[] {1,1,1,1}));
	}

	public static int solution(int[] A, int[] B) {
		int answer = 0;
		int n = A.length;
		// B 리스트를 정렬해
		// 낮은 값이 이길 수 있어? 없으면 상대의 가장 높은 값 제외
		int now = 0;
		int end = n-1;
		Arrays.sort(A); // 1 3 5 7
		Arrays.sort(B); // 2 2 6 8
		for(int i=0; i<n; i++) {
			if(A[now] > B[i]) { // A가 크면
				end--;
			} else if(A[now] < B[i]) {
				answer++;
				now++;
			} 
		}
		return answer;
	}
}
