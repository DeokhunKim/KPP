package Personal.KPP.app.glossary.service;

import Personal.KPP.app.glossary.dto.DocumentDTO;
import Personal.KPP.app.glossary.library.kmp.KMP;
import Personal.KPP.app.glossary.repository.GlossaryContentRepository;
import Personal.KPP.app.glossary.repository.GlossaryDocumentRepository;
import Personal.KPP.app.glossary.repository.GlossarySynonymTitleRepository;
import Personal.KPP.app.glossary.repository.entity.Content;
import Personal.KPP.app.glossary.repository.entity.Document;
import Personal.KPP.app.glossary.repository.entity.SynonymTitle;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Service
@Transactional
public class DocumentServiceImpl implements DocumentService{

    final private GlossaryDocumentRepository glossaryDocumentRepository;
    final private GlossaryContentRepository glossaryContentRepository;
    final private GlossarySynonymTitleRepository glossarySynonymTitleRepository;

    @PersistenceContext
    private EntityManager em;

    public DocumentServiceImpl(GlossaryDocumentRepository glossaryDocumentRepository, GlossaryContentRepository glossaryContentRepository, GlossarySynonymTitleRepository glossarySynonymTitleRepository) {
        this.glossaryDocumentRepository = glossaryDocumentRepository;
        this.glossaryContentRepository = glossaryContentRepository;
        this.glossarySynonymTitleRepository = glossarySynonymTitleRepository;
    }

    @Override
    public ArrayList<DocumentDTO> findAll() {
        ArrayList<DocumentDTO> result = new ArrayList<>();
        ArrayList<Document> findDocuments = glossaryDocumentRepository.findLastDocumentsOrderBYTitle();
        for (Document findDocument : findDocuments) {
            result.add(DocumentDTO.fromEntity(findDocument));
        }
        return result;
    }

    @Override
    public ArrayList<DocumentDTO> findByKeyword(String keyword) {
        ArrayList<DocumentDTO> result = new ArrayList<>();
        ArrayList<Document> findDocuments = glossaryDocumentRepository.findLastDocuments();
        for (Document findDocument : findDocuments) {
            // 동의어 검색
            boolean findSynonym = false;
            List<SynonymTitle> synonyms = findDocument.getSynonyms();
            for (SynonymTitle synonym : synonyms) {
                if (synonym.getTitleStr().equalsIgnoreCase(keyword)) {
                    findSynonym = true;
                    break;
                }
            }

            // 동의어에 있거나 내용에 포함된경우
            if (findSynonym
                    || KMP.isContain(findDocument.getContent().getContentHtml().toLowerCase(Locale.ROOT),
                    keyword.toLowerCase(Locale.ROOT))) {
                result.add(DocumentDTO.fromEntity(findDocument));
            }
        }
        return result;
    }

    @Override
    public DocumentDTO getDocByTitle(String title) {
        Document document = glossaryDocumentRepository.findDocumentByMainTitleStrAndIsUse(title, true);
        return DocumentDTO.fromEntity(document);
    }

    @Override
    public boolean createDoc(DocumentDTO doc) {
        Document document = null;
        Boolean isUse = glossaryDocumentRepository.findUseByMainTitleStr(doc.getTitle());
        // 신규 페이지
        if (isUse == null) {
            document = new Document(doc.getTitle());
        }
        // 이미 사용 중인 페이지
        else if (true == isUse) {
            return false;
        }
        // 삭제된 페이지
        else if (false == isUse) {
            document = glossaryDocumentRepository.findDocumentByMainTitleStrAndIsUse(doc.getTitle(), false);
            document.setUse(true);
            glossaryDocumentRepository.save(document);
        }
        else {
            /// impossible..
        }


        // 객체 생성
        Content content = new Content(doc.getContent().getDate(), doc.getContent().getUser(), doc.getContent().getContentHtml() );
        ArrayList<String> synonymStrList = doc.getSynonym();
        ArrayList<SynonymTitle> synonyms = getSynonymTitles(doc.getTitle(), document, synonymStrList);

        // 릴레이션 설정
        document.setContent(content);
        document.setSynonyms(synonyms);
        content.setDocument(document);


        // 저장
        em.persist(document);
        em.persist(content);
        for (SynonymTitle synonym : synonyms) {
            em.persist(synonym);
        }
        //em.flush();

        return true;
    }

    @Override
    public boolean updateDoc(String title, DocumentDTO doc) {
        Document document = glossaryDocumentRepository.findDocumentByMainTitleStrAndIsUse(title, true);
        if (document == null) {
            return false;
        }
        Content content = new Content(doc.getContent().getDate(), doc.getContent().getUser(), doc.getContent().getContentHtml());
        ArrayList<String> synonyms = doc.getSynonym();

        ArrayList<SynonymTitle> synonymTitleArrayList = getSynonymTitles(title, document, synonyms);

        for (SynonymTitle synonym : document.getSynonyms()) {
            em.remove(synonym);
        }

        content.setDocument(document);
        document.setContent(content);
        document.setSynonyms(synonymTitleArrayList);

        em.persist(content);
        em.persist(document);
        for (SynonymTitle synonymTitle : synonymTitleArrayList) {
            em.persist(synonymTitle);
        }

        return true;
    }

    private ArrayList<SynonymTitle> getSynonymTitles(String title, Document document, ArrayList<String> synonyms) {
        ArrayList<SynonymTitle> synonymTitleArrayList = new ArrayList<>();
        for (String synonym : synonyms) {
            if (synonym.equals("")) {
                continue;
            }
            synonym = synonym.trim();
            SynonymTitle synonymTitle = new SynonymTitle(synonym, document);
            synonymTitleArrayList.add(synonymTitle);
        }

        if (false == synonyms.contains(title)) {
            SynonymTitle synonymTitle = new SynonymTitle(title, document);
            synonymTitleArrayList.add(synonymTitle);
        }
        return synonymTitleArrayList;
    }

    @Override
    public boolean changeDocName(String beforeTitle, String afterTitle) {
        Document document = glossaryDocumentRepository.findDocumentByMainTitleStrAndIsUse(beforeTitle, true);
        if (document == null) {
            return false;
        }
        document.setMainTitleStr(afterTitle);
        em.persist(document);

        return true;
    }

    @Override
    public boolean delByTitle(String title) {
        Document document = glossaryDocumentRepository.findDocumentByMainTitleStrAndIsUse(title, true);
        if (document == null) {
            return false;
        }
        document.setUse(false);
        em.persist(document);

        return true;
    }

    @Override
    public boolean isContainDocByTitle(String title) {
        // 필요없을것 같아서 일단 보류
        return false;
    }

    @Override
    public void clearDocument() {
        glossarySynonymTitleRepository.deleteAll();
        glossaryContentRepository.deleteAll();
        glossaryDocumentRepository.deleteAll();
        glossarySynonymTitleRepository.flush();
        glossaryDocumentRepository.flush();
        glossaryContentRepository.flush();
    }

    public void insertTestData() {
        Content content = new Content(2022, "user",
                "<p>C언어와 조상을 공유하고 객체 지향 및 일반화 프로그래밍과 같은 멀티 패러다임을 지원하는 프로그래밍 언어이다. C언어의 문법과 기능을 대부분 사용할 수 있다. 1979년에 C언어에서 직접적으로 파생된 C with Classes라는 이름의 언어로 시작되었다가, 1983년에 지금의 이름을 갖게 되었다.</p>" );
        Document document = new Document("c++");
        SynonymTitle title = new SynonymTitle("c++", document);
        SynonymTitle title2 = new SynonymTitle("c플플", document);
        ArrayList<SynonymTitle> synonyms = new ArrayList<>(Arrays.asList(title, title2));
        document.setContent(content);
        document.setSynonyms(synonyms);
        content.setDocument(document);

        createDoc(DocumentDTO.fromEntity(document));
    }
}
