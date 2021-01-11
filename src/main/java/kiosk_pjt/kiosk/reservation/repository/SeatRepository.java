package kiosk_pjt.kiosk.reservation.repository;
import kiosk_pjt.kiosk.Seat.domain.Seat;

import java.util.List;

public interface SeatRepository {
    int MaxSeat = 20;
    void save(Seat seat);
    void remove(Seat seat);
    List<Seat> finaAll();
    Seat findByNum(int num);
}
