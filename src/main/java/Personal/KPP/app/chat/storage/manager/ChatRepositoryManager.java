package Personal.KPP.app.chat.storage.manager;


import Personal.KPP.app.chat.domain.ChatDto;
import Personal.KPP.app.chat.domain.ChatLogDto;
import Personal.KPP.app.chat.domain.RoomInfoDto;
import Personal.KPP.core.domain.member.MemberGrade;

import java.util.List;


public interface ChatRepositoryManager {

    public static final int DEFAULT_GETCHATNUM = 20;

    public ChatLogDto getRecentRoomChatLogByRoomId(String roomId, int numChat);

    public ChatLogDto getRecentRoomChatLogByRoomId(String roomId);

    public void saveChat(ChatDto chat, String roomId);

    public String createNewRoom(String roomName, List<String> members);

    public String createNewRoomDirect(String roomName, List<String> members, String roomId);

    public List<RoomInfoDto> getRoomListByUserName(String userName);

    public RoomInfoDto getRoomInfoByRoomId(String roomId);

    public boolean saveMember(String name, MemberGrade grade);

    public boolean existsByUserName(String name);


}
