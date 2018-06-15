package service.user;

import entity.User;
import model.Shoppingcart;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "RegisterServlet",urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Session session = null;
        Transaction tx = null;
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");//Solve the problem of chinese random code
        PrintWriter writer = response.getWriter();
        try{
            if(checkUser(username)){
                writer.print("duplicate");
                return;
            }
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            User newUser = createUser(username,password);
            session.save(newUser);
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

    private User createUser(String username,String password){
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setRole("user");
        newUser.setAge(0);
        newUser.setCity("未填写");
        newUser.setDefaultAddress("未填写");
        newUser.setIntroduction("未填写");
        newUser.setSchool("未填写");
        return newUser;
    }

    private boolean checkUser(String username){
        Session session = null;
        Transaction tx = null;
        List<User> users = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            // Get Query object,begin transaction
            Query query = session.createQuery("from User where username=?");
            //Fill the placeholder
            query.setParameter(0, username);
            users = query.list();
            tx.commit();
        }catch (Exception e) {
            e.printStackTrace();
            if(tx!=null) tx.rollback();
        }
        return users.size()!=0;
    }
}
