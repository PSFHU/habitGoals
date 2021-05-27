package hu.mik.prog4.habbitgoals.servlet.measure;

import hu.mik.prog4.habbitgoals.entity.goal.Goal;
import hu.mik.prog4.habbitgoals.entity.measure.Measure;
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

public class MeasureListServlet extends HttpServlet {
    private MeasureValueService measureValueService;
    private MeasureFieldService measureFieldService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.measureValueService = new MeasureValueService();
        this.measureFieldService = new MeasureFieldService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        List<Measure> measures = new ArrayList<>();
        measures.addAll(this.measureValueService.listAll());
        measures.addAll(this.measureFieldService.listAll());

        req.setAttribute("measures", measures);
        // TODO implement forward after actions
        req.getRequestDispatcher("/measureList.jsp").forward(req, resp);
    }
}
