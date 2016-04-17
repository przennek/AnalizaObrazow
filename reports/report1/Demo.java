package AnalizaObrazow.reports.report1;

import AnalizaObrazow.reports.report1.plugins.binar.NiblackBinPlugin;
import AnalizaObrazow.reports.report1.plugins.binar.SauvolaBinPlugin;
import AnalizaObrazow.reports.report1.plugins.context.MedianFilterPlugin;
import AnalizaObrazow.reports.report1.plugins.noncontext.QuantumPlugin;
import AnalizaObrazow.reports.report1.plugins.noncontext.ReflectionPlugin;
import AnalizaObrazow.reports.report1.plugins.noncontext.ShuffleColorIntensityPlugin;
import kimage.tools.executors.Executor;
import kimage.tools.executors.gui.StepHandlerExecutor;

/**
 * Created by p on 04.04.16.
 */
public class Demo {
    public static void main(String[] args) {
        String filename = "./res/lena.png";
        Executor exec = new StepHandlerExecutor(filename);
        exec.add(new ReflectionPlugin());
        exec.add(new ShuffleColorIntensityPlugin());
        exec.add(new QuantumPlugin(4));
        exec.execute();
        exec.save("./res/out.png");

        Executor exec2 = new StepHandlerExecutor(filename);
        exec2.add(new NiblackBinPlugin(-0.1, 2));
        exec2.execute();

        Executor exec3 = new StepHandlerExecutor(filename);
        exec3.add(new SauvolaBinPlugin(-0.01, 2));
        exec3.execute();

        Executor exec4 = new StepHandlerExecutor(filename);
        exec4.add(new MedianFilterPlugin(1));
        exec4.execute();
    }
}
