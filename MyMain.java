import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;


@SuppressWarnings("serial")
public class MyMain extends Level implements KeyListener, ActionListener, MouseListener{
	public MyMain(int playerX, int playerY, int vx, int vy, String mapPath, String gridPath, int gridColumns, int gridRows) throws InterruptedException {
        super(playerX, playerY, vx, vy, mapPath, gridPath, gridColumns, gridRows, 0, 2300, 0, 1944, 0);
    }

}