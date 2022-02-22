package Personal.KPP.core.member;

public interface MemberRepository {

    //@ToDo
    //Security using Spring Interceptor
    public Member login(String userName);

    public Member store(String userName);


}
