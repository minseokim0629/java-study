package prob03;

import java.util.Scanner;

public class Sol03 {

	public static void main(String[] args) { 
		/* 코드 작성 */
		while (true) {
			System.out.print("수를 입력하세요 : ");
			Scanner scanner = new Scanner(System.in);
			int a = scanner.nextInt();
			int total = 0;
			System.out.print("결과값: ");
			for (int i = 1; i <= a; i++) {
				if (a % 2 == 1) { // 홀수
					if (i % 2 == 1) {
						total += i;
					}
				} else {
					if (i % 2 == 0) { // 짝수
						total += i;
					}
				}
			}
			System.out.println(total);
		}
	}
}
