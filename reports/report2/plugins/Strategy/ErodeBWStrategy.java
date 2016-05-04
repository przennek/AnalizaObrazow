package AnalizaObrazow.reports.report2.plugins.Strategy;

/**
 * Created by p on 04.05.16.
 */
public class ErodeBWStrategy extends AbstractOperationStrategy {
    private static final Integer MIN_VAL = 0;

    @Override
    public Integer getHitVal(Integer x, Integer y) {
        return MIN_VAL;
    }

    @Override
    public Integer getFillVal(Integer x, Integer y) {
        return MIN_VAL;
    }
}
