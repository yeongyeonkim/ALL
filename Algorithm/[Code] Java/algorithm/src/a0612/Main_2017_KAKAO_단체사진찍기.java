package a0612;

public class Main_2017_KAKAO_단체사진찍기 {

	public static int answer;
	public static String[] arr = new String[] { "A", "C", "F", "J", "M", "N", "R", "T" };

	public static void main(String[] args) {
		System.out.println(solution(2, new String[] { "N~F=0", "R~T>2" }));
		System.out.println(solution(2, new String[] { "M~C<2", "C~M>1" }));
	}

	public static int solution(int n, String[] data) {
		answer = 0;
		boolean[] visit = new boolean[8];
		createArray("", visit, data);
		return answer;
	}

	public static void createArray(String permutation, boolean[] visit, String[] data) {
		if (permutation.length() == 7) {
			if (search(permutation, data)) {
				answer++;
			}
			return;
		}
		for (int i = 0; i < 8; i++) {
			if (!visit[i]) {
				visit[i] = true;
				createArray(permutation + arr[i], visit, data);
				visit[i] = false;
			}
		}
	}

    private static boolean search(String names, String[] datas) {
        for (String data : datas) {
            int position1 = names.indexOf(data.charAt(0));
            int position2 = names.indexOf(data.charAt(2));
            char op = data.charAt(3); // 수식
            int index = data.charAt(4) -'0'; // 간격
            if (op == '=') {
                if (!(Math.abs(position1 - position2) == index+1)) return false; 
            } else if (op == '>') {
                if (!(Math.abs(position1 - position2) > index+1)) return false;
            } else if (op == '<') {
                if (!(Math.abs(position1 - position2) < index+1)) return false;
            }
        }
        return true;
    }
}
