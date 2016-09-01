package org.wdp.shashlyk.classifier;

import com.jcabi.log.Logger;
import org.wdp.shashlyk.parser.Dish;

/**
 * @author Aleksey Popov (alopen@yandex.ru)
 */
class Drinks implements DishCategory {
    @Override
    public boolean contains(final Dish dish) {
        Logger.debug(this, "contains(%s)", dish);
        for (final Drinks.DrinkNames name : Drinks.DrinkNames.values()) {
            if (name.getName().equals(dish.getName())) {
                return true;
            }
        }
        return false;
    }

    private enum DrinkNames {
        ;

        private final String name;

        DrinkNames(final String dish) {
            this.name = dish;
        }

        public String getName() {
            return this.name;
        }
    }

}
