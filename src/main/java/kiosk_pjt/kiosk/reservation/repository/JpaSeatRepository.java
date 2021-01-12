package kiosk_pjt.kiosk.reservation.repository;

import kiosk_pjt.kiosk.Item.domain.Item;
import kiosk_pjt.kiosk.Seat.domain.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Component
public class JpaSeatRepository implements SeatRepository{

    private final EntityManager em;
    @Autowired
    public JpaSeatRepository(EntityManager em) {
        this.em = em;
    }

    @Override@Transactional
    public Seat register(Seat seat) {
        em.persist(seat);
        return seat;
    }

    @Override@Transactional
    public void save(Seat seat) {
        int seatInt = seat.getSeatNum();
        String newBarcode = seat.getBarcode();

        Seat dbSeat = this.findById(seatInt);
        dbSeat.setBarcode(newBarcode);
        dbSeat.setIsoccupied(true);
    }

    @Override@Transactional
    public void remove(Seat seat) {

        int seatInt = seat.getSeatNum();

        Seat dbSeat = this.findById(seatInt);
        dbSeat.setBarcode(null);
        dbSeat.setIsoccupied(false);
    }

    @Override
    public List<Seat> findAll() {
        return em.createQuery("select s from Seat s", Seat.class)
                .getResultList();
    }

    @Override
    public Seat findById(int seatNum) {
        return em.find(Seat.class, seatNum);
    }
}
