package de.hhu.pp2.ziegelsteine_online_shop;

import de.hhu.pp2.ziegelsteine_online_shop.controller.ShopController;
import de.hhu.pp2.ziegelsteine_online_shop.model.Produkt;
import de.hhu.pp2.ziegelsteine_online_shop.model.ProduktService;
import de.hhu.pp2.ziegelsteine_online_shop.model.Warenkorb;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ShopController.class)
public class ShopControllerTest {

    @Autowired
    MockMvc mvc;
    @MockitoBean
    ProduktService service;


    @Test
    @DisplayName("Get auf / zeigt alle Produkten")
    void test1() throws Exception{
        mvc.perform(get("/"))
                .andExpect(model().attributeExists("produkten"))
                .andExpect(view().name("shop"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("produkten",service.allProducts()));

    }

    @Test
    @DisplayName("Post aud /add soll erfolgreich ein produkt mit id & menge zu korb addieren")
    void test2() throws Exception{

        long id=123L;
        when(service.findById(id)).thenReturn(new Produkt(id,"Milch", 0.99));

        mvc.perform(post("/add")
                .param("id", "123")
                .param("quantity", "3")
        )
                .andExpect(view().name("redirect:/"))
                .andExpect(status().is3xxRedirection());


    }

    @Test
    @DisplayName("Get /bezahlen mit Warenkorb  zeigt Items mit Gesammt price korrekt")
    void test3() throws Exception{
        Warenkorb korb = new Warenkorb();

        mvc.perform(get("/bezahlen")
                .param("korb1","korb"))
                .andExpect(model().attribute("total",korb.getGesamtPreis()))
                .andExpect(model().attribute("items", korb.getItems()))
                .andExpect(view().name("bezahlen"))
                .andExpect(status().isOk());

    }


    @Test
    @DisplayName("Post /order mit ein Warenkorb zeigt Items mit Gesammt price um zu betätigen")
    void test4() throws Exception{
        Warenkorb korb = new Warenkorb();

        mvc.perform(post("/order")
               .param("korb2","korb"))
                .andExpect(model().attribute("items", korb.getItems()))
                .andExpect((model().attribute("total",korb.getGesamtPreis())))
                .andExpect(status().isOk())
                .andExpect(view().name("order"));



    }


}
