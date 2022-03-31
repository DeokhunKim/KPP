package Personal.KPP.core;

import Personal.KPP.core.domain.member.Member;
import Personal.KPP.core.domain.member.MemberGrade;
import Personal.KPP.core.domain.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class TestDataInit {

    private final MemberRepository memberRepository;

    @Autowired
    public TestDataInit(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @PostConstruct
    public void storeInitData(){
        memberRepository.save(new Member("admin", "admin", MemberGrade.ADMIN));
        memberRepository.save(new Member("ChatBot_1", "admin", MemberGrade.CHATBOT));
        memberRepository.save(new Member("ChatBot_2", "admin", MemberGrade.CHATBOT));
        memberRepository.save(new Member("ChatBot_3", "admin", MemberGrade.CHATBOT));
        memberRepository.save(new Member("tester1", "admin", MemberGrade.ADMIN));
        memberRepository.save(new Member("tester2", "admin", MemberGrade.ADMIN));
        memberRepository.save(new Member("tester3", "admin", MemberGrade.ADMIN));
        memberRepository.save(new Member("tester4", "admin", MemberGrade.ADMIN));
        memberRepository.save(new Member("tester5", "admin", MemberGrade.ADMIN));
    }


}
