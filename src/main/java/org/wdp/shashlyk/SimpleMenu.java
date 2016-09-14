package org.wdp.shashlyk;

import com.jcabi.log.Logger;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;
import org.wdp.shashlyk.classifier.DishClass;
import org.wdp.shashlyk.classifier.DishClassifier;
import org.wdp.shashlyk.parser.Dish;

/**
 * Menu implementation.
 * @author Aleksey Popov (alopen@yandex.ru)
 */
public class SimpleMenu implements Menu {
    private final DishClass clazz;
    private Dish salad;
    private Dish soup;
    private Dish hot;
    private Dish drink;

    SimpleMenu(final DishClass cls) {
        this.clazz = cls;
        this.rollDishes();
    }

    private void rollDishes() {
        Logger.debug(this, "rollDishes(%s)", this.clazz);
        do {
            this.salad = DishClassifier.SALAD.random(this.clazz);
            this.soup = DishClassifier.SOUP.random(this.clazz);
            this.hot = DishClassifier.HOT.random(this.clazz);
        } while (!new DishChooser(this.salad, this.soup, this.hot).hasClass(this.clazz));
        this.drink = DishClassifier.DRINK.random(this.clazz);
    }

    @Override
    public String getTitle() {
        return this.clazz.getTitle();
    }

    public Dish getSalad() {
        return this.salad;
    }

    public Dish getSoup() {
        return this.soup;
    }

    public Dish getDrink() {
        return this.drink;
    }

    public Dish getHot() {
        return this.hot;

    }

    private class DishChooser {
        private final Collection<Dish> data;

        public DishChooser(final Dish ...dishes) {
            this.data = Arrays.asList(dishes);
        }

        public boolean hasClass(final DishClass clazz) {
            return this.data.stream().anyMatch(new Predicate<Dish>() {
                @Override
                public boolean test(final Dish dish) {
                    return dish.getCls() == clazz;
                }
            });
        }
    }
}
