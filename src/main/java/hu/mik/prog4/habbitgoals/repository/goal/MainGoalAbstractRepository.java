package hu.mik.prog4.habbitgoals.repository.goal;

import hu.mik.prog4.habbitgoals.entity.goal.MainGoal;
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
public class MainGoalAbstractRepository extends AbstractRepository implements Repository<MainGoal> {

    @Override
    public List<MainGoal> listAll() {
        try (Connection con = this.getConnection(); Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT main_goal_id, title FROM main_goal");
            List<MainGoal> list = new ArrayList<>();
            while (rs.next()) {
                list.add(this.mapMainGoal(rs));
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
    public MainGoal findById(Long id) {
        try (Connection con = this.getConnection();
             PreparedStatement stmt = con.prepareStatement("SELECT main_goal_id, title FROM main_goal WHERE main_goal_id = ?")) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            rs.next();
            return this.mapMainGoal(rs);
        } catch (NamingException e) {
            log.error("Data naming error: " + e.getMessage(), e);
            throw new DataNamingException();
        } catch (SQLException e) {
            log.error("Data access error: " + e.getMessage(),e);
            throw new DataAccessException();
        }
    }

    @Override
    public MainGoal create(MainGoal mainGoal) {
        try (Connection con = this.getConnection();
             PreparedStatement stmt = con.prepareStatement("Insert Into main_goal(title) VALUES (?)")) {
            stmt.setString(1,mainGoal.getTitle());

            stmt.executeUpdate();

            return mainGoal;
        } catch (NamingException e) {
            log.error("Data naming error: " + e.getMessage(), e);
            throw new DataNamingException();
        } catch (SQLException e) {
            log.error("Data access error: " + e.getMessage(),e);
            throw new DataAccessException();
        }
    }

    @Override
    public MainGoal update(MainGoal mainGoal) {
        try (Connection con = this.getConnection();
             PreparedStatement stmt = con.prepareStatement("UPDATE main_goal SET title = ? WHERE main_goal_id = ?",Statement.RETURN_GENERATED_KEYS) ) {
            stmt.setString(1,mainGoal.getTitle());
            stmt.setLong(2,mainGoal.getId());

            stmt.executeUpdate();
            return mainGoal;
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
             PreparedStatement stmt = con.prepareStatement("DELETE FROM main_goal WHERE main_goal_id = ?")){
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

    private MainGoal mapMainGoal(ResultSet rs) throws SQLException {
        MainGoal mainGoal = new MainGoal();
        mainGoal.setId(rs.getLong("main_goal_id"));
        mainGoal.setTitle(rs.getString("title"));
        return mainGoal;
    }
}
