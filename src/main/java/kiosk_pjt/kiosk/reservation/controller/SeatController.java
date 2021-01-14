package kiosk_pjt.kiosk.reservation.controller;

import kiosk_pjt.kiosk.Seat.domain.Seat;
import kiosk_pjt.kiosk.reservation.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/seating")
public class SeatController {
    private final SeatService seatService;
    @Autowired
    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping("/seatlist")
    public String seatList(Model model){
        List<Seat> seats = seatService.currentSeatsList();
        model.addAttribute("seats", seats);
        return "seatSelectTemplate";
    }

    @GetMapping("/seatlist/num")
    @ResponseBody
    public String jsonSeatlist(@RequestParam("seatNum")String seatNum){
        System.out.println("seatNum = " + seatNum);
        return seatNum;
    }
}
