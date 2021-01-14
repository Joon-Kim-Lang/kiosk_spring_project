package kiosk_pjt.kiosk.payment.service;

import kiosk_pjt.kiosk.AppConfig;
import kiosk_pjt.kiosk.payment.domain.PaymentInfo;
import kiosk_pjt.kiosk.payment.repository.PaymentRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class PaymentServiceImpl implements PaymentService{

    private final PaymentRepository paymentRepository;
    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public String join(PaymentInfo paymentInfo) {
        paymentRepository.save(paymentInfo);
        return paymentInfo.getBarcode();
    }

    @Override
    public List<PaymentInfo> showPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public PaymentInfo findPaymentInfo(String barcode) {
        return paymentRepository.findByBarcode(barcode);
    }
}
