package ordination;


import gui.TypeOrdination;

import java.time.LocalDate;
import java.time.LocalTime;

public class DagligFast extends Ordination{
    // TODO
    private Dosis[] doser = new Dosis[4];

    public DagligFast(LocalDate startDen, LocalDate slutDen, double morgenAntal, double middagAntal, double aftenAntal, double natAntal) {
        super(startDen, slutDen);
        doser[0] = new Dosis(LocalTime.of(8,0), morgenAntal);
        doser[1] = new Dosis(LocalTime.of(12,0), middagAntal);
        doser[2] = new Dosis(LocalTime.of(18,0), aftenAntal);
        doser[3] = new Dosis(LocalTime.of(23,59), natAntal);
    }

    @Override
    public double doegnDosis() {
        double sum = 0;
        for (Dosis dosis : doser) {
            sum += dosis.getAntal();
        }
        return sum;
    }

    @Override
    public double samletDosis() {
        return doegnDosis() * antalDage();
    }

    @Override
    public int antalDage() {
        return super.antalDage();
    }

    public Dosis[] getDoser() {
        return doser;
    }

    @Override
    public String getType() {
        return "DagligFast";
    }
}
