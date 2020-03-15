package dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CalendarDao {
    private Connection conn;
    private PreparedStatement pps;
    private String sql;

    /**
     * 排班表添加
     *
     * @param myinsert
     * @return
     */
    public boolean addScheduling(String myinsert) {
        boolean flag = false;
        String lock = null;
        try {//数据库加锁，
            conn = Connectionutil.getintConnectionutil().getConnection();
            conn.setAutoCommit(false);
            lock = "lock table SCHEDULING in exclusive mode";
            flag = isFlag(myinsert, flag, lock, "insert into SCHEDULING (STAFF_ACCOUNT,STAFF_NAME,DATADAY) values(");
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
     * 查询排班表根据账号和要排班的时间
     *
     * @param account
     * @param mydate
     * @return
     */
    public com.sample.Scheduling myQueryDateID(String account, String mydate) {
        com.sample.Scheduling scheduling = null;
        try {
            conn = Connectionutil.getintConnectionutil().getConnection();
            conn.setAutoCommit(false);
            String lock = null;
            lock = "lock table Scheduling in share mode";
            pps = conn.prepareStatement(lock);
            boolean i = pps.execute();
            QueryRunner qr = new QueryRunner();
            String sql = "select * from Scheduling where STAFF_ACCOUNT = "
                    + "'" + account + "' AND DATADAY='" + mydate + "'";
            scheduling = qr.query(conn, sql, new BeanHandler<>(com.sample.Scheduling.class));
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

        return scheduling;

    }

    /**
     * 排班表加完后预约表添加
     *
     * @param myinsert
     * @return
     */
    public boolean addAppointment(String myinsert) {
        boolean flag = false;
        String lock = null;
        try {//数据库加锁，
            conn = Connectionutil.getintConnectionutil().getConnection();
            conn.setAutoCommit(false);
            lock = "lock table Appointment in exclusive mode";
            flag = isFlag(myinsert, flag, lock,
                    "insert into Appointment (SCHEDULING_ID,HOUR,APPOINTMENT_STAGE) values(");
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
     * 查询某个账号的排班时间表
     *
     * @param account
     * @return
     */
    public List<com.sample.Scheduling> queryMyAccountDate(String account) {
        conn = Connectionutil.getintConnectionutil().getConnection();
        String lock = null;
        List<com.sample.Scheduling> list = null;
        try {
            conn.setAutoCommit(false);
            lock = "lock table SCHEDULING in share mode";
            pps = conn.prepareStatement(lock);
            boolean i = pps.execute();
            sql = "select*from SCHEDULING where STAFF_ACCOUNT='" +
                    account + "'";
            System.out.println(sql + "");
            QueryRunner qr = new QueryRunner();
            list = qr.query(conn, sql, new BeanListHandler<com.sample.Scheduling>(com.sample.Scheduling.class));
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
     * 获取某个日期的时刻
     *
     * @param account
     * @param mydate
     * @return
     */
    public List<com.sample.Appointment> queryDateHour(String account, String mydate) {
        conn = Connectionutil.getintConnectionutil().getConnection();
        String lock = null;
        List<com.sample.Appointment> list = null;
        try {
            conn.setAutoCommit(false);
            lock = "lock table SCHEDULING in share mode";
            pps = conn.prepareStatement(lock);
            lock = "lock table Appointment in share mode";
            pps = conn.prepareStatement(lock);
            boolean i = pps.execute();
            sql = "select A.STAFF_ACCOUNT,A.DATADAY,B.* from SCHEDULING A,APPOINTMENT B " +
                    "where STAFF_ACCOUNT='" + account + "' AND DATADAY='" + mydate + "' " +
                    "AND A.SCHEDULING_ID=B.SCHEDULING_ID" +
                    "  order by hour";
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

    /**
     * 删除未预约的时刻表
     *
     * @param account
     * @param mydate
     * @return
     */
    public boolean mydelete(String account, String mydate) {
        boolean flag = false;
        String lock = null;
        try {//数据库加锁，
            conn = Connectionutil.getintConnectionutil().getConnection();
            conn.setAutoCommit(false);
            lock = "lock table Appointment in exclusive mode";
            pps = conn.prepareStatement(lock);
            boolean s = pps.execute();
            QueryRunner qr = new QueryRunner();
            sql = "DELETE from APPOINTMENT where SCHEDULING_ID " +
                    "in (select SCHEDULING_ID from SCHEDULING where STAFF_ACCOUNT='" + account + "' and " +
                    " DATADAY ='" + mydate + "'）";
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
     * 删除排班表的某一天
     *
     * @param account
     * @param mydate
     * @return
     */
    public boolean mydeletescheduling(String account, String mydate) {
        boolean flag = false;
        String lock = null;
        try {//数据库加锁，
            conn = Connectionutil.getintConnectionutil().getConnection();
            conn.setAutoCommit(false);
            lock = "lock table SCHEDULING in exclusive mode";
            pps = conn.prepareStatement(lock);
            boolean s = pps.execute();
            QueryRunner qr = new QueryRunner();
            sql = "DELETE  from SCHEDULING where STAFF_ACCOUNT='" + account + "' and " +
                    " DATADAY ='" + mydate + "'";
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

    /**对于按确认键的先对未预约进行删除
     *
     * @param account
     * @param mydate
     * @return
     */
    public boolean myloadhourdelete(String account, String mydate) {
        boolean flag = false;
        String lock = null;
        try {//数据库加锁，
            conn = Connectionutil.getintConnectionutil().getConnection();
            conn.setAutoCommit(false);
            lock = "lock table Appointment in exclusive mode";
            pps = conn.prepareStatement(lock);
            boolean s = pps.execute();
            QueryRunner qr = new QueryRunner();
            sql = "delete from APPOINTMENT where SCHEDULING_ID " +
                    "  in (select SCHEDULING_ID " +
                    "  from SCHEDULING " +
                    " where STAFF_ACCOUNT = '"+account+"' " +
                    "  and DATADAY = '"+mydate+"' " +
                    "  ) and APPOINTMENT_STAGE = '未预约' ";
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
     * 添加数据的封装
     *
     * @param myinsert
     * @param flag
     * @param lock
     * @param s2
     * @return
     * @throws SQLException
     */
    private boolean isFlag(String myinsert, boolean flag, String lock, String s2) throws SQLException {
        pps = conn.prepareStatement(lock);
        boolean s = pps.execute();
        QueryRunner qr = new QueryRunner();
        sql = s2 + myinsert + ")";
        System.out.println("sql=" + sql);
        int i = 0;

        i = qr.update(conn, sql);
        if (i > 0) {
            flag = true;
        }
        return flag;
    }
}
