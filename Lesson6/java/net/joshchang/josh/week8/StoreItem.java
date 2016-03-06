package net.joshchang.josh.week8;

/**
 * Created by JoshDesktop on 3/6/2016.
 */
public class StoreItem {
    public int id;
    public String name;
    public int click;
    public int cost;
    public int lvl;

    public StoreItem(int id, String name, int click, int cost, int lvl) {
        this.id = id;
        this.name = name;
        this.click = click;
        this.cost = cost;
        this.lvl = lvl;
    }
}
