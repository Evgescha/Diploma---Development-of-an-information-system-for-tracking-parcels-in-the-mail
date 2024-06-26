package com.hescha.mailtracking.controller;

import com.hescha.mailtracking.model.User;
import com.hescha.mailtracking.service.LocationService;
import com.hescha.mailtracking.service.ParcelService;
import com.hescha.mailtracking.service.RoleService;
import com.hescha.mailtracking.service.SecurityService;
import com.hescha.mailtracking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.stereotype.Controller;


@Controller
@RequiredArgsConstructor
@RequestMapping(UserController.CURRENT_ADDRESS)
public class UserController {
    public static final String API_PATH = "user";
    public static final String CURRENT_ADDRESS = "/" + API_PATH;
    public static final String MESSAGE = "message";
    public static final String THYMELEAF_TEMPLATE_ALL_ITEMS_PAGE = API_PATH;
    public static final String THYMELEAF_TEMPLATE_ONE_ITEM_PAGE = THYMELEAF_TEMPLATE_ALL_ITEMS_PAGE + "-one";
    public static final String THYMELEAF_TEMPLATE_EDIT_PAGE = THYMELEAF_TEMPLATE_ALL_ITEMS_PAGE + "-edit";
    public static final String REDIRECT_TO_ALL_ITEMS = "redirect:" + CURRENT_ADDRESS;

    private final UserService service;
    private final LocationService locationService;
    private final RoleService roleService;
    private final ParcelService parcelService;
    private final SecurityService securityService;

    @GetMapping
    public String readAll(Model model) {
        model.addAttribute("list", service.readAll());
        return THYMELEAF_TEMPLATE_ALL_ITEMS_PAGE;
    }

    @GetMapping("/{id}")
    public String read(@PathVariable("id") Long id, Model model) {
        model.addAttribute("entity", service.read(id));
        return THYMELEAF_TEMPLATE_ONE_ITEM_PAGE;
    }

    @GetMapping("/profile")
    public String profile() {
        return "redirect:/user/" + securityService.getLoggedIn().getId();
    }

    @GetMapping(path = {"/edit", "/edit/{id}"})
    public String editPage(Model model, @PathVariable(name = "id", required = false) Long id) {
        if (id == null) {
            model.addAttribute("entity", new User());
        } else {
            model.addAttribute("entity", service.read(id));
        }

        model.addAttribute("location_list", locationService.readAll());
        model.addAttribute("role_list", roleService.readAll());
        model.addAttribute("parcel_list", parcelService.readAll());

        return THYMELEAF_TEMPLATE_EDIT_PAGE;
    }

    @PostMapping
    public String save(@ModelAttribute User entity, RedirectAttributes ra) {
        if (entity.getId() == null) {
            try {
                User createdEntity = service.create(entity);
                ra.addFlashAttribute(MESSAGE, "Creating is successful");
                return REDIRECT_TO_ALL_ITEMS + "/" + createdEntity.getId();
            } catch (Exception e) {
                ra.addFlashAttribute(MESSAGE, "Creating failed");
                e.printStackTrace();
            }
            return REDIRECT_TO_ALL_ITEMS;
        } else {
            try {
                service.update(entity.getId(), entity);
                ra.addFlashAttribute(MESSAGE, "Editing is successful");
            } catch (Exception e) {
                e.printStackTrace();
                ra.addFlashAttribute(MESSAGE, "Editing failed");
            }
            return REDIRECT_TO_ALL_ITEMS + "/" + entity.getId();
        }
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        try {
            service.delete(id);
            ra.addFlashAttribute(MESSAGE, "Removing is successful");
        } catch (Exception e) {
            e.printStackTrace();
            ra.addFlashAttribute(MESSAGE, "Removing failed");
        }
        return REDIRECT_TO_ALL_ITEMS;
    }
}
