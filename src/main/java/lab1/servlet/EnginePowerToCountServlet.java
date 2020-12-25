package lab1.servlet;

import com.google.gson.Gson;
import lab1.EntityManagerProvider;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EnginePowerToCountServlet extends HttpServlet {

    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Object> result = EntityManagerProvider.provide()
                .createNamedQuery("enginePowerToCount", Object.class)
                .getResultList();
        resp.getWriter().write(gson.toJson(result));
    }
}
