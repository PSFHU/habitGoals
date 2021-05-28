package hu.mik.prog4.habbitgoals.repository.goal;

import hu.mik.prog4.habbitgoals.entity.goal.SideGoal;
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
public class SideGoalAbstractRepository extends AbstractRepository implements Repository<SideGoal> {

    @Override
    public List<SideGoal> listAll() {
        try (Connection con = this.getConnection(); Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT id, main_goal_id, measure_field_id, title, goal_value FROM side_goal");
            List<SideGoal> list = new ArrayList<>();
            while (rs.next()) {
                list.add(this.mapSideGoal(rs));
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
    public SideGoal findById(Long id) {
        try (Connection con = this.getConnection();
             PreparedStatement stmt = con.prepareStatement("SELECT id, main_goal_id, measure_field_id, title, goal_value FROM side_goal WHERE id = ?")) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            rs.next();
            return this.mapSideGoal(rs);
        } catch (NamingException e) {
            log.error("Data naming error: " + e.getMessage(), e);
            throw new DataNamingException();
        } catch (SQLException e) {
            log.error("Data access error: " + e.getMessage(),e);
            throw new DataAccessException();
        }
    }

    @Override
    public SideGoal create(SideGoal sideGoal) {
        try (Connection con = this.getConnection();
             PreparedStatement stmt = con.prepareStatement("Insert Into side_goal(main_goal_id, measure_field_id, title, goal_value) VALUES (?,?,?,?)")) {
            stmt.setLong(1,sideGoal.getMainGoalId());
            stmt.setLong(2,sideGoal.getMeasureFieldId());
            stmt.setString(3,sideGoal.getTitle());
            stmt.setDouble(4,sideGoal.getGoalValue());

            stmt.executeUpdate();
            return sideGoal;
        } catch (NamingException e) {
            log.error("Data naming error: " + e.getMessage(), e);
            throw new DataNamingException();
        } catch (SQLException e) {
            log.error("Data access error: " + e.getMessage(),e);
            throw new DataAccessException();
        }
    }

    @Override
    public SideGoal update(SideGoal sideGoal) {
        try (Connection con = this.getConnection();
             PreparedStatement stmt = con.prepareStatement("UPDATE side_goal SET main_goal_id = ?, measure_field_id = ?, title = ?, goal_value = ? WHERE id = ?",Statement.RETURN_GENERATED_KEYS) ) {
            stmt.setLong(1,sideGoal.getMainGoalId());
            stmt.setLong(2,sideGoal.getMeasureFieldId());
            stmt.setString(3,sideGoal.getTitle());
            stmt.setDouble(4,sideGoal.getGoalValue());
            stmt.setLong(5,sideGoal.getId());

            stmt.executeUpdate();
            return sideGoal;
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
             PreparedStatement stmt = con.prepareStatement("DELETE FROM side_goal WHERE id = ?")){
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

    private SideGoal mapSideGoal(ResultSet rs) throws SQLException {
        SideGoal sideGoal = new SideGoal();
        sideGoal.setId(rs.getLong("id"));
        sideGoal.setMainGoalId(rs.getLong("main_goal_id"));
        sideGoal.setMeasureFieldId(rs.getLong("measure_field_id"));
        sideGoal.setTitle(rs.getString("title"));
        sideGoal.setGoalValue(rs.getDouble("goal_value"));
        return sideGoal;
    }
}
