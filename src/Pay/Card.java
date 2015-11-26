package Pay;




public class Card implements Payment {

	@Override
	public void pay() {
		// TODO Auto-generated method stub
		System.out.println("결제 카드 선택과 공인 인증서, 보안카드 번호 입력");
	}

	@Override
	public void payConfirm() {
		// TODO Auto-generated method stub
		System.out.println("결제가 완료되었습니다.");
	}
	
	

}
