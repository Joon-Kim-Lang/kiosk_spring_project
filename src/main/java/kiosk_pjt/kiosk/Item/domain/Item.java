package kiosk_pjt.kiosk.Item.domain;

public class Item {

    private Kind kind;
    private int price;

    public Item(Kind kind, int price) {
        this.kind = kind;
        this.price = price;
    }

    public Kind getKind() {
        return kind;
    }

    public void setKind(Kind kind) {
        this.kind = kind;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
