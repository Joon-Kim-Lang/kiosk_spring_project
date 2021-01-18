package kiosk_pjt.kiosk.payment.repository;

import kiosk_pjt.kiosk.Item.domain.Item;
import kiosk_pjt.kiosk.payment.domain.PaymentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Component
public class JpaPaymentRepository implements PaymentRepository{

    private final EntityManager em;
    @Autowired
    public JpaPaymentRepository(EntityManager em) {
        this.em = em;
    }

    @Override@Transactional
    public PaymentInfo save(PaymentInfo paymentInfo) {
        em.persist(paymentInfo);
        return paymentInfo;
    }

    @Override
    public List<PaymentInfo> findAll() {
        return em.createQuery("select p from PaymentInfo p", PaymentInfo.class)
                .getResultList();
    }

    @Override
    public PaymentInfo findByBarcode(String barcode) {
        return em.find(PaymentInfo.class, barcode);
    }
}
