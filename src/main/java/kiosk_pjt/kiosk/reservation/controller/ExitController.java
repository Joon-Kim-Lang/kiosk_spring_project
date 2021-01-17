package kiosk_pjt.kiosk.reservation.controller;


import kiosk_pjt.kiosk.Seat.domain.Seat;
import kiosk_pjt.kiosk.reservation.service.SeatService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ExitController {

    private final SeatService seatService;

    public ExitController(SeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping("/exitInfo")
    public String exitInfo(){
        return "barcodeInfoForExit";
    }

    @PostMapping("/exit")
    public  String exit(@RequestParam("barcode")String barcode){
        Seat seat = null;
        try{
             seat= seatService.findSeat(barcode);
        }catch(Exception e){
            return "redirect:/";
        }
        seatService.leave(seat);
        return "redirect:/";
    }
}
