package ordination;

import java.time.LocalTime;
import java.util.ArrayList;

public class DagligSkaev extends Ordination {
    private ArrayList<Dosis> dosisArrayList = new ArrayList<>();

    public DagligSkaev() {
    }

    public void opretDosis(LocalTime tid, double antal) {
        Dosis dosis = new Dosis(tid,antal);
        dosisArrayList.add(dosis);
    }

    @Override
    public double samletDosis() {
        return 0;
    }

    @Override
    public double doegnDosis() {
        double doegndosis = 0;
        for ()
    }

    @Override
    public String getType() {
        return "";
    }
}
