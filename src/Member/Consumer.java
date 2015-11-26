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

	Connection con = null; //���ᰴü�� ������
	Statement  stmt = null; //���۰�ü�� ������
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




		System.out.println("�����Ͻ� title�� ���������� �Է����ּ���.");
		inputdate = in.nextLine();



		quality.selectQuality();
		inputQuality = in.next();


		System.out.println("���������� �������ּ���.");
		System.out.println("1. ������ �Ա�");
		System.out.println("2. ī�� ����");
		inputPayment = in.nextInt();

		switch(inputPayment){
		case 1:
			bankbook.pay();
			System.out.println("\n������ �����մϴ�.\n");
			bankbook.payConfirm();
			break;

		case 2:
			card.pay();
			System.out.println("\n������ �����մϴ�.\n");
			card.payConfirm();
			break;

		default : 
			System.out.println(" 1,2�� �� �������ּ���");
			break;
		}

	}

}
