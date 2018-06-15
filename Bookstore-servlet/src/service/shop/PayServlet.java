package service.shop;

import entity.Book;
import entity.Order;
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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "PayServlet",urlPatterns = "/pay")
public class PayServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");//Solve the problem of chinese random code
        PrintWriter writer = response.getWriter();
        HttpSession userSession = request.getSession(false);
        if(userSession==null){
            writer.print("false");
            writer.close();
            return;
        }
        Shoppingcart cart = (Shoppingcart) userSession.getAttribute("shoppingcart");
        Session session = null;
        Transaction tx = null;
        int user_id = Integer.valueOf(request.getParameter("user_id"));
        int book_id = Integer.valueOf(request.getParameter("book_id"));
        int amount = Integer.valueOf(request.getParameter("amount"));
        String receiver = request.getParameter("receiver");
        String address = request.getParameter("address");
        Order order = new Order();
        order.setBookId(book_id);
        order.setUserId(user_id);
        order.setAddress(address);
        order.setAmount(amount);
        order.setReceiver(receiver);
        order.setTime(new Timestamp(new Date().getTime()));
        try{
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            //Find that book
            tx = session.beginTransaction();
            // Get Query object,begin transaction
            Query query = session.createQuery("from Book where id=?");
            //Fill the placeholder
            query.setParameter(0, book_id);
            Book book = null;
            try {
                book = (Book) query.getSingleResult();
            }catch (NoResultException e){book = null;}
            if(book == null){
                writer.write("failed");
                tx.commit();
            }else if(amount>book.getAmount()){
                writer.write("Notenough");
                tx.commit();
            }else{
                book.setSaleAmount(book.getSaleAmount()+amount);
                book.setAmount(book.getAmount()-amount);
                session.update(book);
                order.setBookname(book.getBookname());
                order.setImagePath(book.getImagePath());
                order.setBookprice(book.getPrice());
                //If can be bought,save order
                session.save(order);
                tx.commit();
                cart.removeItem(book_id);
                writer.write("success");
            }
        } catch (Exception e) {
            e.printStackTrace();
            writer.write("failed");
            if(tx!=null) tx.rollback();
        } finally {
            if(writer!=null) writer.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

//    private boolean checkAmount(int book_id){
////        Session session = null;
////        Transaction tx = null;
////        Book book
////        try {
////            session = HibernateUtil.getSessionFactory().getCurrentSession();
////            tx = session.beginTransaction();
////            // Get Query object,begin transaction
////            Query query = session.createQuery("from Book where id=?");
////            //Fill the placeholder
////            query.setParameter(0, book_id);
////            users = query.getSingleResult();
////            tx.commit();
////        }catch (Exception e) {
////            e.printStackTrace();
////            if(tx!=null) tx.rollback();
////        } finally {
////            if(session!=null) session.close();
////        }
////        return users.size()!=0;
////    }
}
