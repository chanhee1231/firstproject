package teamproject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ProcessManager {
	private static ProcessManager instance = new ProcessManager();

	private ProcessManager() {
		super();
	}

	public static ProcessManager getInstance() {
		return instance;
	}

	public void ProductfileUpload(ArrayList<Shopping> shoppingList) throws FileNotFoundException {
		FileInputStream fi = new FileInputStream("D:/Java_Labs/teamproject/res/clothes.txt");

		try (Scanner s = new Scanner(fi)) {
			if (s.hasNextLine()) {
				ShoppingMain.menuTitle = s.nextLine();
			}
			while (s.hasNextLine()) {
				String data = s.nextLine();
				String[] tokens = data.split(",");
				String name = tokens[0];
				String category = tokens[1];
				char clothegender = tokens[2].charAt(0);
				String size = tokens[3];
				int price = Integer.parseInt(tokens[4]);
				Shopping shopping = new Shopping(name, category, clothegender, size, price);
				shoppingList.add(shopping);
			}
		} catch (NumberFormatException e) {
		}
	}

	public int selectNo() {
		Scanner scan = new Scanner(new InputStreamReader(System.in, StandardCharsets.UTF_8));
		int choice = 0;
		while (true) {
			System.out.print("번호 선택>");
			String input = scan.nextLine();
			boolean isInputCheck = Pattern.matches("^(1[0-2]|[1-9])$", input);
			if (isInputCheck) {
				choice = Integer.parseInt(input);
				break;
			}
			System.out.println("유효한 번호를 입력해주세요");
		}
		return choice;
	}

	public void userFileUpload(ArrayList<UserInfo> userInfoList) throws FileNotFoundException {
		FileInputStream fi = new FileInputStream("D:/Java_Labs/teamproject/res/userInfo.txt");
		try (Scanner s = new Scanner(fi)) {
			if (s.hasNext()) {
				ShoppingMain.userMenuTitle = s.nextLine();
			}
			while (s.hasNext()) {
				String data = s.nextLine();
				String[] tokens = data.split(",");
				String id = tokens[0];
				String pwd = tokens[1];
				String name = tokens[2];
				UserInfo userInfo = new UserInfo(id, pwd, name);
				userInfoList.add(userInfo);
			}
		}
	}

	public void couponFileUpload(ArrayList<Coupon> couponList) throws FileNotFoundException {
		FileInputStream fi = new FileInputStream("D:/Java_Labs/teamproject/res/coupon.txt");
		try (Scanner s = new Scanner(fi)) {
			if (s.hasNext()) {
				ShoppingMain.couponTitle = s.nextLine();
			}
			while (s.hasNext()) {
				String data = s.nextLine();
				String[] tokens = data.split(",");
				String name = tokens[0];
				int value = Integer.parseInt(tokens[1]);
				Coupon coupon = new Coupon(name, value);
				couponList.add(coupon);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public UserInfo login(ArrayList<UserInfo> userInfoList) throws IOException {
		Scanner scan = new Scanner(System.in,"UTF-8");
		boolean stopFlag = false;
		while (!stopFlag) {
			System.out.println("=========");
			System.out.println("1. 로그인");
			System.out.println("2. 회원가입");
			System.out.println("3. 종료");			
			System.out.println("=========");
			int no = selectNo();
			switch (no) {
			case 1:
				int attempt = 0;
				while (attempt < 3) {
					System.out.println("==== 로그인 ====");
					System.out.print("id :");
					String id = scan.nextLine();
					System.out.print("password :");
					String pwd = scan.nextLine();
					for (UserInfo userInfo : userInfoList) {
						if (userInfo.getId().equals(id) && userInfo.getPwd().equals(pwd)) {
							System.out.println(userInfo.getName() + "님 환영합니다.");
							return userInfo;
						}
					}
					System.out.println("로그인 실패. 아이디나 패스워드가 틀렸습니다.");
					attempt++;
				}
				if (attempt >= 3) {
					System.out.println("로그인 3회 실패, 초기화면으로 돌아갑니다.");
				}

				break;
			case 2:

				System.out.print("이름을 입력해주세요.");
				String name = scan.nextLine();

				String id = null;
				UserInfo userInfo = null;

				while (true) {
					System.out.print("사용하실 아이디(영문자+숫자)를 입력해주세요.");
					id = scan.nextLine();
					boolean isIdInputCheck = id.matches("^[a-zA-Z0-9]+$");
					if (!isIdInputCheck) {
						System.out.println("유효한 값을 입력하세요.");
						continue;
					}
					break;

				}

				String pwd = null;
				while (true) {
					System.out.print("사용하실 비밀번호(4자리 이상 숫자)를 입력해주세요.");
					pwd = scan.nextLine();
					boolean isPwdInputCheck = pwd.matches("^[0-9]{4,}$");
					if (!isPwdInputCheck) {
						System.out.println("유효한 값을 입력하세요.");
						continue;
					}
					break;
				}
				System.out.print("비밀번호 확인(재입력)>");
				String chekingdPwd = scan.nextLine();

				if (pwd.equals(chekingdPwd)) {
					// 비밀번호가 일치하면 userInfo 객체 생성
					userInfo = new UserInfo(id, chekingdPwd, name);
					userInfoList.add(userInfo);
					stopFlag = false;

					
					try {
						
						PrintStream ps = new PrintStream(
								new FileOutputStream("D:/Java_Labs/teamproject/res/userInfo.txt", true), true,"UTF-8");
						ps.println(userInfo.getId() + "," + userInfo.getPwd() + "," + userInfo.getName());
						ps.close(); 
					} catch (IOException e) {
						System.out.println("파일 저장 중 오류가 발생했습니다.");
						e.printStackTrace(); // 오류의 원인 출력
					}

					break;
				} else {
					System.out.println("비밀번호가 일치하지 않습니다.");
				}
			case 3:
				stopFlag = true;
				break;
			}
		}
		return null;
	}

	public void adminAddProduct(ArrayList<Shopping> shoppingList) throws UnsupportedEncodingException {
		Scanner scan = new Scanner(new InputStreamReader(System.in, "UTF-8"));
		System.out.print("추가하실 옷을 입력하세요.");
		String name = scan.nextLine();
		System.out.print("추가하실 옷의 분류를 입력해주세요.");
		String category = scan.nextLine();
		System.out.print("추가하실 옷의 착용성별(M,W,U)을 입력해주세요.");
		char clothegender = scan.nextLine().charAt(0);
		System.out.print("추가하실 옷의 사이즈(S,M,L,Free)를 입력해주세요.");
		String size = scan.nextLine();
		System.out.print("추가하실 옷의 가격을 입력해주세요.");
		int price = Integer.parseInt(scan.nextLine());
		Shopping s = new Shopping(name, category, clothegender, size, price);
		shoppingList.add(s);
		// 파일에 사용자 정보 저장
		try {
			// 사용자 정보 파일
			PrintStream ps = new PrintStream(new FileOutputStream("D:/Java_Labs/teamproject/res/clothes.txt", true),
					true, "UTF-8");
			ps.println(s.getName() + "," + s.getCategory() + "," + s.getClotheGender() + "," + s.getSize() + ","
					+ s.getPrice());
			ps.close();
		} catch (IOException e) {

		}
	}

	public void adminRemoveProduct(ArrayList<Shopping> shoppingList, ArrayList<Shopping> cart) {
		Scanner scan = new Scanner(new InputStreamReader(System.in));
		boolean removeFlag = false;
		System.out.print("장바구니에서 삭제할 제품의 이름을 입력>");
		String removeData = scan.nextLine();
		
		for (int i = 0; i < shoppingList.size(); i++) {
			if (shoppingList.get(i).getName().equals(removeData)) {
				System.out.printf("%s의 상품정보를 삭제합니다.\n", removeData);
				shoppingList.remove(i);
				removeFlag = true;
			}
		}
		
		if (removeFlag == false) {
			System.out.printf("%s의 상품정보를 찾지 못했습니다.\n", removeData);
		
		}

	}

	public void cartProductDelete(ArrayList<Shopping> cart) {
		Scanner scan = new Scanner(new InputStreamReader(System.in));
		boolean removeFlag = false;
		System.out.print("장바구니에서 삭제할 제품의 이름을 입력>");
		String removeData = scan.nextLine();
		for (int i = 0; i < cart.size(); i++) {
			if (cart.get(i).getName().equals(removeData)) {
				System.out.printf("%s의 상품정보를 삭제합니다.\n", removeData);
				cart.remove(i);
				removeFlag = true;
			}
		}
		if (removeFlag == false) {
			System.out.printf("%s의 상품정보를 찾지 못했습니다.\n", removeData);
		}
	}

	public void DiscountCouponApply(ArrayList<Shopping> cart, ArrayList<Coupon> couponList) {
		
	}

	public void cartPayMent(ArrayList<Shopping> cart,ArrayList<Coupon> couponList) {
		Scanner scan = new Scanner(new InputStreamReader(System.in));
		for (int i = 0; i < cart.size(); i++) {
			System.out.println(cart.get(i).toString());
		}
		System.out.print("할인쿠폰을 적용할 상품을 선택해주세요.");
		String selectedItemName = scan.nextLine();
		for (int i = 0; i < couponList.size(); i++) {
			System.out.println(couponList.get(i).toString());
		}
		System.out.print("적용할 할인쿠폰을 선택해주세요.");
		String selectedCouponName = scan.nextLine();

		Coupon selectedCoupon = null;
		Shopping selectedItem = null;

		for (Coupon c : couponList) {
			if (c.getName().equals(selectedCouponName)) {
				selectedCoupon = c;
				break;
			}
		}
		for (Shopping s : cart) {
			if (s.getName().equals(selectedItemName)) {
				selectedItem = s;
				break;
			}
		}
		if (selectedCoupon != null && selectedItem != null) {
			int discountPrice = selectedCoupon.Applydiscount(selectedItem.getPrice());
			selectedItem.setPrice(discountPrice);
		} else {
			System.out.println("해당 쿠폰이나 상품을 찾지 못했습니다.");
		}
		System.out.print("결제 진행하시겠습니까?(yes/no)");
		String response = scan.nextLine();
		if (response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("Yes")) {
			for (int i = 0; i < cart.size(); i++) {
				int paymentPrice = 0;
				paymentPrice = paymentPrice + cart.get(i).getPrice();
				System.out.printf("합산금액 %d원을 결제합니다.\n", paymentPrice);
			}
		} else if (response.equalsIgnoreCase("no") || response.equalsIgnoreCase("No")) {
			return;
		} else {
			System.out.println("유효한 값을 입력해주세요.");
		}
		System.out.println("영수증 출력");
		int totalPrice = 0;
		for (int i = 0; i < cart.size(); i++) {
			System.out.println(cart.get(i).toString());
			totalPrice = totalPrice + cart.get(i).getPrice();
		}
		System.out.printf("구매목록의 합산금액은 %d입니다.\n", totalPrice);

	}

	

	public void cartItemAdd(ArrayList<Shopping> shoppingList, ArrayList<Shopping> cart) {
		Scanner scan = new Scanner(new InputStreamReader(System.in));
		for (int i = 0; i < shoppingList.size(); i++) {
			System.out.println(shoppingList.get(i).toString());
		}
		System.out.print("장바구니에 추가할 항목을 입력해주세요.");
		String addItem = scan.nextLine();
		boolean foundFlag = false;
		for (Shopping shopping : shoppingList) {
			if (shopping.getName().equals(addItem)) {
				cart.add(shopping);
				foundFlag = true;
			}
		}
		if (foundFlag == false) {
			System.out.println("해당 상품은 List에 없습니다.");
		}

	}

//	//public void clear() {
//		Scanner scan = new Scanner(new InputStreamReader(System.in));
//		System.out.print("continue>");
//		scan.nextLine();
//		try {
//			String operatingSystem = System.getProperty("os.name");
//			if (operatingSystem.contains("Windows")) {
//				ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
//				Process startProcess = pb.inheritIO().start();
//				startProcess.waitFor();
//			} else {
//				ProcessBuilder pb = new ProcessBuilder("clear");
//				Process startProcess = pb.inheritIO().start();
//				startProcess.waitFor();
//			}
//		} catch (Exception e) {
//			System.out.println(e);
//		}

	//}

	public void productListView(ArrayList<Shopping> shoppingList) {
		Scanner scan = new Scanner(new InputStreamReader(System.in, StandardCharsets.UTF_8));
		int page = 1;
		int pageSize = 5;
		while (true) {
			int totalPage = shoppingList.size() / pageSize;
			int remainValue = shoppingList.size() % 5;
			if (remainValue != 0) {
				totalPage += 1;
			}
			// 해당되는 페이지 시작위치, 끝위치
			int start = pageSize * (page - 1);
			int stop = pageSize * (page - 1) + pageSize;

			// 마지막 페이지일때 나머지값이 있을때 끝위치 1~4증가
			if (page == totalPage && remainValue != 0) {
				stop = pageSize * (page - 1) + remainValue;
			}

			System.out.printf("전체 %dpage/현재 %dpage \n", totalPage, page);
			for (int i = start; i < stop; i++) {
				System.out.println(shoppingList.get(i).toString());
			}
			System.out.printf("(-1:exit)페이지선택");
			page = Integer.parseInt(scan.nextLine());
			if (page == -1) {
				break;
			}
		}

	}

	public void cartListView(ArrayList<Shopping> cart) {
		if (cart.isEmpty()) {
			System.out.println("장바구니가 비어있습니다.");
		} else {
			for (int i = 0; i < cart.size(); i++) {
				System.out.println(cart.get(i).toString());
			}
		}
	}

	
		
}
