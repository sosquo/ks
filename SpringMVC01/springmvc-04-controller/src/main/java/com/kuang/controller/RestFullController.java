package com.kuang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RestFullController {

    @RequestMapping(value = "/commit/{p1}/{p2}")
    public String addIntType(@PathVariable int p1, @PathVariable int p2, Model model) {
        int result = p1 + p2;
        model.addAttribute("msg", "addIntType 结果为：" + result);
        return "hello";
    }

    @RequestMapping(value = "/commit/str/{p1}/{p2}")
    public String addStringType(@PathVariable int p1, @PathVariable String p2, Model model) {
        String result = p1 + p2;
        model.addAttribute("msg", "addStringType 结果为：" + result);
        return "hello";
    }


    @RequestMapping(value = "/commit/test/{p1}/{p2}", method = RequestMethod.GET)
    public String test1(@PathVariable String p1, @PathVariable String p2, Model model) {
        String result = p1 + p2;
        model.addAttribute("msg", "test 结果为：" + result);
        return "hello";
    }
    @RequestMapping(value = "/commit/test/{p1}/{p2}", method = RequestMethod.HEAD)
    public String test2(@PathVariable String p1, @PathVariable String p2, Model model) {
        String result = p1 + p2;
        model.addAttribute("msg", "test 结果为：" + result);
        return "hello";
    }
    /*@RequestMapping(value = "/commit/test/{p1}/{p2}", method = RequestMethod.POST)
    public String test3(@PathVariable String p1, @PathVariable String p2, Model model) {
        String result = p1 + p2;
        model.addAttribute("msg", "test 结果为：" + result);
        return "hello";
    }*/
    @PostMapping(value = "/commit/test/{p1}/{p2}")
    public String test3_1(@PathVariable String p1, @PathVariable String p2, Model model) {
        String result = p1 + p2;
        model.addAttribute("msg", "test 结果为：" + result);
        return "hello";
    }
    @RequestMapping(value = "/commit/test/{p1}/{p2}", method = RequestMethod.PUT)
    public String test4(@PathVariable String p1, @PathVariable String p2, Model model) {
        String result = p1 + p2;
        model.addAttribute("msg", "test 结果为：" + result);
        return "hello";
    }
    @RequestMapping(value = "/commit/test/{p1}/{p2}", method = RequestMethod.PATCH)
    public String test5(@PathVariable String p1, @PathVariable String p2, Model model) {
        String result = p1 + p2;
        model.addAttribute("msg", "test 结果为：" + result);
        return "hello";
    }
    @RequestMapping(value = "/commit/test/{p1}/{p2}", method = RequestMethod.DELETE)
    public String test6(@PathVariable String p1, @PathVariable String p2, Model model) {
        String result = p1 + p2;
        model.addAttribute("msg", "test 结果为：" + result);
        return "hello";
    }
    @RequestMapping(value = "/commit/test/{p1}/{p2}", method = RequestMethod.OPTIONS)
    public String test7(@PathVariable String p1, @PathVariable String p2, Model model) {
        String result = p1 + p2;
        model.addAttribute("msg", "test 结果为：" + result);
        return "hello";
    }
    @RequestMapping(value = "/commit/test/{p1}/{p2}", method = RequestMethod.TRACE)
    public String test8(@PathVariable String p1, @PathVariable String p2, Model model) {
        String result = p1 + p2;
        model.addAttribute("msg", "test 结果为：" + result);
        return "hello";
    }
}
