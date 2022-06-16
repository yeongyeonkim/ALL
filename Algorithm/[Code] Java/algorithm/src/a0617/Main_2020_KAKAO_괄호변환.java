package a0617;

public class Main_2020_KAKAO_괄호변환 {

	public static void main(String[] args) {
		System.out.println(solution("(()())()"));
		System.out.println(solution(")("));
		System.out.println(solution("()))((()"));
	}

	/* ))(( (()) )( 이라면?
	 * u : ))((
	 * v : (()))(
	 * '('
	 *  u2 : (()) 
	 *  v2 : )(
	 *   tmp1 : (
	 *   u2-1 : )(
	 *   v2-2 : ""
	 *   tmp1 : ()
	 *   u2-1 : ""
	 *   v2-2 : ""
	 *   -> () return
	 *  -> u가 올바름. v를 1단게부터
	 *   u3-1 : )(
	 *   v3-1 : ""
	 *   tmp2 : (
	 *   tmp2 : ()
	 *   u3-1 : ""
	 *   -> () return
	 *  u2+return : (())()
	 * u1 : ))((
	 * -> tmp3 : (
	 * tmp3 + ( (())()
	 * tmp3 + ( (())() )
	 * u : )(
	 * u : ()
	 * tmp3 + u : ((())())()
	 *  
	 * -> ((())())() 똑같다.
	 * 
	 * 1. 입력이 빈 문자열인 경우, 빈 문자열을 반환합니다. 
	 * 2. 문자열 w를 두 "균형잡힌 괄호 문자열" u, v로 분리합니다. 
	 *    단, u는 "균형잡힌 괄호 문자열"로 더 이상 분리할 수 없어야 하며, v는 빈 문자열이 될 수 있습니다. 
	 * 3. 문자열 u가 "올바른 괄호 문자열" 이라면 문자열 v에 대해 1단계부터 다시 수행합니다. 
	 *    3-1. 수행한 결과 문자열을 u에 이어 붙인 후 반환합니다. 
	 * 4. 문자열 u가 "올바른 괄호 문자열"이 아니라면 아래 과정을 수행합니다. 
	 *    4-1. 빈 문자열에 첫 번째 문자로 '('를 붙입니다. 
	 *    4-2. 문자열 v에 대해 1단계부터 재귀적으로 수행한 결과 문자열을 이어 붙입니다.
	 *    4-3. ')'를 다시 붙입니다. 
	 *    4-4. u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙입니다.
	 *    4-5. 생성된 문자열을 반환합니다.
	 */
	public static String u, v;
	public static String solution(String p) {
		String answer = "";
		while (answer.length() != p.length()) {
			split(p);
		}

		return answer;
	}

	public static void split(String p) {
		int cnt = p.charAt(0) == '(' ? 1 : -1;
		for(int i=1; i<p.length(); i++) {
			if(p.charAt(i) == '(') {
				cnt++;
				if(cnt == 0) { // 이상한 문자열
					
				}
			} else { // 균형잡힌 문자열
				cnt--;
				if(cnt == 0) {
					
				}
			}
		}
	}
}
