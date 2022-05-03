package Personal.KPP.app.glossary.service;

import Personal.KPP.app.glossary.dto.DocumentDTO;

import java.util.ArrayList;


public interface DocumentService {

    // 조회
    public ArrayList<DocumentDTO> findAll();

    public ArrayList<DocumentDTO> findByKeyword(String keyword);

    public DocumentDTO getDocByTitle(String title);


    // 변경
    public boolean createDoc(DocumentDTO doc);

    public boolean updateDoc(String title, DocumentDTO doc);

    public boolean changeDocName(String beforeTitle, String afterTitle);

    public boolean delByTitle(String title);

    public boolean isContainDocByTitle(String title);


    // 테스트 용도
    public void clearDocument();

    public void insertTestData();
}

