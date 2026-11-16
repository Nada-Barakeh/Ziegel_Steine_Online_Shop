package de.hhu.pp2.ziegelsteine_online_shop.controller;

import de.hhu.pp2.ziegelsteine_online_shop.model.Produkt;
import de.hhu.pp2.ziegelsteine_online_shop.model.ProduktService;
import de.hhu.pp2.ziegelsteine_online_shop.model.Warenkorb;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;

@Controller
@SessionAttributes({"korb"})
public class ShopController {

    private final ProduktService service;

    public ShopController(ProduktService service){
        this.service=service;
    }

    @ModelAttribute("korb")
    public Warenkorb init(){
        return new Warenkorb();
    }

    @GetMapping
    public String displayProdukten(Model model){
        model.addAttribute("produkten",service.allProducts());
        return "shop";
    }

    //produkt zu Warenkorp addieren
    @PostMapping("/add")
    public String add(@RequestParam Long id , @RequestParam(defaultValue = "1") int quantity,
                      @ModelAttribute("korb") Warenkorb korb){
     //  Warenkorb korb =(Warenkorb)session.getAttribute("warenkorb");
       //falls den korb leer ist soll ein nuees korb erzeugen
       // if(korb == null){
         //   korb = new Warenkorb();
       // }
        //produkt von ProduktService mit seinem id bringen:
         Produkt prod=service.findById(id);
          if(prod == null){
              return "redirect:/";
          }
        //produkt addieren:
         korb.addItem(service.findById(id), quantity);


        return "redirect:/";

    }


    //Bezahlen seite zeigen:
    @GetMapping("/bezahlen")
    public String bezahlungen(@ModelAttribute("korb") Warenkorb korb, Model model){
        model.addAttribute("total",korb.getGesamtPreis());
        model.addAttribute("items", korb.getItems());
        return "bezahlen";

    }

    // den Auftrag bestätigen
    @PostMapping("/order")
    public String order(@ModelAttribute("korb") Warenkorb korb, Model model){
        model.addAttribute("items",korb.getItems());
        model.addAttribute("total",korb.getGesamtPreis());
        return "order";
    }


    }
