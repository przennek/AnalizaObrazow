package AnalizaObrazow.reports.report2;

import AnalizaObrazow.reports.report2.plugins.ClearBordersPlugin;
import AnalizaObrazow.reports.report2.plugins.MorphPlugin;
import AnalizaObrazow.reports.report2.plugins.Strategy.ErodeBWStrategy;
import AnalizaObrazow.reports.report2.plugins.Strategy.DylationBWStrategy;
import AnalizaObrazow.reports.report2.plugins.Strategy.PointsBWStrategy;
import kimage.tools.executors.Executor;
import kimage.tools.executors.gui.StepHandlerExecutor;

/**
 * Created by p on 04.04.16.
 */
public class Demo {
    public static void main(String[] args) {
        Integer[][] dylationSePoints = new Integer[][] {{-1, 0, -1}, {0, -1, 0}, {-1, 0, -1}};

        String filename = "./src/AnalizaObrazow/res/art1.gif";
        Executor points = new StepHandlerExecutor(filename);
        points.add(new MorphPlugin(new PointsBWStrategy(255)));
        points.add(new MorphPlugin(new ErodeBWStrategy(3)));
        points.add(new MorphPlugin(new DylationBWStrategy(3)));
        points.add(new MorphPlugin(new DylationBWStrategy(dylationSePoints)));
        points.add(new ClearBordersPlugin(4));
        points.execute();

        Executor lines = new StepHandlerExecutor(filename);

        lines.execute();
    }
}
