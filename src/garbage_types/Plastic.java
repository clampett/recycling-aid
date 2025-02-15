package src.garbage_types;

public class Plastic implements PlasticInterface {
    private String garbageName, plasticName, plasticSymbol;
    private int plasticNum;

    public Plastic(int plasticNum) {
        garbageName = "plastic";
        setPlasticType(plasticNum);
    }

    public void setPlasticType(int plasticNum) {
        switch(plasticNum) {
            case 1:
                this.plasticName = "PET";
                this.plasticNum = plasticNum;
                this.plasticSymbol = "♳";
                break;
            case 2:
                this.plasticName = "HDPE";
                this.plasticNum = plasticNum;
                this.plasticSymbol = "♴";
                break;
            case 3:
                this.plasticName = "PVC";
                this.plasticNum = plasticNum;
                this.plasticSymbol = "♵";
                break;
            case 4:
                this.plasticName = "LDPE";
                this.plasticNum = plasticNum;
                this.plasticSymbol = "♶";
                break;
            case 5:
                this.plasticName = "PP";
                this.plasticNum = plasticNum;
                this.plasticSymbol = "♷";
                break;
            case 6:
                this.plasticName = "PS";
                this.plasticNum = plasticNum;
                this.plasticSymbol = "♸";
                break;
            case 7:
                this.plasticName = "OTHER";
                this.plasticNum = plasticNum;
                this.plasticSymbol = "♹";
                break;
            default:
                System.out.println("ERROR - Invalid Plastic Number");
                this.plasticName = "ERROR";
                this.plasticNum = -1;
                this.plasticSymbol = "♺";
        }
    }

    public int getPlasticType() {
        return plasticNum;
    }

    public String getGarbageName() {
        return plasticName;
    }

    public void printGarbageName() {
        System.out.printf("%s: %s (%d)\n", garbageName, plasticName, plasticNum);
    }

    public String getGarbageImagePath() {
        return plasticSymbol;
    }
}
