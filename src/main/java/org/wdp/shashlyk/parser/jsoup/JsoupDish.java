package org.wdp.shashlyk.parser.jsoup;

import org.jsoup.nodes.Element;
import org.wdp.shashlyk.parser.Dish;

/**
 * Jsoup based dish.
 * @author Aleksey Popov (alopen@yandex.ru)
 */
public class JsoupDish implements Dish {
    private final Element data;

    public JsoupDish(final Element element) {
        this.data = element;
    }
}
