package kiosk_pjt.kiosk.Item.repository;

import kiosk_pjt.kiosk.Item.domain.Item;
import kiosk_pjt.kiosk.payment.domain.PaymentInfo;

import java.util.List;

public interface ItemRepository {

    Item save(Item item);

    List<Item> findAll();
}
