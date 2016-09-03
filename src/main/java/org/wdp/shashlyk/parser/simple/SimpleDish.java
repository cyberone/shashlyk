package org.wdp.shashlyk.parser.simple;

import org.wdp.shashlyk.parser.Dish;

/**
 * @author Aleksey Popov (alopen@yandex.ru)
 */
public class SimpleDish implements Dish {
    private final String name;
    private Dish garnish = null;

    public SimpleDish(final String dish) {
        this.name = dish;
    }

    public SimpleDish(final String dish, final Dish addition) {
        this(dish);
        this.garnish = addition;
    }

    @Override
    public String getName() {
        if (this.garnish != null) {
            return String.format("%s и %s", this.name, this.garnish.getName());
        }
        return this.name;
    }
}
