package kiosk_pjt.kiosk;
import kiosk_pjt.kiosk.reservation.repository.JdbcTemplateSeatRepository;
import kiosk_pjt.kiosk.Item.repository.ItemRepository;
import kiosk_pjt.kiosk.Item.service.ItemService;
import kiosk_pjt.kiosk.Item.service.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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


}
