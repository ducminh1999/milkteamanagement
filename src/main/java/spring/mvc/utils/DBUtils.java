/**
 * (C) Copyright of Fresher FPT Software Academy. All Rights Reserved
 *
 * @author Minh
 * @date Aug 14, 2021
 * @version 1.0
 */
package spring.mvc.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
	static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=CMS";
	static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	static final String USER = "sa";
	static final String PASS = "root";
	public static void main(String[] args) {
//		UserRes2 userRes = new UserRes2();
//		System.out.println(userRes.findAll());
	}

	public static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=CMS", "sa", "root");
			System.out.println("connect successfully!");
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("connect failure!"+e);
			e.printStackTrace();
		}
		return connection;
	}

}
