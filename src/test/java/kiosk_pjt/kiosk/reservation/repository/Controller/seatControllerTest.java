package kiosk_pjt.kiosk.reservation.repository.Controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.scheduling.support.SimpleTriggerContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class seatControllerTest {

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
}
