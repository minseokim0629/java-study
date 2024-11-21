package chapter03;

public class Goods {
	public static int countOfGoods = 0;
	private String name;
	private int price;
	private int countStock; // 재고
	private int countSold; // 판매량

	public Goods() {
		// 클래스 이름은 생략 가능
		countOfGoods++;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		if (price < 0) {
			price = 0;
		}
		this.price = price;
	}

	public int getCountStock() {
		return countStock;
	}

	public void setCountStock(int countStock) {
		this.countStock = countStock;
	}

	public int getCountSold() {
		return countSold;
	}

	public void setCountSold(int countSold) {
		this.countSold = countSold;
	}

	public void printInfo() {
		System.out.println("상품이름:" + name + 
				", 가격:" + price + 
				", 판매량:" + countSold + 
				", 재고량:" + countStock);
	}
	public int calcDiscountPrice(float discountRate) {
		//내부(지역) 변수는 꼭 초기화 시켜줘야 한다.
		int result = price -(int)(price * discountRate); 
		return result;
	}
}
