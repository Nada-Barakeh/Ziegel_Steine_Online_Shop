package de.hhu.pp2.ziegelsteine_online_shop.model;

public class WarenKorbItems {
//falls den Kunde mehrmals den produkt verkauft

    private Produkt produkt;
    private int menge;

    public WarenKorbItems(Produkt produkt , int menge){
        this.produkt=produkt;
        this.menge=menge;
    }

    public double getTotalPrice(){
        return produkt.getPrice()*menge;
    }

    public Produkt getProdukt() {
        return produkt;
    }

    public void setProdukt(Produkt produkt) {
        this.produkt = produkt;
    }


    public int getMenge() {
        return menge;
    }

    public void setMenge(int menge) {
        this.menge = menge;
    }
}
