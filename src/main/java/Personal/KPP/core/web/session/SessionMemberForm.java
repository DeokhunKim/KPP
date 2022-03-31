package Personal.KPP.core.web.session;


import Personal.KPP.core.domain.member.Member;
import Personal.KPP.core.domain.member.MemberGrade;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class SessionMemberForm {
    private String userName;
    private MemberGrade grade;

    public SessionMemberForm(Member member) {
        this.userName = member.getUserName();
        this.grade = member.getGrade();
    }

    public SessionMemberForm(String userName, MemberGrade grade) {
        this.userName = userName;
        this.grade = grade;
    }
}
