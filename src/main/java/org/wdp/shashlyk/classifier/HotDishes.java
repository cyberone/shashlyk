package org.wdp.shashlyk.classifier;

import com.jcabi.log.Logger;
import org.wdp.shashlyk.parser.Dish;

/**
 * @author Aleksey Popov (alopen@yandex.ru)
 */
class HotDishes implements DishCategory {
    @Override
    public boolean contains(final Dish dish) {
        Logger.debug(this, "contains(%s)", dish);
        for (final HotDishes.DishNames name : HotDishes.DishNames.values()) {
            if (name.getName().equals(dish.getName())) {
                return true;
            }
        }
        return false;
    }

    private enum DishNames {
        BURRITOS("Бурритос с курицей_БЛ", false),
        UDON("Удон Микс_БЛ", false),
        DRANNIKI("Драники_БЛ", false),
        CHICKEN_SHITSEL("Шницель куриный_БЛ", true),
        MEAT_SHNITSEL("Шницель мясной_БЛ", true),
        STEAK_AND_EGGS("Бифштекс с глазуньей_БЛ", true),
        TURK_STEAKS("Турецкие котлетки_БЛ", true),
        CHICKEN_KEBAB("Кебаб Куриный_БЛ", true),
        PORIDGE_MUSH_EGGS("Гречка с грибами и глазуньей (Новинка)_БЛ", false),
        KESADIA("Кесадия с курицей_БЛ", false),
        CHICKEN_GRILL("Цыпленок - гриль_БЛ", true),
        CHICKEN_SHASHLYK("Шашлык куриный_БЛ", true),
        CHUCKCHA("Чукча (Новинка)_БЛ", true),
        MACKEREL_GRILL("Скумбрия на гриле филе (Новинка)_БЛ", true),
        LIVER_BURGERS("Котлеты печеночные_БЛ", false),
        MINI_BURGER("Мини Бургер_БЛ", false),
        ASSORTED_GRILL_SMALL("Ассорти гриль \"Small\"_БЛ", true),
        CHICKEN_SHASHLYK_LAVASH("Шашлык куриный в лаваше_БЛ", false),
        TURKEY_SHASHLYK("Шашлык из индейки_БЛ", true),
        TURKEY_SHASHLYK_LAVASH("Шашлык из индейки в лаваше_БЛ", false),
        PORK_SHASHLYK("Шашлык из свинины_БЛ", true),
        PORK_SHASHLYK_LAVASH("Шашлык из свинины в лаваше_БЛ", false)
        ;

        private final String name;

        DishNames(final String dish, final boolean garnish) {
            this.name = dish;
        }

        public String getName() {
            return this.name;
        }
    }
}
