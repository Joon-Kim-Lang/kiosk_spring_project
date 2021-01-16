package kiosk_pjt.kiosk.payment.domain;

import kiosk_pjt.kiosk.Item.domain.Item;
import kiosk_pjt.kiosk.Item.domain.Kind;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
@Entity
public class PaymentInfo {
    @Id
    private String barcode;
    private String name;
    private String phoneNumber;
    private LocalDateTime paymentTime;
    private Kind itemKind;

    public PaymentInfo() {

//        this.name = name;
//        this.phoneNumber = phoneNumber;
//        this.paymentTime = paymentTime;
//        this.item  = item;
//        //바코드는 상품종류, 이름,번호,결제시각의 조합
//        this.barcode = item.getKind()+"_"+name+"_"+phoneNumber+"_"+paymentTime;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode() {
        this.barcode = this.getItemKind()+"_"+this.getName()+"_"+this.getPhoneNumber()+"_"+this.getPaymentTime();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDateTime getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime() {
        this.paymentTime = LocalDateTime.now();
    }

    public Kind getItemKind() {
        return itemKind;
    }

    public void setItemKind(Kind itemKind) {
        this.itemKind = itemKind;
    }

}
