package Data;

public class PrintMenu {

	void privilMenu(){
		
		System.out.println("유저 타입을 입력하세요.");
		System.out.println("1. 관리자모드");
		System.out.println("2. 일반 유저");
	}
	
//	void normalMenu(){
//		System.out.println("1.");
//		System.out.println("유저 타입을 입력하세요.");
//		System.out.println("1. 일반 유저");
//		System.out.println("2. 프로그램 관리 매니저");
//	}
	
	void managerMenu(){
		System.out.println("\n\n관리자 메뉴를 선택해주세요.(-1 입력시 빠져나옴)");
		System.out.println("1. 콘서트 추가");
		System.out.println("2. 콘서트 삭제");
		System.out.println("3. 콘서트 수정");
	}
	
	
}
