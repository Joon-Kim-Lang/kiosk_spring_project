package kiosk_pjt.kiosk.Item.service;

import kiosk_pjt.kiosk.Item.domain.Item;
import kiosk_pjt.kiosk.Item.domain.Kind;

import java.util.List;

public interface ItemService {
    List<Item> showAllItems();

    Item showItem(Kind kind);

}
