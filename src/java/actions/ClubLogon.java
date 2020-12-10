
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
        try {
            String msg = "";
            Map request = (Map) ActionContext.getContext().get("request");
//            Retrieve member from input. If member name is correct validate password
            member = MemberDB.getMemberByID(this.userid);
            if(member != null) {
//                password validation
                member.setPassattempt(Long.parseLong(this.pattempt));
                if(member.isAuthenticated()) {
//                    member is authenticated
                    request.put("msg", "Member " + this.userid + " is authenticated");
                    session.put("member", member);
                    return SUCCESS;
                } else {
//                    member was not authenticated
                    request.put("msg", "Member " + this.userid + " found but not authenticated");
                    return INPUT;
                }
            } else {
//                member is null or non existent
                request.put("msg", "Member " + this.userid + " not found");
                return INPUT;
            }
        } catch(NumberFormatException e) {
            return ERROR;
        }
    }
    @Override
    public void validate() {
        try {
            long pat = Long.parseLong(this.pattempt);
            if(pat <= 0) {
                throw new NumberFormatException("Bad password: not > 0");
            }
        } catch(NumberFormatException e) {
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
