package Personal.KPP.app.glossary.repository.entity;



import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Content {
    @Id @GeneratedValue @Column(name = "content_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "document_id")
    private Document document;

    @OneToOne(mappedBy = "content")
    private Document mappedDocument;

    private long date;
    private String updateUser;
    @Column(columnDefinition = "TEXT")
    private String contentHtml;

    public Content(long date, String updateUser, String contentHtml) {
        this.date = date;
        this.updateUser = updateUser;
        this.contentHtml = contentHtml;
    }


}
