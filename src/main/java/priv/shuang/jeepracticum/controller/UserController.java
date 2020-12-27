package priv.shuang.jeepracticum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import priv.shuang.jeepracticum.model.User;
import priv.shuang.jeepracticum.service.UserService;
import priv.shuang.jeepracticum.utils.JsonGenerator;

import javax.servlet.http.HttpSession;

/**
 * @author Jonathan
 */
@Controller
public class UserController {
    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseBody
    @RequestMapping(value = {"/getUser"}, method = {RequestMethod.GET})
    public String getUser(HttpSession session) {
        Integer userId = (Integer) session.getAttribute("user_id");
        User    user   = userService.getUserById(userId);
        return JsonGenerator.toJson(user);
    }

    @ResponseBody
    @RequestMapping(value = {"/getUsername"}, method = {RequestMethod.GET})
    public String getUsername(HttpSession session) {
        Integer userId = (Integer) session.getAttribute("user_id");
        return userService.getUsername(userId);
    }

    @ResponseBody
    @RequestMapping(value = "/updateUser", method = {RequestMethod.POST})
    public boolean updateUser(HttpSession session, Integer age, String contactInfo, String gender) {
        Integer userId = (Integer) session.getAttribute("user_id");
        if (userId != null) {
            return userService.updateUser(userId, age, contactInfo, gender) != null;
        }
        return false;
    }

    @ResponseBody
    @RequestMapping(value = "/updatePassword", method = {RequestMethod.POST})
    public boolean updatePassword(HttpSession session, String password) {
        Integer userId = (Integer) session.getAttribute("user_id");
        if (userId != null) {
            return userService.updatePassword(userId, password) != null;
        }
        return false;
    }

    @ResponseBody
    @RequestMapping(value = {"/doRegister"}, method = {RequestMethod.POST})
    public boolean register(String username, String password, String age, String contactInfo, String gender) {
        if (!userService.exists(username)) {
            Integer res = userService.register(username, password, age, contactInfo, gender);
            return res != null && res > 0;
        }
        return false;
    }
}
