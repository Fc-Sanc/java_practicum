package priv.shuang.jeepracticum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Jonathan
 */
@Controller
public class PageController {

    @RequestMapping(value = {"/component/navigator"}, method = {RequestMethod.GET})
    public String toNavigator() {
        return "component/navigator";
    }

    @RequestMapping(value = {"/index"}, method = {RequestMethod.GET})
    public String toIndex() {
        return "index";
    }

    @RequestMapping(value = {"/login"}, method = {RequestMethod.GET})
    public String toLogin() {
        return "login";
    }

    @RequestMapping(value = {"/register"}, method = {RequestMethod.GET})
    public String toRegister() {
        return "register";
    }

    @RequestMapping(value = {"/details"}, method = {RequestMethod.GET})
    public String toDetails() {
        return "details";
    }

    @RequestMapping(value = {"/viewBooks"}, method = {RequestMethod.GET})
    public String toViewBooks() {
        return "viewBooks";
    }

    @RequestMapping(value = {"/profile"}, method = {RequestMethod.GET})
    public String toProfile() {
        return "profile";
    }

    @RequestMapping(value = {"/borrowedItems"}, method = {RequestMethod.GET})
    public String toBorrowedItems() {
        return "borrowedItems";
    }
}
