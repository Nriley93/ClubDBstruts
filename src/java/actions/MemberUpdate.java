
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
public class MemberUpdate extends ActionSupport implements SessionAware {
    
    public MemberUpdate() {
    }
    
    @Override
    public String execute() throws Exception {
//        Retrieve the current member and prepare for update.
        Member oldmem = (Member) session.get("member");
        oldmem.setFirstname(member.getFirstname());
        oldmem.setLastname(member.getLastname());
        oldmem.setMiddlename(member.getMiddlename());
        oldmem.setPassword(member.getPassword());
        oldmem.setPassattempt(member.getPassword());
//        if error retain old member else update member 
        String msg = MemberDB.updtMember(oldmem);
        if(msg.startsWith("Error")) {
            member = MemberDB.getMemberByID(oldmem.getMemid());
            session.put("member", member);
        }
        Map request = (Map) ActionContext.getContext().get("request");
        request.put("msg",msg);
        return SUCCESS;
    }
    private Member member;
    private Map session;
    public Member getMember() {return member;}
    public void setMember(Member member) {this.member = member;}
    @Override
    public void setSession(Map session) {this.session = session;}
}
