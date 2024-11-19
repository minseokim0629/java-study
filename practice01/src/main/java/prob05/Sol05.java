package prob05;

public class Sol05 {
	public static void main(String[] args) {

		/* 코드 작성 */
		for (int i = 1; i <= 100; i++) {
			String clap = "";
			String s = String.valueOf(i);
			for (int j = 0; j < s.length(); j++) {
				int cur = s.charAt(j) - '0';
				if (cur > 0 && cur % 3 == 0) {
					clap += "짝";
				}
			}
			if (clap != "") {
				System.out.println(s + " " + clap);
			}
		}
	}
}
