package org.wdp.shashlyk.parser;

import java.util.Iterator;
import org.jsoup.nodes.Element;

/**
 * Iterable with combos.
 * @author Aleksey Popov (alopen@yandex.ru)
 */
public class Combos implements Iterable<Combo> {
    private final Element element;

    public Combos(final Element elt) {
        this.element = elt;
    }

    @Override
    public Iterator<Combo> iterator() {
        return new ComboIterator(this.element);
    }

    private static class ComboIterator implements Iterator<Combo> {
        private final Iterator<Element> iterator;
        private Element current;

        private ComboIterator(final Element elt) {
            this.iterator = elt.children().iterator();
        }

        @Override
        public boolean hasNext() {
            if (this.current != null) {
                return true;
            }
            while (this.iterator.hasNext()) {
                final Element item = this.iterator.next();
                if ("h3".equalsIgnoreCase(item.tagName())) {
                    if (item.text().contains("Комбо")) {
                        this.current = item;
                        return true;
                    }
                }
            }
            return false;
        }

        @Override
        public Combo next() {
            // todo implement
            throw new UnsupportedOperationException("next");
        }
    }
}
