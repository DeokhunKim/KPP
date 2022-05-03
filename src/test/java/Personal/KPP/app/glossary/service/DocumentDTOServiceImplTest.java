package Personal.KPP.app.glossary.service;

import Personal.KPP.app.glossary.dto.DocumentDTO;
import Personal.KPP.app.glossary.repository.entity.Content;
import Personal.KPP.app.glossary.repository.entity.Document;
import Personal.KPP.app.glossary.repository.entity.SynonymTitle;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootTest
class DocumentDTOServiceImplTest {

    @Autowired
    private DocumentServiceImpl documentService;

    @BeforeEach
    void init() {
        documentService.clearDocument();
    }

    @Test
    void findAll() {
        // given
        Document document = givenDocument();
        documentService.createDoc(DocumentDTO.fromEntity(document));

        // when
        ArrayList<DocumentDTO> findDocumentList = documentService.findAll();

        // then
        Assertions.assertThat(findDocumentList.size()).isGreaterThan(0);
    }

    @Test
    void findByKeyword() {
        // given
        Document document = givenDocument();
        documentService.createDoc(DocumentDTO.fromEntity(document));

        // when
        ArrayList<DocumentDTO> findDocumentList1 = documentService.findByKeyword("c++");
        ArrayList<DocumentDTO> findDocumentList2 = documentService.findByKeyword("c플플");
        ArrayList<DocumentDTO> findDocumentList3 = documentService.findByKeyword("c쁠쁠");
        ArrayList<DocumentDTO> findDocumentList4 = documentService.findByKeyword("프로그래밍 언어");
        ArrayList<DocumentDTO> findDocumentList5 = documentService.findByKeyword("C++");
        ArrayList<DocumentDTO> findDocumentList6 = documentService.findByKeyword("C+++");

        // then
        Assertions.assertThat(findDocumentList1.size()).isGreaterThan(0);
        Assertions.assertThat(findDocumentList2.size()).isGreaterThan(0);
        Assertions.assertThat(findDocumentList3.size()).isEqualTo(0);
        Assertions.assertThat(findDocumentList4.size()).isGreaterThan(0);
        Assertions.assertThat(findDocumentList5.size()).isGreaterThan(0);
        Assertions.assertThat(findDocumentList6.size()).isEqualTo(0);
    }

    @Test
    void getDocByTitle() {
        // given
        Document document = givenDocument();
        documentService.createDoc(DocumentDTO.fromEntity(document));

        // when
        ArrayList<DocumentDTO> findDocumentList1 = documentService.findByKeyword("c++");
        ArrayList<DocumentDTO> findDocumentList2 = documentService.findByKeyword("c플플");
        ArrayList<DocumentDTO> findDocumentList3 = documentService.findByKeyword("c쁠쁠");
        ArrayList<DocumentDTO> findDocumentList4 = documentService.findByKeyword("프로그래밍 언어이다");
        ArrayList<DocumentDTO> findDocumentList5 = documentService.findByKeyword("프로그래밍 언어이당");

        // then
        Assertions.assertThat(findDocumentList1.size()).isEqualTo(1);
        Assertions.assertThat(findDocumentList2.size()).isEqualTo(1);
        Assertions.assertThat(findDocumentList3.size()).isEqualTo(0);
        Assertions.assertThat(findDocumentList4.size()).isEqualTo(1);
        Assertions.assertThat(findDocumentList5.size()).isEqualTo(0);
    }

    @Test
    void createDoc() {
        // given
        Document document = givenDocument();

        //when
        documentService.createDoc(DocumentDTO.fromEntity(document));

        // then
        DocumentDTO docByTitle = documentService.getDocByTitle("c++");
        Assertions.assertThat(docByTitle).isNotNull();
    }

    @Test
    void updateDoc() {
        // given
        Document document = givenDocument();
        documentService.createDoc(DocumentDTO.fromEntity(document));
        DocumentDTO newDocDTO = DocumentDTO.fromEntity(document);
        newDocDTO.content.contentHtml = "change content";

        // when
        boolean result = documentService.updateDoc("c++", newDocDTO);
        DocumentDTO docByTitle = documentService.getDocByTitle("c++");

        // then
        Assertions.assertThat(result).isTrue();
        Assertions.assertThat(docByTitle.content.contentHtml).isEqualTo("change content");
    }

    @Test
    void changeDocName() {
        // given
        Document document = givenDocument();
        documentService.createDoc(DocumentDTO.fromEntity(document));

        // when
        boolean result = documentService.changeDocName("c++", "cpp");
        DocumentDTO docByTitle = documentService.getDocByTitle("c++");
        DocumentDTO cpp = documentService.getDocByTitle("cpp");

        // then
        Assertions.assertThat(result).isTrue();
        Assertions.assertThat(docByTitle).isNull();
        Assertions.assertThat(cpp).isNotNull();
    }

    @Test
    void delByTitle() {
        // given
        Document document = givenDocument();
        documentService.createDoc(DocumentDTO.fromEntity(document));

        // when
        documentService.delByTitle("c++");
        DocumentDTO docByTitle = documentService.getDocByTitle("c++");

        // then
        Assertions.assertThat(docByTitle).isNull();
    }

    @Test
    void isContainDocByTitle() {
        // given
        Document document = givenDocument();
        documentService.createDoc(DocumentDTO.fromEntity(document));

        // when
        boolean containDocByTitle1 = documentService.isContainDocByTitle("c++");
        boolean containDocByTitle2 = documentService.isContainDocByTitle("c+");

        // then
        // 보류 테스트
        //Assertions.assertThat(containDocByTitle1).isNotNull();
        //Assertions.assertThat(containDocByTitle2).isNull();
    }


    private Document givenDocument(){
        Content content = new Content(2022, "user",
                "<p>C언어와 조상을 공유하고 객체 지향 및 일반화 프로그래밍과 같은 멀티 패러다임을 지원하는 프로그래밍 언어이다. C언어의 문법과 기능을 대부분 사용할 수 있다. 1979년에 C언어에서 직접적으로 파생된 C with Classes라는 이름의 언어로 시작되었다가, 1983년에 지금의 이름을 갖게 되었다.</p>" );
        Document document = new Document("c++");
        SynonymTitle title = new SynonymTitle("c++", document);
        SynonymTitle title2 = new SynonymTitle("c플플", document);
        ArrayList<SynonymTitle> synonyms = new ArrayList<>(Arrays.asList(title, title2));
        document.setContent(content);
        document.setSynonyms(synonyms);
        content.setDocument(document);

        return document;
    }
}