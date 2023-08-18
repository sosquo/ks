package org.example;

import com.kuang.pojo.Teacher;
import com.kuang.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {

    @Test
    public void testMethodAutowire() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        byBeanXmlProp(context);
        System.out.println("");

        try {
            byName(context);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("");
        }

        try {
            byType(context);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("");
        }

        byAutowiredOrByResource(context);

    }

    private static void byBeanXmlProp(ApplicationContext context) {
        System.out.println("--- byBeanXmlProp ---");
        User user = (User) context.getBean("user");
        user.getCat().shout();
        user.getDog().shout();
    }

    private static void byName(ApplicationContext context) {
        System.out.println("--- byName ---");
        User user = (User) context.getBean("userByName");
        user.getCat().shout();
        user.getDog().shout();
    }

    private static void byType(ApplicationContext context) {
        System.out.println("--- byType ---");
        User user = (User) context.getBean("userByType");
        user.getCat().shout();
        user.getDog().shout();
    }

    /**
     * @Resource(name="xxx") 或者 @Autowired + @Qualifier(value="xxx") 则只会根据 ByName xxx
     *
     * @Resource 或者 @Autowired 优先级顺序一样，如下：
     * 1、根据属性名称 byName 查找
     * 2、byType 找到多个报异常： NoUniqueBeanDefinitionException
     * 3、未找到报异常： NoSuchBeanDefinitionException
     * ps: todo 狂神的说法是 @Autowired 只有byType 没有 byName ?? chatgpt 也是这么说的。。
     * @param context
     */
    private static void byAutowiredOrByResource(ApplicationContext context) {
        System.out.println("--- byAutowired ---");
        Teacher teacher = (Teacher) context.getBean(Teacher.class);
        System.out.println("teacher.getStu() = " + teacher.getStu());
        System.out.println("teacher.getStu1() = " + teacher.getStu1());
        System.out.println("teacher.getStu2() = " + teacher.getStu2());
        System.out.println("teacher.getStu3() = " + teacher.getStu3());
        System.out.println("teacher.getStu4() = " + teacher.getStu4());
        teacher.getDog0().shout();
    }
}
