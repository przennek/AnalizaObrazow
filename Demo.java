package pchFiles;

import kimage.image.Image;
import kimage.plugin.Plugin;
import kimage.utils.gui.ImageFrame;
import kimage.utils.histogram.gui.HistogramGUI;

public class Demo {

    public static void main(String[] args) {
        Image image = new Image("./res/lena.png");

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
    }
}