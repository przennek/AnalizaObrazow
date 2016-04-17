package AnalizaObrazow.reports.report1;

import AnalizaObrazow.reports.report1.plugins.QuantumPlugin;
import AnalizaObrazow.reports.report1.plugins.ReflectionPlugin;
import AnalizaObrazow.reports.report1.plugins.ShuffleColorIntensityPlugin;
import kimage.image.Image;
import kimage.plugin.Plugin;
import kimage.tools.executors.Executor;
import kimage.tools.executors.gui.StepHandlerExecutor;
import kimage.utils.gui.ImageFrame;

/**
 * Created by p on 04.04.16.
 */
public class Demo {
    public static void main(String[] args) {
        String filename = "./res/lena.png";
        Executor exec = new StepHandlerExecutor(filename);
        exec.add(new ReflectionPlugin());
        exec.add(new ShuffleColorIntensityPlugin());
        exec.add(new QuantumPlugin(16));
        exec.execute();
        exec.save("./res/out.png");

//        Image image = new Image("./res/lena.png");
//        Plugin a = new ReflectionPlugin();
//        a.process(image, image);
//        ImageFrame frame2 = new ImageFrame(image);
//        frame2.display();
    }
}
