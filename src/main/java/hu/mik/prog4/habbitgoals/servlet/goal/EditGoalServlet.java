package hu.mik.prog4.habbitgoals.servlet.goal;

import hu.mik.prog4.habbitgoals.entity.goal.Goal;
import hu.mik.prog4.habbitgoals.entity.goal.MainGoal;
import hu.mik.prog4.habbitgoals.entity.goal.SideGoal;
import hu.mik.prog4.habbitgoals.entity.measure.MeasureField;
import hu.mik.prog4.habbitgoals.exception.GoalTypeException;
import hu.mik.prog4.habbitgoals.service.goal.MainGoalService;
import hu.mik.prog4.habbitgoals.service.goal.SideGoalService;
import hu.mik.prog4.habbitgoals.service.measure.MeasureFieldService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class EditGoalServlet extends HttpServlet {

    private static final String MAINGOAL = "MainGoal";
    private static final String SIDEGOAL = "SideGoal";
    private MainGoalService mainGoalService;
    private SideGoalService sideGoalService;
    private MeasureFieldService measureFieldService;


    @Override
    public void init() throws ServletException {
        super.init();
        this.mainGoalService = new MainGoalService();
        this.sideGoalService = new SideGoalService();
        this.measureFieldService = new MeasureFieldService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<MainGoal> mainGoals = new ArrayList<>(mainGoalService.listAll());
        List<MeasureField> measureFields = new ArrayList<>(measureFieldService.listAll());
        req.setAttribute("measureFields",measureFields);
        req.setAttribute("mainGoals", mainGoals);
        Long id = Long.valueOf(req.getParameter("id"));
        String type = req.getParameter("type");
        Goal byId;
        if (MAINGOAL.equals(type)) {
            byId = mainGoalService.findById(id);
        }else if (SIDEGOAL.equals(type)) {
            byId = sideGoalService.findById(id);
        }else {
            log.error("Invalid Goal Type while trying to delete Goal!");
            throw new GoalTypeException("Invalid Goal Type!");
        }
        req.setAttribute("goal", byId);
        req.getRequestDispatcher("/goalAdd.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String goalType = req.getParameter("goalType");
        String name = req.getParameter("name");
        String address = req.getParameter("address");
        Long id = Long.valueOf(req.getParameter("id"));

        if (MAINGOAL.equals(goalType)) {
            String title = req.getParameter("title");
            MainGoal mainGoal = new MainGoal();
            mainGoal.setId(id);
            mainGoal.setTitle(title);

            log.info("Editing main-goal: " + mainGoal);
            req.setAttribute("goal", mainGoal);
            this.mainGoalService.edit(mainGoal);
        } else if (SIDEGOAL.equals(goalType)) {
            Long mainGoalId = Long.valueOf(req.getParameter("mainGoalId"));
            Long measureFieldId = Long.valueOf(req.getParameter("measureFieldId"));
            Double goalValue = Double.valueOf(req.getParameter("goalValue"));
            String title = req.getParameter("title");

            SideGoal sideGoal = new SideGoal();
            sideGoal.setId(id);
            sideGoal.setMainGoalId(mainGoalId);
            sideGoal.setMeasureFieldId(measureFieldId);
            sideGoal.setGoalValue(goalValue);
            sideGoal.setTitle(title);

            log.info("Editing side-goal: " + sideGoal);
            req.setAttribute("goal", sideGoal);
            this.sideGoalService.edit(sideGoal);
        } else {
            log.error("Invalid Goal Type while trying to edit Goal!");
            throw new GoalTypeException("Invalid Goal Type!");
        }
        req.setAttribute("successfulEdit", true);
        req.getRequestDispatcher("/goalList.jsp").forward(req, resp);
    }
}
