package kiosk_pjt.kiosk.timetype.repository;


import kiosk_pjt.kiosk.timetype.domain.TimeType;

import java.util.List;

public interface TimeTypeRepository {

    TimeType save(TimeType timeType);

    void remove(TimeType timeType);

    List<TimeType> findAll();

    TimeType findById(String barcode);

}
