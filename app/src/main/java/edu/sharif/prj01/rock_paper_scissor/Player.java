import java.util.Random;

public class Player extends Thread {
    private Weapon weapon;

    public Player() {
        move();
    }

    public Weapon getWeapon() {
        return weapon;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(new Random().nextInt(1000) + 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            move();
            try {
                synchronized (this) {
                    this.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void move() {
        Random rand = new Random();
        int move = rand.nextInt(3) + 1;
        synchronized (this) {
            switch (move) {
                case 1:
                    weapon = Weapon.ROCK;
                    break;
                case 2:
                    weapon = Weapon.PAPER;
                    break;
                case 3:
                    weapon = Weapon.SCISSOR;
                    break;
            }
            this.notify();
        }
    }
}
