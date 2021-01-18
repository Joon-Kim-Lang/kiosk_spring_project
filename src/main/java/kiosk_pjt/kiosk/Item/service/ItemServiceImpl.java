package kiosk_pjt.kiosk.Item.service;

import kiosk_pjt.kiosk.Item.domain.Item;
import kiosk_pjt.kiosk.Item.domain.Kind;
import kiosk_pjt.kiosk.Item.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemServiceImpl implements ItemService{

    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Item> showAllItems() {
        List<Item> itemList = itemRepository.findAll();
        return itemList;
    }
    @Override
    public Item showItem(Kind kind){
        Item item = itemRepository.findById(kind);
        return item;
    }
}
