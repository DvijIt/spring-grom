package com.lesson1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Test {

    @RequestMapping(method = RequestMethod.GET,  value = "/hi", produces = "text/plain")
    public @ResponseBody String test() {
        return  "test";
    }

    @RequestMapping(method = RequestMethod.GET,  value = "/test", produces = "text/plain")
    public @ResponseBody String test2() {
        return  "welcome";
    }
}
