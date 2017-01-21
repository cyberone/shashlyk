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
class HotDishes implements DishCategory {
    @Override
    public boolean contains(final Dish dish) {
        Logger.debug(this, "contains(%s)", dish);
        for (final HotDishes.DishData name : HotDishes.DishData.values()) {
            if (name.getName().equals(dish.getName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Dish random() {
        Logger.debug(this, "random()");
        final HotDishes.DishData name = HotDishes.DishData.values()[
            new Random().nextInt(HotDishes.DishData.values().length)];
        if (name.needsGarnish()) {
            return new SimpleDish(
                name.getName(), new Garnishes().random(),
                name.getCls());
        }
        return new SimpleDish(name.getName(), name.getCls());
    }

    @Override
    public Dish random(final DishClass clazz) {
        Logger.debug(this, "random(%s)", clazz);
        final List<HotDishes.DishData> list = Arrays.stream(
            HotDishes.DishData.values()
        ).filter(
            dishData -> dishData.getCls().ordinal() <= clazz.ordinal()
        ).collect(Collectors.toList());
        final HotDishes.DishData dish = list.get(
            new Random().nextInt(list.size())
        );
        if (dish.needsGarnish()) {
            return new SimpleDish(dish.getName(), new Garnishes().random(), clazz);
        }
        return new SimpleDish(dish.getName(), clazz);
    }

    @Override
    public Dish strictRandom(final DishClass cls) {
        Logger.debug(this, "strictRandom()");
        final List<HotDishes.DishData> hots = Arrays.stream(HotDishes.DishData.values()).filter(
            data -> cls == data.getCls()
        ).collect(Collectors.toList());
        final HotDishes.DishData data = hots.get(new Random().nextInt(hots.size()));
        return new SimpleDish(data.getName(), data.getCls());
    }

    private enum DishData {
        BURRITOS("Бурритос с курицей_БЛ", false, DishClass.PLEBS),
        UDON("Удон Микс_БЛ", false, DishClass.PLEBS),
        TURK_STEAKS("Турецкие котлетки_БЛ", true, DishClass.PLEBS),
        DRANNIKI("Драники_БЛ", false, DishClass.PLEBS),
        CHICKEN_KEBAB("Кебаб Куриный_БЛ", true, DishClass.PLEBS),
        PASTA_MUSH("Паста с курочкой и грибами", false, DishClass.PLEBS),
        PORIDGE_MUSH_EGGS("Гречка с грибами и глазуньей (Новинка)_БЛ", false, DishClass.PLEBS),
        CHICKEN_SHITSEL("Шницель рубленый (куриный)_БЛ", true, DishClass.PLEBS),
        AZU_CHIKEN("Азу из цыпленка (NEW)_БЛ", true, DishClass.PLEBS),
        KESADIA("Кесадия с курицей_БЛ", false, DishClass.PROLETARIAT),
        CHUCKCHA("Чукча (Новинка)_БЛ", true, DishClass.PROLETARIAT),
        TONGUE_OMLETTE("Омлет с языком (NEW)_БЛ", false, DishClass.PROLETARIAT),
        CHICKEN_BURGER("Котлета куриная (NEW)_БЛ", true, DishClass.PROLETARIAT),
        TURKEY_SHASHLYK("Шашлык из индейки_БЛ", true, DishClass.BOYARS),
        TURKEY_SHASHLYK_LAVASH("Шашлык из индейки в лаваше_БЛ", false, DishClass.BOYARS),
        PORK_SHASHLYK("Шашлык из свинины_БЛ", true, DishClass.BOYARS),
        PORK_SHASHLYK_LAVASH("Шашлык из свинины в лаваше_БЛ", false, DishClass.BOYARS),
        MINI_BURGER("Мини Бургер_БЛ", false, DishClass.BOYARS),
        LIVER_BURGERS("Котлеты печеночные_БЛ", true, DishClass.BOYARS),
        ASSORTED_GRILL_SMALL("Ассорти гриль \"Small\"_БЛ", true, DishClass.BOYARS),
        DUCK_GRILL("Утиная грудка на гриле_БЛ", true, DishClass.BOYARS),
        DUCK_SHASHLYK("Шашлык из утки_БЛ", true, DishClass.BOYARS),
        CHIKEN_FOIL("Курица в фольге_БЛ", true, DishClass.BOYARS),
        DUCK_SHASHLYK_LAVASH("Шашлык из утки в лаваше_БЛ", false, DishClass.BOYARS),
        MACKEREL_FOIL("Скумбрия запеченая в фольге_БЛ", true, DishClass.BOYARS)
        ;

        private final String name;
        private final boolean garnish;
        private final DishClass clazz;

        DishData(final String dish, final boolean grnsh, final DishClass cls) {
            this.name = dish;
            this.garnish = grnsh;
            this.clazz = cls;
        }

        public String getName() {
            return this.name;
        }

        public boolean needsGarnish() {
            return this.garnish;
        }

        public DishClass getCls() {
            return this.clazz;
        }
    }
}
