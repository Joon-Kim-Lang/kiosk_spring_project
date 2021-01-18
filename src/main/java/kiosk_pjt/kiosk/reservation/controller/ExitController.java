package kiosk_pjt.kiosk.reservation.controller;


import kiosk_pjt.kiosk.Seat.domain.Seat;
import kiosk_pjt.kiosk.reservation.service.SeatService;
import kiosk_pjt.kiosk.timetype.service.TimeTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ExitController {

    private final SeatService seatService;
    private final TimeTypeService timeTypeService;

    public ExitController(SeatService seatService, TimeTypeService timeTypeService) {
        this.seatService = seatService;
        this.timeTypeService = timeTypeService;
    }

    @GetMapping("/exitInfo")
    public String exitInfo(){
        return "barcodeInfoForExit";
    }

    @PostMapping("/exit")
    public  String exit(@RequestParam("barcode")String barcode){
        Seat seat = null;
        barcode = barcode.trim();
        try{
             seat= seatService.findSeat(barcode);
        }catch(Exception e){
            System.out.println("error = " + e);
            return "redirect:/";
        }
        String[] s = barcode.split("_");
        String menuInfo = s[0];
        seatService.leave(seat);
        if(menuInfo.equals("tim")) {
            timeTypeService.setRemainTime(barcode);
        }
        return "redirect:/";
    }
}
