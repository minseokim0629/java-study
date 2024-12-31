package behavioral.strategy;

public class Client {

	public static void main(String[] args) {
		CalculateContext cc = new CalculateContext();
		cc.operation(new CalculateStrategy() {
			@Override
			public int calculate(int val1, int val2) {
				return val1 + val2;
			}
		});
		
		// method가 1개 있는 interface는 화살표로 변경할 수 있다.
		// 메서드가 1개만 있으니까 메서드명을 작성하지 않아도 뭔지 알아서 생략 가능
		cc.operation((val1, val2) -> val1 - val2);
		cc.operation((val1, val2) -> val1 * val2);
		cc.operation((val1, val2) -> val1 / val2);
	}
}
