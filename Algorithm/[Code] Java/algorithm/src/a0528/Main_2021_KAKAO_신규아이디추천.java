package a0528;

import java.util.Objects;

public class Main_2021_KAKAO_신규아이디추천 {
	/*
	 * 다른 분의 replaceAll을 사용한 멋진 코드
	 *  public String solution(String new_id) {
        String answer = "";
        String temp = new_id.toLowerCase();

        temp = temp.replaceAll("[^-_.a-z0-9]","");
        System.out.println(temp);
        temp = temp.replaceAll("[.]{2,}",".");
        temp = temp.replaceAll("^[.]|[.]$","");
        System.out.println(temp.length());
        if(temp.equals(""))
            temp+="a";
        if(temp.length() >=16){
            temp = temp.substring(0,15);
            temp=temp.replaceAll("^[.]|[.]$","");
        }
        if(temp.length()<=2)
            while(temp.length()<3)
                temp+=temp.charAt(temp.length()-1);

        answer=temp;
        return answer;
    }
	 */
	public static void main(String[] args) {
		System.out.println(solution("...!@BaT#*..y.abcdefghijklm"));
		System.out.println(solution("z-+.^."));
		System.out.println(solution("=.="));
		System.out.println(solution("123_.def"));
		System.out.println(solution("abcdefghijklmn.p"));
	}

	public static String solution(String new_id) {
		// 1단계
		new_id = new_id.toLowerCase();
		// 2단계
		new_id = two(new_id);
		// 3단계
		new_id = three(new_id);
		// 4단계
		new_id = four(new_id);
		// 5단계
		new_id = five(new_id);
		// 6단계
		new_id = six(new_id);
		// 7단계
		new_id = seven(new_id);
		return new_id;
	}

	private static String seven(String new_id) {
		if (new_id.length() <= 2) {
			String tmp = new_id;
			while(tmp.length() < 3) {
				tmp += new_id.charAt(new_id.length() - 1);
			}
			return tmp;
		}
		return new_id;
	}

	private static String six(String new_id) {
		if (new_id.length() >= 16) {
			if (new_id.charAt(14) == '.') {
				return new_id.substring(0, 14);
			} else {
				return new_id.substring(0, 15);
			}
		}
		return new_id;
	}

	private static String five(String new_id) {
		if (Objects.isNull(new_id) || new_id.equals("")) {
			return "a";
		}
		return new_id;
	}

	private static String four(String new_id) {
		// 길이가 1인 경우
		if (new_id.length() == 1) {
			if (new_id.equals(".")) {
				return null;
			} else {
				return new_id;
			}
		} else {
			int first = 0;
			int last = 0;
			if (new_id.charAt(0) == '.') {
				first++;
			}
			if (new_id.charAt(new_id.length() - 1) == '.') {
				last++;
			}
			new_id = new_id.substring(first, new_id.length() - last);
		}
		// 길이가 2인 경우, null 체크
		return new_id;
	}

	public static String three(String new_id) {
		StringBuilder sb = new StringBuilder();
		boolean flag = false; // . 갱신용
		for (int i = 0; i < new_id.length(); i++) {
			char c = new_id.charAt(i);
			if (c == '.') {
				if (!flag) {
					flag = true;
					sb.append('.');
				}
			} else {
				sb.append(c);
				flag = false;
			}
		}
		return sb.toString();
	}

	public static String two(String new_id) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < new_id.length(); i++) {
			char c = new_id.charAt(i);
			if (('a' <= c && c <= 'z') || ('0' <= c && c <= '9') || c == '-' || c == '_' || c == '.') {
				sb.append(c);
			}
		}
		return sb.toString();
	}
}
