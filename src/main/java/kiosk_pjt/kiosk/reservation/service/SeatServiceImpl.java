package kiosk_pjt.kiosk.reservation.service;

import kiosk_pjt.kiosk.Seat.domain.Seat;
import kiosk_pjt.kiosk.reservation.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
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
        return seatRepository.findById(seatNum);
    }
    @Override
    public Seat findSeat(String barcode) { return seatRepository.findByBarcode(barcode); }
    @Override
    public boolean isAvailableSeat(int seatNum) {
        return seatRepository.findById(seatNum) != null;
    }
    @Override
    public List<Seat> currentSeatsList(){
        List<Seat> seats = seatRepository.findAll();
        return seats;
    }
}
