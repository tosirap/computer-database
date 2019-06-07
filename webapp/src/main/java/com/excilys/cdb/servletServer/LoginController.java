package com.excilys.cdb.servletServer;

import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.view.RedirectView;

import com.excilys.cdb.model.CdbUserDetails;
import com.excilys.cdb.model.User;

@SessionAttributes({ "currentUser" })
@Controller
public class LoginController {

	//on arrive sur la page logggin
	 @GetMapping(value = "/login")
	public String login() {
		return "login";
	}

	//on fail a se log
	 @GetMapping(value = "/loginFailed")
	public String loginError(Model model) {	
		model.addAttribute("error", "login failed");
		return "login";
	}
	
	//on se deco
    @GetMapping(value = "/logout")
    public RedirectView logout(SessionStatus session) {
        SecurityContextHolder.getContext().setAuthentication(null);
        session.setComplete();
    	return new RedirectView("dashboard");
    }
    
    //co reussie
    @PostMapping(value = "/postLogin")
    public RedirectView postLogin(Model model, HttpSession session) {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        validatePrinciple(authentication.getPrincipal());
        User loggedInUser = ((CdbUserDetails) authentication.getPrincipal()).getUserDetails();
        model.addAttribute("currentUser", loggedInUser.getPseudo());
        session.setAttribute("userId", loggedInUser.getId());
        return new RedirectView("dashboard");
    }
    
    private void validatePrinciple(Object principal) {
        if (!(principal instanceof CdbUserDetails)) {
            throw new  IllegalArgumentException("Principal can not be null!");
        }

    }

}
