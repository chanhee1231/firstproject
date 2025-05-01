package teamproject;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class ShoppingMain {
	static Scanner scan = new Scanner(System.in);
	static String menuTitle;
	static String userMenuTitle;
	static String couponTitle;
	static ArrayList<Shopping> shoppingList = new ArrayList<>();
	static ProcessManager pm = ProcessManager.getInstance();

	public static void main(String[] args) throws IOException {
		ArrayList<UserInfo> userInfoList = new ArrayList<>();
		ArrayList<Shopping> cart = new ArrayList<>();
		ArrayList<Coupon> couponList = new ArrayList<>();
		pm.userFileUpload(userInfoList);
		pm.ProductfileUpload(shoppingList);
		pm.couponFileUpload(couponList);
		boolean RunningFlag = false;
		while (!RunningFlag) {
			UserInfo loginUser = pm.login(userInfoList);
			if (loginUser == null) {
				System.out.println("프로그램 종료합니다.");
				return;
			}
			switch (loginUser.getId()) {
			case "admin":
				boolean stopFlag = false;
				while (!stopFlag) {
//					pm.clear();
//					System.out.println();
					Menu.adminMenuDisplay();
					int no = pm.selectNo();
					switch (no) {
					case Menu.ADMIN_USER_INFO_VIEW:
						for (int i = 0; i < userInfoList.size(); i++) {
							System.out.println(userInfoList.get(i).toString());
						}
						break;
					case Menu.ADMIN_ADD_PRODUCT:
						pm.adminAddProduct(shoppingList);
						break;
						
					case Menu.ADMIN_REMOVE_PRODUCT:
						pm.adminRemoveProduct(shoppingList, cart);
						break;
						
					case Menu.ADMIN_EXIT:
						System.out.println("로그아웃합니다.");
						stopFlag = true;
						break;
						
					default:
						System.out.println("유효한 값을 입력하세요.");
						break;
					}
				}
				break;
			default:
				boolean stopFlag1 = false;
				while (!stopFlag1) {
//					pm.clear();
//					System.out.println();
					Menu.userMenuDisplay();
					int no = pm.selectNo();
					switch (no) {
					case Menu.USER_INFO_VIEW:
						for (UserInfo userInfo : userInfoList) {
							if (userInfo.equals(loginUser)) {
								System.out.println(userInfo.toString());
							}
						}
						break;
					case Menu.CART_PURCHASE_LIST:
						pm.cartListView(cart);
						break;
					case Menu.PRODUCT_LIST_VIEW:
						pm.productListView(shoppingList);
						break;
					case Menu.CART_LIST_CLEAR:
						cart.clear();
						System.out.println();
						break;
						
					case Menu.CART_ITEM_ADD:
						pm.cartItemAdd(shoppingList, cart);
						break;
						
					case Menu.CART_ITEM_DELETE:
						pm.cartProductDelete(cart);
						break;
					
					case Menu.CART_PAYMENT:
						pm.cartPayMent(cart,couponList);
						break;
						
					case Menu.EXIT:
						System.out.println("프로그램을 종료합니다.");
						stopFlag1 = true;
						break;
						
					default:
						System.out.println("유효한 값을 입력하세요.");
						break;
					}
				}
			}
		}
	}
}