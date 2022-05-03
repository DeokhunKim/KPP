package Personal.KPP.app.glossary.repository;


import Personal.KPP.app.chat.storage.entity.normal.RoomInfo;
import Personal.KPP.app.glossary.repository.entity.Content;
import Personal.KPP.app.glossary.repository.entity.Document;
import Personal.KPP.app.glossary.repository.entity.SynonymTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface GlossaryDocumentRepository extends JpaRepository<Document, Long> {

    Document findDocumentByMainTitleStrAndIsUse(String mainTitleStr, boolean isUse);

    @Query("SELECT c FROM Content c JOIN c.mappedDocument cd WHERE cd.mainTitleStr = :mainTitleStr")
    Content findContentByMainTitle(@Param("mainTitleStr") String mainTitleStr);

    //@Query("SELECT s FROM SynonymTitle s JOIN s.documentId sd WHERE sd.mainTitleStr = :mainTitleStr")
    @Query("SELECT d.synonyms FROM Document d WHERE d.mainTitleStr = :mainTitleStr")
    ArrayList<SynonymTitle> findSynonymsByMainTitle(@Param("mainTitleStr") String mainTitleStr);

    @Query("SELECT d FROM Document d WHERE d.isUse = true")
    ArrayList<Document> findLastDocuments();

    @Query("SELECT d FROM Document d WHERE d.isUse = true ORDER BY d.mainTitleStr")
    ArrayList<Document> findLastDocumentsOrderBYTitle();

    @Query("SELECT d.isUse FROM Document d WHERE d.mainTitleStr = :mainTitleStr")
    Boolean findUseByMainTitleStr(@Param("mainTitleStr") String mainTitleStr);

}
