package org.wdp.shashlyk.classifier;

import com.jcabi.log.Logger;
import java.util.Random;
import org.wdp.shashlyk.parser.Dish;
import org.wdp.shashlyk.parser.simple.SimpleDish;

/**
 * @author Aleksey Popov (alopen@yandex.ru)
 */
class Sweets implements DishCategory {
    @Override
    public boolean contains(final Dish dish) {
        Logger.debug(this, "contains(%s)", dish);
        for (final Sweets.SweetNames name : Sweets.SweetNames.values()) {
            if (name.getName().equals(dish.getName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Dish random() {
        final Sweets.SweetNames data = Sweets.SweetNames.values()[
            new Random().nextInt(Sweets.SweetNames.values().length)];
        return new SimpleDish(data.getName(), DishClass.BOYARS);
    }

    @Override
    public Dish random(final DishClass clazz) {
        return this.random();
    }

    @Override
    public Dish strictRandom(final DishClass cls) {
        Logger.debug(this, "strictRandom()");
        throw new UnsupportedOperationException("#strictRandom)");
    }

    private enum SweetNames {
        EKLER("Пирожное \"Эклер\"_БЛ"),
        BIRD_MILK("Пирожное \"Птичье молоко\"_БЛ"),
        POTATO("Пирожное \"Картошка\"_БЛ"),
        PANCAKES("Блинчики_БЛ"),
        BELGIAN_WAFER("Бельгийская вафля_БЛ")
        ;

        private final String name;

        SweetNames(final String dish) {
            this.name = dish;
        }

        public String getName() {
            return this.name;
        }
    }


}
