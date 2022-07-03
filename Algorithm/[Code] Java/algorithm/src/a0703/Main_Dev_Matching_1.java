package a0703;

public class Main_Dev_Matching_1 {

	// 배열의 앞 요소가 뒷 요소보다 작을 수 없다는 문제.
	// 크다면 값을 조정하고 조정을 위해 깎아낸 만큼 count 하여 총 count를 리턴
	// -> 23.5점 정도 나온 맞지 않은 문제...
	public static void main(String[] args) {
		System.out.println(solution(new int[] {2,1,3})); // 1 1 3 -> 1
		System.out.println(solution(new int[] {1,2,3})); // 1 2 3 -> 0
		System.out.println(solution(new int[] {3,2,3,6,4,5})); // 2, 2, 3, 4, 4, 5 -> 3
		System.out.println(solution(new int[] {4,3,2,1})); // 1, 1, 1, 1 -> 6
		System.out.println(solution(new int[] {4,3,2,1,6})); // 1, 1, 1, 1, 6 -> 6
		
	}

	public static int solution(int[] grade) {
		
		return 1;
	}

//	int n = grade.length;
//	int cnt = 0;
//	int start = 0;
//	boolean flag = false;
//	for(int i=0; i<n-1; i++) {
//		if(grade[i] > grade[i + 1]) {
//			if(!flag) {
//				flag = true;
//				start = i;
//			} 
//		} else { //
//			if(flag) {
//				for(int j=start; j<=i; j++) {
//					cnt += grade[j] - grade[i];
//				}
//			}
//			flag = false;
//			start = -1; 
//		}
//	}
//	if(start != -1) {
//		for(int i=start; i<n; i++) {
//			cnt += grade[i] - grade[n-1];
//		}
//	}
//	return cnt;
}
