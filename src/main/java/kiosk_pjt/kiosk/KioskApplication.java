package kiosk_pjt.kiosk;

import kiosk_pjt.kiosk.Item.domain.Item;
import kiosk_pjt.kiosk.Item.domain.Kind;
import kiosk_pjt.kiosk.Item.repository.ItemRepository;
import kiosk_pjt.kiosk.Seat.domain.Seat;
import kiosk_pjt.kiosk.reservation.repository.SeatRepository;
import kiosk_pjt.kiosk.timetype.repository.JpaTimeTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class KioskApplication {
	public static void main(String[] args) {
		SpringApplication.run(KioskApplication.class, args);
	}
}
