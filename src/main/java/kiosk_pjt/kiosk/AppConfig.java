package kiosk_pjt.kiosk;

<<<<<<< Updated upstream
import kiosk_pjt.kiosk.Item.repository.ItemRepository;
import kiosk_pjt.kiosk.Item.service.ItemService;
import kiosk_pjt.kiosk.Item.service.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
=======
import kiosk_pjt.kiosk.payment.repository.MemoryPaymentRepository;
import kiosk_pjt.kiosk.reservation.repository.MemorySeatRepository;
import kiosk_pjt.kiosk.payment.repository.PaymentRepository;
import kiosk_pjt.kiosk.reservation.repository.SeatRepository;
import kiosk_pjt.kiosk.payment.service.PaymentService;
import kiosk_pjt.kiosk.payment.service.PaymentServiceImpl;
import kiosk_pjt.kiosk.reservation.service.SeatService;
import kiosk_pjt.kiosk.reservation.service.SeatServiceImpl;
>>>>>>> Stashed changes
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class AppConfig {
<<<<<<< Updated upstream
=======
//  다음강의에 수정
    @Bean
    public PaymentRepository paymentRepository(){
        return new MemoryPaymentRepository();
    }

    @Bean
    public PaymentService paymentService(){
        return new PaymentServiceImpl(paymentRepository());
    }
>>>>>>> Stashed changes

    @Bean
    public SeatService seatService(){
        return new SeatServiceImpl(seatRepository());
    }
    @Bean
    public SeatRepository seatRepository(){
        return new MemorySeatRepository();
    }

}
