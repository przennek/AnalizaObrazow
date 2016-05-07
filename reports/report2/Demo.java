package AnalizaObrazow.reports.report2;

import AnalizaObrazow.reports.report2.plugins.MorphPlugin;
import AnalizaObrazow.reports.report2.plugins.Strategy.DylateBWStrategy;
import AnalizaObrazow.reports.report2.plugins.Strategy.ErodeBWStrategy;
import AnalizaObrazow.reports.report2.plugins.Strategy.PointsBWStrategy;
import kimage.tools.executors.Executor;
import kimage.tools.executors.gui.StepHandlerExecutor;

/**
 * Created by p on 04.04.16.
 */
public class Demo {
    public static void main(String[] args) {
        String filename = "./src/AnalizaObrazow/res/art1.gif";
        Executor points = new StepHandlerExecutor(filename);
//        points.add(new MorphPlugin(new ErodeBWStrategy(3), 1));
//        points.add(new MorphPlugin(new DylateBWStrategy(3), 1));
        points.add(new MorphPlugin(new PointsBWStrategy()));
        points.execute();
    }
}
