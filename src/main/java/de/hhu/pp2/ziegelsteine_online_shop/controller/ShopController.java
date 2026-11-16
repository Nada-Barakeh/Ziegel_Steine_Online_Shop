package de.hhu.pp2.ziegelsteine_online_shop.controller;

import de.hhu.pp2.ziegelsteine_online_shop.model.ProduktService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShopController {

    private final ProduktService service;

    public ShopController(ProduktService service){
        this.service=service;
    }

    @GetMapping
    public String displayProdukten(Model model){
        model.addAttribute("produkten",service.allProducts());
        return "shop";
    }





    }
