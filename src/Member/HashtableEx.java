package Member;
import java.util.*;
import java.sql.Connection; 
import java.sql.DatabaseMetaData;
import java.sql.DriverManager; 
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException; 
import java.sql.Statement; 
import java.util.Scanner;

import Data.QueryManage;

public class HashtableEx {

	//public static void main(String[] args){

	public void HashtableEx(){
		Map<String,String> map = new Hashtable<String, String>();

		map.put("user", "user");
		map.put("cho", "5630");
		map.put("bit", "123");
		QueryManage test = new QueryManage();


		Scanner scanner = new Scanner(System.in);
		Scanner introinput = new Scanner(System.in);
		Scanner login = new Scanner(System.in);

		int intro;

		while(true){
			System.out.println("\n\n�¶��� �ܼ�Ʈ ���� �ý���");

			System.out.println("1. ȸ������");
			System.out.println("2. �α���");
			intro = introinput.nextInt();
			if(intro ==1){
				System.out.println("�߰��� ���̵� �Է��ϼ���");
				String inputID = scanner.nextLine();

				System.out.println("�߰��� ���̵��� ��й�ȣ �Է��ϼ���");
				String inputPass = scanner.nextLine();

				if(map.containsKey(inputID)){
					System.out.println("�����ϴ� ���̵��Դϴ�.");
					continue;
				}
				else{
					map.put(inputID, inputPass);

				}
			}
			if(intro ==2){

				while(true){
					System.out.println("���̵�� ��й�ȣ�� �Է����ּ���"); 
					System.out.println("���̵� �Է����ּ���.");
					String id = login.nextLine();

					System.out.println("��й�ȣ�� �Է����ּ���");
					String password = login.nextLine();

					if(map.containsKey(id)){

						if(map.get(id).equals(password)){
							System.out.println("�α��εǾ����ϴ�.");


							test.testRun();


							break;
						} else{
							System.out.println("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
						}
					} else {
						System.out.println("�Է��Ͻ� ���̵� �������� �ʽ��ϴ�.");
					}
				}

			}

		}
	}





}
