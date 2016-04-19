/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;
import Bean.SpecificBean.ReplyComment;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.function.Supplier;
import java.util.logging.Logger;
/**
 *
 * @author Dennis T
 */
public class Comment {
    private int id;
    private Timestamp postTime;
    private String comment;
    private int sid;
    private int cid;
    private int mid;
    private int replyId;
    private ReplyComment reply;
    private String username;
    
    
    public Comment(){};
    
    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }
    
    public Timestamp getPostTime(){
        return this.postTime;
    }
    public void setPostTime(Timestamp postTime){
        this.postTime = postTime;
    }
    
    public String getComment(){
        return this.comment;
    }
    public void setComment(String comment){
        this.comment = comment;
    }
    
    public int getSid(){
        return this.sid;
    }
    public void setSid(int sid){
        this.sid = sid;
    }
    
    public int getCid(){
        return this.cid;
    }
    public void setCid(int cid){
        this.cid = cid;
    }
    
    public int getMid(){
        return this.mid;
    }
    public void setMid(int mid){
        this.mid = mid;
    }
    
    public int getReplyId(){
        return this.replyId;
    }
    public void setReplyId(int replyId){
        this.replyId = replyId;
    }
    
    public ReplyComment getReply(){
        return this.reply;
    }
    
    public void insert() throws ClassNotFoundException, SQLException{
    try {
            Globals.openConn();
            
            // Create a preparedstatement to set the SQL statement	
            PreparedStatement pstmt = Globals.con.prepareStatement("INSERT INTO [Comment] ([PostTime], [Comment], [Sid], [Cid], [Mid], [ReplyId]) VALUES (?, ?, ?, ?, ?, ?)");
            pstmt.setTimestamp(1, postTime);
            pstmt.setString(2, comment);
            pstmt.setString(3, Integer.toString(sid));
            pstmt.setString(4, Integer.toString(sid));
            pstmt.setString(5, Integer.toString(mid));
            pstmt.setString(6, Integer.toString(replyId));
            
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
            PreparedStatement pstmt = Globals.con.prepareStatement("UPDATE [Comment] SET [PostTime] = ?, [Comment] = ?, [Sid] = ?, [Cid] = ?, [Mid] = ?, [ReplyId] = ? WHERE [CommentId] = ?");
            pstmt.setTimestamp(1, postTime);
            pstmt.setString(2, comment);
            pstmt.setString(3, Integer.toString(sid));
            pstmt.setString(4, Integer.toString(cid));
            pstmt.setString(5, Integer.toString(mid));
            pstmt.setString(6, Integer.toString(replyId));
            pstmt.setString(7, Integer.toString(id));
                        
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
            PreparedStatement pstmt = Globals.con.prepareStatement("DELETE FROM [Comment] WHERE [CommentId] = ?");
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
            ResultSet rs = stmt.executeQuery("SELECT * FROM [Comment] WHERE [CommentId] = " + Integer.toString(id));
            
            int numRow = 0;
            if(rs != null && rs.last() != false) {
                numRow = rs.getRow();
                rs.beforeFirst();
            }
                
            if(numRow == 1) {
                while(rs != null && rs.next() != false) {
                    postTime = rs.getTimestamp("PostTime");
                    comment = rs.getString("Comment");
                    sid = Integer.parseInt(rs.getString("Sid"));
                    cid = Integer.parseInt(rs.getString("Cid"));
                    mid = Integer.parseInt(rs.getString("Mid"));
                    replyId = Integer.parseInt(rs.getString("ReplyId"));
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

    public void setReplyComment(int idd) throws SQLException {
        reply = new ReplyComment();
        reply.setId(idd);
        reply.getOnId();
    }

    public void setCustomerUsername(String username) {
        this.username=username;
    }
}
