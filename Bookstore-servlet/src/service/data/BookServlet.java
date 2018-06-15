package service.data;

import entity.Book;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "BookServlet",urlPatterns = "/books")
public class BookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Session session = null;
        Transaction tx = null;
        //response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");//解决中文乱码
        PrintWriter writer = response.getWriter();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            // 1、得到Query对象，并写入hql语句
            Query query = session.createQuery("from Book ");
            //3、使用Query对象的list方法得到数据集合
            List<Book> list = query.list();
            //3、遍历集合获取数据
            tx.commit();
            JSONArray books = JSONArray.fromObject(list);
            writer.print(books);
        } catch (Exception e) {
            if(tx!=null) tx.rollback();
        } finally {
            if(writer!=null) writer.close();
        }
    }
}
