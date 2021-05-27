package hu.mik.prog4.habbitgoals.servlet.measure;

import hu.mik.prog4.habbitgoals.entity.goal.Goal;
import hu.mik.prog4.habbitgoals.entity.measure.Measure;
import hu.mik.prog4.habbitgoals.entity.measure.MeasureField;
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
import java.util.stream.Collectors;

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
        List<MeasureField> measureFields = this.measureFieldService.listAll();
        measureFields.forEach(measureField -> measureField.setMeasureValues(measureValueService.listAll()
                .stream()
                .filter(measureValue -> measureValue.getMeasureFieldId().equals(measureField.getId()))
                .collect(Collectors.toList())));

        req.setAttribute("measureFields", measureFields);
        req.getRequestDispatcher("/listMeasures.jsp").forward(req, resp);
    }
}
