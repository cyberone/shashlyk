package org.wdp.shashlyk.parser.jsoup;

import com.jcabi.log.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.wdp.shashlyk.parser.Dish;

/**
 * Jsoup based dish.
 * @author Aleksey Popov (alopen@yandex.ru)
 */
class JsoupDish implements Dish {
    private final Element data;

    JsoupDish(final Element element) {
        this.data = element;
    }

    @Override
    public String getName() {
        Logger.debug(this, "getName()");
        final Elements elements = this.data.getElementsByClass("dish-title");
        if (elements.isEmpty()) {
            throw new IllegalStateException(
                String.format(
                    "No dish-title fot dish %s", this.data.text()
                )
            );
        }
        return elements.get(0).text();
    }

    @Override
    public String toString() {
        return String.format("Dish(%s)", this.getName());
    }
}
