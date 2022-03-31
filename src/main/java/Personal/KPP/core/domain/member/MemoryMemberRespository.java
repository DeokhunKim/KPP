package Personal.KPP.core.domain.member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Repository
public class MemoryMemberRespository implements MemberRepository{

    private static Map<String, Member> store = new ConcurrentHashMap<>();
    private static long sequence = 0L;


    @Override
    public Optional<Member> findByUserName(String userName) {
        return Optional.ofNullable(store.get(userName));
    }

    @Override
    public Member save(Member member) {
        if (store.containsKey(member.getUserName())) {
            log.info("It is a user who already exists.");
            return null;
        }

        member.setSid(++sequence);
        store.put(member.getUserName(), member);
        log.info("Store new member [{}][{}]", member.getUserName(), member);
        return member;
    }

    public List<Member> getAllMemberList(){
        return new ArrayList<>(store.values());
    }

    public void clear(){
        store.clear();
    }

}
