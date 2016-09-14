package org.wdp.shashlyk.parser;

import com.jcabi.log.Logger;
import java.util.Locale;
import java.util.function.Predicate;
import org.wdp.shashlyk.classifier.DishClass;

/**
 * Lunch dish.
 * @author Aleksey Popov (alopen@yandex.ru)
 */
public interface Dish {
    String getName();

    String getPrettyName();

    DishClass getCls();

    class IsBusinessLunch implements Predicate<Dish> {
        @Override
        public boolean test(final Dish obj) {
            Logger.debug(this, "IsBusinessLunch(%s)", obj);
            return obj.getName().toUpperCase(Locale.getDefault()).endsWith("_БЛ");
        }
    }
}
