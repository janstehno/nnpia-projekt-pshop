package application.controllers.error;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, HttpServletResponse response) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        String errorMessage;

        if (statusCode != null) {
            errorMessage = switch (statusCode) {
                case 404 -> "The resource you are looking for is not found.";
                case 500 -> "Internal server error occurred.";
                default -> "An error occurred.";
            };
        } else {
            errorMessage = "An unexpected error occurred.";
        }

        return errorMessage;
    }

}
