package Personal.KPP.app.glossary.repository.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(
        uniqueConstraints={
                @UniqueConstraint(
                        columnNames={"synonymtitle_titleStr", "synonymtitle_documentId"}
                )
        }
)
public class SynonymTitle {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "synonymtitle_titleStr")
    private String titleStr;

    @Column(name = "synonymtitle_documentId")
    private Long documentId;

    public SynonymTitle(String titleStr) {
        this.titleStr = titleStr;
    }

    public SynonymTitle(String titleStr, Document document) {
        this.titleStr = titleStr;
    }
}
