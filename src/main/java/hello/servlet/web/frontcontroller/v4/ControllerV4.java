package hello.servlet.web.frontcontroller.v4;

import java.util.Map;

public interface ControllerV4 {

    /**
     * @Param paramMap
     * @Param model
     * @return viewName
     */

    String process(Map<String, String> paramMap, Map<String, Object> model);
}
