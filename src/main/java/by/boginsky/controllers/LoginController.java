//package by.boginsky.controllers;
//
//import by.boginsky.domain.plaintobjects.User;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpSession;
//
//@Controller
//public class LoginController {
//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public ModelAndView login(HttpSession session) {
//        return new ModelAndView("login", "user", new User());
//    }
//
//    @RequestMapping(value = "/registration", method = RequestMethod.GET)
//    public ModelAndView main(HttpSession session) {
//        return new ModelAndView("registration", "user", new User());
//    }
//
////    @RequestMapping(value = "/check-user", method = RequestMethod.POST)
////    public ModelAndView checkUser(@ModelAttribute User user) {
////        return new ModelAndView("main", "user", user);
////    }
////
//}
