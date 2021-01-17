package kiosk_pjt.kiosk.reservation.controller;

import kiosk_pjt.kiosk.Seat.domain.Seat;
import kiosk_pjt.kiosk.payment.domain.PaymentInfo;
import kiosk_pjt.kiosk.payment.service.PaymentService;
import kiosk_pjt.kiosk.reservation.service.SeatService;
import kiosk_pjt.kiosk.timetype.domain.TimeType;
import kiosk_pjt.kiosk.timetype.repository.TimeTypeRepository;
import kiosk_pjt.kiosk.timetype.service.TimeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.*;

@Controller
@RequestMapping("/seating")
public class SeatController {
    private final SeatService seatService;
    private final PaymentService paymentService;
    private final TimeTypeService timeTypeService;
    private final TimeTypeRepository timeTypeRepository;


    @Autowired
    public SeatController(SeatService seatService, PaymentService paymentService, TimeTypeService timeTypeService,TimeTypeRepository timeTypeRepository) {
        this.seatService = seatService;
        this.paymentService = paymentService;
        this.timeTypeService = timeTypeService;
        this.timeTypeRepository = timeTypeRepository;
    }

    @GetMapping("/seatlist")
    public String seatList(Model model){
        List<Seat> seats = seatService.currentSeatsList();
        model.addAttribute("seats", seats);
        return "seatSelectTemplate";
    }

    @PostMapping("/seatlist/seatInfoSave")
    public String seatInfoSave(@RequestBody Data data){
        String barcode = data.getBarcode();
        String seatNum = data.getSeatNum();

        String[] s = barcode.split("_");
        String menuInfo = s[0];
        Seat seat = new Seat(Integer.parseInt(seatNum), barcode, true);
        seatService.join(seat);
        if(menuInfo.equals("time")){
            timeTypeService.setStartTime(barcode);
            timeTypeService.setRemainTime(barcode);
        }
        return "index";
    }
    static class Data{
        private String seatNum;
        private String barcode;
        public String getSeatNum() { return seatNum; }
        public void setSeatNum(String seatNum) { this.seatNum = seatNum; }
        public String getBarcode() { return barcode; }
        public void setBarcode(String barcode) { this.barcode = barcode; }
    }

    @GetMapping("/barcodeInfo")
    public String seatInfo(){
        return "barcodeInfo";
    }

    @PostMapping("/barcodeInfoCheck")
    public String seatInfoCheck(@RequestParam(value = "barcode") String barcode,Model model) throws ParseException {
        PaymentInfo paymentInfo;Seat seat = null;
        paymentInfo = paymentService.findPaymentInfo(barcode);
        //바코드가 없어
        if(paymentInfo==null){
            return "redirect:/";
        }
        //바코드가 있어
        if(isBarcodeAvailable(barcode)==true){
            try {
                seat = seatService.findSeat(barcode);
            }catch (Exception e){
                model.addAttribute("barcode", barcode);
                List<Seat> seats = seatService.currentSeatsList();
                model.addAttribute("seats", seats);
                return "seatSelectTemplate";
            }
        }
        return "redirect:/";
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
            TimeType byId = timeTypeRepository.findById(barcode);
            long remainTime = byId.getRemainTime();
            int remain = Long.valueOf(Optional.ofNullable(remainTime).orElse(0L)).intValue();
            cal.add(Calendar.HOUR,remain);
            startTime =  Date.from( byId.getStartTime().atZone( ZoneId.systemDefault()).toInstant());
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
        SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date to = form.parse(date);
        return to;
    }
}
