package scraper;

import net.dean.jraw.models.SubredditSort;

/**
 * a wrapper enum for SubredditSort in jraw.
 * @author Shariar
 */
public enum Sort {
    HOT(SubredditSort.HOT),
    BEST(SubredditSort.BEST),
    NEW(SubredditSort.NEW),
    RISING(SubredditSort.RISING),
    CONTROVERSIAL(SubredditSort.CONTROVERSIAL),
    TOP(SubredditSort.TOP);

    private final SubredditSort value;

    Sort(SubredditSort value) {
        this.value = value;
    }

    public SubredditSort value() {
        return value;
    }
}
