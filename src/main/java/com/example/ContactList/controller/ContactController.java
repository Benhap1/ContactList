package com.example.ContactList.controller;

import com.example.ContactList.Contact;
import com.example.ContactList.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/contacts")
public class ContactController {

    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public String listContacts(Model model) {
        model.addAttribute("contacts", contactService.getAllContacts());
        return "contacts/list";
    }

    @GetMapping("/form")
    public String showForm(@RequestParam(required = false) Long id, Model model) {
        Contact contact = id != null ? contactService.getContactById(id) : new Contact();
        model.addAttribute("contact", contact);
        return "contacts/form";
    }

    @PostMapping("/save")
    public RedirectView saveContact(@ModelAttribute Contact contact) {
        contactService.saveContact(contact);
        return new RedirectView("/contacts");
    }

    @GetMapping("/delete/{id}")
    public RedirectView deleteContact(@PathVariable Long id) {
        contactService.deleteContactById(id);
        return new RedirectView("/contacts");
    }
}
