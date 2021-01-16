package kiosk_pjt.kiosk.reservation.controller;

import kiosk_pjt.kiosk.Seat.domain.Seat;
import kiosk_pjt.kiosk.payment.domain.PaymentInfo;
import kiosk_pjt.kiosk.payment.service.PaymentService;
import kiosk_pjt.kiosk.reservation.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Enumeration;
import java.util.List;

@Controller
@RequestMapping("/seating")
public class SeatController {
    private final SeatService seatService;
    private final PaymentService paymentService;

    @Autowired
    public SeatController(SeatService seatService,PaymentService paymentService) {
        this.seatService = seatService;
        this.paymentService = paymentService;
    }

    @GetMapping("/seatlist")
    public String seatList(Model model){
        List<Seat> seats = seatService.currentSeatsList();
        model.addAttribute("seats", seats);
        return "seatSelectTemplate";
    }
    @GetMapping("/seatInfo")
    public String seatInfo(){
        return "seatInfo";
    }

    @RequestMapping(value="/seatInfoCheck",method = RequestMethod.POST)
    public String seatInfoCheck(@RequestParam(value = "barcode") String barcode){
        PaymentInfo paymentInfo = paymentService.findPaymentInfo(barcode);
        Seat seat = seatService.findSeat(barcode);
        LocalDateTime paymentTime = paymentInfo.getPaymentTime();
        if(paymentInfo==null){
            return "index";
        }else{
            if(seat.getIsoccupied()==true){
                return "index";
            }else{
                return "seatSelectTemplate";
            }
        }
    };
//    @RequestMapping(value="/seatInfoSave",method=RequestMethod.POST)
//    public String seatInfoSave(@RequestParam(value = "barcode")String barcode){
//        PaymentInfo paymentInfo = paymentService.findPaymentInfo(barcode);
//        Seat seat = seatService.findSeat(barcode);
//    }

    @GetMapping("/seatlist/num")
    public String jsonSeatlist(@RequestParam("seatNum")String seatNum){
        System.out.println("seatNum = " + seatNum);
        return seatNum;
    }

}
