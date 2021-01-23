package lab1;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;

public final class ServletHelper {

    private ServletHelper() {
    }

    public static String getBody(HttpServletRequest req) throws IOException {
        int contentLength = req.getContentLength();
        char[] buf = new char[contentLength];
        Reader reader = req.getReader();
        int bytesRead = 0;
        do {
            bytesRead += reader.read(buf, bytesRead, contentLength - bytesRead);
        } while (bytesRead < contentLength);
        reader.close();
        return new String(buf);
    }

    private static void setResponse(HttpServletResponse resp, int status, String message) throws IOException {
        resp.setStatus(status);
        resp.getWriter().write(message);
    }

    public static void setBadRequest(HttpServletResponse resp, String message) throws IOException {
        setResponse(resp, HttpServletResponse.SC_BAD_REQUEST, message);
    }

    public static void setNotFound(HttpServletResponse resp, String message) throws IOException {
        setResponse(resp, HttpServletResponse.SC_NOT_FOUND, message);
    }
}
