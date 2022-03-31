package Personal.KPP.core.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemoryMemberRespositoryTest {

    MemoryMemberRespository repository = new MemoryMemberRespository();

    @AfterEach
    void afterEach(){
        repository.clear();
    }

    @Test
    void saveTest(){
        Member member1 = new Member("member1", "1234");
        Member member2 = new Member("member2", "1234");
        repository.save(member1);
        repository.save(member2);

        assertThat(repository.getAllMemberList().size()).isEqualTo(2);

    }


}