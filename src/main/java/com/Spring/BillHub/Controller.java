package src.main.java.com.Spring.BillHub;

@Controller
public class GreetingController {

    @GetMapping("/welcome")
    public String welcome(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "welcome";
    }

}