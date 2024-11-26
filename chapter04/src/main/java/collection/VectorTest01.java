package collection;

import java.util.Enumeration;
import java.util.Vector;

public class VectorTest01 {

	public static void main(String[] args) {
		// 옛날 버전
		Vector<String> v = new Vector<>();

		v.addElement("둘리");
		v.addElement("마이콜");
		v.addElement("또치");

		// 순회1
		for (int i = 0; i < v.size(); i++) {
			String s = v.elementAt(i);
			System.out.println(s);
		}

		// 삭제
		v.removeElementAt(2);

		// 순회2
		// 여기서는 Enumeration이 Iterator가 되는 것
		Enumeration<String> e = v.elements(); // 한번만 순회 가능
		while(e.hasMoreElements()) {
			String s = e.nextElement();
			System.out.println(s);
		}
	}

}
