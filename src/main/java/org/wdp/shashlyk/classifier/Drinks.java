package org.wdp.shashlyk.classifier;

import com.jcabi.log.Logger;
import java.util.Random;
import org.wdp.shashlyk.parser.Dish;
import org.wdp.shashlyk.parser.simple.SimpleDish;

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

    @Override
    public Dish random() {
        Logger.debug(this, "random()");
        return new SimpleDish(
            Drinks.DrinkNames.values()[
                new Random().nextInt(Drinks.DrinkNames.values().length)].getName()
        );
    }

    private enum DrinkNames {
        BLACK_CURRANT("Чёрная смородина"),
        ROWAN("Рябина"),
        TEA("Чай пакетированный"),
        CHERRY("Морс \"Дикая вишня\""),
        CRANBERRIES("Морс клюквенный"),
        SEA_BUCKTHORN("Облепиха"),
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
