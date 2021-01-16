package kiosk_pjt.kiosk.reservation.repository;
import kiosk_pjt.kiosk.Seat.domain.Seat;

import java.util.List;

public interface SeatRepository {
    int MaxSeat = 20;
    Seat register(Seat seat);
    void save(Seat seat);
    void remove(Seat seat);
    List<Seat> findAll();
    Seat findById(int seatNum);
    Seat findByBarcode(String barcode);
}
