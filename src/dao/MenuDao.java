package dao;

import javabean.MyMenu;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class MenuDao {
    private Connection conn;
    private PreparedStatement pps;
    private String sql;

    /**后台端不同角色获得菜单列表
     *
     * @param roleID
     * @return
     */
    public HashMap<String, Vector<MyMenu>> queryByMenu(String roleID,String menuStage) {
        Vector<MyMenu> myVector = null;
        HashMap<String, Vector<MyMenu>> hashMap = new HashMap<>();
        MyMenu myMenu = null;
        try {
            conn = Connectionutil.getintConnectionutil().getConnection();
            conn.setAutoCommit(false);
            String lock = null;
            lock = "lock table MENU in share mode";
            pps = conn.prepareStatement(lock);
            boolean i = pps.execute();
            sql = "select  B.MENU_NAME firstmenu,A.MENU_NAME secondmenu,A.MENU_PATH menupath " +
                    "from MENU A ,MENU B WHERE A.MENU_ID in " +
                    "(select MENU_ID from ROLE_MENU where ROLE_NAME=( " +
                    "select ROLE_NAME from ROLE where ROLE_ID='"+roleID+"')" +
                    "  and  MENU_STAGE='"+menuStage+"') " +
                    "and B.MENU_ID=A.FARTHER_ID ORDER BY firstmenu DESC";
            pps = conn.prepareStatement(sql);
            ResultSet rs = pps.executeQuery();
            myVector = new Vector<>();
            System.out.println(sql);
            while (rs.next()) {
                myMenu = new MyMenu();
                myMenu.setFirstmenu(rs.getString("firstmenu"));
                myMenu.setSecondmenu(rs.getString("secondmenu"));
                myMenu.setMenupath(rs.getString("menupath"));
                for (int j = 0; j < myVector.size(); j++) {
                    if (!myMenu.getFirstmenu().equals(myVector.get(j).getFirstmenu())) {
                        myVector = new Vector<>();
                    }
                }
                myVector.add(myMenu);
                hashMap.put(myMenu.getFirstmenu(), myVector);
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
        return hashMap;
    }

    /**
     * 用户端获得的菜单列表，或者给菜单权限分配进行左右列表的查询
     * @param roleID
     * @return
     */
    public List<com.sample.Menu> queryMenu(String roleID,String menuStage){
        List<com.sample.Menu> list=null;
        try {
            conn = Connectionutil.getintConnectionutil().getConnection();
            conn.setAutoCommit(false);
            String lock = null;
            lock = "lock table MENU in share mode";
            pps = conn.prepareStatement(lock);
            boolean i = pps.execute();
            sql = "select*from MENU where MENU_ID in( " +
                    " select MENU_ID from ROLE_MENU where ROLE_NAME=( " +
                    "  select ROLE_NAME from ROLE where ROLE_ID='"+roleID+"'  AND MENU_STAGE='"+menuStage+"') " +
                    "  )";
            System.out.println(sql);
            QueryRunner qr = new QueryRunner();
            list = qr.query(conn, sql, new BeanListHandler<com.sample.Menu>(com.sample.Menu.class));
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

    /**修改菜单
     *
     * @param menuname
     * @param stage
     * @param staffwork
     * @return
     */
   public boolean upadataMenu(String menuname,String stage,String staffwork){
       boolean flag = false;
       String lock = null;
       try {//数据库加锁，
           conn = Connectionutil.getintConnectionutil().getConnection();
           conn.setAutoCommit(false);
           lock = "lock table ROLE_MENU in exclusive mode";
           pps = conn.prepareStatement(lock);
           boolean s = pps.execute();
           QueryRunner qr = new QueryRunner();
           String sql = "UPDATE ROLE_MENU SET MENU_STAGE =\n" +
                   "'"+stage+"' WHERE ROLE_NAME = '"+staffwork+"'\n" +
                   "and MENU_ID =(select MENU_ID from MENU where MENU_NAME='"+menuname+"')" ;
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
