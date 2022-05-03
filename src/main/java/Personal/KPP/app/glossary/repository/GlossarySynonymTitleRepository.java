package Personal.KPP.app.glossary.repository;

import Personal.KPP.app.glossary.repository.entity.SynonymTitle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GlossarySynonymTitleRepository extends JpaRepository<SynonymTitle, String> {
}
