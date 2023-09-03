import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.kuang.bean.User;
import com.kuang.util.HttpClientUtil;
import com.kuang.util.HttpClientUtil2;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

public class MyTest {

    @Test
    public void testJDKApi(){
        //测试get方法
        String s = HttpClientUtil2.doGet("http://localhost:9092/api/getHello");
        System.out.println("get方法:"+s);
        //测试post方法
        User user = new User();
        user.setUname("胡萝卜");
        user.setRole("普通用户");
        //把对象转换为json格式
        String s1 = JSONObject.toJSONString(user);
        String postString = HttpClientUtil2.doPost("http://localhost:9092/api/postHello",s1);
        System.out.println("post方法:"+postString);
    }


    @Test
    public void testApi() throws UnsupportedEncodingException {
        //测试get方法
        String s = HttpClientUtil.doGet("http://localhost:9092/api/getHello", "UTF-8");
        System.out.println("get方法:"+s);
        //测试post方法
        User user = new User();
        user.setUname("胡萝卜");
        user.setRole("普通用户");
        JSONObject jsonObject = new JSONObject();
        String s1 = JSONUtil.toJsonStr(user);
//        String s1 = JSONObject.toJSONString(user);
        jsonObject.put("param",s1);
        String postString = HttpClientUtil.doPost("http://localhost:9092/api/postHello", jsonObject);
        System.out.println("post方法:"+postString);
        String postString1 = HttpClientUtil.doPost("http://localhost:8080/testBody", jsonObject);
        System.out.println("postString1 = " + postString1);

    }

}
