package Personal.KPP.app.chat.storage.repository.mongo;

import Personal.KPP.app.chat.storage.entity.mongo.RoomInfo;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoomInfoRepository extends MongoRepository<RoomInfo, String> {
    RoomInfo findByRoomId(String roomId);
    ObjectId findIdByRoomId(String roomId);
}
