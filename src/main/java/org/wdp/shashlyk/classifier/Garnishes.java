package org.wdp.shashlyk.classifier;

import com.jcabi.log.Logger;
import org.wdp.shashlyk.parser.Dish;

/**
 * @author Aleksey Popov (alopen@yandex.ru)
 */
public class Garnishes implements DishCategory {
    @Override
    public boolean contains(final Dish dish) {
        Logger.debug(this, "contains(%s)", dish);
        for (final GarnishNames name : GarnishNames.values()) {
            if (name.getName().equals(dish.getName())) {
                return true;
            }
        }
        return false;
    }

    private enum GarnishNames {
        PUREE("Поре"),
        FRIED("Жареная картоха"),
        RICE("Рис")
        ;

        private final String name;

        GarnishNames(final String dish) {
            this.name = dish;
        }

        public String getName() {
            return this.name;
        }
    }

}
