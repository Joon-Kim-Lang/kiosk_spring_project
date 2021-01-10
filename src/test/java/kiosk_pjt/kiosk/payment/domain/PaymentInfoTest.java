package kiosk_pjt.kiosk.payment.domain;

import kiosk_pjt.kiosk.Item.domain.Item;
import kiosk_pjt.kiosk.Item.domain.Kind;
import org.apache.tomcat.jni.Time;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PaymentInfoTest {

    @Test
    @DisplayName("바코드명 잘 들어가는지 테스트")
    void barcodeNameTest(){
        //given
        Item item = new Item(Kind.hou_1, 2000);
        PaymentInfo paymentInfo= new PaymentInfo("김형준", "01038067268", LocalDateTime.now(), item);
        //then
        System.out.println("paymentInfo.getBarcode() = " + paymentInfo.getBarcode());
    }

}