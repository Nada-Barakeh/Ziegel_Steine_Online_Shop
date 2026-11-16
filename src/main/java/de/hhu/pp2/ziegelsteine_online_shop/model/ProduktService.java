package de.hhu.pp2.ziegelsteine_online_shop.model;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProduktService {

    private final List<Produkt> produkte = List.of(
            new Produkt(1L, "Rote Ziegel", 0.50),
            new Produkt(2L,"Weise Ziegel" ,0.75),
            new Produkt(3L,"Gelbe Ziegel" , 0.60),
            new Produkt(4L,"Dach Ziegel" , 1.20),
            new Produkt(5L,"Wand Ziegel" , 0.90)
    );

// Default Konstruktur die WarenKorb am anfang ist leer

    public List<Produkt> allProducts(){
        return Collections.unmodifiableList(produkte);
    }

}
