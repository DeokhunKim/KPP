package Personal.KPP.app.chat.storage;

import Personal.KPP.app.chat.storage.repository.jpa.JpaChatRepository;
import Personal.KPP.app.chat.domain.ChatLogDto;
import Personal.KPP.app.chat.storage.entity.normal.Chat;
import Personal.KPP.app.chat.storage.entity.normal.Member;
import Personal.KPP.app.chat.storage.entity.normal.MemberRoomInfo;
import Personal.KPP.app.chat.storage.entity.normal.RoomInfo;
import Personal.KPP.app.chat.storage.manager.JpaChatRepositoryManager;
import Personal.KPP.core.domain.member.MemberGrade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;
import java.util.UUID;


@SpringBootTest
@Transactional
@Rollback(value = false)
class JpaChatRepositoryManagerTest {

    @Autowired
    JpaChatRepository jpaChatRepository;

    @PersistenceContext
    EntityManager em;

    @Autowired
    JpaChatRepositoryManager jpaChatRepositoryManager = new JpaChatRepositoryManager();

    @Test
    void test(){
        // 회원들이 존재하고
        Member member1 = new Member("userName1",  MemberGrade.USER);
        Member member2 = new Member("userName2",  MemberGrade.ADMIN);
        Member member3 = new Member("userName3",  MemberGrade.CHATBOT);

// 대화방이 만들어지면
        String roomId1 = UUID.randomUUID().toString();
        String roomId2 = UUID.randomUUID().toString();
        RoomInfo room1 = new RoomInfo(roomId1, "room1");
        RoomInfo room2 = new RoomInfo(roomId2, "room2");

// 대화방의 멤버들을 셋팅해주고
        MemberRoomInfo memberRoomInfo1 = new MemberRoomInfo(member1, room1);
        MemberRoomInfo memberRoomInfo2 = new MemberRoomInfo(member1, room2);
        MemberRoomInfo memberRoomInfo3 = new MemberRoomInfo(member2, room1);
        MemberRoomInfo memberRoomInfo4 = new MemberRoomInfo(member3, room2);

// 채팅을한다
        Chat chat1 = new Chat(room1, "userName1", "1:40", "안녕하세요");
        Chat chat2 = new Chat(room1, "userName2", "1:41", "반가워요");
        Chat chat3 = new Chat(room2, "userName1", "1:42", "좋은하루되세요");
        Chat chat4 = new Chat(room2, "userName3", "1:43", "만나서 반가웠습니다");
        Chat chat5 = new Chat(room1, "userName1", "1:44", "안녕히계세요");

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);

        em.persist(memberRoomInfo1);
        em.persist(memberRoomInfo2);
        em.persist(memberRoomInfo3);
        em.persist(memberRoomInfo4);

        em.persist(room1);
        em.persist(room2);

        em.persist(chat1);
        em.persist(chat2);
        em.persist(chat3);
        em.persist(chat4);
        em.persist(chat5);

        em.flush();
        em.clear();

        List<RoomInfo> roomInfos = jpaChatRepository.testQuery();
        for (RoomInfo roomInfo : roomInfos) {
            System.out.println("roomInfo = " + roomInfo);
        }

        List<Member> members = jpaChatRepository.testQuery2();
        for (Member member : members) {
            System.out.println("member = " + member.getUserName());
        }


        ChatLogDto recentRoomChatLogByRoomId = jpaChatRepositoryManager.getRecentRoomChatLogByRoomId(roomId1, 2);
        System.out.println("size = " + recentRoomChatLogByRoomId.getChatLogList().size());


    }

}