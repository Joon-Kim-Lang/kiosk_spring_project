package kiosk_pjt.kiosk.reservation.controller;

import kiosk_pjt.kiosk.Seat.domain.Seat;
import kiosk_pjt.kiosk.reservation.service.SeatService;
import kiosk_pjt.kiosk.timetype.domain.TimeType;
import kiosk_pjt.kiosk.timetype.repository.TimeTypeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Controller
public class OpenController {

    private final TimeTypeRepository timeTypeRepository;
    private final SeatService seatService;

    public OpenController(TimeTypeRepository timeTypeRepository, SeatService seatService) {
        this.timeTypeRepository = timeTypeRepository;
        this.seatService = seatService;
    }

    @GetMapping("/openInfo")
    public String openInfo(){
        return "barcodeInfoForOpen";
    }

    @PostMapping("open")
    public String open(@RequestParam("barcode")String barcode) throws ParseException {
        Seat seat;
        try{ seat = seatService.findSeat(barcode);
        }catch (Exception e){ return "redirect:/"; }

        if(isBarcodeAvailable(barcode)==true){
            return "redirect:/";
        }else{
            return "basicInfoTemplate";
        }
    }
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
