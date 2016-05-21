package net.joshchang.josh.cookieclicker;

import java.util.ArrayList;


public class GameState {
    public static ArrayList<StoreItem> list = null;
    public static int click;

    // sets our individual click to give additional clicks
    // based off of our upgrades
    public static void init(ArrayList<StoreItem> list) {
        click = 1;
        GameState.list = list;
        // iterates through our list of upgrades to increase
        // the value of our click
        for (StoreItem item : GameState.list) {
            click += item.click * item.lvl;
        }
    }

}
