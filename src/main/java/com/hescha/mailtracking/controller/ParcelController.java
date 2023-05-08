package com.hescha.mailtracking.controller;

import com.hescha.mailtracking.model.Parcel;
import com.hescha.mailtracking.model.ParcelStatus;
import com.hescha.mailtracking.model.Route;
import com.hescha.mailtracking.model.User;
import com.hescha.mailtracking.service.ParcelService;
import com.hescha.mailtracking.service.RouteService;
import com.hescha.mailtracking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.stereotype.Controller;

import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping(ParcelController.CURRENT_ADDRESS)
public class ParcelController {
    public static final String API_PATH = "parcel";
    public static final String CURRENT_ADDRESS = "/" + API_PATH;
    public static final String MESSAGE = "message";
    public static final String THYMELEAF_TEMPLATE_ALL_ITEMS_PAGE = API_PATH;
    public static final String THYMELEAF_TEMPLATE_ONE_ITEM_PAGE = THYMELEAF_TEMPLATE_ALL_ITEMS_PAGE + "-one";
    public static final String THYMELEAF_TEMPLATE_EDIT_PAGE = THYMELEAF_TEMPLATE_ALL_ITEMS_PAGE + "-edit";
    public static final String REDIRECT_TO_ALL_ITEMS = "redirect:" + CURRENT_ADDRESS;

    private final ParcelService service;

    private final UserService userService;
    private final RouteService routeService;

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

    @GetMapping("/search")
    public String search(@RequestParam("id") Long id, Model model) {
        Parcel read = service.read(id);
        model.addAttribute("entity", read);
        return THYMELEAF_TEMPLATE_ONE_ITEM_PAGE;
    }

    @GetMapping(path = {"/edit", "/edit/{id}"})
    public String editPage(Model model, @PathVariable(name = "id", required = false) Long id) {
        if (id == null) {
            model.addAttribute("entity", new Parcel());
        } else {
            model.addAttribute("entity", service.read(id));
        }

        model.addAttribute("users", userService.readAll());
        model.addAttribute("routes", routeService.readAll());

        return THYMELEAF_TEMPLATE_EDIT_PAGE;
    }

    @PostMapping
    public String save(@ModelAttribute Parcel entity, RedirectAttributes ra) {
        if (entity.getId() == null) {
            try {
                Parcel createdEntity = service.create(entity);
                User sender = userService.read(entity.getSender().getId());
                User recipient = userService.read(entity.getRecipient().getId());

                sender.getSendedParcel().add(createdEntity);
                recipient.getReceivedParcel().add(createdEntity);
                userService.update(sender);
                userService.update(recipient);

                ra.addFlashAttribute(MESSAGE, "Creating is successful");
                return REDIRECT_TO_ALL_ITEMS + "/" + createdEntity.getId();
            } catch (Exception e) {
                ra.addFlashAttribute(MESSAGE, "Creating failed");
                e.printStackTrace();
            }
            return REDIRECT_TO_ALL_ITEMS;
        } else {
            try {
                Parcel parcel = service.read(entity.getId());
                User oldSender = userService.read(entity.getSender().getId());
                User oldRecipient = userService.read(entity.getRecipient().getId());
                oldSender.getSendedParcel().remove(parcel);
                oldRecipient.getReceivedParcel().remove(parcel);
                userService.update(oldSender);
                userService.update(oldRecipient);

                Parcel updatedEntity = service.update(entity.getId(), entity);

                User newSender = userService.read(entity.getSender().getId());
                User newRecipient = userService.read(entity.getRecipient().getId());
                newSender.getSendedParcel().add(updatedEntity);
                newRecipient.getReceivedParcel().add(updatedEntity);
                userService.update(newSender);
                userService.update(newRecipient);

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
            Parcel read = service.read(id);
            List<Route> routes = read.getRoutes();
            User recipient = read.getRecipient();
            User sender = read.getSender();
            read.setRoutes(null);
            read.setSender(null);
            read.setRecipient(null);
            for (Route route : routes) {
                route.setParcel(null);
                route.setLocations(null);
                routeService.update(route);
                routeService.delete(route.getId());
            }
            recipient.getReceivedParcel().remove(read);
            sender.getSendedParcel().remove(read);
            service.update(read);
            service.delete(id);
            ra.addFlashAttribute(MESSAGE, "Removing is successful");
        } catch (Exception e) {
            e.printStackTrace();
            ra.addFlashAttribute(MESSAGE, "Removing failed");
        }
        return REDIRECT_TO_ALL_ITEMS;
    }
}
