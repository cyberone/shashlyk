package org.wdp.shashlyk;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.wdp.shashlyk.classifier.DishClass;
import org.wdp.shashlyk.classifier.DishClassifier;

/**
 * Main controller.
 * @author Aleksey Popov (alopen@yandex.ru)
 */
@Controller
public class MainController {
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView index() {
        return new ModelAndView("index", this.getDefaultModel());
    }

    private Map<String, ?> getDefaultModel() {
        final Map<String, Object> result = new HashMap<>(1);
        Arrays.asList(DishClassifier.SALAD, DishClassifier.SOUP, DishClassifier.HOT, DishClassifier.DRINK)
            .stream().forEach(classifier -> result.put(classifier.name(), classifier.random()));
        final List<Menu> menus = Arrays.stream(DishClass.values()).map(MainController::toMenu).collect(Collectors.toList());
        result.put("menus", menus);
        return result;
    }

    private static Menu toMenu(final DishClass cls) {
        return new SimpleMenu(cls);
    }
}
