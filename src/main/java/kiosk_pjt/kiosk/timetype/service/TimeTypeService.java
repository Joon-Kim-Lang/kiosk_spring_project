package kiosk_pjt.kiosk.timetype.service;

import kiosk_pjt.kiosk.timetype.domain.TimeType;

import java.time.LocalDateTime;

public interface TimeTypeService {

    TimeType join(TimeType timeType);

    TimeType delete(String barcode);

    void setStartTime(String barcode);

    void setRemainTime(String barcode);
}
