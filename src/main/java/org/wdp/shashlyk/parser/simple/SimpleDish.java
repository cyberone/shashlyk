package org.wdp.shashlyk.parser.simple;

import com.jcabi.log.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.wdp.shashlyk.parser.Dish;

/**
 * @author Aleksey Popov (alopen@yandex.ru)
 */
public class SimpleDish implements Dish {
    private static final Pattern PATTERN = Pattern.compile("_БЛ", Pattern.LITERAL);
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

    @Override
    public String getPrettyName() {
        Logger.debug(this, "getPrettyName()");
        return PATTERN.matcher(this.getName()).replaceAll(Matcher.quoteReplacement(""));
    }
}
