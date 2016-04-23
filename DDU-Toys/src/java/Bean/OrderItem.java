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
public class OrderItem {
    private int id;
    private int recycled;
    private int amount;
    private float price;
    private String description;
    private int oid;
    private int tid;
    private int cid;
    
    public OrderItem(){};
    
    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }
    
    public int getRecycled(){
        return this.recycled;
    }
    public void setRecycled(int recycled){
        if(recycled > 1)
            recycled = 1;
        
        this.recycled = recycled;
    }
    
    public int getAmount(){
        return this.amount;
    }
    public void setAmount(int amount){
        this.amount = amount;
    }
    
    public float getPrice(){
        return this.price;
    }
    public void setPrice(float price){
        this.price = price;
    }
    
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    
    public int getOid(){
        return this.oid;
    }
    public void setOid(int Oid){
        this.oid = Oid;
    }
    
    public int getTid(){
        return this.tid;
    }
    public void setTid(int Tid){
        this.tid = Tid;
    }
    
    public void insert() throws ClassNotFoundException, SQLException{
    try {
            Globals.openConn();
            
            // Create a preparedstatement to set the SQL statement	
            PreparedStatement pstmt = Globals.con.prepareStatement("INSERT INTO [OrderItem] ([Recycled], [Amount], [Price], [Description], [Oid], [Tid], [Cid]) VALUES (?, ?, ?, ?, ?, ?, ?)");
            pstmt.setString(1, Integer.toString(recycled));
            pstmt.setString(2, Integer.toString(amount));
            pstmt.setString(3, Float.toString(price));
            pstmt.setString(4, description);
            pstmt.setString(5, Integer.toString(oid));
            pstmt.setString(6, Integer.toString(tid));
            pstmt.setString(7, Integer.toString(cid));
            
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
            PreparedStatement pstmt = Globals.con.prepareStatement("UPDATE [OrderItem] SET [Recycled] = ?, [Amount] = ?, [Price] = ?, [Description] = ?, [Oid] = ?, [Tid] = ?, [Cid] = ? WHERE [OItemId] = ?");
            pstmt.setString(1, Integer.toString(recycled));
            pstmt.setString(2, Integer.toString(amount));
            pstmt.setString(3, Float.toString(price));
            pstmt.setString(4, description);
            pstmt.setString(5, Integer.toString(oid));
            pstmt.setString(6, Integer.toString(tid));
            pstmt.setString(7, Integer.toString(cid));
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
            PreparedStatement pstmt = Globals.con.prepareStatement("DELETE FROM [OrderItem] WHERE [OItemId] = ?");
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
            ResultSet rs = stmt.executeQuery("SELECT * FROM [OrderItem] WHERE [OItemId] = " + Integer.toString(id));
            
            int numRow = 0;
            if(rs != null && rs.last() != false) {
                numRow = rs.getRow();
                rs.beforeFirst();
            }
                
            if(numRow == 1) {
                while(rs != null && rs.next() != false) {
                    recycled = Integer.parseInt(rs.getString("Recycled"));
                    amount = Integer.parseInt(rs.getString("Amount"));
                    price = Float.parseFloat(rs.getString("Price"));
                    description = rs.getString("Description");
                    oid = Integer.parseInt(rs.getString("Oid"));
                    tid = Integer.parseInt(rs.getString("Tid"));
                    cid = Integer.parseInt(rs.getString("Cid"));
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

    /**
     * @return the cid
     */
    public int getCid() {
        return cid;
    }

    /**
     * @param cid the cid to set
     */
    public void setCid(int cid) {
        this.cid = cid;
    }
    
}
