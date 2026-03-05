package ordination;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class DagligSkaev extends Ordination {
    private ArrayList<Dosis> dosisArrayList = new ArrayList<>();

    public DagligSkaev(LocalDate startDen, LocalDate slutDen) {
        super(startDen, slutDen);
    }

    public void opretDosis(LocalTime tid, double antal) {
        Dosis dosis = new Dosis(tid,antal);
        dosisArrayList.add(dosis);
    }

    @Override
    public double samletDosis() {
        return doegnDosis()*dosisArrayList.size();
    }

    @Override
    public double doegnDosis() {
        double doegndosis = 0;
        for (Dosis dosis : dosisArrayList){
            doegndosis+=dosis.getAntal();
        }
        return doegndosis;
    }

    @Override
    public String getType() {
        return "DaglisSkaev";
    }
}
