package kiosk_pjt.kiosk.Seat.domain;

public class Seat {
    private int seatNum;
    private String barcode;

    public Seat(int seatNum,String barcode) {
        this.seatNum = seatNum;
        this.barcode = barcode;
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
}
