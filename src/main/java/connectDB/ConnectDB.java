package connectDB;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectDB {
	public static Connection con = null;
	private static ConnectDB instance = null;
	
	public ConnectDB(){
		try{

			String url = "jdbc:sqlserver://localhost:1433;DatabaseName=QLBVT;encrypt=true;trustServerCertificate=true";
			String user = "sa";
			String password = "3.141592653";
			con = DriverManager.getConnection(url, user, password);
			if (con != null) {
				System.out.println("Kết nối thành công");
			}
		}catch (SQLException e){
			System.err.println(e.getMessage());
		}
	}
	public void disconnect() {
		if (con!= null) {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public static ConnectDB getInstance() {
		if(instance == null){
			instance = new ConnectDB();
		}
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
