package hu.mik.prog4.habbitgoals.repository.measure;

import hu.mik.prog4.habbitgoals.entity.measure.MeasureField;
import hu.mik.prog4.habbitgoals.exception.DataAccessException;
import hu.mik.prog4.habbitgoals.exception.DataNamingException;
import hu.mik.prog4.habbitgoals.repository.Repository;
import hu.mik.prog4.habbitgoals.repository.RepositoryInterface;
import lombok.extern.log4j.Log4j2;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class MeasureFieldRepository extends Repository implements RepositoryInterface<MeasureField> {

    @Override
    public List<MeasureField> listAll() {
        try (Connection con = this.getConnection(); Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT measure_field_id, title FROM measure_field");
            List<MeasureField> list = new ArrayList<>();
            while (rs.next()) {
                list.add(this.mapMeasureField(rs));
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
    public MeasureField findById(Long id) {
        try (Connection con = this.getConnection();
             PreparedStatement stmt = con.prepareStatement("SELECT measure_field_id,title FROM measure_field WHERE measure_field_id = ?")) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            rs.next();
            return this.mapMeasureField(rs);
        } catch (NamingException e) {
            log.error("Data naming error: " + e.getMessage(), e);
            throw new DataNamingException();
        } catch (SQLException e) {
            log.error("Data access error: " + e.getMessage(),e);
            throw new DataAccessException();
        }
    }

    @Override
    public MeasureField add(MeasureField measureField) {
        try (Connection con = this.getConnection();
             PreparedStatement stmt = con.prepareStatement("Insert Into measure_field(title) VALUES (?)")) {
            stmt.setString(1,measureField.getTitle());

            stmt.executeUpdate();
            return measureField;
        } catch (NamingException e) {
            log.error("Data naming error: " + e.getMessage(), e);
            throw new DataNamingException();
        } catch (SQLException e) {
            log.error("Data access error: " + e.getMessage(),e);
            throw new DataAccessException();
        }
    }

    @Override
    public MeasureField edit(MeasureField measureField) {
        try (Connection con = this.getConnection();
             PreparedStatement stmt = con.prepareStatement("UPDATE measure_field SET title = ? WHERE measure_field_id = ?", Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1,measureField.getTitle());

            stmt.executeUpdate();
            return measureField;
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
             PreparedStatement stmt = con.prepareStatement("DELETE FROM measure_field WHERE measure_field_id = ?")){
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

    private MeasureField mapMeasureField(ResultSet rs) throws SQLException {
        MeasureField measureField = new MeasureField();
        measureField.setId(rs.getLong("measure_field_id"));
        measureField.setTitle(rs.getString("title"));
        return measureField;
    }
}
