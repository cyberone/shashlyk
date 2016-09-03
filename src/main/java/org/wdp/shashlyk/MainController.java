package org.wdp.shashlyk;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
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
        for (final DishClassifier classifier : new DishClassifier[]{DishClassifier.SALAD, DishClassifier.SOUP, DishClassifier.HOT, DishClassifier.DRINK}) {
            result.put(classifier.name(), classifier.random());
        }
        return result;
    }
}
