package dao;

import javabean.MyCount;
import javabean.Staff;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ReportDao {
    private Connection conn;
    private PreparedStatement pps;
    private String sql;

    /**
     * 查询报告的数量
     *
     */
    public int mySubjectCount(String account,String date1,
                              String date2,String score1,
                              String score2) {
        MyCount myCount = null;
        try {
            conn = Connectionutil.getintConnectionutil().getConnection();
            conn.setAutoCommit(false);
            String lock = null;
            lock = "lock table REPORT_TABLE in share mode";
            pps = conn.prepareStatement(lock);
            boolean i = pps.execute();
            StringBuffer sql1 = new StringBuffer("select COUNT(*)  mycount " +
                    " from REPORT_TABLE where 1= 1");
            if (account!=null){
                sql1.append(" and PATIENT_ACCOUNT='"+account+"' ");
            }
            if (!"".equals(date1)) {
                sql1.append(" and REPORT_TIME BETWEEN '" + date1 + "' ");
            }
            if (!"".equals(date2)){
                sql1.append(" AND '" + date2 + "'");
            }
            if (!"".equals(score1)){
                    sql1.append(" and SCORE BETWEEN '" + score1 + "' ");
            }
            if (!"".equals(score2)){
                sql1.append(" and  '" + score2 + "' ");
            }
            System.out.println(sql1);
            sql = String.valueOf(sql1);
            QueryRunner qr = new QueryRunner();
            myCount = qr.query(conn, sql, new BeanHandler<>(MyCount.class));
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Connectionutil.getintConnectionutil().closscon(null, pps, conn);
        }
        return myCount.getMycount();
    }

    /**分页查询，带条件也有
     *
     * @param start
     * @param end
     * @param account
     * @param date1
     * @param date2
     * @param score1
     * @param score2
     * @return
     */
    public List<com.sample.ReportTable> mySubject(int start, int end,String account,String date1,
                                              String date2,String score1,
                                              String score2) {
    conn = Connectionutil.getintConnectionutil().getConnection();
    String lock = null;
    List<com.sample.ReportTable> list = null;
    try {
        conn.setAutoCommit(false);
        lock = "lock table REPORT_TABLE in share mode";
        pps = conn.prepareStatement(lock);
        boolean i = pps.execute();
        StringBuffer sql1 = new StringBuffer(
                "select * from REPORT_TABLE where 1= 1");
        if (account!=null){
            sql1.append(" and PATIENT_ACCOUNT='"+account+"' ");
        }
        if (!"".equals(date1)) {
            sql1.append(" and REPORT_TIME BETWEEN '" + date1 + "' ");
        }
        if (!"".equals(date2)){
            sql1.append(" AND '" + date2 + "'");
        }
        if (!"".equals(score1)){
            sql1.append(" and SCORE BETWEEN '" + score1 + "' ");
        }
        if (!"".equals(score2)){
            sql1.append(" and   '" + score2 + "' ");
        }
        String getsql = String.valueOf(sql1);
        sql = "SELECT*FROM " +
                "(select ROWNUM RN, A.* FROM  " +
                "("+getsql+
                " order by REPORT_TIME desc )A)B " +
                "    WHERE B.RN BETWEEN "+start+" AND "+end;
        System.out.println(sql + "");
        QueryRunner qr = new QueryRunner();
        list = qr.query(conn, sql, new BeanListHandler<com.sample.ReportTable>(com.sample.ReportTable.class));
    } catch (SQLException e) {
        try {
            conn.rollback();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        // TODO Auto-generated catch block
        e.printStackTrace();
    } finally {
        try {
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Connectionutil.getintConnectionutil().closscon(null, pps, conn);
    }

    return list;
}

    /**查询某个评测报告
     *
     * @param report_id
     * @return
     */
    public com.sample.ReportTable queryById(String report_id) {
        com.sample.ReportTable reportTable = null;
        try {
            conn = Connectionutil.getintConnectionutil().getConnection();
            QueryRunner qr = new QueryRunner();
            sql = "select * from REPORT_TABLE where " +
                    "  REPORT_ID='"+report_id+"'";
            System.out.println(sql);
            reportTable = qr.query(conn, sql, new BeanHandler<>(com.sample.ReportTable.class));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Connectionutil.getintConnectionutil().closscon(null, null, conn);
        }
        return reportTable;
    }
}

