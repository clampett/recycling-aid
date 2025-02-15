package src.garbage_types;

public class Garbage implements GarbageInterface {
    private String garbageName, garbageImagePath;

    public Garbage(String garbageName) {
        this.garbageName = garbageName;
        setGarbageImagePath();
    }

    public String getGarbageName() {
        return this.garbageName;
    }

    public void printGarbageName() {
        System.out.println(this.garbageName);
    }

    public String getGarbageImagePath() {
        return this.garbageImagePath;
    }

    public void setGarbageImagePath() {
        String path = "src/data/images/";
        
        switch(garbageName) {
            case "biodegradables":
                path += "biodegradables.png";
                break;
            case "electronics":
                path += "electronic.png";
                break;
            case "hazardous":
                path += "hazardous.png";
                break;
            case "recyclables":
                path += "recyclable.png";
                break;
            case "donatables":
                path += "donatable.png";
                break;
            default:
                System.out.println("Invalid Garbage Name - Could not get image path");
                path += "error.png";
        }
        
        this.garbageImagePath = path;
    }
}
