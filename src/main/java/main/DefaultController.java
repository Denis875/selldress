package main;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class DefaultController
{
    @RequestMapping
    public static String Default()
    {
        return "Hello world";
    }
}
