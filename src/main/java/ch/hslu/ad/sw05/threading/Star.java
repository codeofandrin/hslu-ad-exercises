package ch.hslu.ad.sw05.threading;

public class Star {
    private static final Object LOCK = new Object();

    private final int delay = (int) (Math.random() * 50) + 50;
    private int row = 1;
    private final int col;

    public Star(final int col) {
        this.col = col;
    }

    private void del(final int row) {
        synchronized (LOCK) {
            System.out.println("\033[" + row + ";" + this.col + "H ");
        }
    }

    private void set(final int row) {
        synchronized (LOCK) {
            System.out.println("\033[" + row + ";" + this.col + "H*");
        }
    }

    public void move() {
        while (true) {
            this.del(this.row);

            if (this.row > 20) {
                this.row = 1;
            } else {
                this.row++;
            }

            this.set(this.row);

            try {
                Thread.sleep(this.delay);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
