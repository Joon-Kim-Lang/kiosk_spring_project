package kiosk_pjt.kiosk.reservation.controller;

import kiosk_pjt.kiosk.Seat.domain.Seat;
import kiosk_pjt.kiosk.payment.domain.PaymentInfo;
import kiosk_pjt.kiosk.payment.service.PaymentService;
import kiosk_pjt.kiosk.reservation.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
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

    @GetMapping("/seatlist/num")
    public String jsonSeatlist(@RequestParam("seatNum")String seatNum){
        System.out.println("seatNum = " + seatNum);
        return seatNum;
    }

    @GetMapping("/barcodeInfo")
    public String seatInfo(){
        return "barcodeInfo";
    }

    @RequestMapping(value="/barcodeInfoCheck",method = RequestMethod.POST)
    public String seatInfoCheck(@RequestParam(value = "barcode") String barcode) throws ParseException {
        PaymentInfo paymentInfo;Seat seat;
        paymentInfo = paymentService.findPaymentInfo(barcode);
        //바코드가 없어
        if(paymentInfo==null){ return "index"; }
        //바코드가 있어
        if(isBarcodeAvailable(barcode)==true){

            return "success";
        }else{
            return "index";
        }
    };

    private boolean isBarcodeAvailable(String barcode) throws ParseException {
        String[] s = barcode.split("_");
        String menuInfo = s[0];
        int time = Integer.parseInt(s[1]);

        Date startTime = startTimebyBarcode(barcode);
        Date currentTime = currentTime();
        Date endTime;

        Calendar cal = Calendar.getInstance();
        cal.setTime(startTime);

        if(menuInfo.equals("hou")){
            cal.add(Calendar.HOUR,time);
        }else if(menuInfo.equals("time")){

        }else if(menuInfo.equals("day")){
            cal.add(Calendar.DATE,time);
        }
        endTime = cal.getTime();
        if(startTime.getTime()<currentTime.getTime() && currentTime.getTime()<endTime.getTime()){ return true; }
        else{return false;}
    }
    private Date currentTime() throws ParseException {
        Date date_now = new Date(System.currentTimeMillis()); // 현재시간을 가져와 Date형으로 저장한다
        return date_now;
    }
    private Date startTimebyBarcode(String barcode) throws ParseException {
        String[] s = barcode.split("_");
        String date = s[4].split("\\.")[0];
        date = date.replace("T", " ");
        System.out.println(date);
        SimpleDateFormat form = new SimpleDateFormat("YYYY-MM-ddHH:mm:ss");
        Date to = form.parse(date);
        return to;
    }
}
