package src;

public class SimulationResult{
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