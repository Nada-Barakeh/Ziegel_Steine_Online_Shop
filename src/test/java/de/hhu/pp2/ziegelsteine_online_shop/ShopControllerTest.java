package de.hhu.pp2.ziegelsteine_online_shop;

import de.hhu.pp2.ziegelsteine_online_shop.controller.ShopController;
import de.hhu.pp2.ziegelsteine_online_shop.model.ProduktService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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



}
