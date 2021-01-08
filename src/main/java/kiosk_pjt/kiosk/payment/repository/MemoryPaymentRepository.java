package kiosk_pjt.kiosk.payment.repository;

import kiosk_pjt.kiosk.payment.domain.PaymentInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryPaymentRepository implements PaymentRepository{

    private static Map<String, PaymentInfo> store = new HashMap<>();

    @Override
    public PaymentInfo save(PaymentInfo paymentInfo) {
        store.put(paymentInfo.getBarcode(), paymentInfo);
        return paymentInfo;
    }

    @Override
    public List<PaymentInfo> findAll() {
        return new ArrayList<>(store.values());
    }
}
