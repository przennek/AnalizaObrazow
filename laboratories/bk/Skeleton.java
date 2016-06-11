package AnalizaObrazow.laboratories.bk;

import kimage.image.Image;
import kimage.plugin.Plugin;

/**
 * Created by alita on 04.05.16.
 */
public class Skeleton extends Plugin { // scienia obiekt

    // maski:
    int[][] SE1 = new int[][]{
            {0, 0, 0},
            {13, 255, 13},
            {255, 255, 255}};
    int[][] SE2 = new int[][]{
            {13, 0, 0},
            {255, 255, 0},
            {13, 255, 13}};
    int[][] SE3 = new int[][]{
            {255, 13, 0},
            {255, 255, 0},
            {255, 13, 0}};
    int[][] SE4 = new int[][]{
            {13, 255, 13},
            {255, 255, 0},
            {13, 0, 0}};
    int[][] SE5 = new int[][]{
            {255, 255, 255},
            {13, 255, 13},
            {0, 0, 0}};
    int[][] SE6 = new int[][]{
            {13, 255, 13},
            {0, 255, 255},
            {0, 0, 13}};
    int[][] SE7 = new int[][]{
            {0, 13, 255},
            {0, 255, 255},
            {0, 13, 255}};
    int[][] SE8 = new int[][]{
            {0, 0, 13},
            {0, 255, 255},
            {13, 255, 13}};

    int[][][] maski = {SE1, SE2, SE3, SE4, SE5, SE6, SE7, SE8};

    int szerokoscSE = SE1.length;
    int zakres = (int) Math.floor(szerokoscSE / 2);
    int liczbaIteracji = 10;

    @Override
    public void process(Image imgIn, Image imgOut) {
        for (int iteracja = 0; iteracja < liczbaIteracji; ++iteracja) {
            for (int maska = 0; maska < maski.length; ++maska) {
                Plugin hitormiss = new HitOrMiss(maski[maska]);
                hitormiss.process(imgIn, imgOut);
                for (int i = 0; i < imgIn.getWidth(); ++i) {
                    for (int j = 0; j < imgIn.getHeight(); ++j) {
                        if (imgOut.getBlue(i,j) == 0)
                            imgIn.setRGB(i, j, 0, 0, 0); // ustawienie nowej wartosci piksela dla obrazu wyjsciowego
                    }
                }
            }
        }
    }
}