package kiosk_pjt.kiosk.reservation.service;

import kiosk_pjt.kiosk.Seat.domain.Seat;
import kiosk_pjt.kiosk.reservation.repository.SeatRepository;

import java.util.ArrayList;
import java.util.List;

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
    public List<Integer> currentSeatNum(){
        List<Seat> seats = seatRepository.finaAll();
        List<Integer> currentSeats = new ArrayList<>();
        seats.forEach((v)-> currentSeats.add(v.getSeatNum()));
        return currentSeats;
    }
}
