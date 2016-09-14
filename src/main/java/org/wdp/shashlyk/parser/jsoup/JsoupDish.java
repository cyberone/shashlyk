package org.wdp.shashlyk.parser.jsoup;

import com.jcabi.log.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.wdp.shashlyk.classifier.DishClass;
import org.wdp.shashlyk.parser.Dish;

/**
 * Jsoup based dish.
 * @author Aleksey Popov (alopen@yandex.ru)
 */
class JsoupDish implements Dish {
    private static final Pattern PATTERN = Pattern.compile("_БЛ", Pattern.LITERAL);
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
    public String getPrettyName() {
        Logger.debug(this, "getPrettyName()");
        return JsoupDish.PATTERN.matcher(this.getName()).replaceAll(Matcher.quoteReplacement(""));
    }

    @Override
    public DishClass getCls() {
        throw new UnsupportedOperationException("getCls");
    }

    @Override
    public String toString() {
        return String.format("Dish(%s){%s}", this.getName(), this.data);
    }
}
