package kiosk_pjt.kiosk.payment.controller;

import kiosk_pjt.kiosk.Item.domain.Item;
import kiosk_pjt.kiosk.Item.service.ItemService;
import kiosk_pjt.kiosk.payment.domain.PaymentInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class PaymentController {

    private final ItemService itemService;

    public PaymentController(ItemService itemService) {
        this.itemService = itemService;
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
        System.out.println("paymentInfoName = " + paymentInfo.getName());
        System.out.println("paymentInfo = " + paymentInfo.getPhoneNumber());
        System.out.println("paymentInfo = " + paymentInfo.getItemKind());
        return "basicInfoTemplate";
    }


//    static class Info{
//        private String name;
//        private String phoneNumber;
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public String getPhoneNumber() {
//            return phoneNumber;
//        }
//
//        public void setPhoneNumber(String phoneNumber) {
//            this.phoneNumber = phoneNumber;
//        }
//    }


}
