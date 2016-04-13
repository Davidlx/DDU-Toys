/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.function.Supplier;
import java.util.logging.Logger;

/**
 *
 * @author Dennis T
 */
public class Toy {
    private int id;
    private String name;
    private String des;
    private int sex;
    private int age;
    private float price;
    private String picUrl;
    private int categoryId;
    private String categoryName;
    
    public Toy(){};
    
    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }
    
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    
    public String getDes(){
        return this.des;
    }
    public void setDes(String des){
        this.des = des;
    }
    
    public int getSex(){
        return this.sex;
    }
    public void setSex(int sex){
        // Need some kind of validation
        this.sex = sex;
    }
    
    public int getAge(){
        return this.age;
    }
    public void setAge(int age){
        this.age = age;
    }
    
    public float getPrice(){
        return this.price;
    }
    public void setPrice(float price){
        this.price = price;
    }
    
    public String getPicUrl(){
        return this.picUrl;
    }
    public void setPicUrl(String picUrl){
        this.picUrl = picUrl;
    }
    
    public int getCategoryId(){
        return this.categoryId;
    }
    public void setCategoryId(int catId) throws SQLException{
        this.categoryId = catId;
        setCategoryName();
    }
    
    public String getCategoryName(){
        return this.categoryName;
    }
    private void setCategoryName() throws SQLException{
        if(categoryId != 0) {
            Category cat = new Category();
            cat.setId(categoryId);
            cat.getOnId();
            this.categoryName = cat.getName();
        } else {
            this.categoryName = "Unknown";
        }        
    }
    
    public void insert() throws ClassNotFoundException, SQLException{
    try {
            Globals.openConn();
            
            // Create a preparedstatement to set the SQL statement	
            PreparedStatement pstmt = Globals.con.prepareStatement("INSERT INTO [Toys] ([Name], [Description], [Sex], [Age], [Price], [PicUrl], [CategoryId]) VALUES (?, ?, ?, ?, ?, ?, ?)");
            pstmt.setString(1, name);
            pstmt.setString(2, des);
            pstmt.setString(3, Integer.toString(sex));
            pstmt.setString(4, Integer.toString(age));
            pstmt.setString(5, Float.toString(price));
            pstmt.setString(6, picUrl);
            pstmt.setString(7, Integer.toString(categoryId));
            
            // execute the SQL statement
            pstmt.executeUpdate();
            
            // set new id of object
            Statement stmt = Globals.con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT @@IDENTITY AS [@@IDENTITY]");
            if (rs != null && rs.next() != false) {
                id = rs.getInt(1);
                rs.close();
            }
                      
            // close connections and statements
            if(rs != null) {
                rs.close();
            }
            
            if(stmt != null) {
                stmt.close();
            }
            
            if(pstmt != null) {
                pstmt.close();
            }
            
            Globals.closeConn();
            
        } catch (ClassNotFoundException e) {
            Globals.beanLog.info(e.toString());
        } catch (SQLException e) {
            Globals.beanLog.info(e.toString());
        } finally {
            Globals.closeConn();
        }        
    }
    
    public void update() throws SQLException {
    try {
            Globals.openConn();
            
            // Create a preparedstatement to set the SQL statement	
            PreparedStatement pstmt = Globals.con.prepareStatement("UPDATE [Toys] SET [Name] = ?, [Description] = ?, [Sex] = ?, [Age] = ?, [Price] = ?, [PicUrl] = ?, [CategoryId] = ? WHERE [Tid] = ?");
            pstmt.setString(1, name);
            pstmt.setString(2, des);
            pstmt.setString(3, Integer.toString(sex));
            pstmt.setString(4, Integer.toString(age));
            pstmt.setString(5, Float.toString(price));
            pstmt.setString(6, picUrl);
            pstmt.setString(7, Integer.toString(categoryId));
            pstmt.setString(8, Integer.toString(id));
                        
            // execute the SQL statement
            pstmt.executeUpdate();
            
            // close connections
            if(pstmt != null) {
                pstmt.close();
            }
            
            Globals.closeConn();
            
        } catch (ClassNotFoundException e) {
            Globals.beanLog.info(e.toString());
        } catch (SQLException e) {
            Globals.beanLog.info(e.toString());
        } finally {
            Globals.closeConn();
        } 
    }
    
    public void delete() throws SQLException {
    try {
            Globals.openConn();
            
            // Create a preparedstatement to set the SQL statement	
            PreparedStatement pstmt = Globals.con.prepareStatement("DELETE FROM [Toys] WHERE [Tid] = ?");
            pstmt.setString(1, Integer.toString(id));
                        
            // execute the SQL statement
            pstmt.executeUpdate();
            
            // close connections
            if(pstmt != null) {
                pstmt.close();
            }
            
            Globals.closeConn();
            
        } catch (ClassNotFoundException e) {
            Globals.beanLog.info(e.toString());
        } catch (SQLException e) {
            Globals.beanLog.info(e.toString());
        } finally {
            Globals.closeConn();
        } 
    }

    public void getOnId() throws SQLException {
    try {
            
            if(id == 0) {
               throw new IllegalArgumentException("Id not set");
            }
            
            Globals.openConn();

            
            // Create SQL statement and execute	
            Statement stmt = Globals.con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("SELECT * FROM [Toys] WHERE [Tid] = " + Integer.toString(id));
            
            int numRow = 0;
            if(rs != null && rs.last() != false) {
                numRow = rs.getRow();
                rs.beforeFirst();
            }
                
            if(numRow == 1) {
                while(rs != null && rs.next() != false) {
                    name = rs.getString("Name");
                    des = rs.getString("Description");
                    price = Float.parseFloat(rs.getString("Price"));
                    age = Integer.parseInt(rs.getString("Age"));
                    sex = Integer.parseInt(rs.getString("Sex"));
                    picUrl = rs.getString("PicUrl");
                    setCategoryId(Integer.parseInt(rs.getString("CategoryId")));
                }
            }
                                  
            // close connections and statements
            if(rs != null) {
                rs.close();
            }
            
            if(stmt != null) {
                stmt.close();
            }
            
            Globals.closeConn();
            
        } catch (IllegalArgumentException e) {
            Globals.beanLog.info(e.toString());
        } catch (ClassNotFoundException e) {
            Globals.beanLog.info(e.toString());
        } catch (SQLException e) {
            Globals.beanLog.info(e.toString());
        } finally {
            Globals.closeConn();
        }     
    }
    
}
