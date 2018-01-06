package com.nowcoder.wenda.controller;

import com.nowcoder.wenda.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by hsw on 2018/1/1.
 */

@Controller
public class IndexController {

    @RequestMapping(path = {"/","/index"})
    @ResponseBody
    public String index(HttpSession session){
        return "Hello nowcoder!" + session.getAttribute("msg");
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

    @RequestMapping(path = {"/request"})
    @ResponseBody
    public String request(Model model, HttpServletRequest request,
                          HttpServletResponse response,
                          HttpSession session){

        StringBuilder sb = new StringBuilder();

        Enumeration<String> names = request.getHeaderNames();
        while(names.hasMoreElements()){
            String name = names.nextElement();
            sb.append(name+ ":" + request.getHeader(name)+"<br>");
        }
        sb.append(request.getMethod()+"<br>");
        sb.append(request.getQueryString()+"<br>");
        sb.append(request.getPathInfo()+"<br>");
        sb.append(request.getRequestURI()+"<br>");

        return sb.toString();

    }

    //测试重定向
    @RequestMapping(path = {"/redirect/{code}"})
    public String redirect(Model model, @PathVariable("code") int code,HttpSession session){
        session.setAttribute("msg","jump from redirect");
        return "redirect:/";
    }


    //测试重定向
    @RequestMapping(path = {"/admin"})
    @ResponseBody
    public String admin(@RequestParam("key") String key){
        if("admin".equals(key)){
            return "hello admin";
        }

        throw new IllegalArgumentException("参数不对");
    }
    //统一处理异常的页面
    @ExceptionHandler
    @ResponseBody
    public String error(Exception e){
        return "error:" + e.getMessage();
    }
}
