package hu.mik.prog4.habbitgoals.servlet.goal;

import hu.mik.prog4.habbitgoals.entity.goal.Goal;
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
        List<Goal> goals = new ArrayList<>();
        goals.addAll(this.mainGoalService.listAll());
        goals.addAll(this.sideGoalService.listAll());

        req.setAttribute("goals", goals);
        req.getRequestDispatcher("/goalList.jsp").forward(req, resp);
    }

}
