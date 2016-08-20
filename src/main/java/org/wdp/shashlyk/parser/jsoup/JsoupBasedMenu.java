package org.wdp.shashlyk.parser.jsoup;

import com.jcabi.log.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.wdp.shashlyk.parser.Combo;
import org.wdp.shashlyk.parser.Combos;
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
    public Iterable<Combo> combos() {
        Logger.debug(this, "combos()");
        final Element content = this.doc.getElementById("content-inside");
        return new Combos(content);
    }
}
