package cn.peng.spring.jdbc;

import java.sql.SQLException;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class MySQLDatabaseConnection {
	public static final String DRIVER = "org.gjt.mm.mysql.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/mldn";
	public static final String USERNAME = "root";
	public static final String PASSWORD = "mysqladmin";
	
	public static void main(String[] args) throws SQLException {
		DriverManagerDataSource datasource = new DriverManagerDataSource();
		datasource.setDriverClassName(DRIVER);
		datasource.setUrl(URL);
		datasource.setUsername(USERNAME);
		datasource.setPassword(PASSWORD);
		System.out.println(datasource.getConnection());
		datasource.getConnection().close();
		
	}
}
