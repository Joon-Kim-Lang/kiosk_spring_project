package kiosk_pjt.kiosk.payment.service;

import kiosk_pjt.kiosk.Item.domain.Item;
import kiosk_pjt.kiosk.Item.domain.Kind;
import kiosk_pjt.kiosk.payment.domain.PaymentInfo;
import kiosk_pjt.kiosk.payment.repository.MemoryPaymentRepository;
import kiosk_pjt.kiosk.payment.repository.PaymentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PaymentServiceImplTest {

    PaymentRepository paymentRepository = new MemoryPaymentRepository();
    PaymentService paymentService = new PaymentServiceImpl(paymentRepository );

    @Test
    @DisplayName("저장소 기록테스트")
    void joinTest(){
        //given
        Item item = new Item(Kind.hou_1, 2000);
        PaymentInfo paymentInfo= new PaymentInfo("김형준", "01038067268", LocalDateTime.now(), item);
        //when
        String barcode = paymentService.join(paymentInfo);
        //then
        assertThat(barcode).isEqualTo(paymentInfo.getBarcode());
    }

    @Test
    @DisplayName("저장소 출력테스트")
    void showListTest(){
        //given
        Item item1 = new Item(Kind.hou_1, 2000);
        Item item2 = new Item(Kind.hou_2, 3000);
        PaymentInfo paymentInfo1= new PaymentInfo("김형준", "01038067268", LocalDateTime.now(), item1);
        PaymentInfo paymentInfo2= new PaymentInfo("금교석", "01071214552", LocalDateTime.now(), item2);
        //when
        String barcode1 = paymentService.join(paymentInfo1);
        String barcode2 = paymentService.join(paymentInfo2);
        //then
        List<PaymentInfo> paymentInfos = paymentService.showPayments();
        for (PaymentInfo paymentInfo : paymentInfos) {
            System.out.println("paymentInfo = " + paymentInfo.getBarcode());
        }
    }
    @Test
    @DisplayName("바코드로 payment 찾기")
    void findByBarcode(){
        Item item1 = new Item(Kind.hou_1, 2000);
        Item item2 = new Item(Kind.hou_2, 3000);
        PaymentInfo paymentInfo1= new PaymentInfo("김형준", "01038067268", LocalDateTime.now(), item1);
        String barcode1 = paymentService.join(paymentInfo1);
        PaymentInfo findPaymentInfo = paymentService.findPaymentInfo(barcode1);
        Assertions.assertThat(findPaymentInfo).isEqualTo(paymentInfo1);

    }

}