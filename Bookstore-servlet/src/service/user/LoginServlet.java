package service.user;

import entity.User;
import model.Shoppingcart;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "LoginServlet",urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Session session = null;
        Transaction tx = null;
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");//Solve the problem of chinese random code
        PrintWriter writer = response.getWriter();
        try{
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            // Get Query object,begin transaction
            Query query = session.createQuery("from User where username=? and password=?");
            //Fill the placeholder
            query.setParameter(0, username);
            query.setParameter(1, password);
            User user = null;
            try {
                user = (User) query.getSingleResult();
                tx.commit();
            }catch (NoResultException e){
                user = null;
                tx.rollback();
            }
            //Handle the query result
            System.out.println(username+password);
            if(user!=null){
                HttpSession userSession = request.getSession();
                userSession.setMaxInactiveInterval(-1);
                userSession.setAttribute("user",user);
                userSession.setAttribute("shoppingcart",new Shoppingcart());
                System.out.println("登录成功！！！！！");
                writer.print(user.getRole());
            }
            else{
                writer.print("failed");
            }
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
