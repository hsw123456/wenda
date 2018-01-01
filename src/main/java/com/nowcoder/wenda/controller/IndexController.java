package com.nowcoder.wenda.controller;

import com.nowcoder.wenda.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hsw on 2018/1/1.
 */

@Controller
public class IndexController {

    @RequestMapping(path = {"/","/index"})
    @ResponseBody
    public String index(){
        return "Hello nowcoder!";
    }

    /**
     *路径参数注解：PathVariable
     * 请求参数注解： RequestParam
     */
    @RequestMapping(path = {"/profile/{userId}"})
    @ResponseBody
    public String profile(@PathVariable("userId") int userId, @RequestParam("type") int type,
                          @RequestParam(value = "key",required =false) String key){
        return String.format("haha! profile of %d type=%d, key=%s", userId,type,key);
    }

    @RequestMapping(path = {"/vm"})
    public String template(Model model){
        model.addAttribute("value1","haha");
        List<String> colors = Arrays.asList(new String[]{"RED","GREEN","BLUE"});
        model.addAttribute("colors",colors);

        Map<String,String> map = new HashMap();
        for(int i=0; i<4; ++i){
            map.put(String.valueOf(i),String.valueOf(i*i));
        }
        model.addAttribute("map",map);

        User user = new User("Ye shen");
        model.addAttribute("user", user);
        return "home";
    }
}
