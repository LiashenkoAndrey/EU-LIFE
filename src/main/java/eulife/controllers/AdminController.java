package eulife.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admins")
public class AdminController {

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/page")
    public String get() {
        return "admin_page";
    }
}
