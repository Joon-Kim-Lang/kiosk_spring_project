package kiosk_pjt.kiosk.reservation.repository;

import kiosk_pjt.kiosk.Seat.domain.Seat;
import kiosk_pjt.kiosk.reservation.repository.SeatRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MemorySeatRepository implements SeatRepository {
    Map<Integer, Seat> store = new HashMap<>();

    @Override
    public void save(Seat seat) {
        if(store.size()>SeatRepository.MaxSeat)return;
        store.put(seat.getSeatNum(), seat);
    }

    @Override
    public void remove(Seat seat) {
        if (store.isEmpty())return;
        store.remove(seat.getSeatNum());
    }

    @Override
    public ArrayList finaAll() {
        return new ArrayList(store.values());
    }

    @Override
    public Seat findByNum(int num) {
        return store.get(num);
    }


}
