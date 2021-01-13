package kiosk_pjt.kiosk.reservation.repository;

import kiosk_pjt.kiosk.Seat.domain.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JdbcTemplateSeatRepository implements SeatRepository{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplateSeatRepository(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Seat register(Seat seat) {
        return null;
    }

    @Override
    public void save(Seat seat) {
        jdbcTemplate.update("UPDATE SEAT SET barcode=? WHERE SEATNUM=?",
                seat.getBarcode(),seat.getSeatNum());
        jdbcTemplate.update("UPDATE SEAT SET isoccupied=? WHERE SEATNUM=?",
                seat.getIsoccupied(),seat.getSeatNum());
    }
    @Override
    public void remove(Seat seat) {
        jdbcTemplate.update("update seat set barcode=? where seatnum=?",
                null,seat.getSeatNum());
        jdbcTemplate.update("update seat set isoccupied=? where seatnum=?",
                null,seat.getSeatNum());
    }
    @Override
    public List<Seat> findAll() {
        List<Seat> seatList = jdbcTemplate.query("select * from seat", seatRowMapper());
        return seatList;
    }

    @Override
    public Seat findById(int id) {
        List<Seat> seats = jdbcTemplate.query("select * from seat where seatnum = ?", seatRowMapper(),id);
        return seats.get(0);
    }

    private RowMapper<Seat> seatRowMapper(){
        return new RowMapper<Seat>() {
            @Override
            public Seat mapRow(ResultSet rs, int rowNum) throws SQLException {
                int seatnum = rs.getInt("seatnum");
                String barcode = rs.getString("barcode");
                boolean isoccupied = rs.getBoolean("isoccupied");
                return new Seat(seatnum, barcode, isoccupied);
            }
        };
    }
}
