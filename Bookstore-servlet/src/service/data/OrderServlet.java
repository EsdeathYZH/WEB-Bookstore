package service.data;

import entity.Order;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "OrderServlet",urlPatterns = "/record")
public class OrderServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
    //Query the history orders of current user
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Session session = null;
        Transaction tx = null;
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");//Solve the problem of chinese random code
        HttpSession userSession = request.getSession(false);
        PrintWriter writer = response.getWriter();
        if(userSession==null){
            writer.print("false");
            writer.close();
            return;
        }
        try{
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            // Get Query object,begin transaction
            Query query = session.createQuery("from Order where userId=?");
            //Fill the placeholder
            User user = (User)userSession.getAttribute("user");
            query.setParameter(0, user.getId());
            List<Order> list;
            try {
                list = query.list();
                tx.commit();
            }catch (NoResultException e){list = new ArrayList<>();}
            //Handle the query result
            JSONArray orders = JSONArray.fromObject(list);
            writer.print(orders);
        } catch (Exception e) {
            e.printStackTrace();
            if(tx!=null) tx.rollback();
        } finally {
            if(writer!=null) writer.close();
        }
    }
}
