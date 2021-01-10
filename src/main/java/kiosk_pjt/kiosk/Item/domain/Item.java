package kiosk_pjt.kiosk.Item.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
public class Item {

    @Id
    @Enumerated(EnumType.STRING)
    private Kind kind;
    private int price;

    public Item(Kind kind, int price) {
        this.kind = kind;
        this.price = price;
    }

    public Item() {

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
