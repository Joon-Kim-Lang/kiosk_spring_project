package kiosk_pjt.kiosk.timetype.service;

import kiosk_pjt.kiosk.timetype.domain.TimeType;
import kiosk_pjt.kiosk.timetype.repository.TimeTypeRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
@Component
public class TimeTypeServiceImpl implements TimeTypeService{

    private final TimeTypeRepository timeTypeRepository;

    public TimeTypeServiceImpl(TimeTypeRepository timeTypeRepository) {
        this.timeTypeRepository = timeTypeRepository;
    }

    @Override@Transactional
    public TimeType join(TimeType timeType) {

        timeTypeRepository.save(timeType);
        return timeType;
    }

    @Override@Transactional
    public TimeType delete(String barcode) {
        TimeType removeItem = timeTypeRepository.findById(barcode);
        timeTypeRepository.remove(removeItem);
        return removeItem;
    }

    @Override@Transactional
    public void setStartTime(String barcode) {
        TimeType timeType = timeTypeRepository.findById(barcode);
        timeType.setStartTime(LocalDateTime.now());
    }

    @Override@Transactional
    public void setRemainTime(String barcode) {
        TimeType timeType = timeTypeRepository.findById(barcode);

        Duration duration = Duration.between(timeType.getStartTime(), LocalDateTime.now());
        long usedSeconds = Math.abs(duration.getSeconds());
        long newRemainTime = timeType.getRemainTime() - usedSeconds;
        timeType.setRemainTime(newRemainTime);
    }
}
