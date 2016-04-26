/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Dennis T
 */
public class TempToy {

    private int id;
    private String name;
    private String des;
    private String conDes;
    private int sex;
    private int age;
    private float price;
    private float orgPrice;
    private int amount;
    private String picUrl;
    private int cid;
    private String customerName;
    private int categoryId;
    private String categoryName;
    private int tid;

    public TempToy() {
    }
    
    public void setCustomerName(String customerName){
        this.customerName=customerName;
    }

    public String getCustomerName(){
        return this.customerName;
    }
    public void setCategoryName(String categoryName){
        this.categoryName=categoryName;
    }
    
    public String getCategoryName(){
        return this.categoryName;
    }
    
    
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return this.des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getConDes() {
        return this.conDes;
    }

    public void setConDes(String conDes) {
        this.conDes = conDes;
    }

    public int getSex() {
        return this.sex;
    }

    public void setSex(int sex) {
        // Needs some kind of validation
        this.sex = sex;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getPrice() {
        return this.price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getOrgPrice() {
        return this.orgPrice;
    }

    public void setOrgPrice(float orgPrice) {
        this.orgPrice = orgPrice;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getPicUrl() {
        return this.picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public int getCid() {
        return this.cid;
    }

    public void setCid(int Cid) throws SQLException {
        this.cid = Cid;
        setCustomerName();
    }

    public int getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(int catId) throws SQLException {
        this.categoryId = catId;
        setCategoryName();
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
    
    private void setCustomerName() throws SQLException{
        if(cid != 0) {
            Customer c = new Customer();
            c.setId(cid);
            c.getOnId();
            this.customerName = c.getUsername();
        } else {
            this.customerName = "Unknown";
        }        
    }

    public void insert() throws ClassNotFoundException, SQLException {
        try {
            Globals.openConn();
            PreparedStatement pstmt = Globals.con.prepareStatement("INSERT INTO [TempToys] ([Price], [Description], [ConditionDescription], [OrgPrice], [Amount], [Name], [Age], [Sex], [Cid], [CategoryId], [PicUrl], [Tid]) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)");
            pstmt.setString(1, Float.toString(price));
            pstmt.setString(2, des);
            pstmt.setString(3, conDes);
            pstmt.setString(4, Float.toString(orgPrice));
            pstmt.setString(5, Integer.toString(amount));
            pstmt.setString(6, name);
            pstmt.setString(7, Integer.toString(age));
            pstmt.setString(8, Integer.toString(sex));
            pstmt.setString(9, Integer.toString(cid));
            pstmt.setString(10, Integer.toString(categoryId));
            pstmt.setString(11, picUrl);
            pstmt.setString(12, Integer.toString(tid));

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
            if (rs != null) {
                rs.close();
            }

            if (stmt != null) {
                stmt.close();
            }

            if (pstmt != null) {
                pstmt.close();
            }

            Globals.con.close();

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
            PreparedStatement pstmt = Globals.con.prepareStatement("UPDATE [TempToys] SET [Price] = ?, [Description] = ?, [ConditionDescription] = ?, [OrgPrice] = ?, [Amount] = ?, [Name] = ?, [Age] = ?, [Sex] = ?, [Cid] = ?, [CategoryId] = ?, [PicUrl] = ?, [Tid] = ? WHERE [TTid] = ?");
            pstmt.setString(1, Float.toString(price));
            pstmt.setString(2, des);
            pstmt.setString(3, conDes);
            pstmt.setString(4, Float.toString(orgPrice));
            pstmt.setString(5, Integer.toString(amount));
            pstmt.setString(6, name);
            pstmt.setString(7, Integer.toString(age));
            pstmt.setString(8, Integer.toString(sex));
            pstmt.setString(9, Integer.toString(cid));
            pstmt.setString(10, Integer.toString(categoryId));
            pstmt.setString(11, Integer.toString(id));
            pstmt.setString(12, picUrl);
            pstmt.setString(13, Integer.toString(tid));

            // execute the SQL statement
            pstmt.executeUpdate();

            // close connections
            if (pstmt != null) {
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
            PreparedStatement pstmt = Globals.con.prepareStatement("DELETE FROM [TempToys] WHERE [TTid] = ?");
            pstmt.setString(1, Integer.toString(id));

            // execute the SQL statement
            pstmt.executeUpdate();

            // close connections
            if (pstmt != null) {
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

            if (id == 0) {
                throw new IllegalArgumentException("Id not set");
            }

            Globals.openConn();

            // Create SQL statement and execute	
            Statement stmt = Globals.con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("SELECT * FROM [TempToys] WHERE [TTid] = " + Integer.toString(id));

            int numRow = 0;
            if (rs != null && rs.last() != false) {
                numRow = rs.getRow();
                rs.beforeFirst();
            }

            if (numRow == 1) {
                while (rs != null && rs.next() != false) {
                    price = Float.parseFloat(rs.getString("Price"));
                    des = rs.getString("Description");
                    conDes = rs.getString("ConditionDescription");
                    orgPrice = Float.parseFloat(rs.getString("OrgPrice"));
                    amount = Integer.parseInt(rs.getString("Amount"));
                    name = rs.getString("Name");
                    age = Integer.parseInt(rs.getString("Age"));
                    sex = Integer.parseInt(rs.getString("Sex"));
                    setCid(Integer.parseInt(rs.getString("Cid")));
                    setCategoryId(Integer.parseInt(rs.getString("CategoryId")));
                    picUrl = rs.getString("PicUrl");
                    tid = Integer.parseInt(rs.getString("Tid"));
                }
            }

            // close connections and statements
            if (rs != null) {
                rs.close();
            }

            if (stmt != null) {
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

    public void getOnTid() throws SQLException, ClassNotFoundException {
        try {

            if (tid == 0) {
                throw new IllegalArgumentException("Id not set");
            }

            Globals.openConn();

            // Create SQL statement and execute	
            Statement stmt = Globals.con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("SELECT * FROM [Toys] WHERE [Tid] = " + tid);

            int numRow = 0;
            if (rs != null && rs.last() != false) {
                numRow = rs.getRow();
                rs.beforeFirst();
            }

            if (numRow == 1) {
                while (rs != null && rs.next() != false) {
                    name = rs.getString("Name");
                    des = rs.getString("Description");
                    age = Integer.parseInt(rs.getString("Age"));
                    sex = Integer.parseInt(rs.getString("Sex"));
                    orgPrice = Float.parseFloat(rs.getString("Price"));
                    picUrl = rs.getString("PicUrl");
                    categoryId=Integer.parseInt(rs.getString("CategoryId"));
                }
            }

            // close connections and statements
            if (rs != null) {
                rs.close();
            }

            if (stmt != null) {
                stmt.close();
            }

            Globals.closeConn();

        } catch (IllegalArgumentException e) {
            Globals.beanLog.info(e.toString());
        } catch (SQLException e) {
            Globals.beanLog.info(e.toString());
        } finally {
            Globals.closeConn();
        }
    }

    /**
     * @return the tid
     */
    public int getTid() {
        return tid;
    }

    /**
     * @param tid the tid to set
     */
    public void setTid(int tid) {
        this.tid = tid;
    }
}
