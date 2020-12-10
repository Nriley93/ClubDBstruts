
package actions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author n.riley
 */
public class ShowPurchases extends ActionSupport implements SessionAware {
    
    public ShowPurchases() {}
    
    @Override
    public String execute() throws Exception {
        Map request = (Map) ActionContext.getContext().get("request");
        String dt = this.month+"-"+this.day+"-"+this.year;
        Date pd;
        try{
            pd = new SimpleDateFormat("MM-dd-yyy").parse(dt);
        } catch(Exception e) {
            pd = null;
        }
        request.put("pd",pd);
        return SUCCESS;
    }
    
    private Map session;
    private String month;
    private String day;
    private String year;

    @Override
    public void setSession(Map session) {this.session = session;}
    
    public void setMonth(String month){this.month = month;}
    public void setDay(String day){this.day = day;}
    public void setYear(String year){this.year = year;}
    
    @Override
    public void validate() {
        if(!this.month.isEmpty()){
            try{
                int mo = Integer.parseInt(month);
                if(mo<1||mo>12){throw new Exception("Month out of bounds");}                             
            } catch(Exception e) {
                addFieldError("month","Date error: " + e.getMessage());
            }
        } 
        if(!this.day.isEmpty()){
            try{
                int dy = Integer.parseInt(day);          
                if(dy<1||dy>31){throw new Exception("Day out of bounds");}                
            } catch(Exception e) {
                addFieldError("day","Date error: " + e.getMessage());
            }
        }
        if(!this.year.isEmpty()) {
            try{
                int yr = Integer.parseInt(year);                          
                if(yr<0||yr>2050){throw new Exception("year out of bounds");}
            } catch(Exception e) {
                addFieldError("year","Date error: " + e.getMessage());
            }
        }
    }    
}
