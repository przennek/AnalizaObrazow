package AnalizaObrazow.reports.report2.plugins.Strategy;

/**
 * Created by p on 04.05.16.
 */
public class DylateBWStrategy extends AbstractOperationStrategy {
    private static final Integer MAX_VAL = 255;

    @Override
    public Integer getHitVal(Integer x, Integer y) {
        return MAX_VAL;
    }

    @Override
    public Integer getFillVal(Integer x, Integer y) {
        return MAX_VAL;
    }
}
