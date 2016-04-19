/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean.SpecificBean;

import Bean.Admin;
import Bean.Globals;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

/**
 *
 * @author Ugo
 */
public class ReplyComment {
    private int id;
    private Timestamp postTime;
    private String comment;
    private int sid;
    private int mid;
    private String username;
    
    
    public ReplyComment(){}
    
    public String getComment(){
        return this.comment;
    }
    
    public Timestamp getPostTime(){
        return this.postTime;
    }
    
    public String getUsername(){
        return username;
    }
    
    public int getMid(){
        return this.mid;
    }
    
    public int getSid(){
        return this.sid;
    }
    
    public void setId(int id){
        this.id=id;
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
                    sid = Integer.parseInt(rs.getString("sid"));
                    mid = Integer.parseInt(rs.getString("mid"));
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
            Admin admin = new Admin();
            admin.setId(mid);
            admin.getOnId();
            this.username=admin.getUsername();
            
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
