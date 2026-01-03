package data;

import engine.map.Map;

public class Sauvegarde {
    private ActiveStats saveAStats;
    private PassiveStats savePstats;
    private Map saveMap;

    public Sauvegarde(){

    }

    public ActiveStats getSaveAStats() {
        return saveAStats;
    }
    public void setSaveAStats(ActiveStats saveAStats) {
        this.saveAStats = saveAStats;
    }


}
