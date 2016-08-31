package org.wdp.shashlyk.parser.jsoup;

import com.jcabi.log.Logger;
import org.jsoup.nodes.Document;
import org.wdp.shashlyk.parser.Dishes;
import org.wdp.shashlyk.parser.Menu;

/**
 * {@link Menu} implementation based on Jsoup.
 * @author Aleksey Popov (alopen@yandex.ru)
 */
public class JsoupBasedMenu implements Menu {
    private final Document doc;

    public JsoupBasedMenu(final Document document) {
        this.doc = document;
    }

    @Override
    public Dishes dishes() {
        Logger.debug(this, "dishes()");
        return new JsoupDishes(this.doc.getElementsByClass("dish"));
    }
}
