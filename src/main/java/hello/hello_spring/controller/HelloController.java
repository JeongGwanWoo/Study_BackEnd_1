package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping("hello-string")
    @ResponseBody // http에서 body부에 return 값을 직접 넣겠다는 의미.
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; // name = spring 이라면 "hello spring"
    }

    @GetMapping("hello-api")
    @ResponseBody // 기본으로 json으로 반환
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello; // 객체를 반환한다.
    }
    /*
    @ResponseBody 사용
    HTTP의 BODY에 문자 내용을 직접 반환
    'viewResolver' 대신에 'HttpMessageConverter'가 동작
    기본 문자처리 : 'StringHttpMessageConverter'
    기본 객체처리 : 'MappingJackson2HttpMessageConverter'
    byte 처리 등등 기타 여러 HttpMessageConverter가 기본으로 등록되어 있음
    참고 : 클라이언트의 HTTP Accept 해더와 서버의 컨트롤러 반환 타입 정보 둘을 조합해서
    'HttpMessageConverter' 가 선택된다.
     */

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}


