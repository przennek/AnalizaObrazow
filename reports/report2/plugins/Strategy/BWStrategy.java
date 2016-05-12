package AnalizaObrazow.reports.report2.plugins.Strategy;

/**
 * Created by p on 10.05.16.
 */
public class BWStrategy extends AbstractOperationStrategy {
    public BWStrategy(Integer[][] sE, Integer fillVal) {
        super(sE, fillVal);
    }

    @Override
    protected boolean shouldBeFilled(Integer hit, Integer comp) {
        return hit.equals(comp);
    }
}
