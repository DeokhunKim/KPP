package Personal.KPP.core.domain.member;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MemberRepositoryConfig {

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRespository();
    }
}
