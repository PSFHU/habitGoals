package hu.mik.prog4.habbitgoals.servlet.measure;

import hu.mik.prog4.habbitgoals.entity.measure.Measure;
import hu.mik.prog4.habbitgoals.entity.measure.MeasureField;
import hu.mik.prog4.habbitgoals.entity.measure.MeasureValue;
import hu.mik.prog4.habbitgoals.exception.MeasureTypeException;
import hu.mik.prog4.habbitgoals.service.measure.MeasureFieldService;
import hu.mik.prog4.habbitgoals.service.measure.MeasureValueService;
import hu.mik.prog4.habbitgoals.servlet.FinalValues;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class EditMeasureServlet extends HttpServlet {

    private MeasureFieldService measureFieldService;
    private MeasureValueService measureValueService;


    @Override
    public void init() throws ServletException {
        super.init();
        this.measureFieldService = new MeasureFieldService();
        this.measureValueService = new MeasureValueService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<MeasureField> measureFields = new ArrayList<>(measureFieldService.listAll());
        req.setAttribute("measureFields",measureFields);
        Long id = Long.valueOf(req.getParameter("id"));
        String type = req.getParameter("type");
        Measure byId;
        if (FinalValues.FIELD.equals(type)) {
            byId = measureFieldService.findById(id);
        }else if (FinalValues.VALUE.equals(type)) {
            byId = measureValueService.findById(id);
        }else {
            log.error("Invalid Measure Type while trying to delete Goal!");
            throw new MeasureTypeException("Invalid Measure Type!");
        }
        req.setAttribute("measure", byId);
        req.getRequestDispatcher("/measureAdd.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String measureType = req.getParameter("measureType");
        Long id = Long.valueOf(req.getParameter("id"));

        if (FinalValues.FIELD.equals(measureType)) {
            String title = req.getParameter("title");
            MeasureField measureField = new MeasureField();
            measureField.setId(id);
            measureField.setTitle(title);

            log.info("Editing measure-field: " + measureField);
            req.setAttribute("measure", measureField);
            this.measureFieldService.update(measureField);
        } else if (FinalValues.VALUE.equals(measureType)) {
            Long measureFieldId = Long.valueOf(req.getParameter("measureFieldId"));
            Double value = Double.valueOf(req.getParameter("measureValue"));
            Date timeStamp = Date.valueOf(req.getParameter("Date"));

            MeasureValue measureValue = new MeasureValue();
            measureValue.setId(id);
            measureValue.setValue(value);
            measureValue.setMeasureFieldId(measureFieldId);
            measureValue.setDate(timeStamp);

            log.info("Editing measure-value: " + measureValue);
            req.setAttribute("measure", measureValue);
            this.measureValueService.update(measureValue);
        } else {
            log.error("Invalid Measure Type while trying to save Goal!");
            throw new MeasureTypeException("Invalid Measure Type!");
        }
        req.setAttribute("successfulEdit", true);
        // TODO implement forward after actions
        req.getRequestDispatcher("/listMeasures.jsp").forward(req, resp);
    }
}
