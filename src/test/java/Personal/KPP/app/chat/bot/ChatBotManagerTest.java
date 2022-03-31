package Personal.KPP.app.chat.bot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChatBotManagerTest {

    ChatBotManager chatBotManager = new ChatBotManager();

    @Test
    void getRandomChatMessageTest(){
        chatBotManager.initBotData();
        System.out.println(chatBotManager.getRandomBotMessage());
        System.out.println(chatBotManager.getRandomBotMessage());
        System.out.println(chatBotManager.getRandomBotMessage());
        System.out.println(chatBotManager.getRandomBotMessage());
        System.out.println(chatBotManager.getRandomBotMessage());
        System.out.println(chatBotManager.getRandomBotMessage());
        System.out.println(chatBotManager.getRandomBotMessage());
        System.out.println(chatBotManager.getRandomBotMessage());
    }

}