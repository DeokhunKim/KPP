package Personal.KPP.app.chat.storage.repository.mongo;

import Personal.KPP.app.chat.storage.entity.mongo.Member;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository("mongoMemberRepository")
public interface MemberRepository extends MongoRepository<Member, String> {
    Member findByUserName(String userName);

    boolean existsByUserName(String userName);
}
