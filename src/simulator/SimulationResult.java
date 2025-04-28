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
    int total, recycled, trashed;

     public SimulationResult(int total, int recycled, int trashed) {
        this.total = total;
        this.recycled = recycled;
        this.trashed = trashed;
    }

    @Override
    public String toString() {
        double recycleRate = (total == 0) ? 0 : (double) recycled / total * 100.0;
        return String.format("Simulation Complete!\nTotal Materials: %d\nRecycled: %d\nTrashed: %d\nRecycle Rate: %.2f%%", total, recycled, trashed, recycleRate);
    }
}