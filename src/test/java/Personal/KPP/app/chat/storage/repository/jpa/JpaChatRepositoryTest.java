package Personal.KPP.app.chat.storage.repository.jpa;

import Personal.KPP.app.chat.storage.entity.normal.Chat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@SpringBootTest
class JpaChatRepositoryTest {

    @Autowired
    JpaChatRepository repository;

    @Test
    void checkPageQuery(){

        List<Chat> findChatLogList = repository.GetRoomChatPageByRoomId("1234", PageRequest.of(5, 10))
                .toList();

        Chat chat = findChatLogList.get(0);

    }

}