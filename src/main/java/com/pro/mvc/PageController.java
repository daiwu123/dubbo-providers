package com.pro.mvc;

import com.s.service.IPopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class PageController {

    @Resource(name="popServiceImpl")
    IPopService iPopService;

    @RequestMapping("/index")
    public String pageDispatcher(){
        return "index.html";
    }


  /*  @RequestMapping("/data")
    @ResponseBody
    public Object getData(){
        return iPopService.query();
    }*/
}
