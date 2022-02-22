package Personal.KPP.core.controller;

import Personal.KPP.app.AppList;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class MainpageControllerTest {

    @Test
    public void applicationContextTest(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppList.class);

        AppList appList = ac.getBean("appList", AppList.class);

        System.out.println("appList = " + appList);

        ApplicationContext ac2 = new AnnotationConfigApplicationContext(AppList.class);


    }


}