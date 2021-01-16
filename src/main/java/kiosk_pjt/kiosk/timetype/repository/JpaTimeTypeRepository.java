package kiosk_pjt.kiosk.timetype.repository;

import kiosk_pjt.kiosk.Item.domain.Item;
import kiosk_pjt.kiosk.payment.domain.PaymentInfo;
import kiosk_pjt.kiosk.timetype.domain.TimeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Component
public class JpaTimeTypeRepository implements TimeTypeRepository{

    private final EntityManager em;

    @Autowired
    public JpaTimeTypeRepository(EntityManager em) {
        this.em = em;
    }

    @Override@Transactional
    public TimeType save(TimeType timeType) {
        em.persist(timeType);
        return timeType;
    }

    @Override@Transactional
    public void remove(TimeType timeType) {
        em.remove(timeType);
    }

    @Override
    public List<TimeType> findAll() {
        return em.createQuery("select t from TimeType t", TimeType.class)
                .getResultList();
    }

    @Override
    public TimeType findById(String barcode) {
        return em.find(TimeType.class, barcode);
    }
}
