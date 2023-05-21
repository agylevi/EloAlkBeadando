package eloalk.com;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
    private MainFrame thisMain = this;
    private JButton ujJatekButton = new JButton("Új Játék");
    private JButton betoltesButton = new JButton("Játék Betöltése");
    private JButton kilepesButton = new JButton("Kilépés");

    public class UjJatekButtonActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            /*FelvetelFrame felvetel = new FelvetelFrame();
            thisMain.dispose();
            felvetel.setVisible(true);*/

            GameFrame gameFrame = new GameFrame();
            thisMain.dispose();
            gameFrame.setVisible(true);
        }

    }

    public class BetoltesButtonActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            /*KivetelFrame kivetel = new KivetelFrame();
            thisMain.dispose();
            kivetel.setVisible(true);*/
        }

    }

    public class KilepesButtonActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }

    }

    public MainFrame(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Harcos vs Varázsló");
        setSize(400, 250);
        setResizable(true);
        setLayout(new GridLayout(3, 1));

        JLabel titleLabel = new JLabel("Harcos vs Varázsló");
        JPanel titlePanel = new JPanel(new FlowLayout());
        JPanel menuPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel kilepesPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        UjJatekButtonActionListener ujJatekAction = new UjJatekButtonActionListener();
        BetoltesButtonActionListener betoltesAction = new BetoltesButtonActionListener();
        KilepesButtonActionListener kilepesAction = new KilepesButtonActionListener();

        titlePanel.add(titleLabel, FlowLayout.LEFT);
        menuPanel.add(ujJatekButton, FlowLayout.LEFT);
        menuPanel.add(betoltesButton, FlowLayout.CENTER);
        kilepesPanel.add(kilepesButton);

        ujJatekButton.addActionListener(ujJatekAction);
        betoltesButton.addActionListener(betoltesAction);
        kilepesButton.addActionListener(kilepesAction);

        getContentPane().add(titlePanel);
        getContentPane().add(menuPanel);
        getContentPane().add(kilepesPanel);
    }

}
