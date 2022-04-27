package main;
import java.sql.*;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;

public class Leaderboard {

    Font f=new Font("Arial", Font.BOLD, 18);
    

    public void showRecords(Graphics2D g2){  
        try{  
            //Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            String dbUrl="jdbc:mysql://localhost:3306/anibs?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
            Connection myConnection=DriverManager.getConnection(dbUrl, "root", "Admin2171@R");//uname and passwd
            Statement myStatement=myConnection.createStatement();
            ResultSet myResultSet=myStatement.executeQuery("select * from records");//table name
            int y=48;

            g2.setFont(f);
            g2.setColor(Color.WHITE);

            g2.drawString("Difficulty", 288, 24);
            g2.drawString("Name", 432, 24);
            
            while(myResultSet.next()){
                //System.out.println("difficulty : "+myResultSet.getString("time"));
                g2.drawString(myResultSet.getString("difficulty"), 288, y);
                g2.drawString(myResultSet.getString("name"), 432, y);
                y+=24;
            }
            //System.out.println("Connected");
            myConnection.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }  
    public void pushRecords(GamePanel gp, String name){  
        try{  
            //Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            String dbUrl="jdbc:mysql://localhost:3306/anibs?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
            Connection myConnection=DriverManager.getConnection(dbUrl, "root", "Admin2171@R");//uname and passwd
            PreparedStatement myStatement;
            //String name="100";
            String sqlQuery="insert into records values (?,?)";
            myStatement=myConnection.prepareStatement(sqlQuery);
            if(gp.difficulty==0){
                myStatement.setString(1, "Easy");
                myStatement.setString(2, name);
            }
            else if(gp.difficulty==1){
                myStatement.setString(1, "Medium");
                myStatement.setString(2, name);
            }
            else if(gp.difficulty==2){
                myStatement.setString(1, "Hard");
                myStatement.setString(2, name);
            }
            myStatement.executeUpdate();
            
            //System.out.println("Connected");
            myConnection.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }  
        
}

