package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "spring!!");
        return "hello"; // 컨트롤러에서 리턴 값으로 문자를 반환하면 뷰 리졸버 (viewResolver) 가 화면을 찾아서 처리한다.
        //스프링 부트 템플릿엔진 기본 viewName 매핑
        //resources/templates/hello.html
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        // 주소창의 hello-mvc?name= 뒤의 값을 넘겨준다.
        // ex) /hello-mvc?name=spring!! -> spring!!을 넘겨준다.
        model.addAttribute("name", name);
        return "hello-template"; // templates/hello-template.html 을 찾는다.
    }
}
