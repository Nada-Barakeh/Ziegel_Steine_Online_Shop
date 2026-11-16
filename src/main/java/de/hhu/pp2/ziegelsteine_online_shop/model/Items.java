package de.hhu.pp2.ziegelsteine_online_shop.model;

public class Items {
//falls den Kunde mehrmals den produkt verkauft

    private Produkt produkt;
    private int quantity;

    public Items(Produkt produkt , int quantity){
        this.produkt=produkt;
        this.quantity=quantity;
    }

    public double getTotalPrice(){
        return produkt.getPrice()*quantity;
    }

    public Produkt getProdukt() {
        return produkt;
    }

    public void setProdukt(Produkt produkt) {
        this.produkt = produkt;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
