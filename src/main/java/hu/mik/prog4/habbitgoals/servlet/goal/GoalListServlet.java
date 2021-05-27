package hu.mik.prog4.habbitgoals.servlet.goal;

import hu.mik.prog4.habbitgoals.entity.goal.Goal;
import hu.mik.prog4.habbitgoals.entity.goal.MainGoal;
import hu.mik.prog4.habbitgoals.service.goal.MainGoalService;
import hu.mik.prog4.habbitgoals.service.goal.SideGoalService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GoalListServlet extends HttpServlet {

    private MainGoalService mainGoalService;
    private SideGoalService sideGoalService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.mainGoalService = new MainGoalService();
        this.sideGoalService = new SideGoalService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        List<MainGoal> mainGoals = this.mainGoalService.listAll();
        mainGoals.forEach(mainGoal -> mainGoal.setSideGoals(sideGoalService.listAll()
                .stream()
                .filter(sideGoal -> sideGoal.getMainGoalId().equals(mainGoal.getId()))
                .collect(Collectors.toList())));

        req.setAttribute("goals", mainGoals);
        req.getRequestDispatcher("/listGoals.jsp").forward(req, resp);
    }

}
