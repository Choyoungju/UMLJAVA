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
			System.out.println("\n\n온라인 콘서트 참관 시스템");

			System.out.println("1. 회원가입");
			System.out.println("2. 로그인");
			intro = introinput.nextInt();
			if(intro ==1){
				System.out.println("추가할 아이디를 입력하세요");
				String inputID = scanner.nextLine();

				System.out.println("추가할 아이디의 비밀번호 입력하세요");
				String inputPass = scanner.nextLine();

				if(map.containsKey(inputID)){
					System.out.println("존재하는 아이디입니다.");
					continue;
				}
				else{
					map.put(inputID, inputPass);

				}
			}
			if(intro ==2){

				while(true){
					System.out.println("아이디와 비밀번호를 입력해주세요"); 
					System.out.println("아이디를 입력해주세요.");
					String id = login.nextLine();

					System.out.println("비밀번호를 입력해주세요");
					String password = login.nextLine();

					if(map.containsKey(id)){

						if(map.get(id).equals(password)){
							System.out.println("로그인되었습니다.");


							test.testRun();


							break;
						} else{
							System.out.println("비밀번호가 일치하지 않습니다.");
						}
					} else {
						System.out.println("입력하신 아이디가 존재하지 않습니다.");
					}
				}

			}

		}
	}





}
