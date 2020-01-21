package iwe.zh.test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zh on 2020/1/14.
 */
@RestController
public class Test {

    @RequestMapping("/*")
    public String test() {
        return "hello world";
    }

//    public static void main(String[] args) {
//        JSONObject json = JSONObject.parseObject("");
//
//    }
}
