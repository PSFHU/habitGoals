package hu.mik.prog4.habbitgoals.servlet.goal;

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

@Log4j2
public class DeleteGoalServlet extends HttpServlet {
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        if(MAINGOAL.equals(req.getParameter("type"))){
            mainGoalService.deleteById(id);
        }else if (SIDEGOAL.equals(req.getParameter("type"))) {
            sideGoalService.deleteById(id);
        }else {
            log.error("Invalid Goal Type while trying to delete Goal!");
            throw new GoalTypeException("Invalid Goal Type!");
        }
        req.setAttribute("successfulDelete", true);
        req.getRequestDispatcher("/listGoals.jsp").forward(req, resp);
    }
}
