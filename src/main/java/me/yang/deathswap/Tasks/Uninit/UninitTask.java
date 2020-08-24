package me.yang.deathswap.Tasks.Uninit;

import me.yang.deathswap.Tasks.Uninit.Classes.UninitBossBar;
import me.yang.deathswap.Tasks.Uninit.Classes.UninitScoreboard;
import me.yang.deathswap.Tasks.Uninit.Classes.UninitTeam;

public class UninitTask {
    public void run() throws NullPointerException{
        new UninitScoreboard().run();
        new UninitTeam().run();
        new UninitBossBar().run();
    }
}
