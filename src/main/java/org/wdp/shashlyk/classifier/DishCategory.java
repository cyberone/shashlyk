package org.wdp.shashlyk.classifier;

import org.wdp.shashlyk.parser.Dish;

/**
 * category of dishes: salad, soup, hot dish, drink e.t.c
 * @author Aleksey Popov (alopen@yandex.ru)
 */
interface DishCategory {
    boolean contains(Dish dish);

    default boolean needsGarnish(Dish dish) {
        return false;
    }

    Dish random();

    Dish random(DishClass clazz);

    Dish strictRandom(DishClass cls);
}
