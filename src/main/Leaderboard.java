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
            ResultSet myResultSet=myStatement.executeQuery("select * from leaderboard");//table name
            int y=48;

            g2.setFont(f);
            g2.setColor(Color.WHITE);

            g2.drawString("Difficulty", 288, 24);
            g2.drawString("Time", 432, 24);
            
            while(myResultSet.next()){
                //System.out.println("difficulty : "+myResultSet.getString("time"));
                g2.drawString(myResultSet.getString("difficulty"), 288, y);
                g2.drawString(myResultSet.getString("time"), 432, y);
                y+=24;
            }
            //System.out.println("Connected");
            myConnection.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }  
    public void pushRecords(GamePanel gp){  
        try{  
            //Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            String dbUrl="jdbc:mysql://localhost:3306/anibs?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
            Connection myConnection=DriverManager.getConnection(dbUrl, "root", "Admin2171@R");//uname and passwd
            Statement myStatement=myConnection.createStatement();
            int k=100;
            if(gp.difficulty==0){
                myStatement.executeUpdate("insert into leaderboard values('Easy',"+k+")");
            }
            else if(gp.difficulty==1){
                myStatement.executeUpdate("insert into leaderboard values('Medium',"+k+")");
            }
            else if(gp.difficulty==2){
                myStatement.executeUpdate("insert into leaderboard values('Hard',"+k+")");
            }
            
            //System.out.println("Connected");
            myConnection.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }  
        
}

