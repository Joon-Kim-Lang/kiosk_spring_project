package kiosk_pjt.kiosk;

import kiosk_pjt.kiosk.reservation.repository.JdbcTemplateSeatRepository;
import kiosk_pjt.kiosk.payment.repository.MemoryPaymentRepository;
import kiosk_pjt.kiosk.payment.repository.PaymentRepository;
import kiosk_pjt.kiosk.reservation.repository.SeatRepository;
import kiosk_pjt.kiosk.payment.service.PaymentService;
import kiosk_pjt.kiosk.payment.service.PaymentServiceImpl;
import kiosk_pjt.kiosk.reservation.service.SeatService;
import kiosk_pjt.kiosk.reservation.service.SeatServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;

@Configuration
@ComponentScan
public class AppConfig {

//    private final DataSource dataSource;
//    public AppConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
//    @Bean
//    public PaymentRepository paymentRepository(){
//        return new MemoryPaymentRepository();
//    }
//    @Bean
//    public PaymentService paymentService(){
//        return new PaymentServiceImpl(paymentRepository());
//    }
//    @Bean
//    public SeatService seatService(){
//        return new SeatServiceImpl(seatRepository());
//    }
//    @Bean
//    public SeatRepository seatRepository(){
//        return new JdbcTemplateSeatRepository(dataSource);
//    }

}
