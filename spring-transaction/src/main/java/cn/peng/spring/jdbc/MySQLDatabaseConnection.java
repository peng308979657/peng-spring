package cn.peng.spring.jdbc;

import java.sql.SQLException;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class MySQLDatabaseConnection {
	public static final String DRIVER = "org.gjt.mm.mysql.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/mldn";
	public static final String USERNAME = "root";
	public static final String PASSWORD = "mysqladmin";
	
	public static void main(String[] args) throws SQLException {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(DRIVER);
		dataSource.setUrl(URL);
		dataSource.setUsername(USERNAME);
		dataSource.setPassword(PASSWORD);
		System.out.println(dataSource.getConnection());
		dataSource.getConnection().close();
		
	}
}
