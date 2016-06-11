package AnalizaObrazow.laboratories.bk;

import kimage.image.Image;
import kimage.plugin.Plugin;
import kimage.plugins.morphology.Dilation;
import kimage.plugins.morphology.Erosion;
import kimage.utils.gui.ImageFrame;

/**
 * Created by alita on 18.04.16.
 */
public class HitOrMiss extends Plugin {

    int[][] SE = new int[][] // element strukturalny
            {{0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0}}; // 0 - piksel obiektu, 255 - piksel tla, 13 - nieistotne

    int szerokoscSE = SE.length;

    HitOrMiss() {

    }

    HitOrMiss(int[][] elementStrukturalny) {
        SE = elementStrukturalny;
        szerokoscSE = elementStrukturalny.length;
    }

    @Override
    public void process(Image imgIn, Image imgOut) {
        int zakres = (int) Math.floor(szerokoscSE / 2);

        int[][] imgNowy = new int[imgIn.getWidth()][imgIn.getHeight()];
        for (int i = 0; i < imgIn.getWidth(); ++i) { // iteracja po pikselach obrazu z uwzglednieniem ramki
            miss:
            for (int j = 0; j < imgIn.getHeight(); ++j) {
                if (i < zakres || j < zakres || (i >= imgIn.getWidth() - zakres) || (j >= imgIn.getHeight() - zakres)) {
                    imgNowy[i][j] = imgIn.getBlue(i, j);
                    continue miss;
                }
                for (int k = i - zakres, r = 0; k <= i + zakres; ++k, ++r) { // iteracja po otoczeniu przetwarzanego piksela
                    for (int l = j - zakres, s = 0; l <= j + zakres; ++l, ++s) {

                        if ((SE[r][s] == 13) || (SE[r][s] == imgIn.getBlue(k, l))) { // sprawdzenie, czy otoczenie spelnia warunek
                            continue;
                        } else {
                            imgNowy[i][j] = 255; // piksel nie spelnia warunku
                            continue miss;
                        }
                    }
                }
                imgNowy[i][j] = 0; // otoczenie spelnia warunek
            }
        }
        for (int i = 0; i < imgIn.getWidth(); ++i) {
            for (int j = 0; j < imgIn.getHeight(); ++j) {
                imgOut.setRGB(i, j, imgNowy[i][j], imgNowy[i][j], imgNowy[i][j]); // ustawienie nowej wartosci piksela dla obrazu wyjsciowego
            }
        }
    }

    public static void main(String[] args) {
        // ****************   wyluskanie kropek - poczatek  ****************

        int[][] SEkolo = new int[][]
                {{13, 13, 13, 0, 13, 13, 13},
                        {13, 13, 0, 0, 0, 13, 13},
                        {13, 0, 0, 0, 0, 0, 13},
                        {0, 0, 0, 0, 0, 0, 0},
                        {13, 0, 0, 0, 0, 0, 13},
                        {13, 13, 0, 0, 0, 13, 13},
                        {13, 13, 13, 0, 13, 13, 13}};

        Image art = new Image("./res/art1.gif");
        new ImageFrame(art).display();

        Plugin hitormiss1 = new HitOrMiss(SEkolo);
        hitormiss1.process(art);
        new ImageFrame(art).display();
        Plugin Dilation = new Dilation();
        Dilation.process(art);

        Plugin Erosion = new Erosion();
        Erosion.process(art);
        new ImageFrame(art).display();

        // **************** wyluskanie kropek - koniec  ****************


        // **************** wyluskanie kresek - poczatek ****************

        Image image = new Image("./res/art1.gif");

        int[][] SEkolo5 = new int[][]{
                {13, 13, 0, 13, 13},
                {13, 0, 0, 0, 13},
                {0, 0, 0, 0, 0},
                {13, 0, 0, 0, 13},
                {13, 13, 0, 13, 13}};

        Plugin hitormiss = new HitOrMiss(SEkolo5);
        hitormiss.process(image);
        new ImageFrame(image).display();

        int[][] SEkreska = new int[][]{ // prawie wszystkie kreski
                {13, 13, 13, 13, 255},
                {13, 13, 13, 0, 13},
                {13, 13, 0, 13, 13},
                {13, 255, 13, 13, 13},
                {13, 13, 13, 13, 13}};

        Plugin hitormiss2 = new HitOrMiss(SEkreska);
        hitormiss2.process(image);
        new ImageFrame(image).display();

        Image image2 = new Image("./res/art1.gif");
        hitormiss.process(image2);

        int[][] SEkreska2 = new int[][]{ // pozostale kreski
                {255, 13, 13, 13, 13},
                {13, 0, 13, 13, 13},
                {13, 13, 0, 13, 13},
                {13, 13, 13, 255, 13},
                {13, 13, 13, 13, 13}};

        Plugin hitormiss3 = new HitOrMiss(SEkreska2);
        hitormiss3.process(image2);
        new ImageFrame(image2).display();

        for (int i = 0; i < image.getWidth(); ++i) {
            for (int j = 0; j < image.getHeight(); ++j) {
                if (image2.getBlue(i, j) == 0)
                    image.setRGB(i, j, 0, 0, 0);
            }
        }

        Plugin ero = new Erosion();
        ero.process(image);
        new ImageFrame(image).display();

        // **************** wyluskanie kresek - koniec ****************

    }
}