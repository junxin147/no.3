package dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class SubjectDao {
    private Connection conn;
    private PreparedStatement pps;
    private String sql;

    /**删除题目
     *
     * @param subjectId
     * @return
     */
    public boolean deleteSubject(String subjectId){
        boolean flag = false;
        String lock = null;
        try {//数据库加锁，
            conn = Connectionutil.getintConnectionutil().getConnection();
            conn.setAutoCommit(false);
            lock = "lock table SUBJECT in exclusive mode";
            pps = conn.prepareStatement(lock);
            boolean s = pps.execute();
            QueryRunner qr = new QueryRunner();
            String sql = "DELETE FROM SUBJECT WHERE SUBJECT_ID = '"+
                    subjectId+"'";
            System.out.println("sql=" + sql);
            int i = 0;
            i = qr.update(conn, sql);
            if (i > 0) {
                flag = true;
            }
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
        return flag;
    }

    /***获取某题的题目
     *
     * @param subjectId
     * @return
     */
    public com.sample.Subject subjectInfo(String subjectId){
        com.sample.Subject subject = null;
        try {
            conn = Connectionutil.getintConnectionutil().getConnection();
            conn.setAutoCommit(false);
            String lock = "lock table APPOINTMENT in share mode";
            pps = conn.prepareStatement(lock);
            boolean i = pps.execute();
            QueryRunner qr = new QueryRunner();
            sql = "select* FROM SUBJECT WHERE SUBJECT_ID = '"+subjectId+"'";
            System.out.println(sql);
            subject = qr.query(conn, sql, new BeanHandler<>(com.sample.Subject.class));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Connectionutil.getintConnectionutil().closscon(null, null, conn);
        }
        return subject;
    }


    /**修改题目
     * @param subjectId
     * @param subjecttext
     * @param subjectdomain
     * @param optionA
     * @param optionB
     * @param optionC
     * @param optionD
     * @return
     */
    public boolean updataInfo(String subjectId,String subjecttext,
                              String subjectdomain,String optionA,
                              String optionB,String optionC,
                              String optionD){
        boolean flag = false;
        String lock = null;
        try {//数据库加锁，
            conn = Connectionutil.getintConnectionutil().getConnection();
            conn.setAutoCommit(false);
            lock = "lock table SUBJECT in exclusive mode";
            pps = conn.prepareStatement(lock);
            boolean s = pps.execute();
            QueryRunner qr = new QueryRunner();
            String sql = "UPDATE SUBJECT SET SUBJECT_TEXT = '"+subjecttext+"'," +
                    "SUBJECT_DOMAIN = '"+subjectdomain+"',OPTION_A = '"+optionA+"'," +
                    "OPTION_B = '"+optionB+"'," +
                    "OPTION_C = '"+optionC+"'," +
                    "OPTION_D = '"+optionD+"' " +
                    "WHERE SUBJECT_ID = '"+subjectId+"'" ;
            System.out.println("sql=" + sql);
            int i = 0;
            i = qr.update(conn, sql);
            if (i > 0) {
                flag = true;
            }
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
        return flag;
    }

    /**新增题目
     *
     * @param myinsert
     * @return
     */
    public boolean myInsert(String myinsert){
        boolean flag = false;
        String lock = null;
        try {//数据库加锁，
            conn = Connectionutil.getintConnectionutil().getConnection();
            conn.setAutoCommit(false);
            lock = "lock table SUBJECT in exclusive mode";
            pps = conn.prepareStatement(lock);
            boolean s = pps.execute();
            QueryRunner qr = new QueryRunner();
            String sql = "INSERT INTO SUBJECT (SUBJECT_TEXT, SUBJECT_DOMAIN,OPTION_A,OPTION_B,OPTION_C,OPTION_D) " +
                    "VALUES ("+myinsert+") " ;
            System.out.println("sql=" + sql);
            int i = 0;
            i = qr.update(conn, sql);
            if (i > 0) {
                flag = true;
            }
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
        return flag;
    }

    /**随机给用户5道题目
     *
     * @param donmain
     * @return
     */
    public List<com.sample.Subject> randomSubject(String donmain) {
        List<com.sample.Subject> list=null;
        try {
            conn = Connectionutil.getintConnectionutil().getConnection();
            conn.setAutoCommit(false);
            String lock = null;
            lock = "lock table SUBJECT in share mode";
            pps = conn.prepareStatement(lock);
            boolean i = pps.execute();
            QueryRunner qr = new QueryRunner();
            String sql = "select * from\n" +
                    "(select * from SUBJECT order by dbms_random.value)\n" +
                    "where SUBJECT_DOMAIN='"+donmain+"' and rownum<=5";
            System.out.println(sql);
            list = qr.query(conn, sql, new BeanListHandler<com.sample.Subject>(com.sample.Subject.class));
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

    /**添加评测报告
     *
     * @param myinsert
     * @return
     */
    public boolean reportInsert(String myinsert){
        boolean flag = false;
        String lock = null;
        try {//数据库加锁，
            conn = Connectionutil.getintConnectionutil().getConnection();
            conn.setAutoCommit(false);
            lock = "lock table REPORT_TABLE in exclusive mode";
            pps = conn.prepareStatement(lock);
            boolean s = pps.execute();
            QueryRunner qr = new QueryRunner();
            String sql = "INSERT INTO REPORT_TABLE (RESULT,RESULT_TEXT,PATIENT_ACCOUNT,\n" +
                    "   REPORT_TIME,SUBJECT_DOMAIN,SCORE) VALUES\n" +
                    "("+myinsert+")" ;
            System.out.println("sql=" + sql);
            int i = 0;
            i = qr.update(conn, sql);
            if (i > 0) {
                flag = true;
            }
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
        return flag;
    }

}
