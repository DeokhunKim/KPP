package Personal.KPP.app.glossary.repository;

import Personal.KPP.app.glossary.repository.entity.Content;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GlossaryContentRepository extends JpaRepository<Content, Long> {
}
