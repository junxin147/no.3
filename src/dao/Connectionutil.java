package dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Connectionutil {
    private static Connectionutil util;
    private Connection con;
    private ComboPooledDataSource dataSource;
    private Connectionutil() {
        dataSource = new ComboPooledDataSource("oracle");
    }

    /**
     * 懒汉式单例模式
     */
    public static synchronized Connectionutil getintConnectionutil() {
        if (util == null) {
            util = new Connectionutil();
        }
        return util;
    }

	public ComboPooledDataSource getDataSource() {
		return dataSource;
	}

	/**
     * 创建连接方式
     *
     * @return
     */
    public Connection getConnection() {
        try {
            if (con == null) {
                con = dataSource.getConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public void closscon(Statement stm, PreparedStatement pps, Connection con) {
        try {
            if (stm != null) {
                stm.close();
                stm = null;
            }
            if (pps != null) {
                pps.close();
                pps = null;
            }
            if (con != null) {
                con.close();
                con = null;
                this.con = null;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


}
