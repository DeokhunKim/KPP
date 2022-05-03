package Personal.KPP.app.glossary.dto;

import Personal.KPP.app.glossary.repository.entity.Document;
import Personal.KPP.app.glossary.repository.entity.SynonymTitle;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Builder
@Getter
public class DocumentDTO {
    public String title;
    public ArrayList<String> synonym = new ArrayList<>();
    public ContentDTO content;

    public DocumentDTO(String title, ArrayList<String> synonym, ContentDTO content) {
        this.title = title;
        this.synonym = synonym;
        this.content = content;
    }

    public DocumentDTO() {
    }

    public static DocumentDTO fromEntity(Document document) {
        if (document == null) {
            return null;
        }

        ArrayList<String> synonyms = document.getSynonyms().stream()
                .map(m -> m.getTitleStr())
                .collect(Collectors.toCollection(ArrayList::new));


        return DocumentDTO.builder()
                .title(document.getMainTitleStr())
                .synonym(synonyms)
                .content(ContentDTO.fromEntity(document.getContent()))
                .build();
    }
}
