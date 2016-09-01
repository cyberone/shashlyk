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
        BURRITOS("Бурритос с курицей_БЛ"),
        UDON("Удон Микс_БЛ"),
        DRANNIKI("Драники_БЛ"),
        CHICKEN_SHITSEL("Шницель куриный_БЛ"),
        MEAT_SHNITSEL("Шницель мясной_БЛ"),
        STEAK_AND_EGGS("Бифштекс с глазуньей_БЛ"),
        TURK_STEAKS("Турецкие котлетки_БЛ"),
        CHICKEN_KEBAB("Кебаб Куриный_БЛ"),
        PORIDGE_MUSH_EGGS("Гречка с грибами и глазуньей (Новинка)_БЛ"),
        KESADIA("Кесадия с курицей_БЛ"),
        CHICKEN_GRILL("Цыпленок - гриль_БЛ"),
        CHICKEN_SHASHLYK("Шашлык куриный_БЛ"),
        CHUCKCHA("Чукча (Новинка)_БЛ"),
        MACKEREL_GRILL("Скумбрия на гриле филе (Новинка)_БЛ"),
        LIVER_BURGERS("Котлеты печеночные_БЛ"),
        MINI_BURGER("Мини Бургер_БЛ"),
        ASSORTED_GRILL_SMALL("Ассорти гриль \"Small\"_БЛ"),
        CHICKEN_SHASHLYK_LAVASH("Шашлык куриный в лаваше_БЛ"),
        TURKEY_SHASHLYK("Шашлык из индейки_БЛ"),
        TURKEY_SHASHLYK_LAVASH("Шашлык из индейки в лаваше_БЛ"),
        PORK_SHASHLYK("Шашлык из свинины_БЛ"),
        PORK_SHASHLYK_LAVASH("Шашлык из свинины в лаваше_БЛ")
        ;

        private final String name;

        DishNames(final String dish) {
            this.name = dish;
        }

        public String getName() {
            return this.name;
        }
    }

}
