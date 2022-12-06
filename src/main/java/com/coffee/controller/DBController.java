package com.coffee.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// DB연결 확인하기 위한 컨트롤러
public class DBController {
    public static void main(String[] args) {
        Connection conn = null;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC 드라이버 로드하는데 문제 발생" + e.getMessage());
            e.printStackTrace();
        }

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306", "coffee", "coffee4j!");
            System.out.println("연결 완료!");
        } catch (SQLException e) {
            System.out.println("연결 오류 " + e.getMessage());
            e.printStackTrace();
        }

        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
