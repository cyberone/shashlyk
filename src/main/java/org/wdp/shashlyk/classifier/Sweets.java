package org.wdp.shashlyk.classifier;

import com.jcabi.log.Logger;
import org.wdp.shashlyk.parser.Dish;

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
