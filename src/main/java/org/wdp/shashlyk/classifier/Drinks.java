package org.wdp.shashlyk.classifier;

import com.jcabi.log.Logger;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
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
        final Drinks.DrinkNames data = Drinks.DrinkNames.values()[
            new Random().nextInt(Drinks.DrinkNames.values().length)];
        return new SimpleDish(data.getName(), data.getCls());
    }

    @Override
    public Dish random(final DishClass clazz) {
        Logger.debug(this, "random(%s)", clazz);
        final List<Drinks.DrinkNames> list = Arrays.stream(
            Drinks.DrinkNames.values()
        ).filter(
            drink -> drink.getCls().ordinal() <= clazz.ordinal()
        ).collect(Collectors.toList());
        final Drinks.DrinkNames dish = list.get(
            new Random().nextInt(list.size())
        );
        return new SimpleDish(dish.getName(), clazz);
    }

    @Override
    public Dish strictRandom(final DishClass cls) {
        Logger.debug(this, "strictRandom()");
        throw new UnsupportedOperationException("#strictRandom)");
    }

    private enum DrinkNames {
        BLACK_CURRANT("Чёрная смородина", DishClass.PLEBS),
        ROWAN("Рябина", DishClass.PLEBS),
        BLACK_TEA("Чай чёрный пакетированный", DishClass.PLEBS),
        GREEN_TEA("Чай зелёненький пакетированный", DishClass.PLEBS),
        CHERRY("Морс \"Дикая вишня\"", DishClass.PROLETARIAT),
        CRANBERRIES("Морс клюквенный", DishClass.PROLETARIAT),
        SEA_BUCKTHORN("Облепиха", DishClass.PROLETARIAT),
        ;

        private final String name;
        private final DishClass clazz;

        DrinkNames(final String dish, final DishClass cls) {
            this.name = dish;
            this.clazz = cls;
        }

        public String getName() {
            return this.name;
        }

        public DishClass getCls() {
            return this.clazz;
        }
    }

}
