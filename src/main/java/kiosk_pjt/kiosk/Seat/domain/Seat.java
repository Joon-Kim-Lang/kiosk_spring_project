package kiosk_pjt.kiosk.Seat.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Seat {
    @Id
    private int seatNum;
    private String barcode;
    private boolean isoccupied;

    public Seat(int seatNum, String barcode, boolean isoccupied) {
        this.seatNum = seatNum;
        this.barcode = barcode;
        this.isoccupied = isoccupied;
    }

    public Seat() {

    }

    public int getSeatNum() {
        return seatNum;
    }
    public void setSeatNum(int seatNum) {
        this.seatNum = seatNum;
    }
    public String getBarcode() {
        return barcode;
    }
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
    public boolean getIsoccupied(){return isoccupied;}
    public void setIsoccupied(boolean isoccupied) {
        this.isoccupied = isoccupied;
    }
}
