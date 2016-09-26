package org.wdp.shashlyk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
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
    private boolean initialized = false;

    SimpleMenu(final DishClass cls) {
        this.clazz = cls;
    }

    private void roll(final DishClass cls) {
        final List<SimpleMenu.Chooser> choices = new ArrayList<>(
            Arrays.asList(SimpleMenu.Chooser.values())
        );
        final SimpleMenu.Chooser strict = choices.remove(new Random().nextInt(choices.size()));
        strict.setStrict(this, cls);
        for (final SimpleMenu.Chooser choice : choices) {
            choice.set(this, choice.getClazz().random(cls));
        }
        this.drink = DishClassifier.DRINK.random(cls);
        this.initialized = true;
    }

    @Override
    public String getTitle() {
        return this.clazz.getTitle();
    }

    public Dish getSalad() {
        if (!this.initialized) {
            this.roll(this.clazz);
        }
        return this.salad;
    }

    public Dish getSoup() {
        if (!this.initialized) {
            this.roll(this.clazz);
        }
        return this.soup;
    }

    public Dish getDrink() {
        if (!this.initialized) {
            this.roll(this.clazz);
        }
        return this.drink;
    }

    public Dish getHot() {
        if (!this.initialized) {
            this.roll(this.clazz);
        }
        return this.hot;
    }

    private void setSalad(Dish salad) {
        this.salad = salad;
    }

    private void setSoup(Dish soup) {
        this.soup = soup;
    }

    private void setHot(Dish hot) {
        this.hot = hot;
    }

    private enum Chooser {
        SALAD(DishClassifier.SALAD) {
            @Override
            public void set(final SimpleMenu menu, final Dish dish) {
                menu.setSalad(dish);
            }
        },
        SOUP(DishClassifier.SOUP) {
            @Override
            public void set(final SimpleMenu menu, final Dish dish) {
                menu.setSoup(dish);
            }
        },
        HOT(DishClassifier.HOT) {
            @Override
            public void set(final SimpleMenu menu, final Dish dish) {
                menu.setHot(dish);
            }
        };

        private final DishClassifier clazz;

        Chooser(final DishClassifier cls) {
            this.clazz = cls;
        }

        public void setStrict(final SimpleMenu menu, final DishClass cls) {
            final Dish dish = this.getClazz().strictRandom(cls);
            this.set(menu, dish);
        }

        public abstract void set(SimpleMenu menu, Dish dish);

        public DishClassifier getClazz() {
            return this.clazz;
        }
    }
}
