package GUI.applications;

import connectDB.ConnectDB;

public class TestConnection {
    public static void main(String[] args) {
        System.out.println(ConnectDB.getInstance().getConnection());
    }
}
