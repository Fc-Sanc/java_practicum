package priv.shuang.jeepracticum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import priv.shuang.jeepracticum.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Jonathan
 */
@CrossOrigin
@Controller
public class LoginController {

    final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @ResponseBody
    @RequestMapping(value = {"/doLogin"}, method = {RequestMethod.POST})
    public Integer login(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Integer userId = userService.login(
                request.getParameter("username"),
                request.getParameter("password"));
        if (userId != null && userId != -1) {
            session.setAttribute("user_id", userId);
            return 0;
        }
        return 1;
    }

    @RequestMapping(value = {"/doLogout"}, method = {RequestMethod.GET})
    public String logout(HttpSession session) {
        session.removeAttribute("user_id");

        return "index";
    }

    @ResponseBody
    @RequestMapping(value = {"/isLoggedIn"}, method = {RequestMethod.GET})
    public Integer isLoggedIn(HttpSession session) {
        Integer userId = (Integer) session.getAttribute("user_id");
        if (userId != null) {
            return userId;
        }
        return -1;
    }

    @ResponseBody
    @RequestMapping(value = {"/loginTest"}, method = RequestMethod.GET)
    public String loginTest(HttpSession session) {
        session.setAttribute("user_id", 13);
        return String.valueOf(session.getAttribute("user_id"));
    }
}
