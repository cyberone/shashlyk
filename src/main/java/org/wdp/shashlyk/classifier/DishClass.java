package org.wdp.shashlyk.classifier;

/**
 * Dish class.
 * @author Aleksey Popov (alopen@yandex.ru)
 */
public enum DishClass {
    PLEBS("Плебейское"),
    PROLETARIAT("Пролетарское"),
    BOYARS("Барское"),
    ;

    private final String title;

    DishClass(final String ttle) {
        this.title = ttle;
    }

    public String getTitle() {
        return this.title;
    }
}
