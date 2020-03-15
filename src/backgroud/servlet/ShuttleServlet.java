package backgroud.servlet;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.net.Myallow;
import dao.FactoryDao;
import javabean.Message;
import javabean.MyMenu;
import javabean.menuChange;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

@WebServlet(name = "ShuttleServlet", urlPatterns = "/ShuttleServlet")

public class ShuttleServlet extends BaseServlet {

    public void myload(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        if (Myallow.getMyallow().allowed(request,response)){
            Message m = new Message();
            String rightjson = request.getParameter("rightjson");
            String leftjson = request.getParameter("leftjson");
            String myoption = request.getParameter("myoption");
            List<menuChange> rightarray = new Gson().fromJson(rightjson, new TypeToken<List<menuChange>>() {
            }.getType());
            List<menuChange> leftarray = new Gson().fromJson(leftjson, new TypeToken<List<menuChange>>() {
            }.getType());
            boolean flag = false;
            boolean flag1 = false;
            for (int i = 0; i < leftarray.size(); i++) {
                String menuname = leftarray.get(i).getmName().trim();
                flag = FactoryDao.getMenuDao().upadataMenu(menuname, "不可用", myoption);
                if (!flag){
                    break;
                }
            }
            for (int i = 0; i < rightarray.size(); i++) {
                String menuname = rightarray.get(i).getmName().trim();
                flag1 = FactoryDao.getMenuDao().upadataMenu(menuname, "可用", myoption);
                if (!flag1){
                    break;
                }
            }
            if (flag||flag1){
                m.setMsg("success");
            }else {
                m.setMsg("false");

            }
            String sendStr = new Gson().toJson(m);
            response.setContentType("text/html; charset =utf-8");
            response.getWriter().write(sendStr);
            response.getWriter().flush();
        }else {
            Message m = new Message();
            m.setMsg("returnlogin");
            System.out.println(m.getMsg() + "G.MEG");
            String sendStr = new Gson().toJson(m);
            response.setContentType("text/html; charset =utf-8");
            response.getWriter().write(sendStr);
            response.getWriter().flush();
            request.getSession().removeAttribute("staff");
        }


    }

    public void everyworkMenu(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        if (Myallow.getMyallow().allowed(request,response)){
            String workname=request.getParameter("staffwork");
            System.out.println(workname);
            String  myid="";
            if (workname.equals("咨询师")){
                myid="1";
            }else{
                myid="0";
            }
            HashMap<String, Vector<MyMenu>> menuAvailableMap = FactoryDao.getMenuDao().queryByMenu(myid, "可用");
            HashMap<String, Vector<MyMenu>> diableMap = FactoryDao.getMenuDao().queryByMenu(myid, "不可用");
            request.setAttribute("list1",menuAvailableMap);
            request.setAttribute("list2",diableMap);
            request.setAttribute("workname",workname);
            request.getRequestDispatcher("background/jsp/index19.jsp").forward(request,response);

        }else {
            Myallow.getMyallow().staffremoveSession(request,response);
        }


    }
}
