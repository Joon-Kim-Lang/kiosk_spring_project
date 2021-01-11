package kiosk_pjt.kiosk.Item.repository;

import kiosk_pjt.kiosk.AppConfig;
import kiosk_pjt.kiosk.Item.domain.Item;
import kiosk_pjt.kiosk.Item.domain.Kind;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@Transactional
class JpaItemRepositoryTest {


    @Autowired
    ItemRepository itemRepository;

    @Test
    @Commit
    @DisplayName("Jpa 레포 잘 동작하는지 확인")
    void jpaRepositoryTest(){
//        //given
//        Item item1 = new Item(Kind.hou_1, 1000);
//        Item item2 = new Item(Kind.hou_2,2000);
//        Item item3 = new Item(Kind.day_7,50000);
//        Item item4 = new Item(Kind.day_14,120000);
//        Item item5 = new Item(Kind.tim_30,30000);
//        Item item6 = new Item(Kind.tim_50,50000);
//
//        //when
//        itemRepository.save(item1);
//        itemRepository.save(item2);
//        itemRepository.save(item3);
//        itemRepository.save(item4);
//        itemRepository.save(item5);
//        itemRepository.save(item6);


        //then
        List<Item> list = itemRepository.findAll();
        for (Item item : list) {
            System.out.println("item.getKind() = " + item.getKind());
            System.out.println("item.getPrice() = " + item.getPrice());
        }
        Assertions.assertThat(itemRepository.findAll().size()).isEqualTo(6);
    }
}