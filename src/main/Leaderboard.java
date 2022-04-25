package main;
import java.sql.*;

public class Leaderboard {
    public void showRecords(){  
        try{  
            //Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            String dbUrl="jdbc:mysql://localhost:3306/anibs?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
            Connection myConnection=DriverManager.getConnection(dbUrl, "root", "Admin2171@R");
            Statement myStatement=myConnection.createStatement();
            ResultSet myResultSet=myStatement.executeQuery("select * from leaderboard");
            while(myResultSet.next()){
                System.out.println("username : "+myResultSet.getString("time"));
            }
            System.out.println("Connected");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }  
        
}

