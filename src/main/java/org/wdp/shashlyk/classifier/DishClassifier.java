package org.wdp.shashlyk.classifier;

import com.jcabi.log.Logger;
import java.util.Optional;
import org.wdp.shashlyk.parser.Dish;

/**
 * Classifier of dishes.
 * @author Aleksey Popov (alopen@yandex.ru)
 */
public enum DishClassifier {
    SALAD(new Salads()),
    SOUP(new Soups()),
    HOT(new HotDishes()),
    SWEET(new Sweets()),
    DRINK(new Drinks());

    private final DishCategory category;

    DishClassifier(final DishCategory cat) {
        this.category = cat;
    }

    public static Optional<DishCategory> getCategory(final Dish dish) {
        Logger.debug(DishClassifier.class, "getCategory(%s)", dish);
        for (final DishClassifier classifier : DishClassifier.values()) {
            if (classifier.contains(dish)) {
                return Optional.of(classifier.category);
            }
        }
        return Optional.empty();
    }

    private boolean contains(final Dish dish) {
        return this.category.contains(dish);
    }
}
