package com.icss.meeting.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class BaseDao {
	public static final  String DB_URL = "java:comp/env/jdbc/OracleMeeting";
	protected Connection conn;

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}
	/**
	 * 与数据库连接池 建立连接 
	 * @param strJNDIName
	 */
	protected void openConnection(String strJNDIName){
		try {
			if(this.conn == null || this.conn.isClosed()){
				InitialContext context = new InitialContext();
	            DataSource ds = (DataSource)context.lookup(strJNDIName.trim()) ;  //很重要，从配置文件中读到的数据常有不可见字符
	            conn = ds.getConnection();	
			}            		
        } 
		catch (NamingException ex) {
			ex.printStackTrace();			
        } catch (Exception ex) {
        	ex.printStackTrace();        	   	
        }
	}

	public void closeConnection(){
		if(this.conn!= null){
			try {
				conn.close();
			} catch (Exception e) {
                 e.printStackTrace();
			}
		}
	}
	public void beginTransaction() throws Exception {
		this.openConnection(DB_URL);
		if(this.conn!= null){
			this.conn.setAutoCommit(false);
		}
		
	}
	public void commit()throws Exception{
		this.openConnection(DB_URL);
		if(this.conn!= null){
			this.conn.commit();
		}
	}
	public void rollback()throws Exception{
		this.openConnection(DB_URL);
		if(this.conn != null){
			this.conn.rollback();
		}
	}
	
	
	
	
	
	public String pageSql(String sql,int start,int end ){
		String newSql =" select * from(select rownum rn, t.* from ( "+ sql +" ) t )  where rn>=" + start + " and rn<" + end ;
		return newSql;
	}
	public int getpageCount(String sql)throws Exception{
		String newSql = "select count(*) rowcount from( " + sql + ")";
		int allRows = 0;
		this.openConnection(DB_URL);
		PreparedStatement ps = this.conn.prepareStatement(newSql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			allRows = rs.getInt("rowcount");
		}
		return allRows;
		
	}

}
