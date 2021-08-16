package edu.cczu.tmall.admin.property.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author iceorangeduxiaocheng@aliyun.com
 * @date 2021/8/15 15:48
 */

@Controller
public class PropertyController {

    @RequestMapping(path = "/admin_show_property_list.do")
    public ModelAndView doTest(){
        return null;
    }

}
