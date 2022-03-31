package Personal.KPP.app.chat.storage.repository.mongo;

import Personal.KPP.app.chat.storage.entity.mongo.Chat;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChatRepository extends MongoRepository<Chat, String> {
    List<Chat> findByRoomId(String roomId);

}
