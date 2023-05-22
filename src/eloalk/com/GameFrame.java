package eloalk.com;

import exceptions.InvalidHealthException;
import exceptions.OutOfArenaException;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class GameFrame extends JFrame {
    private JPanel kovetkezoKorPanel;
    private JScrollPane logPanel;
    private JPanel gamePanel;
    private JPanel menuPanel;
    private JPanel healthPanel;
    private JTextField warriorHealth;
    private JTextField wizardHealth;
    private JTextArea logArea;
    private JLabel warriorLabel;
    private JLabel wizardLabel;
    private JLabel fightLabel;
    private Arena arena = new Arena(3);
    private Warrior warrior = new Warrior(1);
    private Wizard wizard = new Wizard(3);
    private ArrayList<RoundData> roundDatas = new ArrayList<RoundData>();
    private GameEngine game = new GameEngine(arena, warrior, wizard);
    GameFrame thisFrame = this;

    //Menü gombok
    private JButton visszaButton = new JButton("Vissza");
    private JButton mentesButton = new JButton("Játék mentése");

    //UI
    private JButton kovetkezoKorButton = new JButton("Következő kör");

    private JButton ujJatekButton = new JButton("Új játék");

    //Visszalépés gomb listenere
    public class VisszaButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            MainFrame main = new MainFrame();
            main.setVisible(true);
            thisFrame.dispose();
        }

    }

    //Mentés gomb listenere
    public class MentesButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser chooser = new JFileChooser();

            //Open File Picker
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "*dat files", "dat");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showSaveDialog(thisFrame);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    String filePath = chooser.getSelectedFile().getAbsolutePath();

                    if (!filePath.contains(".dat")) {
                        filePath = filePath.concat(".dat");
                    }

                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath, false));
                    oos.writeObject(roundDatas);
                    oos.close();
                } catch(Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

    }

    //Következő kör gomb listenere
    public class KovetkezoKorButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                RefreshUI(game.advanceRound());
            } catch (OutOfArenaException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }

    //Új játék gomb listenere
    public class UjJatekButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            arena = new Arena(3);
            warrior = new Warrior(1);
            wizard = new Wizard(3);
            game = new GameEngine(arena, warrior, wizard);
            try {
                logArea.setText("");
                RefreshUI(game.play());
            } catch (OutOfArenaException ex) {
                System.out.println(ex.getMessage());
            }
            kovetkezoKorButton.setEnabled(true);
            mentesButton.setEnabled(true);
        }

    }

    public GameFrame(ArrayList<RoundData> roundDataList) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Harcos vs Varázsló - Játék");
        setSize(750, 450);
        setMinimumSize(new Dimension(850, 300));
        setResizable(true);
        setLayout(new GridLayout(7,1));

        if (roundDataList != null) {
            RoundData lastData = roundDataList.get(roundDataList.size() - 1);
            arena = new Arena(3);
            try {
                warrior = new Warrior(lastData.getWarriorHealth(), lastData.getWarriorPosition());
                wizard = new Wizard(lastData.getWizardHealth(), lastData.getWizardPosition());
            }
            catch (InvalidHealthException ex) {
                System.out.println(ex.getMessage());
                System.out.println("A mentés sérült");
                return;
            }

            game = new GameEngine(arena, warrior, wizard, lastData.getRound());
        }

        //Menü felépítése
        menuPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 300, 0));
        menuPanel.setPreferredSize(new Dimension(600, 10));
        menuPanel.add(visszaButton);
        menuPanel.add(mentesButton);

        //Játéktér felépítése
        gamePanel = new JPanel(new GridLayout(1, 3));
        gamePanel.setPreferredSize(new Dimension(600, 10));

        try {
            BufferedImage warriorPicture = ImageIO.read(new File("./src//images/WarriorIcon.png"));
            warriorLabel = new JLabel(new ImageIcon(warriorPicture));

            BufferedImage wizardPicture = ImageIO.read(new File("./src/images/WizardIcon.png"));
            wizardLabel = new JLabel(new ImageIcon(wizardPicture));

            BufferedImage fightPicture = ImageIO.read(new File("./src/images/FightIcon.png"));
            fightLabel = new JLabel(new ImageIcon(fightPicture));

            gamePanel.add(warriorLabel);
            gamePanel.add(wizardLabel);
            BufferedImage emptyFieldPicture = ImageIO.read(new File("./src/images/EmptyFieldIcon.png"));
            JLabel emptyFieldLabel = new JLabel(new ImageIcon(emptyFieldPicture));
            gamePanel.add(emptyFieldLabel);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        kovetkezoKorPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        kovetkezoKorPanel.add(ujJatekButton);
        kovetkezoKorPanel.add(kovetkezoKorButton);

        healthPanel = new JPanel(new FlowLayout());
        healthPanel.add(new JLabel("Harcos: "));
        warriorHealth = new JTextField(5);
        warriorHealth.setEditable(false);
        healthPanel.add(warriorHealth);
        healthPanel.add(new JLabel("Varázsló: "));
        wizardHealth = new JTextField(5);
        wizardHealth.setEditable(false);
        healthPanel.add(wizardHealth);

        logArea = new JTextArea();
        logArea.setEditable(false);
        logPanel = new JScrollPane(logArea);

        JPanel gameTitlePanel = new JPanel(new GridLayout(1,3));
        gameTitlePanel.add(new JLabel("Aktuális kör"));

        JPanel logTitlePanel = new JPanel(new GridLayout(1,3));
        logTitlePanel.add(new JLabel("Részletek"));

        VisszaButtonActionListener visszaAction = new VisszaButtonActionListener();
        visszaButton.addActionListener(visszaAction);

        MentesButtonActionListener mentesAction = new MentesButtonActionListener();
        mentesButton.addActionListener(mentesAction);

        KovetkezoKorButtonActionListener kovetkezoKorAction = new KovetkezoKorButtonActionListener();
        kovetkezoKorButton.addActionListener(kovetkezoKorAction);

        UjJatekButtonActionListener ujJatekAction = new UjJatekButtonActionListener();
        ujJatekButton.addActionListener(ujJatekAction);

        //Kirajzolás
        getContentPane().add(menuPanel);
        getContentPane().add(gameTitlePanel);
        getContentPane().add(gamePanel);
        getContentPane().add(logTitlePanel);
        getContentPane().add(healthPanel);
        getContentPane().add(logPanel);
        getContentPane().add(kovetkezoKorPanel);

        if (roundDataList != null) {
            for (int i = 0; i < roundDataList.size(); i++) {
                RefreshUI(roundDataList.get(i));
            }
        }
        else {
            try {
                RefreshUI(game.play());
            } catch (OutOfArenaException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private void RefreshUI(RoundData roundData) {
        warriorHealth.setText(Integer.toString(roundData.getWarriorHealth()));
        wizardHealth.setText(Integer.toString(roundData.getWizardHealth()));
        gamePanel.removeAll();

        for (int i = 0; i < roundData.getArenaState().length; i++) {
            switch (roundData.getArenaState()[i]){
                case 'X':
                    gamePanel.add(fightLabel);
                break;
                case 'V':
                    gamePanel.add(wizardLabel);
                break;
                case 'H':
                    gamePanel.add(warriorLabel);
                break;
                case '_':
                    try {
                        BufferedImage emptyFieldPicture = ImageIO.read(new File("./src/images/EmptyFieldIcon.png"));
                        JLabel emptyFieldLabel = new JLabel(new ImageIcon(emptyFieldPicture));
                        gamePanel.add(emptyFieldLabel);
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                break;
            }
        }

        logArea.append(roundData.getArenaStateString() + '\n');

        if (roundData.isWarriorWon() || roundData.isWizardWon()) {
            kovetkezoKorButton.setEnabled(false);
            mentesButton.setEnabled(false);
        }

        roundDatas.add(roundData);
        getContentPane().revalidate();
        getContentPane().repaint();
    }
}
