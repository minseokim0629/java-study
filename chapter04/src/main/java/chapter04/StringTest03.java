package chapter04;

public class StringTest03 {

	public static void main(String[] args) {
		// String s1 = "Hello " + "World " + "Java" + 21;

//		String s1 = new StringBuilder("Hello ")
//				.append("World")
//				.append("Java")
//				.append(21)
//				.toString();
		
		String s1 = new StringBuffer("Hello ")
				.append("World")
				.append("Java")
				.append(21)
				.toString();

		System.out.println(s1);
		
		String s2 = "";
		
		for(int i = 0; i < 1000000; i++) {
			// s2 = s2 + "h";
			// s2 = new StringBuffer(s2).append("h").toString();
		}
		
		StringBuffer sb = new StringBuffer("");
		for(int i=0; i<1000000; i++) {
			sb.append("h");
		}
		String s3 = sb.toString();
		
		// String method들....
		String s4 = "abcABCabcAbc";
		System.out.println(s4.length());
		System.out.println(s4.charAt(2));
		System.out.println(s4.indexOf("abc")); //시작 지점 반환, 못찾는 경우 -1 반환
		System.out.println(s4.indexOf("abc", 7)); //시작 지점 반환, 시작 idx 지정 가능, 못찾는 경우 -1 반환
		System.out.println(s4.substring(3)); // 시작 idx부터 끝까지
		System.out.println(s4.substring(3, 5)); // 시작 idx부터 끝 idx 전까지 (3 ~ 4)
		
		String s5 = "     ab      cd   ";
		String s6 = "abc,def,ghi";
		
		String s7 = s5.concat(s6); // s5 + s6
		System.out.println(s7);
		
		System.out.println("---" + s5.trim() + "---"); //앞뒤공백 제거
		System.out.println("---" + s5.replaceAll(" ", "") + "---"); 
		
		String[] tokens = s6.split(",");
		for(String s : tokens) {
			System.out.println(s);
		}
		
		String[] tokens2 = s6.split(" ");
		for(String s : tokens2) {
			System.out.println(s); // 문자열 내에 구분자가 없을 시 전체 출력. null 리턴 x
		}
	}

}
