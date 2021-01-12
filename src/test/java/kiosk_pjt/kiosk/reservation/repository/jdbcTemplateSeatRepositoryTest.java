package kiosk_pjt.kiosk.reservation.repository;

import kiosk_pjt.kiosk.AppConfig;
import kiosk_pjt.kiosk.Seat.domain.Seat;
import kiosk_pjt.kiosk.reservation.service.SeatService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

@SpringBootTest
public class jdbcTemplateSeatRepositoryTest {

    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    SeatRepository seatRepository= ac.getBean(SeatRepository.class);

    @Test
    @DisplayName("seat 저장하는것 확인")
    void saveAndFindTest(){
        Seat seat = new Seat(1, "123456789", true);
        seatRepository.save(seat);
        Seat seat1 = seatRepository.findByNum(1);
        Assertions.assertThat(seat1).isEqualTo(seat);
    }

    @Test
    @DisplayName("find all test")
    void findallTest(){
        List<Seat> seats = seatRepository.finaAll();
        for (Seat seat : seats) {
            System.out.println("seat.getSeatNum() = " + seat.getSeatNum());
        }
    }
}
