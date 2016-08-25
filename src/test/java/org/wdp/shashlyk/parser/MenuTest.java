package org.wdp.shashlyk.parser;

import com.google.common.collect.Iterables;
import java.io.IOException;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.jsoup.Jsoup;
import org.junit.Test;
import org.wdp.shashlyk.parser.jsoup.JsoupBasedMenu;

/**
 * Test case for shashlykoff menu parser.
 * @author Aleksey Popov (alopen@yandex.ru)
 */
public class MenuTest {
    @Test
    public void canFetchCombos() throws Exception {
        final Menu menu = new JsoupBasedMenu(
            Jsoup.parse(
                this.getClass().getClassLoader().getResourceAsStream(
                    "menu_sample.html"
                ),
                "UTF-8",
                "http://shashlikoff.ru"
            )
        );
        MatcherAssert.assertThat(
            menu.combos(),
            CoreMatchers.not(
                Matchers.emptyIterable()
            )
        );
    }

    @Test
    public void canFetchDishes() throws IOException {
        final Menu menu = new JsoupBasedMenu(
            Jsoup.parse(
                this.getClass().getClassLoader().getResourceAsStream(
                    "menu_sample.html"
                ),
                "UTF-8",
                "http://shashlikoff.ru"
            )
        );
        MatcherAssert.assertThat(
            Iterables.getFirst(menu.combos(), null).dishes(),
            CoreMatchers.not(
                Matchers.emptyIterable()
            )
        );
    }
}