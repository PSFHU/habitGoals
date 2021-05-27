package hu.mik.prog4.habbitgoals.servlet.goal;

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
import javax.servlet.jsp.PageContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class AddGoalServlet extends HttpServlet {

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
        req.setCharacterEncoding("UTF-8");
        List<MainGoal> mainGoals = new ArrayList<>(mainGoalService.listAll());
        List<MeasureField> measureFields = new ArrayList<>(measureFieldService.listAll());
        req.setAttribute("measureFields",measureFields);
        req.setAttribute("mainGoals", mainGoals);
        req.getRequestDispatcher("/addGoal.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String goalType = req.getParameter("goalType");
        String title = req.getParameter("title");

        if (MAINGOAL.equals(goalType)) {
            MainGoal mainGoal = new MainGoal();
            mainGoal.setTitle(title);

            log.info("Adding main-goal: " + mainGoal);
            req.setAttribute("goal", mainGoal);
            this.mainGoalService.add(mainGoal);
        } else if (SIDEGOAL.equals(goalType)) {
            Long mainGoalId = Long.valueOf(req.getParameter("mainGoalId"));
            Long measureFieldId = Long.valueOf(req.getParameter("measureFieldId"));
            Double goalValue = Double.valueOf(req.getParameter("goalValue"));

            SideGoal sideGoal = new SideGoal();
            sideGoal.setMainGoalId(mainGoalId);
            sideGoal.setMeasureFieldId(measureFieldId);
            sideGoal.setGoalValue(goalValue);
            sideGoal.setTitle(title);

            log.info("Adding side-goal: " + sideGoal);
            req.setAttribute("goal", sideGoal);
            this.sideGoalService.add(sideGoal);
        } else {
            log.error("Invalid Goal Type while trying to save Goal!");
            throw new GoalTypeException("Invalid Goal Type!");
        }
        req.setAttribute("successfulAdd", true);
        resp.sendRedirect(PageContext.PAGECONTEXT+ "/listGoals");
    }

}
