package Personal.KPP.app.chat.websocket;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class ChatWebSocketHandlerTest {

    @Test
    void dateToTimeonlyTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String inputStr = "1647092025204";
        ChatWebSocketHandler chatWebSocketHandler = new ChatWebSocketHandler();
        Method method = chatWebSocketHandler.getClass().getDeclaredMethod("dateToTimeonly", String.class);
        method.setAccessible(true);

        String result = (String) method.invoke(chatWebSocketHandler, inputStr);

        System.out.println("result = " + result);

    }

}