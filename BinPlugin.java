package AnalizaObrazow;

import kimage.image.Image;
import kimage.plugin.Plugin;
import kimage.plugin.thresholding.ThresholdPlugin;
import kimage.utils.gui.ImageFrame;

/**
 * Created by p on 14.03.16.
 */
public class BinPlugin extends ThresholdPlugin {
    private int threshold = 100;

//    @Override
//    public void process(Image imgIn, Image imgOut) {
//        for (int i = 0; i < imgIn.getWidth(); ++i) {
//            for (int j = 0; j < imgIn.getHeight(); ++j) {
//                if (imgIn.getRed(i, j) < threshold) {
//                    imgOut.setRGB(i, j, 0, 0, 0);
//                } else {
//                    imgOut.setRGB(i, j, 255, 255, 255);
//                }
//            }
//        }
//    }

    @Override
    public int getThreshold(Image imgIn) {
        if (getAttribute("threshold") != null) {
            threshold = (Integer) getAttribute("threshold");
        } else {
            setAttribute("threshold", threshold);
        }
        return threshold;
    }

    public static void main(String[] args) {
        Image image = new Image("./res/lena.png");
        Plugin gPlug = new GrayscalePlugin();
        gPlug.process(image, image);

        Plugin tshPlugin = new BinPlugin();
        tshPlugin.setAttribute("threshold", 100);
        tshPlugin.process(image, image);

        ImageFrame frame = new ImageFrame(image);
        frame.display();
    }


}
