package view;

import coordinate.Point;
import coordinate.Vector;
import designate.Feature;
import factory.BallFactory;
import factory.BrickFactory;
import matter.Matter;
import matter.MovableMatter;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.EnumMap;
import java.util.Map;
import java.util.Random;

public class BrickGame extends JFrame {
    private final BrickWorld world;
    private final MovableMatter player;
    private static int bounceCount = 0;
    private static int gameScore = 0;
    private static int leftBrick = 0;

    private static final JLabel bounceLabel = new JLabel();
    private static final JLabel scoreLabel = new JLabel();
    private static final JLabel brickLabel = new JLabel();

    public BrickGame(int width, int height) {
        setSize(width, height);
        setLayout(null);

        world = new BrickWorld(getWidth() - 100, getHeight() - 200);
        world.setLocation(50, 100);
        add(world);

        player = BrickFactory.createPlayerBrick(
                new Point(500, 550), 100, 20, Feature.UNBREAKABLE, new Vector());

        addKeyListener(new KeyAdapter() {
            // 키보드의 키를 누르면 호출된다.
            @Override
            public void keyPressed(KeyEvent event) {
                int keyCode = event.getKeyCode();
                if (keyCode == KeyEvent.VK_LEFT) { // 왼쪽 방향 키
                    player.setMotion(new Vector(10, -180));
                } else if (keyCode == KeyEvent.VK_RIGHT) { // 오른쪽 방향 키
                    player.setMotion(new Vector(10, 0));
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                player.setMotion(new Vector(0, 0));
            }
        });

        bounceLabel.setBounds(world.getX(), world.getY() - 50, 150, 40);
        bounceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        add(bounceLabel);

        scoreLabel.setBounds(bounceLabel.getX() + bounceLabel.getWidth() + 10, bounceLabel.getY() , 150, 40);
        scoreLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        add(scoreLabel);

        brickLabel.setBounds(scoreLabel.getX() + scoreLabel.getWidth() + 10, scoreLabel.getY() , 150, 40);
        brickLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        add(brickLabel);
    }

    public static void plusBounceCount() {
        bounceCount++;
        bounceLabel.setText("bounceCount : " + bounceCount);
    }

    public static void plusScore(int score) {
        gameScore += score;
        scoreLabel.setText("score : " + gameScore);
    }

    public static void plusLeftBlock() {
        leftBrick += 1;
        brickLabel.setText("leftBrick : " + leftBrick);
    }
    public static void minusLeftBlock() {
        leftBrick -= 1;
        brickLabel.setText("leftBrick : " + leftBrick);
    }

    public void run() {
        world.add(player);

        Map<Feature, Integer> featureLimit = new EnumMap<>(Feature.class);
        featureLimit.put(Feature.DEAD, 10);
        featureLimit.put(Feature.SPEED_UP, 3);
        featureLimit.put(Feature.TWO_LIFE, 2);
        featureLimit.put(Feature.UNBREAKABLE, 1);

        Feature[] features = Feature.values();
        Random random = new Random();

        for (int row = 100; row < 300; row += BrickFactory.HEIGHT + 5) {
            for (int col = 250; col < 650; col += BrickFactory.WIDTH + 5) {
                Feature feature = features[random.nextInt(features.length)];
                Integer count = featureLimit.computeIfPresent(feature, (key, left) -> left - 1);

                if (count == null || count < 0) {
                    feature = Feature.NORMAL;
                }

                world.add(BrickFactory.createNormalBrick(new Point(col, row), feature));
            }
        }
        Matter ball = BallFactory.create(
                new Point(500, 500), 20, -45);
        world.add(ball);
        world.add(BrickFactory.createMovableBrick(
                new Point(100, 70), Feature.NORMAL, new Vector(5, -180)));
        world.run();
    }
}
