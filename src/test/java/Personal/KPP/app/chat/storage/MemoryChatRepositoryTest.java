package Personal.KPP.app.chat.storage;

import Personal.KPP.app.chat.domain.ChatDto;
import Personal.KPP.app.chat.domain.ChatLogDto;
import Personal.KPP.app.chat.storage.manager.MemoryChatRepositoryManager;
import org.junit.jupiter.api.Test;

class MemoryChatRepositoryTest {

    private MemoryChatRepositoryManager repository = new MemoryChatRepositoryManager();

    @Test
    void repositoryBaseTest1(){
        ChatDto chat1 = new ChatDto("user1", "Hi", "time1");
        ChatDto chat2 = new ChatDto("user2", "Hello", "time2");
        ChatDto chat3 = new ChatDto("user3", "Chat", "time3");
        ChatDto chat4 = new ChatDto("user4", "Test", "time4");
        ChatDto chat5 = new ChatDto("user5", "Good", "time5");

        repository.saveChat(chat1, "1");
        repository.saveChat(chat2, "1");
        repository.saveChat(chat3, "2");
        repository.saveChat(chat4, "2");
        repository.saveChat(chat5, "1");

        ChatLogDto room1 = repository.getRecentRoomChatLogByRoomId("1");
        ChatLogDto room2 = repository.getRecentRoomChatLogByRoomId("2");

        room1.getChatLogList().stream()
                .forEach(m -> System.out.println("room1/"+m.getWriter() + "/" + m.getTime() +"/"+ m.getMessage()));

        room2.getChatLogList().stream()
                .forEach(m -> System.out.println("room2/"+m.getWriter() + "/" + m.getTime() +"/"+ m.getMessage()));

    }

}