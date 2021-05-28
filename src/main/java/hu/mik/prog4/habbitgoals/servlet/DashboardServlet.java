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
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

@Log4j2
public class DashboardServlet extends HttpServlet {

    private MainGoalService mainGoalService;
    private SideGoalService sideGoalService;
    private MeasureFieldService measureFieldService;
    private MeasureValueService measureValueService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.mainGoalService = new MainGoalService();
        this.sideGoalService = new SideGoalService();
        this.measureFieldService = new MeasureFieldService();
        this.measureValueService = new MeasureValueService();
        log.info("initParam: " + this.getServletConfig().getInitParameter("initParam"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        List<MainGoal> mainGoals = this.mainGoalService.listAll();
        if(!mainGoals.isEmpty()){
            MainGoal mainGoal = mainGoals.get(new Random().ints(0, mainGoals.size())
                    .findFirst()
                    .getAsInt());
            List<SideGoal> sideGoals = this.sideGoalService.listAll().stream().filter(sideGoal -> sideGoal.getMainGoalId().equals(mainGoal.getId())).collect(Collectors.toList());
            sideGoals.forEach(sideGoal -> sideGoal.setIsCompleted(sideGoalService.isCompleted(sideGoal.getId())));

            List<Long> measureFieldsIds = sideGoals.stream()
                    .map(SideGoal::getMeasureFieldId)
                    .distinct().collect(Collectors.toList());

            List<MeasureField> measureFields = measureFieldsIds.stream().map(aLong -> measureFieldService.findById(aLong)).collect(Collectors.toList());
            measureFields.forEach(measureField -> measureField.setMeasureValues(measureValueService.listAll()
                    .stream()
                    .filter(measureValue -> measureValue.getMeasureFieldId().equals(measureField.getId()))
                    .collect(Collectors.toList())));

//        Giving back Values to JSP
            req.setAttribute("mainGoalCompleted",mainGoalService.isCompleted(mainGoal.getId()));
            req.setAttribute("measureFields",measureFields);
            req.setAttribute("sideGoals", sideGoals);
            req.setAttribute("mainGoal", mainGoal);
            req.setAttribute("nodata",false);
        }else{
            req.setAttribute("nodata",true);
        }

        req.getRequestDispatcher("/dashboard.jsp").forward(req, resp);
    }
}
