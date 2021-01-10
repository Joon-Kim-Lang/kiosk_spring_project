package kiosk_pjt.kiosk;

import kiosk_pjt.kiosk.Item.repository.ItemRepository;
import kiosk_pjt.kiosk.Item.repository.JpaItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

@Configuration
public class AppConfig {


    private final EntityManager em;

    public AppConfig( EntityManager em) {
        this.em = em;
    }

    @Bean
    public ItemRepository itemRepository(){
        return new JpaItemRepository(em);
    }
}
