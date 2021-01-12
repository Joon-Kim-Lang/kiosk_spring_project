package kiosk_pjt.kiosk.reservation.repository;

import kiosk_pjt.kiosk.AppConfig;
import kiosk_pjt.kiosk.Item.domain.Item;
import kiosk_pjt.kiosk.Seat.domain.Seat;
import kiosk_pjt.kiosk.reservation.service.SeatService;
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
public class jdbcTemplateSeatRepositoryTest {

    @Autowired
    SeatRepository seatRepository;
// DB 초기화 시키는 코드니깐 그냥 건들지 말고 가만히 있을 것
//    @Test
//    @Commit
//    @DisplayName("Jpa seat레포 잘 동작하는지 확인")
//    void jpaRepositoryTest(){
//        //given
//        for(int i = 1; i < 21; i++){
//            Seat seat = new Seat(i, null,false);
//            seatRepository.register(seat);
//        }
//
//        //then
//        List<Seat> list = seatRepository.findAll();
//        for (Seat item : list) {
//            System.out.println(item.getSeatNum());
//        }
//        Assertions.assertThat(seatRepository.findAll().size()).isEqualTo(20);
//    }


    @Test
    @DisplayName("seat 저장하는것 확인")
    @Commit
    void saveAndFindTest(){
        Seat seat = new Seat(1, "123456789", true);
        seatRepository.save(seat);
        Seat seat1 = seatRepository.findById(1);
        Assertions.assertThat(seat1.getBarcode()).isEqualTo(seat.getBarcode());
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
