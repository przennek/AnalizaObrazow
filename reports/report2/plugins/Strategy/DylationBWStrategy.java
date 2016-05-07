package AnalizaObrazow.reports.report2.plugins.Strategy;

/**
 * Created by p on 04.05.16.
 */
public class DylationBWStrategy extends AbstractOperationStrategy {
    private static final Integer MIN_VAL = 0;

    public DylationBWStrategy(Integer[][] sE) {
        super(sE);
        dDim = sE.length/2;
        fillVal = MIN_VAL;
    }

    public DylationBWStrategy(Integer n) {
        super(MIN_VAL, n);
        fillVal = MIN_VAL;
        dDim = n/2;
    }
}
