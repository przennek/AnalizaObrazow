package AnalizaObrazow.reports.report2.plugins.Strategy;

/**
 * Created by p on 04.05.16.
 */
public class ErodeBWStrategy extends AbstractOperationStrategy {
    private static final Integer MIN_VAL = 0;

    public ErodeBWStrategy(Integer n) {
        super(MIN_VAL, 3);
        fillVal = MIN_VAL;
    }
}
