package ch.hslu.ad.sw05.histogram;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Main {

    static void main() throws IOException, InterruptedException {
        BufferedImage source =
                ImageIO.read(
                        new FileInputStream("src/main/java/ch/hslu/ad/sw05/histogram/bridge.jpg"));
        byte[] pixels = ((DataBufferByte) source.getRaster().getDataBuffer()).getData();

        runSequential(pixels);
        runParallel(pixels);
    }

    static void runSequential(final byte[] pixels) {
        long sum = 0;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            Histogram.getSequential(pixels);
            sum += System.nanoTime() - start;
        }

        System.out.println("Sequential avg time consumed: " + (sum / 1_000_000) / 10 + " ms");
    }

    static void runParallel(final byte[] pixels) throws InterruptedException {
        long sum = 0;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            Histogram.getParallel(pixels, 11);
            sum += System.nanoTime() - start;
        }

        System.out.println("Parallel avg time consumed: " + (sum / 1_000_000) / 10 + " ms");
    }
}
