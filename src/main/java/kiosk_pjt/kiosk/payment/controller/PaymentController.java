package kiosk_pjt.kiosk.payment.controller;

import kiosk_pjt.kiosk.Item.domain.Item;
import kiosk_pjt.kiosk.Item.service.ItemService;
import kiosk_pjt.kiosk.Seat.domain.Seat;
import kiosk_pjt.kiosk.payment.domain.PaymentInfo;
import kiosk_pjt.kiosk.payment.service.PaymentService;
import kiosk_pjt.kiosk.reservation.service.SeatService;
import kiosk_pjt.kiosk.timetype.domain.TimeType;
import kiosk_pjt.kiosk.timetype.service.TimeTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class PaymentController {

    private final ItemService itemService;
    private final SeatService seatService;
    private final PaymentService paymentService;
    private final TimeTypeService timeTypeService;

    public PaymentController(ItemService itemService, SeatService seatService, PaymentService paymentService, TimeTypeService timeTypeService) {
        this.itemService = itemService;
        this.seatService = seatService;
        this.paymentService = paymentService;
        this.timeTypeService = timeTypeService;
    }

    @RequestMapping(value = "/basicInfo", method = RequestMethod.GET)
    public String basicInfo(Model model) {
        model.addAttribute("paymentInfo", new PaymentInfo());
        return "basicInfoTemplate";
    }

    @RequestMapping(value = "/itemSelect", method = RequestMethod.POST)
    public String itemSelect(@ModelAttribute("paymentInfo") PaymentInfo paymentInfo, Model model) {
        model.addAttribute("paymentInfo", paymentInfo);
        List<Item> items = itemService.showAllItems();
        model.addAttribute("items", items);

        return "itemSelectTemplate";
    }

    @RequestMapping(value = "/seatSelect", method = RequestMethod.POST)
    public String seatSelect(@ModelAttribute("paymentInfo") PaymentInfo paymentInfo, Model model) {

        List<Seat> seats = seatService.currentSeatsList();
        model.addAttribute("paymentInfo", paymentInfo);
        model.addAttribute("seats", seats);

        return "seatSelectTemplate_payment";
    }

    @RequestMapping(value = "/getPay", method = RequestMethod.POST)
    public String getPay(@ModelAttribute("paymentInfo") PaymentInfo paymentInfo, HttpServletRequest request,Model model) {

        model.addAttribute("paymentInfo", paymentInfo);
        model.addAttribute("seatNum", request.getParameter("seatNum"));

        return "payOrNot";
    }

    @RequestMapping(value = "/paymentRegister", method = RequestMethod.POST)
    public String basicInfo(@ModelAttribute("paymentInfo") PaymentInfo paymentInfo,HttpServletRequest request) {
//        System.out.println("paymentInfoName = " + paymentInfo.getName());
//        System.out.println("paymentInfo = " + paymentInfo.getPhoneNumber());
//        System.out.println("paymentInfo = " + paymentInfo.getItemKind());
//        System.out.println("seatNum = " + request.getParameter("seatNum"));
        int seatNum =   Integer.parseInt(request.getParameter("seatNum"));
        paymentInfo.setPaymentTime();
        paymentInfo.setBarcode();
        Seat seat = new Seat(seatNum,paymentInfo.getBarcode(),true);

        paymentService.join(paymentInfo);
        seatService.join(seat);

        String[] str = paymentInfo.getBarcode().split("_");
        if(str[0].equals("tim")){
            long remainTime = Long.parseLong(str[1])*60*60;
            TimeType timeType = new TimeType();
            timeType.setBarcode(paymentInfo.getBarcode());
            timeType.setRemainTime(remainTime);
            timeTypeService.join(timeType);
            timeTypeService.setStartTime(paymentInfo.getBarcode());
        }

        return "index";
    }


}
