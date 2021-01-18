package kiosk_pjt.kiosk.Item.repository;

import kiosk_pjt.kiosk.Item.domain.Item;
import kiosk_pjt.kiosk.Item.domain.Kind;
import kiosk_pjt.kiosk.payment.domain.PaymentInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository {

    Item save(Item item);

    List<Item> findAll();

    Item findById(Kind kind);
}
