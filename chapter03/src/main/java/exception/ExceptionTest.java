package exception;

public class ExceptionTest {

	public static void main(String[] args) {
		int a = 10;
		int b = 10 - a;

		System.out.println("some code1...");

		try {
			System.out.println("some code2...");
			System.out.println("some code3...");

			int result = (1 + 2 + 3) / b;

			System.out.println("some code4...");
			System.out.println("some code5...");

		} catch (ArithmeticException e) {
			/* 예외처리 */
			// 1. 로깅 (파일로 남기던지, 화면으로 출력을 하던지)
			System.out.println("error:" + e);

			// 2. 사과
			System.out.println("미안합니다...");

			// 3. 정상종료
			return;
		} finally { // 옵션
			// catch문에서 return을 해도 finally 구문은 실행이 된다
			System.out.println("자원정리 ex) close file, socket, db coneection");
		}

		System.out.println("some code6...");
		System.out.println("some code7...");
	}

}
