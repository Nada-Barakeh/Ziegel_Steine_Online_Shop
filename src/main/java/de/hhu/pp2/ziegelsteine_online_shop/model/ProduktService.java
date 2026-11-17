package de.hhu.pp2.ziegelsteine_online_shop.model;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.*;

@Service
public class ProduktService {

    private final List<Produkt> produkte = List.of(
            new Produkt(1L, "Rote Ziegel", 0.50),
            new Produkt(2L,"Weise Ziegel" ,0.75),
            new Produkt(3L,"Gelbe Ziegel" , 0.60),
            new Produkt(4L,"Dach Ziegel" , 1.20),
            new Produkt(5L,"Wand Ziegel" , 0.90)
    );


    public List<Produkt> allProducts(){

        return Collections.unmodifiableList(produkte);
    }

    public Produkt findById(Long id){
        return produkte.stream().filter(p->p.getId().equals(id))
                .findFirst()
                .orElse(null); // falls kein Produkt vorhanden gibt null zuruck


    }

}
