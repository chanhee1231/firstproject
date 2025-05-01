package teamproject;


public class Shopping implements Comparable {
	private String name;
	private String category;
	private char clotheGender;
	private String size;
	private int price;
	
	public Shopping() {
		this(null,null,'0',null,0);
	}
	public Shopping(String name, String category, char clotheGender, String size, int price) {
		super();
		this.name = name;
		this.category = category;
		this.clotheGender = clotheGender;
		this.size = size;
		this.price = price;
	
		
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public char getClotheGender() {
		return clotheGender;
	}
	public void setClotheGender(char clotheGender) {
		this.clotheGender = clotheGender;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "Shopping [name=" + name + ", category=" + category + ", clotheGender=" + clotheGender + ", size=" + size
				+ ", price=" + price + "]";
	}
	@Override
	public int compareTo(Object o) {
		
		Shopping shopping= null;
		if (o instanceof Shopping) {
			shopping = (Shopping) o;
		}
		if (price < shopping.price) {
			return -1;
		} else if (price > shopping.price) {
			return 1;
		} else {
			return 0;
		}
		
	}
}
