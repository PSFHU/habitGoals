package hu.mik.prog4.habbitgoals.servlet;

import hu.mik.prog4.habbitgoals.entity.goal.Goal;
import hu.mik.prog4.habbitgoals.entity.goal.MainGoal;
import hu.mik.prog4.habbitgoals.entity.goal.SideGoal;
import hu.mik.prog4.habbitgoals.entity.measure.Measure;
import hu.mik.prog4.habbitgoals.entity.measure.MeasureField;
import hu.mik.prog4.habbitgoals.entity.measure.MeasureValue;
import hu.mik.prog4.habbitgoals.service.goal.MainGoalService;
import hu.mik.prog4.habbitgoals.service.goal.SideGoalService;
import hu.mik.prog4.habbitgoals.service.measure.MeasureFieldService;
import hu.mik.prog4.habbitgoals.service.measure.MeasureValueService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DashboardServlet extends HttpServlet {

    private MainGoalService mainGoalService;
    private SideGoalService sideGoalService;
    private MeasureFieldService measureFieldService;
    private MeasureValueService measureValueService;

    @Override
    public void init() throws ServletException {
        this.mainGoalService = new MainGoalService();
        this.sideGoalService = new SideGoalService();
        this.measureFieldService = new MeasureFieldService();
        this.measureValueService = new MeasureValueService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        List<MainGoal> mainGoals = this.mainGoalService.listAll();
        MainGoal mainGoal = mainGoals.get((int) Math.round(Math.random() * (mainGoals.size())));
        List<SideGoal> sideGoals = sideGoalService.listAllMainGoalId(mainGoal.getId());

        List<Long> measureFieldsIds = sideGoals.stream()
                .map(SideGoal::getMeasureFieldId)
                .distinct().collect(Collectors.toList());

        List<MeasureField> measureFields = measureFieldsIds.stream().map(aLong -> measureFieldService.findById(aLong)).collect(Collectors.toList());
        measureFields.forEach(measureField -> measureField.setMeasureValues(measureValueService.listAllOnMeasureFieldId(measureField.getId())));

//        Giving back Values to JSP
        req.setAttribute("measureFields",measureFields);
        req.setAttribute("sideGoals", sideGoals);
        req.setAttribute("mainGoal", mainGoals);

        req.getRequestDispatcher("/dashboard.jsp").forward(req, resp);
    }
}
