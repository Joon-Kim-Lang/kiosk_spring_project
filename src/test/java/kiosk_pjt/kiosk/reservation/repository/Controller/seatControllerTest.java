package kiosk_pjt.kiosk.reservation.repository.Controller;

import kiosk_pjt.kiosk.payment.domain.PaymentInfo;
import kiosk_pjt.kiosk.payment.service.PaymentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.support.SimpleTriggerContext;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class seatControllerTest {

    @Autowired
    private PaymentService paymentService;

    private String barcodeTest = "hou_1_금교석_01071214552_2021-01-16T12:23:09.901186500";
    @Test
    @DisplayName("바코드 잘 작동하는지 만 테스트")
    void isBarcodeAvailable() throws ParseException {
        String[] s = this.barcodeTest.split("_");
        String menuInfo = s[0];
        String time = s[1];
        String currentInfo = s[4];

        Date startTime = startTimebyBarcode(this.barcodeTest);
        System.out.println("startTime.toString() = " + startTime.toString());
        Date currentTime = currentTime();
        System.out.println("currentTime.toString() = " + currentTime.toString());
    }
    private Date currentTime() throws ParseException {
        Date date_now = new Date(System.currentTimeMillis()); // 현재시간을 가져와 Date형으로 저장한다
        return date_now;
    }
    private Date startTimebyBarcode(String barcode) throws ParseException {
        String[] s = barcode.split("_");
        String date = s[4].split("\\.")[0];
        date = date.replace("T", " ");
        System.out.println(date);
        SimpleDateFormat form = new SimpleDateFormat("YYYY-MM-ddHH:mm:ss");
        Date to = form.parse(date);
        return to;
    }

    @Test
    @DisplayName("바코드 바꾸기")
    void chagneBarcode() throws UnsupportedEncodingException {
        String s = "day_7_금교?��_01071214552_2021-01-16T18:44:59.562999100";
        byte[] bytes = s.getBytes(StandardCharsets.UTF_8);
        byte [] euckr = s.getBytes("EUC-KR");

        String s1 = new String(bytes, "UTF-8");
        String s2 = new String(bytes, "EUC-KR");
        System.out.println("s1 = " + s1);
        System.out.println("s2 = " + s2);
    }

    @Test
    @DisplayName("시간 가지고 오기")
    void checkTime(){
        String barcode = "tim_30_금교석_01071214552_2021-01-17T23:59:28.650261300";
        PaymentInfo paymentInfo = paymentService.findPaymentInfo(barcode);
        LocalDateTime paymentTime = paymentInfo.getPaymentTime();
        Date date = Date.from( paymentTime.atZone( ZoneId.systemDefault()).toInstant());
    }

}
