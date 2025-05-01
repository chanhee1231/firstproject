package teamproject;

public class Coupon {

	private String name;
	private int value;
	
	public Coupon() {
		this(null,0);
	}
	public Coupon(String name, int value) {
		this.name = name;
		this.value = value;
	}
	public int Applydiscount(int price) {
		if(name.equals("5000원 할인쿠폰")) {
			return price - 5000;
		}else {
			return (int)(price * (1- 0.1));
		}
	}
	public String getName() {
		return name;
	}
	public int getValue() {
		return value;
	}
	@Override
	public String toString() {
		return "Coupon [name=" + name + ", value=" + value + "]";
	}
	
	
	
	
	
	
}
