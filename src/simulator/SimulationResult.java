package src.simulator;

/**
 * {@link SimulationResult} is a simple class for displaying the results of a 
 * simulation from {@link src.gui.Gui_Simulator Gui_Simulator}.
 * 
 * @author Andrew Casey, Saadat Emilbekova, Dylan Jablonski, Jason Mele & Will Zakroff
 * @version 4/28/2025
 * @see src.gui.Gui_Simulator Gui_Simulator
 */
public class SimulationResult {
    private int totalCount, recycledCount, trashedCount;

     public SimulationResult(int totalCount, int recycledCount, int trashedCount) {
        this.totalCount = totalCount;
        this.recycledCount = recycledCount;
        this.trashedCount = trashedCount;
    }


    public int getRecycledCount()
    {
        return recycledCount;
    }

    public int getTotalCount()
    {
        return totalCount;
    }
    public int getTrashedCount()
    {
        return trashedCount;
    }
    @Override
    public String toString() {
        double recycleRate = (totalCount == 0) ? 0 : (double) recycledCount / totalCount * 100.0;
        return String.format("Simulation Complete!\nTotal Materials: %d\nRecycled: %d\nTrashed: %d\nRecycle Rate: %.2f%%", totalCount, recycledCount, trashedCount, recycleRate);
    }
}