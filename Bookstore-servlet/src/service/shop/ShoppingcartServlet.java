package service.shop;

import entity.Book;
import entity.User;
import model.ShoppingItem;
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

@WebServlet(name = "ShoppingcartServlet",urlPatterns = "/shoppingcart")
public class ShoppingcartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");//Solve the problem of chinese random code
        Session session = null;
        Transaction tx = null;
        PrintWriter writer = response.getWriter();
        HttpSession userSession = request.getSession(false);
        if(userSession==null){
            writer.print("false");
            writer.close();
            return;
        }
        String method = request.getParameter("method");
        Shoppingcart cart = (Shoppingcart) userSession.getAttribute("shoppingcart");
        if(method.equals("add")){
            int id = Integer.valueOf(request.getParameter("id"));
            String address = request.getParameter("address");
            try{
                session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
                // Get Query object,begin transaction
                Query query = session.createQuery("from Book where id=?");
                //Fill the placeholder
                query.setParameter(0, id);
                Book book = null;
                try {
                    book = (Book) query.getSingleResult();
                    tx.commit();
                }catch (NoResultException e){}
                //Handle the query result
                if(book!=null){
                    ShoppingItem item = new ShoppingItem(book,1,address);
                    cart.addItem(item);
                    writer.print("success");
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
        }else if(method.equals("modify")){
            int id = Integer.valueOf(request.getParameter("id"));
            int amount = Integer.valueOf(request.getParameter("amount"));
            String address = request.getParameter("address");
            if(cart.modifyItem(id,amount,address)){
                writer.print("success");
            }else {
                writer.print("failed");
            }
            writer.close();
        }else if(method.equals("remove")){
            int id = Integer.valueOf(request.getParameter("id"));
            if(cart.removeItem(id)){
                writer.print("success");
            }else {
                writer.print("failed");
            }
            writer.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");//Solve the problem of chinese random code
        HttpSession userSession = request.getSession(false);
        PrintWriter writer = response.getWriter();
        if(userSession!=null){
            Shoppingcart cart = (Shoppingcart) userSession.getAttribute("shoppingcart");
            JSONArray cart_list = JSONArray.fromObject(cart.list());
            writer.print(cart_list);
        }else{
            writer.print("false");
        }
        writer.close();
    }
}
