package ordination;

import java.time.LocalDate;
import java.util.ArrayList;

public class PN extends Ordination {

    private double antalEnheder;
    private ArrayList<LocalDate> datoerIndtaget = new ArrayList<>();

    public PN(LocalDate startDen, LocalDate slutDen, double antalEnheder) {
        super(startDen, slutDen);
        this.antalEnheder = antalEnheder;
    }

    /**
     * Registrerer at der er givet en dosis paa dagen givesDen
     * Returnerer true hvis givesDen er inden for ordinationens gyldighedsperiode og datoen huskes
     * Returner false ellers og datoen givesDen ignoreres
     * @param givesDen
     * @return
     */
    public boolean givDosis(LocalDate givesDen) {
        if (!givesDen.isBefore(getStartDen()) && !givesDen.isAfter(getSlutDen())) {
            datoerIndtaget.add(givesDen);
            return true;
        }
        return false;
    }

    public double doegnDosis() {
        return (getAntalGangeGivet() * antalEnheder / antalDage());
    }

    public double samletDosis() {
        return getAntalGangeGivet() * antalEnheder;
    }

    /**
     * Returnerer antal gange ordinationen er anvendt
     * @return
     */
    public int getAntalGangeGivet() {
        return datoerIndtaget.size();
    }

    public double getAntalEnheder() {
        return antalEnheder;
    }

    public ArrayList<LocalDate> getDatoerIndtaget() {
        return datoerIndtaget;
    }

    public void setDatoerIndtaget(ArrayList<LocalDate> datoerIndtaget) {
        this.datoerIndtaget = datoerIndtaget;
    }

    @Override
    public String getType() {
        return "PN";
    }
}
