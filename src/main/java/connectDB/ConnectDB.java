package ConnectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import ConnectDB.ConnectDB;

public class ConnectDB {
	public static Connection con = null;
	private static ConnectDB instance = new ConnectDB();
	
	public Connection connect() throws SQLException, ClassNotFoundException{
		String url = "jdbc:sqlserver://localhost:51010;databaseName=QLBVT";
		String user = "sa";
		String password = "123";
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		con = DriverManager.getConnection(url, user, password);
		if (con != null) {
			System.out.println("Kết nối thành công");
		}
		return con;
	}
	public void disconnect() {
		if (con!= null) {
			try {
				con.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	public static ConnectDB getInstance() {
		return instance;
	}
	public static Connection getConnection() {
		return con;
	}
	public ResultSet excuteQueryRead(String sql) {
        try {
            return con.createStatement().executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    public void commitQuery() throws Exception {
        try {
            con.commit();
        } catch (SQLException e) {
            throw new Exception("Lỗi commit query");
        }
    }
    public void rollbackQuery() throws Exception {
        try {
            con.rollback();
        } catch (SQLException e) {
            throw new Exception("Lỗi rollback query");
        }
    }
   
}
