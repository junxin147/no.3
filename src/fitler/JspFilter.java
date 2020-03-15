package fitler;

import javabean.Patient;
import javabean.Staff;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "JspFilter", urlPatterns = "/*")
public class JspFilter implements Filter {
    @Override
    public void init(FilterConfig config) throws ServletException {
        System.out.println("JSPFILTER");
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String servletPath = request.getServletPath();
        //浏览器地址栏 /PsychologicalConsultationPlatform/
        String uri = request.getRequestURI();
        System.out.println("URI=" + uri);
        if (uri.endsWith(".css") || uri.endsWith(".js") || uri.endsWith(".png") || uri.endsWith(".jpg")
                || uri.endsWith(".jpeg") || uri.endsWith("bj.jsp") || uri.contains("/layui/")) {
            //放行任何静态资源
            chain.doFilter(request, response);
        } else if (uri.endsWith("Servlet") && !uri.endsWith("UserServlet") ||
                !uri.endsWith("FrontUserServlet")) {
            chain.doFilter(request, response);
            return;
        } else if (uri.contains("UserServlet")) {
            if (uri.contains("FrontUserServlet")) {
                String account = request.getParameter("username");
                System.out.println(account + "123124");
                String pass = request.getParameter("password");
                if (account != null && pass != null) {
                    System.out.println("允许第一次进去");
                    chain.doFilter(request, response);
                    return;
                } else {
                    Patient patient = (Patient) request.getSession().getAttribute("patient");
                    if (patient != null) {
                        System.out.println("pass loginServeltQWE");
                        chain.doFilter(request, response);
                        return;
                    } else {
                        //跟Session的信息不符，要转到首页
                        System.out.println("no pass loginServelt123QEWQ ");
                        //重定向
                        request.setAttribute("waring", "不好意思请先登录");
                        request.getRequestDispatcher("front/jsp/index2.jsp").forward(request, response);
                    }
                }
            } else {
                String account = request.getParameter("username");
                System.out.println(account);
                String pass = request.getParameter("password");
                if (account != null && pass != null) {
                    System.out.println("允许第一次进去");
                    chain.doFilter(request, response);
                } else {
                    Staff staff = (Staff) request.getSession().getAttribute("staff");
                    if (staff != null) {
                        System.out.println("pass loginServelt ");
                        chain.doFilter(request, response);
                        return;
                    } else {
                        //跟Session的信息不符，要转到首页
                        System.out.println("no pass loginServelt123 ");
                        //重定向
                        request.setAttribute("waring", "不好意思请先登录");
                        request.getRequestDispatcher("background/jsp/index1.jsp").forward(request, response);
                    }
                }
            }


        }
        if (uri.contains("FrontUserServlet")) {
            String account = request.getParameter("username");
            System.out.println(account);
            String pass = request.getParameter("password");
            if (account != null && pass != null) {
                System.out.println("允许第一次进去");
                chain.doFilter(request, response);
            } else {
                Patient patient = (Patient) request.getSession().getAttribute("patient");
                if (patient != null) {
                    System.out.println("pass loginServeltQWE");
                    chain.doFilter(request, response);
                } else {
                    //跟Session的信息不符，要转到首页
                    System.out.println("no pass loginServelt123QEWQ ");
                    //重定向
                    request.setAttribute("waring", "不好意思请先登录");
                    request.getRequestDispatcher("front/jsp/index2.jsp").forward(request, response);
                }
            }
        }
        if (uri.endsWith(".jsp")) {
            System.out.println("ttaxt  jsp");
            //index1.js  好比 登录页面
            //  background/js/index1.js
            int i = uri.lastIndexOf("/background/jsp/index1.jsp");
            int j = uri.lastIndexOf("/front/jsp/index2.jsp");
            //   /biddingSystem 只有第一次
            if (uri.equals(request.getContextPath() + "/")) {
                System.out.println("系统路径");
                chain.doFilter(request, response);
            } else {
                //  background/js/index1.js
                if (uri.contains("/background/jsp/")) {
                    if (i > 0) {
                        request.getRequestDispatcher("index1.jsp").forward(request, response);
                        System.out.println("background");
                        chain.doFilter(request, response);
                    } else {
                        //其他所有路径
                        //TestServelt 相当于 登录的验证的servlet
                        Staff staff = (Staff) request.getSession().getAttribute("staff");
                        if (staff != null) {
                            System.out.println("pass loginServelt ");
                            chain.doFilter(request, response);
                        } else {
                            //跟Session的信息不符，要转到首页
                            System.out.println("no pass loginServelt123 ");
                            //重定向
                            request.setAttribute("waring", "不好意思请先登录");
                            request.getRequestDispatcher("index1.jsp").forward(request, response);

                        }
                    }
                } else if (uri.contains("/front/jsp/")) {
                    if (uri.endsWith("index1.jsp")) {
                        System.out.println("放行1");
                        chain.doFilter(request, response);

                    }
                    if (j > 0) {
                        request.getRequestDispatcher("index2.jsp").forward(request, response);
                        System.out.println("frontd213");
                        chain.doFilter(request, response);
                    } else {
                        Patient patient = (Patient) request.getSession().getAttribute("patient");
                        if (patient != null) {
                            System.out.println("pass loginServelt4 ");
                            chain.doFilter(request, response);
                        } else {
                            System.out.println("pass loginServeltdddde ");
                            //跟Session的信息不符，要转到首页
                            System.out.println("no pass loginServelt1234 ");
                            //重定向
                            request.setAttribute("waring", "不好意思请先登录");
                            request.getRequestDispatcher("index2.jsp").forward(request, response);

                        }

                    }
                }


            }
        }
    }
}
