package kiosk_pjt.kiosk.payment.repository;

import kiosk_pjt.kiosk.payment.domain.PaymentInfo;

import java.util.List;

public interface PaymentRepository {

    PaymentInfo save(PaymentInfo paymentInfo);
    List<PaymentInfo> findAll();
    PaymentInfo findByBarcode(String barcode);
}
