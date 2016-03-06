package net.joshchang.josh.week8;

import java.util.ArrayList;

/**
 * Created by JoshDesktop on 3/6/2016.
 */
public class ItemHolder {
    public static ArrayList<StoreItem> list = null;
    public static int click = 1;
    private boolean init = false;

    public static void init(ArrayList<StoreItem> list) {
        ItemHolder.list = list;
        for (StoreItem item : ItemHolder.list) {
            click += item.click * item.lvl;
        }
    }

}
