package kiosk_pjt.kiosk.Item.service;

import kiosk_pjt.kiosk.Item.domain.Item;
import kiosk_pjt.kiosk.Item.domain.Kind;
import kiosk_pjt.kiosk.Item.repository.ItemRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ItemServiceImplTest {


    @Autowired
    ItemService itemService;

    @Test
    void ItemServiceTest(){
        Item item = itemService.showItem(Kind.hou_1);
        Assertions.assertThat(item.getPrice()).isEqualTo(1000);
    }

}