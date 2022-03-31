package Personal.KPP.core.domain.member;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Primary;

import static Personal.KPP.core.domain.member.MemberGrade.USER;

@Getter @Setter
public class Member {
    private long sid;
    private String userName;
    private String password;
    private MemberGrade grade;

    public Member(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.grade = USER;
    }

    public Member(String userName, String password, MemberGrade grade) {
        this.userName = userName;
        this.password = password;
        this.grade = grade;
    }
}
