package org.wdp.shashlyk.classifier;

import com.jcabi.log.Logger;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import org.wdp.shashlyk.parser.Dish;
import org.wdp.shashlyk.parser.simple.SimpleDish;

/**
 * @author Aleksey Popov (alopen@yandex.ru)
 */
class Soups implements DishCategory {
    @Override
    public boolean contains(final Dish dish) {
        Logger.debug(this, "contains(%s)", dish);
        for (final Soups.SoupData name : Soups.SoupData.values()) {
            if (name.getName().equals(dish.getName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Dish random() {
        Logger.debug(this, "random()");
        final Soups.SoupData data = Soups.SoupData.values()[
            new Random().nextInt(Soups.SoupData.values().length)];
        return new SimpleDish(data.getName(), data.getCls());
    }

    @Override
    public Dish random(final DishClass clazz) {
        Logger.debug(this, "random(%s)", clazz);
        final List<Soups.SoupData> list = Arrays.stream(
            Soups.SoupData.values()
        ).filter(
            soupData -> soupData.getCls().ordinal() <= clazz.ordinal()
        ).collect(Collectors.toList());
        final Soups.SoupData dish = list.get(new Random().nextInt(list.size()));
        return new SimpleDish(dish.getName(), clazz);
    }

    @Override
    public Dish strictRandom(final DishClass cls) {
        Logger.debug(this, "strictRandom()");
        final List<Soups.SoupData> soups = Arrays.stream(Soups.SoupData.values()).filter(
            data -> cls == data.getCls()
        ).collect(Collectors.toList());
        final Soups.SoupData data = soups.get(new Random().nextInt(soups.size()));
        return new SimpleDish(data.getName(), data.getCls());
    }

    private enum SoupData {
        SOUP_GOROH("Суп-пюре гороховый_БЛ", DishClass.PLEBS),
        BORSHCH("Борщ_БЛ", DishClass.PLEBS),
        LIGHT("Суп \"Легкий\"_БЛ", DishClass.PLEBS),
        SHASHLIKOFF("Суп \"Шашлыкофф\" с курицей_БЛ", DishClass.PROLETARIAT),
        TUTA_LARSEN("Суп куриный Тута Ларсен (Новинка)_БЛ", DishClass.PROLETARIAT),
        SALTWORT("Солянка_БЛ", DishClass.BOYARS),
        MUSHROOMS("Суп пюре грибной (NEW)_БЛ", DishClass.BOYARS),
        CHEESE_CREAM("Сырный крем-суп_БЛ", DishClass.BOYARS),
        ;

        private final String name;
        private final DishClass clazz;

        SoupData(final String dish, final DishClass cls) {
            this.name = dish;
            this.clazz = cls;
        }

        public String getName() {
            return this.name;
        }

        public DishClass getCls() {
            return this.clazz;
        }
    }

}
