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
class Salads implements DishCategory {
    @Override
    public boolean contains(final Dish dish) {
        Logger.debug(this, "contains(%s)", dish);
        for (final Salads.SaladData name : Salads.SaladData.values()) {
            if (name.getName().equals(dish.getName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Dish random() {
        Logger.debug(this, "random()");
        final Salads.SaladData data = Salads.SaladData.values()[
            new Random().nextInt(Salads.SaladData.values().length)];
        return new SimpleDish(data.getName(),data.getCls());
    }

    @Override
    public Dish random(final DishClass clazz) {
        Logger.debug(this, "random(%s)", clazz);
        final List<Salads.SaladData> list = Arrays.stream(
            Salads.SaladData.values()
        ).filter(
            saladData -> saladData.getCls().ordinal() <= clazz.ordinal()
        ).collect(Collectors.toList());
        final Salads.SaladData dish = list.get(
            new Random().nextInt(list.size())
        );
        return new SimpleDish(dish.getName(), clazz);
    }

    @Override
    public Dish strictRandom(final DishClass cls) {
        final List<Salads.SaladData> salads = Arrays.stream(Salads.SaladData.values()).filter(
            saladData -> cls == saladData.getCls()
        ).collect(Collectors.toList());
        final Salads.SaladData data = salads.get(new Random().nextInt(salads.size()));
        return new SimpleDish(data.getName(), data.getCls());
    }

    private enum SaladData {
        HERING("Сельдь под шубой_БЛ", DishClass.PLEBS),
        VINEGRET("Винегрет_БЛ", DishClass.PLEBS),
        VITAMINS("Салат Витаминный (Новинка)_БЛ", DishClass.PLEBS),
        OLIVE("Оливье (Новинка)_БЛ", DishClass.PLEBS),
        PRESIDENTS("Салат \"Президентский\"_БЛ", DishClass.PROLETARIAT),
        HUNTERS("Салат \"Охотничий\"_БЛ", DishClass.PROLETARIAT),
        HAM_AND_PINEAPPLES("Салат с ветчиной и ананасами_БЛ", DishClass.PROLETARIAT),
        WOODPECKERS_NEST("Салат Гнездо дятла (Новинка)_БЛ", DishClass.PROLETARIAT),
        MOSCOW("Салат \"Московский\"_БЛ", DishClass.BOYARS),
        CESAR("Салат \"Цезарь\"_БЛ", DishClass.BOYARS)
        ;

        private final String name;
        private final DishClass clazz;

        SaladData(final String dish, final DishClass cls) {
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
