package org.wdp.shashlyk.classifier;

import com.jcabi.log.Logger;
import org.wdp.shashlyk.parser.Dish;

/**
 * @author Aleksey Popov (alopen@yandex.ru)
 */
class Soups implements DishCategory {
    @Override
    public boolean contains(final Dish dish) {
        Logger.debug(this, "contains(%s)", dish);
        for (final Soups.SoupNames name : Soups.SoupNames.values()) {
            if (name.getName().equals(dish.getName())) {
                return true;
            }
        }
        return false;
    }

    private enum SoupNames {
        SOUP_GOROH("Суп-пюре гороховый_БЛ"),
        BORSHCH("Борщ_БЛ"),
        LIGHT("Суп \"Легкий\"_БЛ"),
        SHASHLIKOFF("Суп \"Шашлыкофф\" с курицей_БЛ"),
        TUTA_LARSEN("Суп куриный Тута Ларсен (Новинка)_БЛ"),
        CHEESE_CREAM("Сырный крем-суп_БЛ"),
        SALTWORT("Солянка_БЛ"),
        OH_BABY_HAM("Окрошка с ветчиной (Новинка)_БЛ"),
        OH_BABY_BEEF("Окрошка с говядиной (Новинка)_БЛ"),
        ;

        private final String name;

        SoupNames(final String dish) {
            this.name = dish;
        }

        public String getName() {
            return this.name;
        }
    }

}
