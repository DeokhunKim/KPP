package Personal.KPP.app.chat;

import Personal.KPP.app.chat.storage.repository.mongo.RoomInfoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.util.StopWatch;

@SpringBootTest

public class MongoTest {
    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    RoomInfoRepository roomInfoRepository;

    @BeforeEach
    public void reset(){
        //roomInfoRepository.deleteAll();
    }

    void save(String byRoomId){
        /*
        Query query = new Query(Criteria.where("_id").is(byRoomId));
        Update update = new Update();
        update.push("Chat", new Chat("aa", "bb", "cc") );
        mongoTemplate.updateFirst(query, update, RoomInfo.class);
    */

    }

}
