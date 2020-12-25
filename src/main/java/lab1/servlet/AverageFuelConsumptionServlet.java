package lab1.servlet;

import lab1.EntityManagerProvider;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AverageFuelConsumptionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Double result = EntityManagerProvider.provide()
                .createNamedQuery("avgFuelConsumption", Double.class)
                .getResultList()
                .get(0);
        resp.getWriter().print(result);
    }
}
