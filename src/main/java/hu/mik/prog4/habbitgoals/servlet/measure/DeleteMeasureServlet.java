package hu.mik.prog4.habbitgoals.servlet.measure;

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

@Log4j2
public class DeleteMeasureServlet extends HttpServlet {
    private MeasureFieldService measureFieldService;
    private MeasureValueService measureValueService;


    @Override
    public void init() throws ServletException {
        super.init();
        this.measureFieldService = new MeasureFieldService();
        this.measureValueService = new MeasureValueService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        if(FinalValues.FIELD.equals(req.getParameter("type"))){
            measureFieldService.deleteById(id);
        }else if (FinalValues.VALUE.equals(req.getParameter("type"))) {
            measureValueService.deleteById(id);
        }else {
            log.error("Invalid Measure Type while trying to delete Goal!");
            throw new MeasureTypeException("Invalid Measure Type!");
        }
        req.setAttribute("successfulDelete", true);
        req.getRequestDispatcher("/listMeasures.jsp").forward(req, resp);
    }
}
