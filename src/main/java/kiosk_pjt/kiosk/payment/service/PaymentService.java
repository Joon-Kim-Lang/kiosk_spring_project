package kiosk_pjt.kiosk.payment.service;

import kiosk_pjt.kiosk.payment.domain.PaymentInfo;

import java.util.List;

public interface PaymentService {
    String join(PaymentInfo paymentInfo);
    List<PaymentInfo> showPayments();
}
