package kiosk_pjt.kiosk.reservation.repository;
import kiosk_pjt.kiosk.AppConfig;
import kiosk_pjt.kiosk.Seat.domain.Seat;
import kiosk_pjt.kiosk.payment.service.PaymentService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.annotation.Commit;

import java.util.List;

@SpringBootTest
public class JpaSeatRepositoryTest {

    @Autowired
    SeatRepository seatRepository;

    @Test
    @Commit
    @DisplayName("barcode로 seat 정보 가지고 오기")
    void findbyBarcode(){
        Seat s = seatRepository.findByBarcode("123456789");
        System.out.println("s.getSeatNum() = " + s.getSeatNum());
    }

    @Test
    @Commit
    @DisplayName("barcode로 seat 정보 가지고 오기")
    void findbyId(){
        Seat s = seatRepository.findById(1);
        System.out.println("s.getSeatNum() = " + s.getBarcode());
    }
    @Test
    @DisplayName("find all test")
    void findAllTest(){
        List<Seat> seats = seatRepository.findAll();
        for (Seat seat : seats) {
            System.out.println("seat.getSeatNum() = " + seat.getSeatNum());
        }
    }
}
