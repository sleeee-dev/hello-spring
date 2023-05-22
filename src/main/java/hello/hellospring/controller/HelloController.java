package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    //get방식
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "spring!!");

        return "hello";
    }

    @GetMapping("hello-mvc")
    public String hellomvc(@RequestParam(name = "name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello; //json 방식으로 출력 : 최근에는 모두 이런 방식으로 하는 것이 기본
    }

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

//model의 data라는 이름에 hello!!라는 값을 넣어줌
//컨트롤러에서 리턴값으로 문자를 반환하면 viewResolver가 화면을 찾아서 처리함
//스프링부트 템플릿엔진 기본 viewName 매핑
//`resources:templates/`+{ViewName}+`.heml`
//즉 return "hello"; 는 다음과 같이 처리됨
//resources:templates/hello.html 을 찾아서 이동해서 타임리프 템플릿 엔진이 처리
//그래서 model의 attrV가 hello.html의 ${data}로 출력된다

//jsp를 사용하게 되면 설정을 따로 추가해줘야함

//spring-boot-devtools 라이브러리를 추가하면 html 파일을 컴파일만 해주면 서버 재시작 없이 View 파일 변경 가능

//api방식
//@ResponseBody : http의 body에 문자 내용을 직접 반환
//viewResolver 대신 HttpMessageConverter가 동작
//hello-string에 name을 spring이라 넣으면 hello spring이라고 나옴
//hello mvc와의 차이 : html 형식이 아닌 string 형식으로 출력 => StringConverter가 작동
//hello-api는 객체를 사용해서 Json 형식으로 출력 => JsonConverter가 작동 => 요즘은 다 이렇게 사용

//ctrl+shift+Enter : 세미콜론 추가해서 자동완성
//ctrl+shift+V : 변수생성 자동완성