import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;


@SuppressWarnings("serial")
public class MyMain extends Level implements KeyListener, ActionListener, MouseListener{
	private static int leftBound = 0, rightBound = 2300, upBound = 0, downBound = 1944;
	private static int width = 1056, height = 752;
	private static int tileSize = 64;
	public MyMain(int playerX, int playerY, int vx, int vy, String mapPath, String gridPath, int gridColumns, int gridRows) throws InterruptedException {
        super(playerX, playerY, vx, vy, mapPath, gridPath, gridColumns, gridRows, leftBound, rightBound, upBound, downBound, width, height, tileSize, 0);
    }

}