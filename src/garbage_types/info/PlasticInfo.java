package src.garbage_types.info;

import src.garbage_types.*;

public class PlasticInfo extends Plastic implements InfoPiece {
    private int plasticIndex;
    private String[] recyclingInfo;

    public PlasticInfo(int plasticNum) {
        super(plasticNum);
        this.plasticIndex = plasticNum - 1;
        setRecyclingInfo();
    }

    public String[] getRecyclingInfo() {
        return recyclingInfo;
    }

    public void setRecyclingInfo() {
        recyclingInfo = loader("src/data/text/plastic_info.txt")[plasticIndex].split("\\*");
    }
}