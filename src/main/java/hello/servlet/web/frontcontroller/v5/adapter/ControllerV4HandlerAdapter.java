package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v4.ControllerV4;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV4);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        ControllerV4 controller = (ControllerV4) handler;

        Map<String, String> paramMap = createParamMap(request);
        HashMap<String, Object> model = new HashMap<>();

        // handler에 맞는 uri 호출
        // request를 통해서 받아온 정보들을 process를 통해서 model에 담음
        String viewName = controller.process(paramMap, model);

        // 맞는 어댑터를 ModelView에 적용
        // model은 따로 세팅

        // ControllerV4와는 다르게 viewName을 반환하는 것이 아니라 ModelView까지 만들어서 반환한다.
        // 이것이 adapter가 하는 역할이다.
        ModelView mv = new ModelView(viewName);

        // 내용이 담긴 model을 ModelView 객체에 넣는다.
        mv.setModel(model);

        return mv;
    }

    // request로 들어온 정보들을 Map에 담는다.
    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
