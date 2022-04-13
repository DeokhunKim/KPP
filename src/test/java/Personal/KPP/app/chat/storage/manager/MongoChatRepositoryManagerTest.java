package Personal.KPP.app.chat.storage.manager;

import Personal.KPP.core.domain.member.MemberGrade;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class MongoChatRepositoryManagerTest {

    @Autowired
    private ChatRepositoryManager repositoryManager;

    @AfterEach
    void Init(){

    }

    @Test
    void ConnectTest() {
        repositoryManager.saveMember("UnitTest", MemberGrade.ADMIN);
        //repositoryManager.get

    }

}