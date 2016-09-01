package org.wdp.shashlyk.parser;

import com.jcabi.log.Logger;
import java.io.IOException;
import java.net.URL;
import java.util.function.Consumer;
import java.util.stream.StreamSupport;
import org.jsoup.Jsoup;
import org.wdp.shashlyk.classifier.DishClassifier;
import org.wdp.shashlyk.parser.jsoup.JsoupBasedMenu;

/**
 * Test that chacks that all business-linch dishes have corresponding enum element.
 * @author Aleksey Popov (alopen@yandex.ru)
 */
public final class IntegrationTest {
    private IntegrationTest() {
    }

    public static void main(final String... args) {
        try {
            Logger.info(IntegrationTest.class, "Fetching menu");
            final Menu menu = new JsoupBasedMenu(
                Jsoup.parse(
                    new URL("http://shashlik54.ru/delivery/"), 10000
                )
            );
            Logger.info(IntegrationTest.class, "Parsing");
            StreamSupport.stream(
                menu.dishes().spliterator(), false
            ).filter(new Dish.IsBusinessLunch()).forEach(
                new Consumer<Dish>() {
                    @Override
                    public void accept(final Dish dish) {
                        Logger.debug(this, "accept(%s)", dish);
                        if (!DishClassifier.getCategory(dish).isPresent()) {
                            throw new IllegalStateException(
                                String.format(
                                    "Dish %s has no category", dish.getName()
                                )
                            );
                        }
                    }
                }
            );
        } catch (final IOException e) {
            Logger.error(IntegrationTest.class, "Caught %[exception]s", e);
        }
    }
}
