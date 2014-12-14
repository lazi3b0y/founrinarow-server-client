package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class GameBoard extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private JPanel playArea = new JPanel(new GridLayout(6, 0, 2, 2));
    private final Color RED = new Color(255, 0, 0);
    private final Color BLUE = new Color(0, 0, 255);
    private JPanel[][] panelGrid = new JPanel[6][7];
    private JPanel columnSelect = new JPanel(new GridLayout(1, 0, 2, 0));
    private JButton[] columnButtons = new JButton[7];
    private int lastColumn = -1;

    public GameBoard() {
        setLayout(new BorderLayout());
        initColumnButtons();
        initGrids();
        add(playArea, BorderLayout.CENTER);
        add(columnSelect, BorderLayout.PAGE_START);
        setVisible(false);
    }

    public void initColumnButtons() {
        for (int i = 0; i < 7; i++) {
            columnButtons[i] = new JButton("C" + (i + 1));
            columnButtons[i].addActionListener(this);
            columnSelect.add(columnButtons[i]);
        }
    }

    public void initGrids() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                panelGrid[i][j] = new JPanel(new BorderLayout());
                panelGrid[i][j].setBackground(new Color(0, 0, 0));
                playArea.add(panelGrid[i][j]);
            }
        }
    }

    public void updateBoard(int[][] grid) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (grid[i][j] == 1)
                    panelGrid[i][j].setBackground(new Color(255, 0, 0));
                else if (grid[i][j] == 2)
                    panelGrid[i][j].setBackground(new Color(0, 0, 255));
            }
        }
    }

    public void resetGameBoard() {
        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 7; j++)
                panelGrid[i][j].setBackground(new Color(0, 0, 0));
    }

    public int getPlayerInput() throws InterruptedException {
        lastColumn = -1;
        System.out.println("Getting player input.");
        while (lastColumn == -1) {
                Thread.sleep(100);
        }
        return lastColumn;
    }

    public void actionPerformed(ActionEvent e) {
        String buttonName;
        JButton button = (JButton) e.getSource();
        buttonName = button.getText();
        lastColumn = Character.getNumericValue(buttonName.charAt(buttonName.length() - 1)) - 1;
    }
    
    public void setVisiblity(boolean visible) {
    	setVisible(visible);
    }
}
