package src.recycling_types.materials;

import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

import src.recycling_types.Material;
import src.recycling_types.categories.*;

/**
 * {@link Metal} is a concrete representation of garbage made out of metal and
 * is a subclass of {@link src.recycling_types.Material Material}
 * 
 * @author Andrew Casey, Saadat Emilbekova, Dylan Jablonski, Jason Mele & Will Zakroff
 * @version 4/12/2025
 */
public class Metal extends Material implements Centerable, Disposable{
    /**Possible metal types*/
    private enum metalType {
        Aluminum, Brass, Bronze, Copper, Lead, Iron, Nickel, Steel, Tin, Titanium
    };

    /**Metal formulas for metalTypes*/
    private enum metalFormula {
        Al, CuZn, CuSn, Cu, Pb, Fe, Ni, FeC, Sn, Ti
    };

    /**The metal's type*/
    private metalType type;

    /**The metal's formula*/
    private metalFormula typeFormula;
    
    /**
     * Some possible items that metal could be.
     * @see src.recycling_types.Material Material
     */
    private static Set<String> possibleItems = new HashSet<>(
        Arrays.asList("metal", "aluminum Can", "tin can", "aluminum foil", "razor blade", "nail", "screw")
    );
    
    public Metal() {
        this("Al");
    }

    public Metal(metalFormula typeFormula) {
        super(0.25, possibleItems);

        this.typeFormula = typeFormula;
        this.type = metalType.values()[Arrays.binarySearch(metalFormula.values(), typeFormula)];
        
        super.setImpactScore(setImpact(typeFormula));
    }

    public Metal(String typeFormula) {
        this(metalFormula.valueOf(typeFormula));
    }

    /**
     * Sets impact score based on the type of metal
     * 
     * @param type metalFormula 
     * @return impact score
     */
    private double setImpact(metalFormula type) {
        double impact = 0.25;

        switch(type) {
            case Al:
                impact = 0.3;
                break;
            case CuZn:
                impact = 0.4;
                break;
            case CuSn:
                impact = 0.4;
                break;
            case Cu:
                impact = 0.3;
                break;
            case Pb:
                impact = 0.95;
                break;
            case Fe:
                impact = 0.2;
                break;
            case Ni:
                impact = 0.75;
                break;
            case FeC:
                impact = 0.3;
                break;
            case Sn:
                impact = 0.3;
                break;
            case Ti:
                impact = 0.6;
                break;
        }

        return impact;
    }

    /**
     * Get the metalFormulas {@code enum} as a {@code String[]}.
     * @return {@code String[]} of metalFormulas
     */
    public String[] getMetalFormulaStrings() {
        metalFormula[] formulas = metalFormula.values();
        String[] formulaStrings = new String[formulas.length];

        for(int i = 0; i < formulas.length; i++)
            formulaStrings[i] = formulas[i].toString();

        return formulaStrings;
    }

    @Override
    public boolean attemptCenter(Material material){
        // Logic for taking the material to a center
        return true; // Example: Always allow center
    }

    @Override
    public boolean attemptDispose(Material mateiral){
        return true; // Logic for disposing of the material
    }

    public String getSpecial() {
        String specialBlurb = "Common metal types:\n";
        String specialFormat = "\t• %s: (%s)\n";

        for(int i = 0; i < metalType.values().length; i++)
            specialBlurb += String.format(specialFormat, metalType.values()[i], metalFormula.values()[i]);

        return specialBlurb;
    }
}