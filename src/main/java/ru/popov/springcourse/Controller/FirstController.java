package ru.popov.springcourse.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/first")
public class FirstController {

    @GetMapping("/calculator")
    public String calculate(
            @RequestParam(value = "number1", required = false) int number1,
            @RequestParam(value = "operation") String operation,
            @RequestParam(value = "number2", required = false) int number2,
            Model model) {
        int result;
        switch (operation) {
            case "1":
                result = number1 * number2;
                break;
            case "2":
                result =  number1 + number2;
                break;
            case "3":
                result = number1 - number2;
                break;
            case "4":
                if (number2 != 0) {
                    result = number1 / number2;
                    break;
                } else throw new ArithmeticException();
            default:
                throw new IllegalStateException("Unexpected value: " + operation);
        }
        model.addAttribute("result" , result);
        return "first/hello";

    }

    @GetMapping("/calculator1")
    public String calc(@RequestParam("a") int a,
                       @RequestParam("b") int b,
                       @RequestParam("action") String action,
                       Model model) {
        double result;

        switch (action) {
            case "multiplication":
                result = a * b;
                break;
            case  "division":
                result = a / (double) b;
                break;
            case "subtraction":
                result = a - b;
                break;
            case  "addition":
                result = a + b;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + action);
        }
        model.addAttribute("result", result);
        return "first/calculator";
    }

    @GetMapping("/hello")
    public String helloPage(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "surname", required = false) String surname,
            Model model) {

        //System.out.println(name + " " + surname);
        model.addAttribute("message", name + " " + surname);
        return "first/hello";
    }

    @GetMapping("/goodbye")
    public String goodByePage() {
        return "first/goodbye";
    }

}
