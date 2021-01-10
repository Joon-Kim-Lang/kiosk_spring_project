package kiosk_pjt.kiosk.Item.repository;

import kiosk_pjt.kiosk.Item.domain.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

public class JpaItemRepository  implements ItemRepository{

    private final EntityManager em;

    public JpaItemRepository(EntityManager em) {
        this.em = em;
    }


    @Override@Transactional
    public Item save(Item item) {
        em.persist(item);
        return item;
    }

    @Override
    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
