package hu.mik.prog4.habbitgoals.repository.measure;

import hu.mik.prog4.habbitgoals.entity.measure.MeasureValue;
import hu.mik.prog4.habbitgoals.exception.DataAccessException;
import hu.mik.prog4.habbitgoals.exception.DataNamingException;
import hu.mik.prog4.habbitgoals.repository.AbstractRepository;
import hu.mik.prog4.habbitgoals.repository.Repository;
import lombok.extern.log4j.Log4j2;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class MeasureValueAbstractRepository extends AbstractRepository implements Repository<MeasureValue> {

    @Override
    public List<MeasureValue> listAll() {
        try (Connection con = this.getConnection(); Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT id, measure_field_id, value, date FROM measure_value");
            List<MeasureValue> list = new ArrayList<>();
            while (rs.next()) {
                list.add(this.mapMeasureValue(rs));
            }
            return list;

        } catch (NamingException e) {
            log.error("Data naming error: " + e.getMessage(), e);
            throw new DataNamingException();
        } catch (SQLException e) {
            log.error("Data access error: " + e.getMessage(),e);
            throw new DataAccessException();
        }
    }

    @Override
    public MeasureValue findById(Long id) {
        try (Connection con = this.getConnection();
             PreparedStatement stmt = con.prepareStatement("SELECT id, measure_field_id, value, date FROM measure_value WHERE id = ?")) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            rs.next();
            return this.mapMeasureValue(rs);
        } catch (NamingException e) {
            log.error("Data naming error: " + e.getMessage(), e);
            throw new DataNamingException();
        } catch (SQLException e) {
            log.error("Data access error: " + e.getMessage(),e);
            throw new DataAccessException();
        }
    }

    @Override
    public MeasureValue create(MeasureValue measureValue) {
        try (Connection con = this.getConnection();
             PreparedStatement stmt = con.prepareStatement("Insert Into measure_value(measure_field_id, value, date) VALUES (?,?,?)")) {
            stmt.setLong(1,measureValue.getMeasureFieldId());
            stmt.setDouble(2, measureValue.getValue());
            stmt.setDate(3, measureValue.getDate());

            stmt.executeUpdate();
            return measureValue;
        } catch (NamingException e) {
            log.error("Data naming error: " + e.getMessage(), e);
            throw new DataNamingException();
        } catch (SQLException e) {
            log.error("Data access error: " + e.getMessage(),e);
            throw new DataAccessException();
        }
    }

    @Override
    public MeasureValue update(MeasureValue measureValue) {
        try (Connection con = this.getConnection();
             PreparedStatement stmt = con.prepareStatement("UPDATE measure_value SET value = ?, date = ? WHERE id = ?", Statement.RETURN_GENERATED_KEYS)) {
            stmt.setDouble(1, measureValue.getValue());
            stmt.setDate(2, measureValue.getDate());
            stmt.setLong(3,measureValue.getId());

            stmt.executeUpdate();
            return measureValue;
        } catch (NamingException e) {
            log.error("Data naming error: " + e.getMessage(), e);
            throw new DataNamingException();
        } catch (SQLException e) {
            log.error("Data access error: " + e.getMessage(),e);
            throw new DataAccessException();
        }
    }

    @Override
    public boolean deleteById(Long id) {
        try (Connection con = this.getConnection();
             PreparedStatement stmt = con.prepareStatement("DELETE FROM measure_value WHERE id = ?")){
            stmt.setLong(1, id);
            int rowsAffected = stmt.executeUpdate();

            log.info("rowsAffected by delete: " + rowsAffected);
            return rowsAffected > 0;
        } catch (NamingException e) {
            log.error("Data naming error: " + e.getMessage(), e);
            throw new DataNamingException();
        } catch (SQLException e) {
            log.error("Data access error: " + e.getMessage(),e);
            throw new DataAccessException();
        }
    }

    private MeasureValue mapMeasureValue(ResultSet rs) throws SQLException {
        MeasureValue measureValue = new MeasureValue();
        measureValue.setId(rs.getLong("id"));
        measureValue.setMeasureFieldId(rs.getLong("measure_field_id"));
        measureValue.setValue(rs.getDouble("value"));
        measureValue.setDate(rs.getDate("date"));
        return measureValue;
    }
}
