package AnalizaObrazow.reports.report2.plugins.Strategy;

/**
 * Created by p on 04.05.16.
 */
public class ErodeBWStrategy extends AbstractOperationStrategy {
    private static final Integer MAX_VAL = 255;

    public ErodeBWStrategy(Integer n) {
        super(MAX_VAL, n);
        fillVal = MAX_VAL;
        dDim = n/2;
    }
}
