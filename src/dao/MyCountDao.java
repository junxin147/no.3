package dao;

import javabean.MyCount;
import javabean.Patient;
import javabean.Staff;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MyCountDao {
    private Connection conn;
    private PreparedStatement pps;
    private String sql;

    /**后台全表查询用户
     *
     * @return
     */
    public List<Patient> queryAllUser(String star,String end) {
        conn = Connectionutil.getintConnectionutil().getConnection();
        String lock = null;
        List<Patient> list = null;
        try {
            conn.setAutoCommit(false);
            lock = "lock table PATIENT in share mode";
            pps = conn.prepareStatement(lock);
            boolean i = pps.execute();

            sql = "select * from PATIENT\n" +
                    " where PATIENT_REGTIME between '"+star+"' and '"+end+"' ";
            System.out.println(sql + "");
            QueryRunner qr = new QueryRunner();
            list = qr.query(conn, sql, new BeanListHandler<Patient>(Patient.class));
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
        return list;
    }


    /**全表查询咨询师
     *
     * @return
     */
    public List<Staff> mydoctor( ) {
        conn = Connectionutil.getintConnectionutil().getConnection();
        String lock = null;
        List<Staff> list = null;
        try {
            conn.setAutoCommit(false);
            lock = "lock table STAFF in share mode";
            pps = conn.prepareStatement(lock);
            boolean i = pps.execute();

            sql ="select *from STAFF where ROLE_ID='1'";
            System.out.println(sql + "");
            QueryRunner qr = new QueryRunner();
            list = qr.query(conn, sql, new BeanListHandler<Staff>(Staff.class));
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


    public int channelCount(String data1, String data2 ,String staffname) {
        conn = Connectionutil.getintConnectionutil().getConnection();
        String lock = null;
        MyCount myCount = null;
        try {
            conn.setAutoCommit(false);
            lock = "lock table SCHEDULING in share mode";
            pps = conn.prepareStatement(lock);
            boolean i = pps.execute();
            lock = "lock table APPOINTMENT in share mode";
            pps = conn.prepareStatement(lock);
            boolean j = pps.execute();
            StringBuffer sql1 = new StringBuffer(
                    "select count(B.STAFF_NAME) mycount  from APPOINTMENT A,SCHEDULING B\n" +
                            "WHERE A.SCHEDULING_ID=B.SCHEDULING_ID\n" +
                            "AND (A.APPOINTMENT_STAGE!='未预约'\n" +
                            "    and A.APPOINTMENT_STAGE!='已终止') ");
            if (!"".equals(data1)) {
                sql1.append(" and B.DATADAY between '"+data1+"'");
            }
            if (!"".equals(data2)) {
                sql1.append(" AND '" + data2 + "'");
            }

            String getsql = String.valueOf(sql1);
            sql = getsql+" and STAFF_NAME='"+staffname+"'";
            System.out.println(sql + "");
            QueryRunner qr = new QueryRunner();
            myCount = qr.query(conn, sql, new BeanHandler<>(MyCount.class));
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

        return myCount.getMycount();
    }

}
