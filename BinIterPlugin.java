package AnalizaObrazow;

import kimage.image.Image;
import kimage.plugin.Plugin;
import kimage.utils.gui.ImageFrame;

/**
 * Created by p on 14.03.16.
 */
public class BinIterPlugin extends Plugin {
    public BinIterPlugin() {

    }

    @Override
    public void process(Image imgIn, Image imgOut) {
        Integer to = getAverage(imgIn), tn;
        do {
            Integer nBlank = 0, nSig = 0;
            Integer blankHits = 0, signalHits = 0;
            for (int i = 0; i < imgIn.getWidth(); ++i) {
                for (int j = 0; j < imgIn.getHeight(); ++j) {
                    if (imgIn.getRed(i, j) < to) {
                        nBlank += imgIn.getRed(i, j);
                        blankHits++;
                    } else {
                        nSig += imgIn.getRed(i, j);
                        signalHits++;
                    }
                }
            }
            tn = to;
            to = ((nBlank/blankHits) + (nSig/signalHits))/2;
        }
        while (!to.equals(tn));
        for (int i = 0; i < imgIn.getWidth(); ++i) {
            for (int j = 0; j < imgIn.getHeight(); ++j) {
                if (imgIn.getRed(i, j) < to) {
                    imgOut.setRGB(i, j, 0, 0, 0);
                } else {
                    imgOut.setRGB(i, j, 255, 255, 255);
                }
            }
        }
    }

    private Integer getAverage(Image image) {
        int buffer = 0;
        for (int i = 0; i < image.getWidth(); ++i) {
            for (int j = 0; j < image.getHeight(); ++j) {
                buffer += image.getBlue(i, j);
            }
        }
        return buffer / (image.getWidth() * image.getHeight());
    }

    public static void main(String[] args) {
        Image image = new Image("./res/lena.png");
        Plugin gPlug = new GrayscalePlugin();
        gPlug.process(image, image);

        Plugin tshPlugin = new BinIterPlugin();
        tshPlugin.process(image, image);

        ImageFrame frame = new ImageFrame(image);
        frame.display();
    }
}
