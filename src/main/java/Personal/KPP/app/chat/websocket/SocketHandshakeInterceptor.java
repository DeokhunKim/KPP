package Personal.KPP.app.chat.websocket;

import Personal.KPP.app.chat.session.ChatSessionConst;
import Personal.KPP.core.web.session.SessionConst;
import Personal.KPP.core.web.session.SessionMemberForm;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

//https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#websocket-server-handshake
public class SocketHandshakeInterceptor implements HandshakeInterceptor {

    /**
     * Socket Handshake 전에 해당 Session의 user 정보을 attribute 로 저장
     * Socket Handler에서는 httpSession에 접근 할 방법이 없기 때문임
     */
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            HttpSession session = servletRequest.getServletRequest().getSession();
            SessionMemberForm attribute = (SessionMemberForm) session.getAttribute(SessionConst.LOGIN);
            if(attribute == null){
                return true;
            }

            attributes.put(ChatSessionConst.HTTP_SESSION, attribute);
        }
       return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {

    }
}
