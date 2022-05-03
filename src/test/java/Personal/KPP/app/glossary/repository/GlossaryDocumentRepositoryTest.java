package Personal.KPP.app.glossary.repository;

import Personal.KPP.app.glossary.repository.entity.Content;
import Personal.KPP.app.glossary.repository.entity.Document;
import Personal.KPP.app.glossary.repository.entity.SynonymTitle;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class GlossaryDocumentRepositoryTest {

    @Autowired
    private GlossaryDocumentRepository glossaryDocumentRepository;
    @Autowired
    private GlossarySynonymTitleRepository glossarySynonymTitleRepository;
    @Autowired
    private GlossaryContentRepository glossaryContentRepository;

    @PersistenceContext
    private EntityManager em;

    @BeforeEach
    public void init() {


        glossarySynonymTitleRepository.deleteAll();
        glossaryContentRepository.deleteAll();
        glossaryDocumentRepository.deleteAll();

        glossaryDocumentRepository.flush();
        glossarySynonymTitleRepository.flush();
        glossaryContentRepository.flush();


        givenInitCpp();

    }
    @AfterEach
    public void flush() {
        em.flush();
    }

    @Test //CREATE
    public void createDocumentTest() {
        // 객체 생성
        Document document = new Document("java");
        SynonymTitle title1 = new SynonymTitle("java");
        SynonymTitle title2 = new SynonymTitle("자바");
        Content content = new Content(2022, "user", "");

        // 연관 관계
        document.setContent(content);
        content.setDocument(document);
        title1.setDocumentId(document.getId());
        title2.setDocumentId(document.getId());

        // 적용
        em.persist(document);
        em.persist(title1);
        em.persist(title2);
        em.persist(content);

    }

    @Test //READ
    public void findDocumentTest() {
        //READ Document
        Document findDoc = glossaryDocumentRepository.findDocumentByMainTitleStrAndIsUse("c++", true);
        assertThat(findDoc).isNotNull();

        //READ Content
        Content findContent = glossaryDocumentRepository.findContentByMainTitle("c++");
        assertThat(findContent).isNotNull();

        //READ Synonym
        List<SynonymTitle> findSynonyms = glossaryDocumentRepository.findSynonymsByMainTitle("c++");
        assertThat(findSynonyms).isNotNull();
        assertThat(findSynonyms).hasSizeGreaterThan(1);

        //READ LastContents
        ArrayList<Document> documents = glossaryDocumentRepository.findLastDocuments();
        String contentHtml = documents.get(0).getContent().getContentHtml();
        assertThat(contentHtml).isNotNull();

    }

    @Test //UPDATE
    public void updateDocumentTest() {
        //change main title
        Document findDoc1 = glossaryDocumentRepository.findDocumentByMainTitleStrAndIsUse("c++", true);
        findDoc1.setMainTitleStr("cpp");
        em.persist(findDoc1);
        em.flush();
        Document findDoc2 = glossaryDocumentRepository.findDocumentByMainTitleStrAndIsUse("c++", true);
        assertThat(findDoc2).isNull();
        Document findDoc3 = glossaryDocumentRepository.findDocumentByMainTitleStrAndIsUse("cpp", true);
        assertThat(findDoc3).isNotNull();

        //change content
        Document findDoc4 = glossaryDocumentRepository.findDocumentByMainTitleStrAndIsUse("cpp", true);
        Content content = new Content(2023, "user", "change html");
        content.setDocument(findDoc4);
        findDoc4.setContent(content);
        em.persist(content);
        em.persist(findDoc4);
        em.flush();

    }

    @Test //DELETE
    public void nouseTest() {
        Document javaDoc = glossaryDocumentRepository.findDocumentByMainTitleStrAndIsUse("c++", true);
        javaDoc.setUse(false);
        em.persist(javaDoc);

        Document javaDoc2 = glossaryDocumentRepository.findDocumentByMainTitleStrAndIsUse("c++", true);
        assertThat(javaDoc2).isNull();

        Document javaDoc3 = glossaryDocumentRepository.findDocumentByMainTitleStrAndIsUse("c++", false);
        assertThat(javaDoc3).isNotNull();
    }

    @Test
    public void basicScenarioTest() {
        // c++ 데이터가 있을 때
        // givenInitCpp();

        // 메인 타이틀 변경
        Document javaDoc = glossaryDocumentRepository.findDocumentByMainTitleStrAndIsUse("c++", true);
        javaDoc.setMainTitleStr("씨플플");
        SynonymTitle title = new SynonymTitle("씨플플", javaDoc);
        em.persist(javaDoc);
        em.persist(title);



    }


    private void givenInitCpp(){
        Content content = new Content(2022, "user",
                "<p>C언어와 조상을 공유하고 객체 지향 및 일반화 프로그래밍과 같은 멀티 패러다임을 지원하는 프로그래밍 언어이다. C언어의 문법과 기능을 대부분 사용할 수 있다. 1979년에 C언어에서 직접적으로 파생된 C with Classes라는 이름의 언어로 시작되었다가, 1983년에 지금의 이름을 갖게 되었다.</p>" );
        Document document = new Document("c++");
        SynonymTitle title = new SynonymTitle("c++", document);
        SynonymTitle title2 = new SynonymTitle("c플플", document);
        ArrayList<SynonymTitle> synonyms = new ArrayList<>(Arrays.asList(title, title2));
        document.setContent(content);
        document.setSynonyms(synonyms);
        content.setDocument(document);

        em.persist(document);
        em.persist(content);
        em.persist(title);
        em.persist(title2);

        Document document2 = glossaryDocumentRepository.findDocumentByMainTitleStrAndIsUse("c++", true);
        Content content2 = new Content(2023, "user", "\"<p>C언어와 조상을 공유하고 객체 지향 및 일반화 프로그래밍과 같은 멀티 패러다임을 지원하는 <strong>프로그래밍 언어</strong>이다. C언어의 문법과 기능을 대부분 사용할 수 있다. 1979년에 C언어에서 직접적으로 파생된 C with Classes라는 이름의 언어로 시작되었다가, 1983년에 지금의 이름을 갖게 되었다.</p>\"" );
        content2.setDocument(document2);
        document2.setContent(content2);

        em.persist(content2);
        em.persist(document2);
        em.flush();

    }


    @Test
    public void queryTuning() {
        Document document1 = new Document("a");
        Document document2 = new Document("b");
        Document document3 = new Document("c");
        Document document4 = new Document("d");
        SynonymTitle title1 = new SynonymTitle("a", document1);
        SynonymTitle title2 = new SynonymTitle("b", document2);
        SynonymTitle title3 = new SynonymTitle("c", document3);
        SynonymTitle title4 = new SynonymTitle("d", document4);
        document1.setSynonyms(Arrays.asList(title1));
        document2.setSynonyms(Arrays.asList(title2));
        document3.setSynonyms(Arrays.asList(title3));
        document4.setSynonyms(Arrays.asList(title4));
        em.persist(title1);
        em.persist(title2);
        em.persist(title3);
        em.persist(title4);
        em.persist(document1);
        em.persist(document2);
        em.persist(document3);
        em.persist(document4);

        em.flush();



        System.out.println("GlossaryDocumentRepositoryTest.queryTuning");




    }


}