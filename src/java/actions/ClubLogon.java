
package actions;

import business.Member;
import business.MemberDB;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author n.riley
 */
public class ClubLogon extends ActionSupport implements SessionAware {
    
    public ClubLogon() {  
    }
    
    @Override
    public String execute() throws Exception {
//        try {
            String msg = ""; //"Credentials were: " + this.userid + " " + this.pattempt;
            Map request = (Map) ActionContext.getContext().get("request");
            member = MemberDB.getMemberByID(this.userid);
            if(member != null) {
                member.setPassattempt(Long.parseLong(this.pattempt));
                if(member.isAuthenticated()) {
                    request.put("msg", "Member " + this.userid + " is authenticated");
                    session.put("member", member);
                    return SUCCESS;
                } else {
                    request.put("msg", "Member " + this.userid + " found but not authenticated");
                    return INPUT;
                }
            } else {
                request.put("msg", "Member " + this.userid + " not found");
                return INPUT;
            }
//        } catch(Exception e) {
//            return ERROR;
//        }
    }
    @Override
    public void validate() {
        try {
            long pat = Long.parseLong(this.pattempt);
            if(pat <= 0) {
                throw new NumberFormatException("Bad password: not > 0");
            }
        } catch(Exception e) {
            addFieldError("pattempt", "Password issue " + e.getMessage());
        }
    }
    private Member member;
    private String userid;
    private String pattempt;
    private Map session;
    public void setUserid(String uid){this.userid = uid;}
    public void setPattempt(String pat){this.pattempt = pat;}
    @Override
    public void setSession(Map session) {this.session = session;}
    public String getUserid(){return this.userid;}
    public String getPattempt(){return this.pattempt;}

    
}
