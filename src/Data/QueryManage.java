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
		Connection con = null; //���ᰴü�� ������
		Statement  stmt = null; //���۰�ü�� ������


		Scanner in = new Scanner(System.in);
		QueryManage manager = new QueryManage();
		PrintMenu printmenu = new PrintMenu();
		Concert concert = new Concert();

		int manageMenu = 0;
		int selectprivil = 0;





		try { //mysql�� ������ ���ؼ� ����̹��� �޸𸮸� �ø�,������ �������
			Class.forName("org.gjt.mm.mysql.Driver"); 
		} catch(Exception e) { 
			System.err.print(e.toString()); 
		} 
		try { 
			con = DriverManager.getConnection(url,"root","apmsetup"); 
			//���� url,db��,��й�ȣ ���ᰴü ȹ��
			stmt = con.createStatement(); 
			//sql�� �Է��� �����͸� mysql�� �����ϴ� ��ü�� stmt,�������� ���� �޼ҵ尡 ����


			//
			sql = "DROP TABLE concert;";
			ret = stmt.executeUpdate(sql);

			sql ="CREATE TABLE concert (  title    varchar(30) not null,   artist    varchar(15),   concertdate    date,  place  varchar(30),  genre varchar(30),   qualityType    varchar(50),  primary key (title) );";
			ret = stmt.executeUpdate(sql);
			manager.tableshow(con);

			//sql ����
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
			System.out.println("���ڵ� " + ret + "���� �߰��Ǿ����ϴ�."); 
			System.out.println("");


			//sql ����
			System.out.println("");

			ResultSet rs7 = stmt.executeQuery("SELECT * from concert ORDER by concert.title asc");
						System.out.println( "Ÿ��Ʋ�� �����ټ� ������ �ҰԿ�\n" );



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
						System.out.println("������ ��й�ȣ�� �Է����ּ���(�ڷΰ���� -1)");
						managepass = in.nextInt();
						if(managepass==5630){
							printmenu.managerMenu();
							manageMenu = in.nextInt();
							while(manageMenu!=-1){

								switch(manageMenu){
								case 1:
									//sql �����ڿ� �߰�
									
									manager.insertCon(stmt);
									manageMenu = -1;
									break;

									//sql ����
								case 2:
									manager.deleteCon(stmt);
									manageMenu = -1;
									break;

								case 3:
									//sql ���� ����(���� �� �߰� ���)
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
					System.out.println("���� ���");

					concertshow(stmt);
					manager.selectConcert(stmt, concert, title, artist, concertdate, place, genre, qualityType);

					Consumer consumer = new Consumer(manager);

					//break;
					
					default :
						System.out.println("���� ���� �ʴ� �޴��Դϴ�. 1,2 ���� �������ּ���.");

				}

			}


		} catch(Exception e) { 
			System.out.println("Exception: " + e.getMessage()); 
		} finally{ //������ü,���ᰴü�� null���� �ƴ� �� ����ó���� ������ �����ִ� �ҽ��� �ʿ�
			try{  //�� 100����db�� ���ӵǾ��ִµ� ���100���������ϴµ� �̸������ѻ���� �״�� ���� ��
				//��ȭ�� �� db������ ������� �� ����,�׷��� ����� ��ü,������ �ݾ������
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
			System.out.println("�����̼� ���̺� ���� �Ϸ�");

			System.out.println("");
		}catch(Exception e) { 
			System.out.println("Exception: " + e.getMessage()); 
		}
	}

	public void deleteCon(Statement stmt) throws SQLException{
		int ret = 0;
		Scanner in = new Scanner(System.in);

		String delete;
		System.out.println("������ �ܼ�Ʈ�� Ÿ��Ʋ ���� �Է��ϼ���.");
		delete = in.nextLine();

		String sql=	"DELETE FROM concert WHERE title = '" + delete +"'";

		System.out.println("DELETE FROM concert WHERE title = '" + delete +"'");
		ret += stmt.executeUpdate(sql);
		concertshow(stmt);
		System.out.println("���ڵ� " + ret + "���� �����Ǿ����ϴ�."); 
		System.out.println("");
	}

	public void insertCon(Statement stmt) throws SQLException{
		int ret = 0;
		Scanner in = new Scanner(System.in);

		String inTitle, inArtist, inDate, inPlace, inGenre, inQual;
		System.out.println("�߰��� �ܼ�Ʈ�� Ÿ��Ʋ ���� �Է��ϼ���.");
		inTitle = in.nextLine();
		System.out.println("�߰��Ͻ� Ÿ��Ʋ�� ��Ƽ��Ʈ ���� �Է����ּ���.");
		inArtist = in.nextLine();
		System.out.println("�߰��Ͻ� Ÿ��Ʋ�� ���� �ñ⸦ �Է����ּ���.ex)2015-10-23");
		inDate = in.nextLine();
		System.out.println("�߰��Ͻ� Ÿ��Ʋ�� ��Ҹ� �Է����ּ���.");
		inPlace = in.nextLine();
		System.out.println("�߰��Ͻ� Ÿ��Ʋ�� �帣 ���� �Է����ּ���.(��� �ҹ���)");
		inGenre = in.nextLine();
		System.out.println("�߰��Ͻ� Ÿ��Ʋ�� ȭ���� �Է����ּ���.(HD/normal)");
		inQual = in.nextLine();

		String sql=	"INSERT INTO concert VALUES('" + inTitle +"' , '" + inTitle  +"' , '" + inDate +"' , '" +inPlace +"' , '" +inGenre +"' , '" +inQual +"')" ;



		System.out.println("INSERT INTO concert VALUES('" + inTitle +"' , '" + inTitle  +"' , '" + inDate +"' , '" +inPlace +"' , '" +inGenre +"' , '" +inQual +"')");
		ret += stmt.executeUpdate(sql);
		concertshow(stmt);
		System.out.println("���ڵ� " + ret + "���� �߰��Ǿ����ϴ�."); 
		System.out.println("");
	}


	public void updateCon(Statement stmt) throws SQLException{
		int ret = 0;
		Scanner in = new Scanner(System.in);

		System.out.println("�߰��Ͻ� Ʃ���� �����ϰ� ������ ����մϴ�.");
		deleteCon(stmt);
		insertCon(stmt);

	}

	public void selectConcert(Statement stmt, Concert concert, String title, String artist, String concertdate, String place, String genre, String qualityType) throws SQLException{

		String selectconcert;

		Scanner in = new Scanner(System.in);
		System.out.println("Ÿ��Ʋ�� �������ּ���.");
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
