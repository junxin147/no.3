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
import java.util.Dictionary;
import java.util.List;

public class FrontUser {
    private Connection conn;
    private PreparedStatement pps;
    private String sql;

    /**注册业务
     *
     * @param myinsert
     * @return
     */
    public boolean addpatient(String myinsert) {
        boolean flag = false;
        String lock = null;
        try {//数据库加锁，
            conn = Connectionutil.getintConnectionutil().getConnection();
            conn.setAutoCommit(false);
            lock = "lock table PATIENT in exclusive mode";
             pps = conn.prepareStatement(lock);
            boolean s = pps.execute();

            QueryRunner qr = new QueryRunner();
            String sql = "insert into PATIENT (PATIENT_ACCOUNT,PATIENT_NAME,PATIENT_PWD," +
                    "PATIENT_SEXY,PATIENT_AGE,PHONE,ROLE_ID,PATIENT_REGTIME,PATIENT_STAGE) values(" + myinsert + ")";
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

        }finally {
            try {
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Connectionutil.getintConnectionutil().closscon(null, pps, conn);
        }
        return flag;
    }

    /**登录业务
     *
     * @param account
     * @return
     */
    public Patient queryByUserAccount(String account){
        Patient patient=null;
        try {
            conn = Connectionutil.getintConnectionutil().getConnection();
            conn.setAutoCommit(false);
            String lock = null;
            lock = "lock table Patient in share mode";
            pps = conn.prepareStatement(lock);
            boolean i = pps.execute();
            QueryRunner qr = new QueryRunner();
            String sql = "select * from Patient where PATIENT_ACCOUNT = "
                    +"'"+account+"'";
            System.out.println(sql);
            patient = qr.query(conn, sql, new BeanHandler<>(Patient.class));
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

        return  patient;

    }

    /**预约列表通过领域查询员工集合
     *
     * @param domain
     * @return
     */
    public List<Staff> getDoctor(String domain){
        conn = Connectionutil.getintConnectionutil().getConnection();
        String lock = null;
        List<Staff> list = null;
        try {
            conn.setAutoCommit(false);
            lock = "lock table STAFF in share mode";
            pps = conn.prepareStatement(lock);
            boolean i = pps.execute();
            String sql = "select*from STAFF where STAFF_DOMAIN='"+domain+"' and STAFF_STAGE='1'";
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

    /**查询某咨询师的相对应信息
     *
     * @param domain
     * @param name
     * @return
     */
    public Staff queryIfo(String domain,String name){
        conn = Connectionutil.getintConnectionutil().getConnection();
        Staff staff = null;
        try {
            conn.setAutoCommit(false);
            String lock = "lock table STAFF in share mode";
            pps = conn.prepareStatement(lock);
            boolean i = pps.execute();
        conn = Connectionutil.getintConnectionutil().getConnection();
        QueryRunner qr = new QueryRunner();
        String sql = "select*from STAFF where STAFF_DOMAIN='"+domain+"' " +
                "and STAFF_NAME= '"+name+"'";
        System.out.println(sql);
        staff = qr.query(conn, sql, new BeanHandler<>(Staff.class));
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        Connectionutil.getintConnectionutil().closscon(null, null, conn);
    }
        return staff;
}


    /**
     * 对时刻表进行相对应的修改
     * @param problem
     * @param useraccount
     * @param stage
     * @param staffname
     * @param data
     * @param hour
     * @return
     */
    public boolean addappointment(String problem,String useraccount,String stage,String acttime,
                                  String staffname,String data,String hour) {
        boolean flag = false;
        String lock = null;
        try {//数据库加锁，
            conn = Connectionutil.getintConnectionutil().getConnection();
            conn.setAutoCommit(false);
            lock = "lock table APPOINTMENT in exclusive mode";
            pps = conn.prepareStatement(lock);
            boolean s = pps.execute();
            lock = "lock table SCHEDULING in share mode";
            pps = conn.prepareStatement(lock);
            boolean  hs = pps.execute();
            String str="UPDATE APPOINTMENT B SET APPOINTMENT_STAGE='"+stage+"'  ";
            if (problem!=null){
               str=str+(",PROBLEM='"+problem+"'  ");
            }
            if (useraccount!=null){
                str=str+(",PATIENT_ACCOUNT='"+useraccount+"'  ");
            }
            if (acttime!=null){
                str=str+(",ACTTIME='"+acttime+"'  ");
            }

            String sql = str +
                    "WHERE B.SCHEDULING_ID=(select A.SCHEDULING_ID " +
                    "   from SCHEDULING A where STAFF_NAME ='"+staffname+"' " +
                    "  AND DATADAY='"+data+"') " +
                    "  AND B.HOUR='"+hour+"'";
            System.out.println("sql=" + sql);
            QueryRunner qr = new QueryRunner();
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

        }finally {
            try {
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Connectionutil.getintConnectionutil().closscon(null, pps, conn);
        }
        return flag;
    }

    /**
     * 用户预约列表总数
     * @param acconut
     * @return
     */
    public int count(String acconut) {
        MyCount myCount = null;
        try {
            conn = Connectionutil.getintConnectionutil().getConnection();
            conn.setAutoCommit(false);
            String lock = null;
            lock = "lock table APPOINTMENT in share mode";
            pps = conn.prepareStatement(lock);
            boolean i = pps.execute();
            StringBuffer sql1 = new StringBuffer("select COUNT(*) mycount " +
                    "from APPOINTMENT A,SCHEDULING B ,STAFF C " +
                    "WHERE PATIENT_ACCOUNT='"+acconut+"' " +
                    "  AND A.SCHEDULING_ID=B.SCHEDULING_ID " +
                    "  AND B.STAFF_ACCOUNT=C.STAFF_ACCOUNT " +
                    " ORDER BY B.DATADAY,A.HOUR   ");
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

    /**
     * 用户预约列表分页查询
     * @param start
     * @param end
     * @param account
     * @return
     */
    public List<com.sample.Appointment> queryAllAppointment(int start, int end, String account) {
        conn = Connectionutil.getintConnectionutil().getConnection();
        String lock = null;
        List<com.sample.Appointment> list = null;
        try {
            conn.setAutoCommit(false);
            lock = "lock table PATIENT in share mode";
            pps = conn.prepareStatement(lock);
            boolean i = pps.execute();
            sql = "select*from( " +
                    "select ROWNUM RN, D.* FROM( " +
                    "select A.* ,B.STAFF_NAME,B.DATADAY,C.STAFF_DOMAIN " +
                    "from APPOINTMENT A,SCHEDULING B ,STAFF C " +
                    "WHERE PATIENT_ACCOUNT='"+account+"' " +
                    "AND A.SCHEDULING_ID=B.SCHEDULING_ID " +
                    "AND B.STAFF_ACCOUNT=C.STAFF_ACCOUNT " +
                    "ORDER BY B.DATADAY,A.HOUR)D)E where E.RN BETWEEN "+start+" AND "+end;
            System.out.println(sql + "");
            QueryRunner qr = new QueryRunner();
            list = qr.query(conn, sql, new BeanListHandler<com.sample.Appointment>(com.sample.Appointment.class));
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


    /**用户的资金变化
     * @param account
     * @return
     */
    public boolean addPurse(String account,String updateNum){
        boolean flag = false;
        String lock = null;
        try {//数据库加锁，
            conn = Connectionutil.getintConnectionutil().getConnection();
            conn.setAutoCommit(false);
            lock = "lock table PATIENT in exclusive mode";
            pps = conn.prepareStatement(lock);
            boolean s = pps.execute();
            QueryRunner qr = new QueryRunner();
            String sql = "update PATIENT set PURSE='"+updateNum+"' " +
                    " where PATIENT_ACCOUNT='"+account+"'";
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

    /**查询某天某时刻某用户的预约列表
     *
     * @param hour
     * @param date
     * @param staffname
     * @return
     */
    public com.sample.Appointment queryIfo(String hour, String date,
                                           String staffname){
        conn = Connectionutil.getintConnectionutil().getConnection();
        com.sample.Appointment appointment = null;
        try {
            conn.setAutoCommit(false);
            String lock = "lock table APPOINTMENT in share mode";
            pps = conn.prepareStatement(lock);
            boolean i = pps.execute();
             lock = "lock table SCHEDULING in share mode";
            pps = conn.prepareStatement(lock);
            boolean s = pps.execute();
            conn = Connectionutil.getintConnectionutil().getConnection();
            QueryRunner qr = new QueryRunner();
            String sql = "SELECT * from APPOINTMENT WHERE " +
                    "  HOUR='"+hour+"' and SCHEDULING_ID=( " +
                    "select SCHEDULING_ID from SCHEDULING  " +
                    "WHERE DATADAY='"+date+"' AND STAFF_NAME='"+staffname+"')";
            System.out.println(sql);
            appointment = qr.query(conn, sql, new BeanHandler<>(com.sample.Appointment.class));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Connectionutil.getintConnectionutil().closscon(null, null, conn);
        }
        return appointment;
    }
    /**问题评测报告表
     * @param myinsert
     * @return
     */
    public boolean addproblem(String myinsert){
        boolean flag = false;
        String lock = null;
        try {//数据库加锁，
            conn = Connectionutil.getintConnectionutil().getConnection();
            conn.setAutoCommit(false);
            lock = "lock table PATIENT in exclusive mode";
            pps = conn.prepareStatement(lock);
            boolean s = pps.execute();
            QueryRunner qr = new QueryRunner();
            String sql = "INSERT INTO DIAGNOSTIC (PROBLEM_ID,REPLY,REPLY_TIME,APPRAISE) VALUES\n" +
                    "("+myinsert+")";
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

    /**查询评价详情
     *
     * @param staffname
     * @return
     */
    public List<com.sample.Diagnostic> appraiseinfo(String staffname){
        List<com.sample.Diagnostic> list = null;
        try {
            conn = Connectionutil.getintConnectionutil().getConnection();
            conn.setAutoCommit(false);
            String lock = "lock table APPOINTMENT in share mode";
            pps = conn.prepareStatement(lock);
            boolean i = pps.execute();
            lock = "lock table SCHEDULING in share mode";
            pps = conn.prepareStatement(lock);
            boolean j = pps.execute();
            lock = "lock table PATIENT in share mode";
            pps = conn.prepareStatement(lock);
            boolean k = pps.execute();
            QueryRunner qr = new QueryRunner();
            sql = "select A.APPRAISE,C.PATIENT_NAME ,B.ACTTIME from DIAGNOSTIC A,\n" +
                    "(select *from APPOINTMENT where SCHEDULING_ID in (\n" +
                    "select SCHEDULING_ID from SCHEDULING\n" +
                    "where STAFF_NAME ='"+staffname+"')\n" +
                    "and APPOINTMENT_STAGE='已评价')B,PATIENT C\n" +
                    "WHERE A.PROBLEM_ID=B.PROBLEM_ID\n" +
                    "AND B.PATIENT_ACCOUNT=C.PATIENT_ACCOUNT";
            System.out.println(sql);
            list = qr.query(conn, sql, new BeanListHandler<com.sample.Diagnostic>(com.sample.Diagnostic.class));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Connectionutil.getintConnectionutil().closscon(null, null, conn);
        }
        return list;
    }

}

