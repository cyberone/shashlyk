package org.wdp.shashlyk.classifier;

import com.jcabi.log.Logger;
import java.util.Random;
import org.wdp.shashlyk.parser.Dish;
import org.wdp.shashlyk.parser.simple.SimpleDish;

/**
 * @author Aleksey Popov (alopen@yandex.ru)
 */
class Salads implements DishCategory {
    @Override
    public boolean contains(final Dish dish) {
        Logger.debug(this, "contains(%s)", dish);
        for (final Salads.SaladNames name : Salads.SaladNames.values()) {
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
            Salads.SaladNames.values()[
                new Random().nextInt(Salads.SaladNames.values().length)]
                .getName()
        );
    }

    private enum SaladNames {
        HERING("Сельдь под шубой_БЛ"),
        VINEGRET("Винегрет_БЛ"),
        VITAMINS("Салат Витаминный (Новинка)_БЛ"),
        OLIVE("Оливье (Новинка)_БЛ"),
        PRESIDENTS("Салат \"Президентский\"_БЛ"),
        HUNTERS("Салат \"Охотничий\"_БЛ"),
        HAM_AND_PINEAPPLES("Салат с ветчиной и ананасами_БЛ"),
        WOODPECKERS_NEST("Салат Гнездо дятла (Новинка)_БЛ"),
        MOSCOW("Салат \"Московский\"_БЛ"),
        CESAR("Салат \"Цезарь\"_БЛ")
        ;

        private final String name;

        SaladNames(final String dish) {
            this.name = dish;
        }

        public String getName() {
            return this.name;
        }
    }
}
