package Data;


import java.sql.Connection; 
import java.sql.DatabaseMetaData;
import java.sql.DriverManager; 
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException; 
import java.sql.Statement; 
import java.util.Scanner;

import Member.Consumer;
public class QueryManage { 

	public static String title = "";
	public static String artist= "";
	public static String concertdate= "";
	public static String place= "";
	public static String genre= "";
	public static String qualityType= "";


	public void testRun(){

		String sql;
		int ret, managepass = 0;
		String url = "jdbc:mysql://localhost:3306/myconcert"; 
		Connection con = null; //연결객체를 선언만함
		Statement  stmt = null; //전송객체를 선언만함


		Scanner in = new Scanner(System.in);
		QueryManage manager = new QueryManage();
		PrintMenu printmenu = new PrintMenu();
		Concert concert = new Concert();

		int manageMenu = 0;
		int selectprivil = 0;





		try { //mysql과 연동을 위해서 드라이버를 메모리를 올림,에러시 에러출력
			Class.forName("org.gjt.mm.mysql.Driver"); 
		} catch(Exception e) { 
			System.err.print(e.toString()); 
		} 
		try { 
			con = DriverManager.getConnection(url,"root","apmsetup"); 
			//서버 url,db명,비밀번호 연결객체 획득
			stmt = con.createStatement(); 
			//sql에 입력할 데이터를 mysql로 전송하는 객체가 stmt,실질적인 일은 메소드가 수행


			//
			sql = "DROP TABLE concert;";
			ret = stmt.executeUpdate(sql);

			sql ="CREATE TABLE concert (  title    varchar(30) not null,   artist    varchar(15),   concertdate    date,  place  varchar(30),  genre varchar(30),   qualityType    varchar(50),  primary key (title) );";
			ret = stmt.executeUpdate(sql);
			manager.tableshow(con);

			//sql 삽입
			ret = 0;
			sql=	"INSERT INTO concert VALUES ('Shoes Box', 'Epic high', '2015-11-10', 'SangAm Worldcup', 'hiphop', 'HD')";
			ret += stmt.executeUpdate(sql);

			sql=	"INSERT INTO concert VALUES ('Live wire', 'Seo Tae Ji', '2015-11-23', 'COEX', 'rock', 'HD')";
			ret += stmt.executeUpdate(sql);

			sql=	"INSERT INTO concert VALUES ('Dungurani', 'Lee su young', '2015-11-30', 'Bit Academy', 'ballad', 'normal')";
			ret += stmt.executeUpdate(sql);
			
			sql=	"INSERT INTO concert VALUES ('sk8boi', 'Avril Lavigne', '2015-12-23', 'Art hall', 'rock', 'HD')";
			ret += stmt.executeUpdate(sql);
			
			sql=	"INSERT INTO concert VALUES ('peace rock', 'cherry filter', '2015-12-22', 'KBS', 'rock', 'HD')";
			ret += stmt.executeUpdate(sql);
			
			sql=	"INSERT INTO concert VALUES ('In the road', 'Larc', '2015-12-24', 'japan', 'rock', 'HD')";
			ret += stmt.executeUpdate(sql);
			manager.concertshow(stmt);
			System.out.println("레코드 " + ret + "개가 추가되었습니다."); 
			System.out.println("");


			//sql 소팅
			System.out.println("");

			ResultSet rs7 = stmt.executeQuery("SELECT * from concert ORDER by concert.title asc");
						System.out.println( "타이틀을 가나다순 정리좀 할게요\n" );



			while (rs7.next()) {

				title = rs7.getString("title");
				concert.setTitle(title);

				artist = rs7.getString("artist");
				concert.setArtist(artist);

				concertdate = rs7.getString("concertdate");
				concert.setConcertdate(concertdate);

				place = rs7.getString("place");
				concert.setPlace(place);

				genre = rs7.getString("genre");
				concert.setGenre(genre);

				qualityType = rs7.getString("qualityType");
				concert.setQualityType(qualityType);

				System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s\n", title, artist,concertdate,place,genre,qualityType);

			}

			while(true){

				printmenu.privilMenu();
				selectprivil = in.nextInt();
				
				switch(selectprivil){

				case 1:
					
					do{
						manageMenu = 0;
						System.out.println("관리자 비밀번호를 입력해주세요(뒤로가기는 -1)");
						managepass = in.nextInt();
						if(managepass==5630){
							printmenu.managerMenu();
							manageMenu = in.nextInt();
							while(manageMenu!=-1){

								switch(manageMenu){
								case 1:
									//sql 관리자용 추가
									
									manager.insertCon(stmt);
									manageMenu = -1;
									break;

									//sql 삭제
								case 2:
									manager.deleteCon(stmt);
									manageMenu = -1;
									break;

								case 3:
									//sql 내용 수정(제거 후 추가 방식)
									manager.updateCon(stmt);
									manageMenu = -1;
									break;
								}
							
							}
						}
						else
							continue;
						}while(managepass!=-1);
					
				case 2:
					System.out.println("유저 모드");

					concertshow(stmt);
					manager.selectConcert(stmt, concert, title, artist, concertdate, place, genre, qualityType);

					Consumer consumer = new Consumer(manager);

					//break;
					
					default :
						System.out.println("존재 하지 않는 메뉴입니다. 1,2 번중 선택해주세요.");

				}

			}


		} catch(Exception e) { 
			System.out.println("Exception: " + e.getMessage()); 
		} finally{ //서버객체,연결객체가 null값이 아닐 때 예외처리로 연결을 끊어주는 소스가 필요
			try{  //즉 100명이db에 접속되어있는데 계속100명이접속하는데 미리접속한사람은 그대로 유지 시
				//포화가 된 db서버가 멈춰버릴 수 있음,그래서 사용한 객체,전송은 닫아줘야함
				if ( stmt != null){ stmt.close(); } 
			}catch(Exception e){} 
			try{ 
				if ( con != null){ con.close(); } 
			}catch(Exception e){}  }
		
	}




	public static void concertshow(Statement stmt){
		try{
			ResultSet rs1 = stmt.executeQuery
					("SELECT * FROM concert");
			System.out.println("title          artist         concertdate       place          genre         qualityType");
			System.out.println("--------------------------");
			while (rs1.next()) {

				String title = rs1.getString("title");
				String artist = rs1.getString("artist");
				String concertdate = rs1.getString("concertdate");
				String place = rs1.getString("place");
				String genre = rs1.getString("genre");
				String qualityType = rs1.getString("qualityType");


				//System.out.println(title + "     " + artist+ "     " +  concertdate + "     " + place + "     " + genre + "     " + qualityType);
				System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s\n", title, artist,concertdate,place,genre,qualityType);
			}

			System.out.println("");
		} catch(Exception e) { 
			System.out.println("Exception: " + e.getMessage()); 
		}

	}


	public void tableshow(Connection con){
		try{
			DatabaseMetaData md = con.getMetaData();
			ResultSet rs = md.getTables(null, null, "%", null);
			System.out.println("--------------------------");
			while (rs.next()) {
				System.out.println(rs.getString(3));
			}
			System.out.println("릴레이션 테이블 생성 완료");

			System.out.println("");
		}catch(Exception e) { 
			System.out.println("Exception: " + e.getMessage()); 
		}
	}

	public void deleteCon(Statement stmt) throws SQLException{
		int ret = 0;
		Scanner in = new Scanner(System.in);

		String delete;
		System.out.println("삭제할 콘서트의 타이틀 명을 입력하세요.");
		delete = in.nextLine();

		String sql=	"DELETE FROM concert WHERE title = '" + delete +"'";

		System.out.println("DELETE FROM concert WHERE title = '" + delete +"'");
		ret += stmt.executeUpdate(sql);
		concertshow(stmt);
		System.out.println("레코드 " + ret + "개가 삭제되었습니다."); 
		System.out.println("");
	}

	public void insertCon(Statement stmt) throws SQLException{
		int ret = 0;
		Scanner in = new Scanner(System.in);

		String inTitle, inArtist, inDate, inPlace, inGenre, inQual;
		System.out.println("추가할 콘서트의 타이틀 명을 입력하세요.");
		inTitle = in.nextLine();
		System.out.println("추가하신 타이틀의 아티스트 명을 입력해주세요.");
		inArtist = in.nextLine();
		System.out.println("추가하신 타이틀의 공연 시기를 입력해주세요.ex)2015-10-23");
		inDate = in.nextLine();
		System.out.println("추가하신 타이틀의 장소를 입력해주세요.");
		inPlace = in.nextLine();
		System.out.println("추가하신 타이틀의 장르 명을 입력해주세요.(모두 소문자)");
		inGenre = in.nextLine();
		System.out.println("추가하신 타이틀의 화질을 입력해주세요.(HD/normal)");
		inQual = in.nextLine();

		String sql=	"INSERT INTO concert VALUES('" + inTitle +"' , '" + inTitle  +"' , '" + inDate +"' , '" +inPlace +"' , '" +inGenre +"' , '" +inQual +"')" ;



		System.out.println("INSERT INTO concert VALUES('" + inTitle +"' , '" + inTitle  +"' , '" + inDate +"' , '" +inPlace +"' , '" +inGenre +"' , '" +inQual +"')");
		ret += stmt.executeUpdate(sql);
		concertshow(stmt);
		System.out.println("레코드 " + ret + "개가 추가되었습니다."); 
		System.out.println("");
	}


	public void updateCon(Statement stmt) throws SQLException{
		int ret = 0;
		Scanner in = new Scanner(System.in);

		System.out.println("추가하신 튜플을 제거하고 새로이 등록합니다.");
		deleteCon(stmt);
		insertCon(stmt);

	}

	public void selectConcert(Statement stmt, Concert concert, String title, String artist, String concertdate, String place, String genre, String qualityType) throws SQLException{

		String selectconcert;

		Scanner in = new Scanner(System.in);
		System.out.println("타이틀을 선택해주세요.");
		selectconcert = in.nextLine();

		ResultSet rs1 = stmt.executeQuery
				("SELECT * FROM concert WHERE title = '" + selectconcert +"'");
		System.out.println("title          artist         concertdate       place          genre         qualityType");
		System.out.println("--------------------------");
		while (rs1.next()) {

			title = rs1.getString("title");
			concert.setTitle(title);
			artist = rs1.getString("artist");
			concert.setArtist(artist);
			concertdate = rs1.getString("concertdate");
			concert.setConcertdate(concertdate);
			place = rs1.getString("place");
			concert.setPlace(place);
			genre = rs1.getString("genre");
			concert.setGenre(genre);
			qualityType = rs1.getString("qualityType");
			concert.setQualityType(qualityType);

			//System.out.println(title + "     " + artist+ "     " +  concertdate + "     " + place + "     " + genre + "     " + qualityType);
			System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s\n", title, artist,concertdate,place,genre,qualityType);
		}

		System.out.println("");
	}
}
