package prob02;

public class Goods {
	private String name;
	private int price;
	private int stockQty;

	public Goods(String name, int price, int stockQty) {
		this.name = name;
		this.price = price;
		this.stockQty = stockQty;
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
		this.price = price;
	}

	public int getStockQty() {
		return stockQty;
	}

	public void setStockQty(int stockQty) {
		this.stockQty = stockQty;
	}

	public void printInfo() {
		System.out.println(name + "(가격:" + price + "원)이 " + stockQty + "개 입고 되었습니다.");
	}
}