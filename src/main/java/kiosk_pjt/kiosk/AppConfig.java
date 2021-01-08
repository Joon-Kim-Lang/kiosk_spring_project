package kiosk_pjt.kiosk;

import kiosk_pjt.kiosk.payment.repository.MemoryPaymentRepository;
import kiosk_pjt.kiosk.payment.repository.PaymentRepository;
import kiosk_pjt.kiosk.payment.service.PaymentService;
import kiosk_pjt.kiosk.payment.service.PaymentServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    
    
//  다음강의에 수정
    @Bean
    public PaymentRepository paymentRepository(){
        return new MemoryPaymentRepository();
    }

    @Bean
    public PaymentService paymentService(){
        return new PaymentServiceImpl(paymentRepository());
    }

}
