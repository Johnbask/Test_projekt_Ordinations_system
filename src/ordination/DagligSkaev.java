package ordination;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class DagligSkaev extends Ordination {
    private ArrayList<Dosis> doser = new ArrayList<>();

    public DagligSkaev(LocalDate startDen, LocalDate slutDen) {
        super(startDen, slutDen);
    }

    public void opretDosis(LocalTime tid, double antal) {
        Dosis dosis = new Dosis(tid,antal);
        doser.add(dosis);
    }

    @Override
    public double samletDosis() {
        return doegnDosis()*super.antalDage();
    }

    @Override
    public double doegnDosis() {
        double doegndosis = 0;
        for (Dosis dosis : doser){
            doegndosis+=dosis.getAntal();
        }
        return doegndosis;
    }

    public ArrayList<Dosis> getDoser() {
        return new ArrayList<>(doser);
    }

    @Override
    public String getType() {
        return "DaglisSkaev";
    }
}
