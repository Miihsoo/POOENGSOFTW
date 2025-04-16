package primeiroprojeto.projeto.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping("/api")
public class testController {

    @RequestMapping("/primeiro-projeto")
    public string Test(){
        return "Meu primeiro projeto spring";
    }

    
}
