package dao;

import javabean.MyCount;
import javabean.Patient;
import javabean.Staff;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private Connection conn;
    private PreparedStatement pps;
    private String sql;

    /**
     * 添加后台账户
     *
     * @param myinsert
     * @return
     */
    public boolean addBackUser(String myinsert) {
        boolean flag = false;
        String lock = null;

        try {//数据库加锁，
            conn = Connectionutil.getintConnectionutil().getConnection();
            conn.setAutoCommit(false);
            lock = "lock table STAFF in exclusive mode";
            pps = conn.prepareStatement(lock);
            boolean s = pps.execute();

            QueryRunner qr = new QueryRunner();
//            String myinsert = "'" + role_id + "','"  + account + "','" + myname + "','" + pwd
//                    + "','" + school + "','" + "','" + staff_job + "','"
//                    + "','" + domain + "','"+ "','" + background + "','"
//                    + "','" + resume + "','"+"','" + mycost + "','"
//                    +"','" + sexy + "','";
            String sql = "insert into STAFF (ROLE_ID,STAFF_ACCOUNT,STAFF_NAME," +
                    "STAFF_PWD,STAFF_SCHOOL,STAFF_AWARDED,STAFF_DOMAIN,STAFF_BACKGROUND," +
                    "STAFF_RESUME,COST,STAFF_SEXY,STAFF_STAGE) values(" + myinsert + ")";
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

    /**
     * 后台账户
     *
     * @param account
     * @return
     */
    public Staff queryByAccount(String account) {
        Staff staff = null;
        try {
            conn = Connectionutil.getintConnectionutil().getConnection();
            QueryRunner qr = new QueryRunner();
            sql = "select * from Staff where STAFF_ACCOUNT = "
                    + "'" + account + "'";
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
     * 后台查询用户的总数
     *
     * @param name
     * @param stage
     * @return
     */
    public int count(String name, String stage) {
        MyCount myCount = null;
        try {
            conn = Connectionutil.getintConnectionutil().getConnection();
            conn.setAutoCommit(false);
            String lock = null;
            lock = "lock table PATIENT in share mode";
            pps = conn.prepareStatement(lock);
            boolean i = pps.execute();
            StringBuffer sql1 = new StringBuffer("select count(*)  mycount from PATIENT   WHERE 1=1   ");
            if (!"".equals(name)) {
                sql1.append(" and PATIENT_NAME like '%" + name + "%'");
            }
            if (!"".equals(stage)) {
                sql1.append(" and PATIENT_STAGE = '" + stage + "'");
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
     * 后台查询用户表的分页查询
     *
     * @param start
     * @param end
     * @param name
     * @param stage
     * @return
     */
    public List<Patient> queryAllUser(int start, int end, String name,
                                      String stage) {
        conn = Connectionutil.getintConnectionutil().getConnection();
        String lock = null;
        List<Patient> list = null;
        try {
            conn.setAutoCommit(false);
            lock = "lock table PATIENT in share mode";
            pps = conn.prepareStatement(lock);
            boolean i = pps.execute();
            StringBuffer sql1 = new StringBuffer(
                    "select PATIENT.* FROM PATIENT where 1=1  ");
            if (!"".equals(name)) {
                sql1.append(" and PATIENT_NAME like '%" + name + "%'");
            }
            if (!"".equals(stage)) {
                sql1.append(" and PATIENT_STAGE = '" + stage + "'");
            }
            sql1.append("  order by PATIENT_NAME desc ");

            String getsql = String.valueOf(sql1);
            sql = " SELECT*FROM (select ROWNUM RN,A.* FROM (" + getsql + ")A)B" +
                    " WHERE B.RN BETWEEN " + start + " AND " + end;
            System.out.println(sql + "");
            QueryRunner qr = new QueryRunner();
            list = qr.query(conn, sql, new BeanListHandler<Patient>(Patient.class));
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

    /**
     * 后台修改用户表的用户状态
     *
     * @param stageID
     * @param account
     * @return
     */
    public boolean changeStage(String stageID, String account) {
        boolean flag = false;
        String lock = null;
        try {//数据库加锁，
            conn = Connectionutil.getintConnectionutil().getConnection();
            conn.setAutoCommit(false);
            lock = "lock table PATIENT in exclusive mode";
            pps = conn.prepareStatement(lock);
            boolean s = pps.execute();
            QueryRunner qr = new QueryRunner();
            String sql = "UPDATE PATIENT SET PATIENT_STAGE ='" +
                    stageID + "' WHERE PATIENT_ACCOUNT ='" + account + "'";
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

    /**
     * 后台重置用户列表的密码
     *
     * @param account
     * @param resetPwd
     * @return
     */
    public boolean resetPwd(String account, String resetPwd) {
        boolean flag = false;
        String lock = null;
        try {//数据库加锁，
            conn = Connectionutil.getintConnectionutil().getConnection();
            conn.setAutoCommit(false);
            lock = "lock table PATIENT in exclusive mode";
            pps = conn.prepareStatement(lock);
            boolean s = pps.execute();
            QueryRunner qr = new QueryRunner();
            String sql = "UPDATE PATIENT SET PATIENT_PWD='" +
                    resetPwd + "' WHERE PATIENT_ACCOUNT ='" + account + "'";
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


    /**
     * 后台查询后台用户的总数
     *
     * @param name
     * @param stage
     * @return
     */
    public int count(String name, String stage, String jobtitle) {
        MyCount myCount = null;
        try {
            conn = Connectionutil.getintConnectionutil().getConnection();
            conn.setAutoCommit(false);
            String lock = null;
            lock = "lock table STAFF in share mode";
            pps = conn.prepareStatement(lock);
            boolean i = pps.execute();
            StringBuffer sql1 = new StringBuffer("select count(*)  mycount from STAFF   WHERE 1=1   ");
            if (!"".equals(name)) {
                sql1.append(" and STAFF_NAME like '%" + name + "%'");
            }
            if (!"".equals(stage)) {
                sql1.append(" and STAFF_STAGE = '" + stage + "'");
            }
            if (!"".equals(jobtitle)) {
                sql1.append(" and STAFF_AWARDED = '" + jobtitle + "'");
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
     * 后台查询后台用户表的分页查询
     *
     * @param start
     * @param end
     * @param name
     * @param stage
     * @return
     */
    public List<Staff> queryAllUser(int start, int end, String name,
                                    String stage, String jobtitle) {
        conn = Connectionutil.getintConnectionutil().getConnection();
        String lock = null;
        List<Staff> list = null;
        try {
            conn.setAutoCommit(false);
            lock = "lock table STAFF in share mode";
            pps = conn.prepareStatement(lock);
            boolean i = pps.execute();
            StringBuffer sql1 = new StringBuffer(
                    "select STAFF.* FROM STAFF where 1=1 ");
            if (!"".equals(name)) {
                sql1.append(" and STAFF_NAME like '%" + name + "%'");
            }
            if (!"".equals(stage)) {
                sql1.append(" and STAFF_STAGE = '" + stage + "'");
            }
            if (!"".equals(jobtitle)) {
                sql1.append(" and STAFF_AWARDED = '" + jobtitle + "'");
            }
            sql1.append("  order by STAFF_NAME desc ");

            String getsql = String.valueOf(sql1);
            sql = " SELECT*FROM (select ROWNUM RN,A.* FROM (" + getsql + ")A)B" +
                    " WHERE B.RN BETWEEN " + start + " AND " + end;
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

    /**
     * 后台修改后台用户表的用户状态
     *
     * @param stageID
     * @param account
     * @return
     */
    public boolean changeStaffStage(String stageID, String account) {
        boolean flag = false;
        String lock = null;
        try {//数据库加锁，
            conn = Connectionutil.getintConnectionutil().getConnection();
            conn.setAutoCommit(false);
            lock = "lock table STAFF in exclusive mode";
            pps = conn.prepareStatement(lock);
            boolean s = pps.execute();
            QueryRunner qr = new QueryRunner();
            String sql = "UPDATE STAFF SET STAFF_STAGE ='" +
                    stageID + "' WHERE STAFF_ACCOUNT ='" + account + "'";
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

    /**
     * 后台重置后台用户列表的密码
     *
     * @param account
     * @param resetPwd
     * @return
     */
    public boolean resetStaffPwd(String account, String resetPwd) {
        boolean flag = false;
        String lock = null;
        try {//数据库加锁，
            conn = Connectionutil.getintConnectionutil().getConnection();
            conn.setAutoCommit(false);
            lock = "lock table STAFF in exclusive mode";
            pps = conn.prepareStatement(lock);
            boolean s = pps.execute();
            QueryRunner qr = new QueryRunner();
            String sql = "UPDATE STAFF SET STAFF_PWD='" +
                    resetPwd + "' WHERE STAFF_ACCOUNT ='" + account + "'";
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


    /**
     * 咨询师端预约查询设置，查询总数
     *
     * @param data1
     * @param data2
     * @param stage
     * @return
     */
    public int apointmentCount(String data1, String data2, String stage, String account) {
        MyCount myCount = null;
        try {
            conn = Connectionutil.getintConnectionutil().getConnection();
            conn.setAutoCommit(false);
            String lock = null;
            lock = "lock table SCHEDULING in share mode";
            pps = conn.prepareStatement(lock);
            boolean i = pps.execute();
            lock = "lock table APPOINTMENT in share mode";
            pps = conn.prepareStatement(lock);
            boolean j = pps.execute();
            StringBuffer sql1 = new StringBuffer("select count(*) mycount from " +
                    "  SCHEDULING A,APPOINTMENT B,STAFF C " +
                    "where A.STAFF_ACCOUNT='" + account + "' " +
                    "  AND A.SCHEDULING_ID=B.SCHEDULING_ID " +
                    "  and A.STAFF_ACCOUNT=C.STAFF_ACCOUNT and 1=1");
            if (!"".equals(data1)) {
                sql1.append(" and A.DATADAY BETWEEN '" + data1 + "' ");
            }
            if (!"".equals(data2)) {
                sql1.append(" AND '" + data2 + "'");
            }
            if (!"".equals(stage)) {
                sql1.append("and B.APPOINTMENT_STAGE='" + stage + "'");
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


    /** 咨询师端预约查询设置，查询分页
     *
     * @param start
     * @param end
     * @param data1
     * @param data2
     * @param stage
     * @param account
     * @return
     */
    public List<com.sample.Appointment> queryAppointment(int start, int end, String data1,
                                                     String data2, String stage, String account) {
        conn = Connectionutil.getintConnectionutil().getConnection();
        String lock = null;
        List<com.sample.Appointment> list = null;
        try {
            conn.setAutoCommit(false);
            lock = "lock table APPOINTMENT in share mode";
            pps = conn.prepareStatement(lock);
            boolean i = pps.execute();
            lock = "lock table SCHEDULING in share mode";
            pps = conn.prepareStatement(lock);
            boolean j = pps.execute();
            StringBuffer sql1 = new StringBuffer(
                    "select A.STAFF_ACCOUNT,A.DATADAY,B.*,C.STAFF_DOMAIN from " +
                            "SCHEDULING A,APPOINTMENT B,STAFF C " +
                            "where A.STAFF_ACCOUNT='"+account+"' " +
                            "AND A.SCHEDULING_ID=B.SCHEDULING_ID " +
                            "and A.STAFF_ACCOUNT=C.STAFF_ACCOUNT and 1=1 ");
            if (!"".equals(data1)) {
                sql1.append(" and A.DATADAY BETWEEN '" + data1 + "' ");
            }
            if (!"".equals(data2)) {
                sql1.append(" AND '" + data2 + "'");
            }
            if (!"".equals(stage)) {
                sql1.append("and B.APPOINTMENT_STAGE='" + stage + "'");
            }
            String getsql = String.valueOf(sql1);
            sql = "select*from( " +
                    "select ROWNUM RN, D.* FROM( " +
                    getsql +
                    "order by A.DATADAY, HOUR)D)E" +
                    " where E.RN BETWEEN "+start+" AND "+end;
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

    /**咨询师的资金变化
     * @param account
     * @return
     */
    public boolean addPurse(String account,String updateNum){
        boolean flag = false;
        String lock = null;
        try {//数据库加锁，
            conn = Connectionutil.getintConnectionutil().getConnection();
            conn.setAutoCommit(false);
            lock = "lock table STAFF in exclusive mode";
            pps = conn.prepareStatement(lock);
            boolean s = pps.execute();
            QueryRunner qr = new QueryRunner();
            String sql = "update STAFF set PURSE='"+updateNum+"' " +
                    " where STAFF_ACCOUNT='"+account+"'";
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

    /**添加交易记录表
     *
     * @param myinsert
     * @return
     */
    public boolean addBuyRecord(String myinsert){
            boolean flag = false;
            String lock = null;
            try {//数据库加锁，
                conn = Connectionutil.getintConnectionutil().getConnection();
                conn.setAutoCommit(false);
                lock = "lock table BUYRECORD in exclusive mode";
                pps = conn.prepareStatement(lock);
                boolean s = pps.execute();
                QueryRunner qr = new QueryRunner();
                String sql = "INSERT INTO BUYRECORD (MONEYTIME,MATTER,KEY_PERSON,TYPE,MONEY,PATIENT_ACCOUNT) VALUES\n" +
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

    /**查询某咨询师，某天，某刻的预约表,还有回复信息和评价
     *
     * @param staffname
     * @param data
     * @param hour
     * @return
     */
    public com.sample.Appointment myinfo(String staffname,
                                         String data,String hour){
        com.sample.Appointment appointment = null;
        try {
            conn = Connectionutil.getintConnectionutil().getConnection();
            conn.setAutoCommit(false);
            String lock = "lock table APPOINTMENT in share mode";
            pps = conn.prepareStatement(lock);
            boolean i = pps.execute();
            QueryRunner qr = new QueryRunner();
            sql = "select A.REPLY_TIME,A.REPLY,A.APPRAISE,B.*\n" +
                    ",C.STAFF_ACCOUNT,C.STAFF_NAME,C.DATADAY,D.STAFF_DOMAIN," +
                    " D.STAFF_SCHOOL,D.STAFF_AWARDED,D.STAFF_BACKGROUND," +
                    "D.STAFF_RESUME ,D.COST from\n" +
                    "DIAGNOSTIC A,APPOINTMENT B,SCHEDULING C,STAFF D\n" +
                    "WHERE C.STAFF_NAME='"+staffname+"'\n" +
                    "AND DATADAY='"+data+"'\n" +
                    "AND HOUR='"+hour+"'\n" +
                    "AND A.PROBLEM_ID=B.PROBLEM_ID\n" +
                    "AND B.SCHEDULING_ID=C.SCHEDULING_ID " +
                    " AND C.STAFF_NAME=D.STAFF_NAME";
            System.out.println(sql);
            appointment = qr.query(conn, sql, new BeanHandler<>(com.sample.Appointment.class));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Connectionutil.getintConnectionutil().closscon(null, null, conn);
        }
        return appointment;
    }

    /**诊断回复
     *
     * @param reply
     * @param replytime
     * @param appraise
     * @param staffaccount
     * @param date
     * @param hour
     * @return
     */
    public boolean
    updateDIAGNOSTIC(String reply,String replytime,String appraise,
                     String staffaccount,String date,String hour){

        boolean flag = false;
        String lock = null;
        try {//数据库加锁，
            conn = Connectionutil.getintConnectionutil().getConnection();
            conn.setAutoCommit(false);
            lock = "lock table DIAGNOSTIC in exclusive mode";
            pps = conn.prepareStatement(lock);
            boolean s = pps.execute();
            QueryRunner qr = new QueryRunner();
            String str="UPDATE DIAGNOSTIC B SET  ";
            String str1=null;
            if (reply!=null){
                 if (str1==null){
                     str1="REPLY='"+reply+"'";
                 }else{
                     str1=str1+",REPLY='"+reply+"'";
                 }
            }
            if (replytime!=null){
                if (str1==null){
                    str1="REPLY_TIME='"+replytime+"'";
                }else{
                    str1=str1+",REPLY_TIME='"+replytime+"'";
                }
            }
            if (appraise!=null){
                if (str1==null){
                    str1="APPRAISE='"+appraise+"'";
                }else{
                    str1=str1+",APPRAISE='"+appraise+"'";
                }
            }
            String sql = str+str1 +
                    "  where PROBLEM_ID= (\n" +
                    "    seLECT PROBLEM_ID\n" +
                    "    from APPOINTMENT\n" +
                    "    WHERE HOUR = '"+hour+"'\n" +
                    "      and SCHEDULING_ID = (\n" +
                    "        select SCHEDULING_ID\n" +
                    "        from SCHEDULING\n" +
                    "        WHERE DATADAY = '"+date+"'\n" +
                    "          AND STAFF_ACCOUNT = '"+staffaccount+"'))" ;
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

    /**管理员端预约查询设置，查询总数
     *
     * @param data1
     * @param data2
     * @param staffname
     * @param useraccount
     * @return
     */
    public int myApointmentCount(String data1, String data2,
                               String staffname, String useraccount) {
        MyCount myCount = null;
        try {
            conn = Connectionutil.getintConnectionutil().getConnection();
            conn.setAutoCommit(false);
            String lock = null;
            lock = "lock table SCHEDULING in share mode";
            pps = conn.prepareStatement(lock);
            boolean i = pps.execute();
            lock = "lock table APPOINTMENT in share mode";
            pps = conn.prepareStatement(lock);
            boolean j = pps.execute();
            lock = "lock table STAFF in share mode";
            pps = conn.prepareStatement(lock);
            boolean s = pps.execute();
            StringBuffer sql1 = new StringBuffer("select count(*) mycount from SCHEDULING A,APPOINTMENT B,STAFF  C\n" +
                    "WHERE A.SCHEDULING_ID=B.SCHEDULING_ID\n" +
                    "AND A.STAFF_ACCOUNT=C.STAFF_ACCOUNT");
            if (!"".equals(data1)) {
                sql1.append(" and A.DATADAY BETWEEN '" + data1 + "' ");
            }
            if (!"".equals(data2)) {
                sql1.append(" AND '" + data2 + "'");
            }
            if (!"".equals(staffname)) {
                sql1.append(" and A.STAFF_NAME LIKE'%" + staffname + "%'");
            }
            if (!"".equals(useraccount)) {
                sql1.append(" and B.PATIENT_ACCOUNT LIKE'%" + useraccount + "%'");
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


    /** 管理员端预约查询设置，查询分页
     *
     * @param start
     * @param end
     * @param data1
     * @param data2
     * @param staffname
     * @param useraccount
     * @return
     */
    public List<com.sample.Appointment> myqueryAppointment(int start, int end, String data1,
                                                         String data2, String staffname, String useraccount) {
        conn = Connectionutil.getintConnectionutil().getConnection();
        String lock = null;
        List<com.sample.Appointment> list = null;
        try {
            conn.setAutoCommit(false);
            lock = "lock table SCHEDULING in share mode";
            pps = conn.prepareStatement(lock);
            boolean i = pps.execute();
            lock = "lock table APPOINTMENT in share mode";
            pps = conn.prepareStatement(lock);
            boolean j = pps.execute();
            lock = "lock table STAFF in share mode";
            pps = conn.prepareStatement(lock);
            boolean s = pps.execute();
            StringBuffer sql1 = new StringBuffer(
                    "select A.STAFF_NAME,A.DATADAY,B.*,C.STAFF_DOMAIN from " +
                            "SCHEDULING A,APPOINTMENT B,STAFF  C\n" +
                            "WHERE A.SCHEDULING_ID=B.SCHEDULING_ID\n" +
                            "AND A.STAFF_ACCOUNT=C.STAFF_ACCOUNT\n");
            if (!"".equals(data1)) {
                sql1.append(" and A.DATADAY BETWEEN '" + data1 + "' ");
            }
            if (!"".equals(data2)) {
                sql1.append(" AND '" + data2 + "'");
            }
            if (!"".equals(staffname)) {
                sql1.append(" and A.STAFF_NAME LIKE'%" + staffname + "%'");
            }
            if (!"".equals(useraccount)) {
                sql1.append(" and B.PATIENT_ACCOUNT LIKE'%" + useraccount + "%'");
            }
            String getsql = String.valueOf(sql1);
            sql = "SELECT *FROM (\n" +
                    "select ROWNUM RN, D.* FROM(\n" +
                     getsql +
                    "ORDER BY\n" +
                    "DATADAY ,HOUR)D)\n" +
                    " E where E.RN BETWEEN "+start+" AND "+end;
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

    /**咨询师端资金账户，查询总数,可以跟用户资金查询复用
     *
     * @param staffname
     * @param patient_account
     * @return
     */
    public int myQueryBuyRecordCount(String staffname,String patient_account) {
        MyCount myCount = null;
        try {
            conn = Connectionutil.getintConnectionutil().getConnection();
            conn.setAutoCommit(false);
            String lock = null;
            lock = "lock table BUYRECORD in share mode";
            pps = conn.prepareStatement(lock);
            boolean i = pps.execute();
            StringBuffer sql1 = new StringBuffer("select count(*) mycount" +
                    " from BUYRECORD A,PATIENT B where  1=1");
            if (staffname!=null) {
                sql1.append(" and KEY_PERSON= '" + staffname + "' ");
            }
            if (patient_account!=null) {
                sql1.append(" AND A.PATIENT_ACCOUNT='" + patient_account + "'");
            }
            sql1.append(" and B.PATIENT_ACCOUNT=A.PATIENT_ACCOUNT");



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


    /** 咨询师端资金账户，查询总数，可以跟用户资金查询复用
     *
     * @param start
     * @param end
     * @param staffname
     * @param patient_account
     * @return
     */
    public List<com.sample.Buyrecord> myQueryBuyRecord(int start, int end,
                                                           String staffname,String patient_account) {
        conn = Connectionutil.getintConnectionutil().getConnection();
        String lock = null;
        List<com.sample.Buyrecord> list = null;
        try {
            conn.setAutoCommit(false);
            lock = "lock table BUYRECORD in share mode";
            pps = conn.prepareStatement(lock);
            boolean i = pps.execute();
            StringBuffer sql1 = new StringBuffer(
                    "select A.*,B.PATIENT_NAME from BUYRECORD A" +
                            ",PATIENT B where 1=1");
            if (staffname!=null) {
                sql1.append(" and KEY_PERSON= '" + staffname + "' ");
            }
            if (patient_account!=null) {
                sql1.append(" AND A.PATIENT_ACCOUNT='" + patient_account + "'");
            }
            String getsql = String.valueOf(sql1);
            sql = "select* from\n" +
                    "(select ROWNUM RN,C.*FROM(\n" +
                      getsql +
                    "  and B.PATIENT_ACCOUNT=A.PATIENT_ACCOUNT   " +
                    "ORDER BY MONEYTIME)C)D\n" +
                    "where D.RN between "+start+" AND "+end;
            System.out.println(sql + "");
            QueryRunner qr = new QueryRunner();
            list = qr.query(conn, sql, new BeanListHandler<com.sample.Buyrecord>(com.sample.Buyrecord.class));
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

    /**查询题库的数量
     *
     * @param domain
     * @return
     */
    public int mySubjectCount(String domain) {
        MyCount myCount = null;
        try {
            conn = Connectionutil.getintConnectionutil().getConnection();
            conn.setAutoCommit(false);
            String lock = null;
            lock = "lock table SUBJECT in share mode";
            pps = conn.prepareStatement(lock);
            boolean i = pps.execute();
            StringBuffer sql1 = new StringBuffer("select count(*) mycount" +
                    "  from SUBJECT where 1=1");
            if (!"".equals(domain)) {
                sql1.append("  and SUBJECT_DOMAIN= '" + domain + "' ");
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


    /**分页查询题库
     *
     * @param start
     * @param end
     * @param domain
     * @return
     */
    public List<com.sample.Subject> mySubject(int start, int end,
                                              String domain){
        conn = Connectionutil.getintConnectionutil().getConnection();
        String lock = null;
        List<com.sample.Subject> list = null;
        try {
            conn.setAutoCommit(false);
            lock = "lock table SUBJECT in share mode";
            pps = conn.prepareStatement(lock);
            boolean i = pps.execute();
            StringBuffer sql1 = new StringBuffer(
                    "select * from SUBJECT where 1=1");
            if (!"".equals(domain)) {
                sql1.append("  and SUBJECT_DOMAIN= '" + domain + "' ");
            }
            String getsql = String.valueOf(sql1);
            sql = "select*from\n" +
                    "(select ROWNUM RN,A.*FROM(\n" +
                    getsql +
                    "order by SUBJECT_ID)A)B\n" +
                    "where B.RN between "+start+" AND "+end;
            System.out.println(sql + "");
            QueryRunner qr = new QueryRunner();
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

}



