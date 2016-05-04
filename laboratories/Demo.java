package AnalizaObrazow.laboratories;

import AnalizaObrazow.laboratories.plugins.SobelPlugin;
import kimage.plugin.Plugin;
import kimage.tools.executors.Executor;
import kimage.tools.executors.gui.StepHandlerExecutor;

public class Demo {

    public static void main(String[] args) {
//        Image image = new Image("./res/lena.png");
        /*
        Plugin gPlug = new GrayscalePlugin();
        gPlug.process(image, image);

        ImageFrame frame2 = new ImageFrame(image);
        frame2.display();

        try {
            new HistogramGUI(image, true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        HistStrechPlugin strechPlugin = new HistStrechPlugin(256, 256);
        strechPlugin.process(image, image);

        ImageFrame frame3 = new ImageFrame(image);
        frame3.display();
        */
        Plugin sobel = new SobelPlugin();
        Executor ex = new StepHandlerExecutor("./res/lena.png");
        ex.add(new SobelPlugin());
        ex.execute();
    }
}