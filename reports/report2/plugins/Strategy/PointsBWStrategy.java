package AnalizaObrazow.reports.report2.plugins.Strategy;

/**
 * Created by p on 07.05.16.
 */
public class PointsBWStrategy extends AbstractOperationStrategy {
    public PointsBWStrategy() {
        fillVal = 255;
        dDim = 2;
//        sE = new Integer[][]{
//                {255, 255, -1, 255, 255},
//                {255, -1, -1, -1, 255},
//                {-1, -1, -1, -1, -1},
//                {255, -1, -1, -1, 255},
//                {255, 255, -1, -1, 255},
//        };
        sE = new Integer[][] {
                {-1, -1, 255, -1, -1},
                {-1, -1, -1, -1, -1},
                {255, -1, -1, -1, 255},
                {-1, -1, -1, -1, -1},
                {-1, -1, 255, -1, -1},
        };
    }
}
