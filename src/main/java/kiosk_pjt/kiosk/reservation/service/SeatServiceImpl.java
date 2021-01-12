package kiosk_pjt.kiosk.reservation.service;

import kiosk_pjt.kiosk.Seat.domain.Seat;
import kiosk_pjt.kiosk.reservation.repository.SeatRepository;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class SeatServiceImpl implements SeatService {
    private final SeatRepository seatRepository;
    public SeatServiceImpl(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    @Override
    public void join(Seat seat) {
        seatRepository.save(seat);
    }
    @Override
    public void leave(Seat seat) {
        seatRepository.remove(seat);
    }
    @Override
    public Seat findSeat(int seatNum) {
        return seatRepository.findByNum(seatNum);
    }
    @Override
    public boolean isAvailableSeat(int seatNum) {
        return seatRepository.findByNum(seatNum) != null;
    }
    @Override
    public List<Integer> currentSeatsList(){
        List<Seat> seats = seatRepository.finaAll();
        List<Integer> currentSeats = new ArrayList<>();
        for (int i = 1; i < seatRepository.MaxSeat+1; i++) {
            currentSeats.add(i);
        }
        seats.forEach((v)->currentSeats.remove(v.getSeatNum()));
        return currentSeats;
    }
}
