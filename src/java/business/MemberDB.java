
package business;

import javax.persistence.NoResultException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utility.HibernateUtil;

/**
 *
 * @author n.riley
 */
public class MemberDB {
    public static Member getMemberByID(String memid) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = null;
        Member m = null;
        try{
            String qs = "FROM Member m WHERE m.memid = :memid ";
            session = sf.openSession();
            Query q = session.createQuery(qs);
            q.setParameter("memid", memid);
            m = (Member) q.uniqueResult();
            return m;
        } catch(NoResultException e) {
            m = null;
        } finally {
            session.close();
        }
        return m;
    }
    public static String updtMember(Member m) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = null;
        String msg="";
        try{
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.merge(m);
            session.getTransaction().commit();
            session.flush();
            session.refresh(m);
            msg = "Member " + m.getMemid() + " updated.";
        } catch(Exception e) {
            msg = "Error on member update " + e.getMessage();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return msg;
    }
}
