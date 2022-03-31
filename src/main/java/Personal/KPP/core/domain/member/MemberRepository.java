package Personal.KPP.core.domain.member;


import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository {
    public Optional<Member> findByUserName (String userName);

    public Member save(Member member);



}
