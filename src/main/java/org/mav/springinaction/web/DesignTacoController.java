package org.mav.springinaction.web;

import lombok.extern.slf4j.Slf4j;
import org.mav.springinaction.Ingridient;
import org.mav.springinaction.Ingridient.Type;
import org.mav.springinaction.Taco;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {

    @GetMapping
    public String showDesignForm(Model model) {
        List<Ingridient> ingridients = Arrays.asList(
                new Ingridient("FLTO", "Flour Tortilla", Type.WRAP),
                new Ingridient("COTO", "Corn Tortilla", Type.WRAP),
                new Ingridient("GRBF", "Ground Beef", Type.PROTEIN),
                new Ingridient("CARN", "Carnitas", Type.PROTEIN),
                new Ingridient("TMTO", "Diced Tomatoes", Type.VEGGIES),
                new Ingridient("LETC", "Lettuce", Type.VEGGIES),
                new Ingridient("CHED", "Cheddar", Type.CHEESE),
                new Ingridient("JACK", "Monterrey Jack", Type.CHEESE),
                new Ingridient("SLSA", "Salsa", Type.SAUCE),
                new Ingridient("SRCR", "Sour Cream", Type.SAUCE)
        );

        Type[] types = Ingridient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingridients, type));
        }

        model.addAttribute("design", new Taco());

        return "design";
    }

    @PostMapping
    public String processDesign(Taco design) {
        log.info("Processing design: " + design);
        return "redirect:/orders/current";
    }

    private List<Ingridient> filterByType(List<Ingridient> ingredients, Type type) {
        return ingredients.stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

}
