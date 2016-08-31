package org.wdp.shashlyk.parser.jsoup;

import com.jcabi.log.Logger;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.jsoup.select.Elements;
import org.wdp.shashlyk.parser.Dish;
import org.wdp.shashlyk.parser.Dishes;

/**
 * @author Aleksey Popov (alopen@yandex.ru)
 */
class JsoupDishes implements Dishes {
    private final Elements dishes;

    JsoupDishes(final Elements elements) {
        this.dishes = elements.clone();
    }

    @Override
    public Iterator<Dish> iterator() {
        Logger.debug(this, "iterator()");
        return new JsoupDishes.DishIterator(this.dishes);
    }

    private static class DishIterator implements Iterator<Dish> {
        private final Elements dishes;
        private int current;

        private DishIterator(final Elements elements) {
            this.dishes = elements;
            this.current = 0;
        }

        @Override
        public boolean hasNext() {
            Logger.debug(this, "hasNext()");
            return this.current < this.dishes.size() - 1;
        }

        @Override
        public Dish next() {
            Logger.debug(this, "next()");
            if (this.current >= this.dishes.size()) {
                throw new NoSuchElementException(
                    String.format(
                        "Index %s of %s", this.current, this.dishes.size()
                    )
                );
            }
            final Dish result = new JsoupDish(this.dishes.get(this.current));
            this.current += 1;
            return result;
        }
    }
}
