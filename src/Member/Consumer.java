package Member;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import Data.Concert;
import Data.Quality;
import Data.QueryManage;
import Pay.Bankbook;
import Pay.Card;
import Pay.Payment;



public class Consumer extends QueryManage{
	String sql;
	int ret;
	String url = "jdbc:mysql://localhost:3306/myconcert"; 

	Connection con = null; //연결객체를 선언만함
	Statement  stmt = null; //전송객체를 선언만함
	Scanner in = new Scanner(System.in);

	Payment bankbook = new Bankbook();
	Payment card = new Card();
	Concert concert = new Concert();
	QueryManage toget = new Concert();
	Quality quality = new Quality();
	QueryManage selection = new QueryManage();

	String inputTitle, inputQuality, inputdate;
	int inputPayment;

	public Consumer(QueryManage test){




		System.out.println("선택하신 title의 공연연도를 입력해주세요.");
		inputdate = in.nextLine();



		quality.selectQuality();
		inputQuality = in.next();


		System.out.println("결제수단을 선택해주세요.");
		System.out.println("1. 무통장 입금");
		System.out.println("2. 카드 결제");
		inputPayment = in.nextInt();

		switch(inputPayment){
		case 1:
			bankbook.pay();
			System.out.println("\n결제를 진행합니다.\n");
			bankbook.payConfirm();
			break;

		case 2:
			card.pay();
			System.out.println("\n결제를 진행합니다.\n");
			card.payConfirm();
			break;

		default : 
			System.out.println(" 1,2번 중 선택해주세요");
			break;
		}

	}

}
