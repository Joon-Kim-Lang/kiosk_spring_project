package kiosk_pjt.kiosk.backgroundProcess;

import kiosk_pjt.kiosk.AppConfig;
import kiosk_pjt.kiosk.Seat.domain.Seat;
import kiosk_pjt.kiosk.payment.domain.PaymentInfo;
import kiosk_pjt.kiosk.payment.service.PaymentService;
import kiosk_pjt.kiosk.reservation.repository.SeatRepository;
import kiosk_pjt.kiosk.reservation.service.SeatService;
import kiosk_pjt.kiosk.timetype.domain.TimeType;
import kiosk_pjt.kiosk.timetype.repository.TimeTypeRepository;
import kiosk_pjt.kiosk.timetype.service.TimeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@WebListener
public class DaemonListener implements ServletContextListener, Runnable {

    @Autowired
    SeatService seatService;
    @Autowired
    TimeTypeService timeTypeService;
    @Autowired
    TimeTypeRepository timeTypeRepository;
    @Autowired
    PaymentService paymentService;

    /** 작업을 수행할 thread */
    private Thread thread;
    private boolean isShutdown = false;
    /** context */
    private ServletContext sc;


    /** 작업을 수행한다 */
    public void startDaemon() {
        if (thread == null) {
            thread = new Thread(this, "Daemon thread for background task");
//            thread.setDaemon(true);
        }
        if (!thread.isAlive()) {
            thread.start();
        }
    }
    /** 스레드가 실제로 작업하는 부분 */
    public void run() {
        Thread currentThread = Thread.currentThread();
        while (currentThread == thread && !this.isShutdown) {
            try {
                System.out.println ("== DaemonListener is running. ==");

                List<Seat> seats = seatService.currentSeatsList();
                for (Seat seat : seats) {
                    String barcode = seat.getBarcode();
                    if(barcode != null){
                       System.out.println("seat.getBarcode() = " + seat.getBarcode());

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
                        }else if(menuInfo.equals("tim")){
                            TimeType byId = timeTypeRepository.findById(barcode);
                            long remainTime = byId.getRemainTime();
                            int remain = Long.valueOf(Optional.ofNullable(remainTime).orElse(0L)).intValue();
                            cal.add(Calendar.SECOND,remain);
                            startTime =  Date.from( byId.getStartTime().atZone( ZoneId.systemDefault()).toInstant());
                        }else if(menuInfo.equals("day")){
                            cal.add(Calendar.DATE,time);
                        }
                        endTime = cal.getTime();
                        if(startTime.getTime()<currentTime.getTime() && currentTime.getTime()<endTime.getTime()){ continue; }
                        else{
                            seatService.leave(seat);
                            if(menuInfo.equals("tim")){
                                timeTypeService.delete(barcode);
                            }
                            System.out.println(seat.getSeatNum()+" expired and cleared");
                        }
                    }
                }

                Thread.sleep(5000);
            } catch (InterruptedException | ParseException e) {
                e.printStackTrace();
            }
        }
        System.out.println ("== DaemonListener end. ==");
    }
    /** 컨텍스트 초기화 시 데몬 스레드를 작동한다 */
    public void contextInitialized (ServletContextEvent event) {
        AutowireCapableBeanFactory autowireCapableBeanFactory = WebApplicationContextUtils.getRequiredWebApplicationContext(event.getServletContext()).getAutowireCapableBeanFactory();
        autowireCapableBeanFactory.autowireBean(this);
        System.out.println ("== DaemonListener.contextInitialized has been called. ==");
        sc = event.getServletContext();
        startDaemon();
    }
    /** 컨텍스트 종료 시 thread를 종료시킨다 */
    public void contextDestroyed (ServletContextEvent event) {
        System.out.println ("== DaemonListener.contextDestroyed has been called. ==");
        this.isShutdown = true;
        try
        {
            thread.join();
            thread = null;
        }
        catch (InterruptedException ie)
        {
            ie.printStackTrace();
        }
    }

    private Date currentTime() throws ParseException {
        Date date_now = new Date(System.currentTimeMillis()); // 현재시간을 가져와 Date형으로 저장한다
        return date_now;
    }
    private Date startTimebyBarcode(String barcode) throws ParseException {

        PaymentInfo paymentInfo = paymentService.findPaymentInfo(barcode);
        LocalDateTime paymentTime = paymentInfo.getPaymentTime();
        Date date = Date.from( paymentTime.atZone( ZoneId.systemDefault()).toInstant());
        return date;
    }

}
