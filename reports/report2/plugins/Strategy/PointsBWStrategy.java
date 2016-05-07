package AnalizaObrazow.reports.report2.plugins.Strategy;

/**
 * Created by p on 07.05.16.
 */
public class PointsBWStrategy extends AbstractOperationStrategy {
    public PointsBWStrategy(Integer fillVal) {
        this.fillVal = fillVal;
        dDim = 4;
        sE = new Integer[][]{
                {-1,-1,-1,-1,255,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1},
                {-1, 255,-1,-1,-1,-1,-1,255,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1},
                {255,-1,-1,-1,-1,-1,-1,-1,255},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,255,-1,-1,-1,-1,-1,255,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,255,-1,-1,-1,-1},
        };
    }
}
