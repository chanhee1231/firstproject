package teamproject;

public interface Menu {
	//사용자 메뉴 상수
	public static final int USER_INFO_VIEW = 1;
	public static final int CART_PURCHASE_LIST = 2;
	public static final int PRODUCT_LIST_VIEW = 3;
	public static final int CART_LIST_CLEAR = 4;
	public static final int CART_ITEM_ADD = 5;
	public static final int CART_ITEM_DELETE = 6;
	public static final int CART_PAYMENT= 7;
	public static final int EXIT = 8;
	
	//관리자 메뉴 상수
	public static final int ADMIN_USER_INFO_VIEW = 1;
    public static final int ADMIN_ADD_PRODUCT = 2;
    public static final int ADMIN_REMOVE_PRODUCT = 3;
    public static final int ADMIN_EXIT = 4;
	
    //사용자 메뉴 메소드
	public static void userMenuDisplay() {
		System.out.println("================== menu ====================");
		System.out.println("1. 고객 정보 확인하기    	2. 장바구니 출력");
		System.out.println("3. 상품 목록 출력  		4. 장바구니 초기화 ");
		System.out.println("5. 장바구니 항목 추가    	6. 장바구니 항목 삭제");
		System.out.println("7. 장바구니 결제    	8. 프로그램 종료");
		System.out.println("============================================");
	}
	//관리자 메뉴 메소드
	public static void adminMenuDisplay() {
		System.out.println("====== menu ======");
		System.out.println("1.사용자 정보 확인");
		System.out.println("2.판매 상품 추가");
		System.out.println("3.판매 상품 삭제");
		System.out.println("4.프로그램 종료");
		System.out.println("==================");
	}
	
	
}
