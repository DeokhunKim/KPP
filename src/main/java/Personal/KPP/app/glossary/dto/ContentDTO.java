package Personal.KPP.app.glossary.dto;

import Personal.KPP.app.glossary.repository.entity.Content;
import Personal.KPP.app.glossary.repository.entity.Document;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor @AllArgsConstructor
public class ContentDTO {
    public String contentHtml;
    public Long date;
    public String user;


    public static ContentDTO fromEntity(Content content) {
        return ContentDTO.builder()
                .contentHtml(content.getContentHtml())
                .date(content.getDate())
                .user(content.getUpdateUser())
                .build();
    }
}
