package service.manage;

import entity.Book;
import entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

@WebServlet(name = "UserManageServlet",urlPatterns = "/usermanage")
public class UserManageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");//Solve the problem of chinese random code
        PrintWriter writer = response.getWriter();
        HttpSession userSession = request.getSession(false);
        if(userSession==null){
            return;
        }
        User user = (User) userSession.getAttribute("user");
        if(user == null || !user.getRole().equals("manager")){
            return;
        }
        Session session = null;
        Transaction tx = null;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        int age = Integer.valueOf(request.getParameter("age"));
        String school = request.getParameter("school");
        User newuser = new User();
        try{
            newuser.setSchool(school);
            newuser.setUsername(username);
            newuser.setPassword(password);
            newuser.setAge(age);
            newuser.setRole(role);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            session.save(newuser);
            tx.commit();
            writer.write("success");
        } catch (Exception e) {
            e.printStackTrace();
            if(tx!=null) tx.rollback();
        } finally {
            if(writer!=null) writer.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
