
package utility;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

/**
 *
 * @author n.riley
 */
public class Struts2Dispatcher extends StrutsPrepareAndExecuteFilter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        super.init(filterConfig);
        try {
            SessionFactory sessionFactory =
                    HibernateUtil.getSessionFactory();
            if(sessionFactory == null) {
                throw new HibernateException("Session Factroy is null");
            } else {
                System.out.println("Session Factory is ok.");
            }
        } catch(HibernateException e) {
            throw new ServletException(e.getMessage());
        }
    }
}
