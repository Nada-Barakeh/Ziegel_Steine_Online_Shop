package de.hhu.pp2.ziegelsteine_online_shop.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Warenkorb {
    // unsere korb ist am Anfang leer
    private List<WarenKorbItems> items = new ArrayList<>();


    public void addItem(Produkt produkt, int menge){
        // wenn produkt in List vorhanden ist: ändere die menge: item.getMenge()+menge
        for(WarenKorbItems item: items){
            if(item.getProdukt().getId().equals(produkt.getId())){
                item.setMenge(item.getMenge()+menge);
                return;
            }
        }
        //falls produkt nicht in der List: addiere (produkt,menge) zu List
        items.add(new WarenKorbItems(produkt,menge));
    }


    public void removeItem(Long produktId) {
        //nur wenn produkt in list ist: remove produkt
        for (int i = 0; i< items.size(); i++) {
            if (items.get(i).getProdukt().getId().equals(produktId)) {
                items.remove(i);
                return;
            }

        }
    }


    public List<WarenKorbItems> getItems() {

        return Collections.unmodifiableList(items);
    }


    public double getGesamtPreis(){
        return items.stream()  //Stream<WarenKorbItems>
                .mapToDouble(WarenKorbItems::getTotalPrice)  //Stream<Double>
                .sum();
    }


    public boolean istLeer(){
        return items.isEmpty();
    }



}




