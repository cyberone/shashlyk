package org.wdp.shashlyk.classifier;

import com.jcabi.log.Logger;
import java.util.Random;
import org.wdp.shashlyk.parser.Dish;
import org.wdp.shashlyk.parser.simple.SimpleDish;

/**
 * @author Aleksey Popov (alopen@yandex.ru)
 */
public class Garnishes implements DishCategory {
    @Override
    public boolean contains(final Dish dish) {
        Logger.debug(this, "contains(%s)", dish);
        for (final Garnishes.GarnishNames name : Garnishes.GarnishNames.values()) {
            if (name.getName().equals(dish.getName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Dish random() {
        Logger.debug(this, "random()");
        final Garnishes.GarnishNames name = Garnishes.GarnishNames.values()[
            new Random().nextInt(Garnishes.GarnishNames.values().length)];
        return new SimpleDish(name.getName(), DishClass.PLEBS);
    }

    @Override
    public Dish random(final DishClass clazz) {
        Logger.debug(this, "random(%s)", clazz);
        return this.random();
    }

    @Override
    public Dish strictRandom(final DishClass cls) {
        throw new UnsupportedOperationException("#strictRandom)");
    }

    private enum GarnishNames {
        PUREE("Пюре"),
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
