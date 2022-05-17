/**
 * @author Abigail Animah Owusu
 * @author Solomon Kwabena Asante-Ansong
 * @author Oheneba Kwaku Addo Dade
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class GUI {
    static String startLocation;
    static String endLocation;
    static int startIndex;
    static int endIndex;

    public GUI() throws IOException {
        //creation of main application frame
        JFrame frame = new JFrame("Ashesi Location Finder Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        //creation of frame title panel
        JPanel titlePanel = new JPanel();
        titlePanel.setVisible(true);

        //creation of logo image
        BufferedImage logo = ImageIO.read(new File("logo.png"));
        JLabel logoLabel = new JLabel(new ImageIcon(logo));
        logoLabel.setVisible(true);
        titlePanel.add(logoLabel);

        //creation of frame title label
        JLabel title = new JLabel("ASHESI LOCATION FINDER APPLICATION");
        title.setFont(new Font("SchoolHouse Cursive B", Font.BOLD, 25));
        title.setForeground(Color.RED);
        titlePanel.add(title, BorderLayout.CENTER);
        frame.add(titlePanel, BorderLayout.NORTH);

        //creation of selection location panel
        JPanel selectionPanel = new JPanel();
        selectionPanel.setVisible(true);
        selectionPanel.setLayout(new GridLayout(3,1));
        frame.add(selectionPanel);

        //creation of selection location components
        String[] startLocations = {"School gate",
                "Fablab/Health centre",
                "Ash Pitch/Gym",
                "Inner Gate",
                "Library",
                "Dlab",
                "Green Lounge",
                "Norton Motulsky",
                "Research Building",
                "Nana Araba Hall",
                "Akonor Cafeteria",
                "The Hive",
                "Big Ben Cafeteria",
                "New Hostels",
                "BasketBall Court 2",
                "Car Park 2",
                "Lobby 2",
                "BasketBall Court 1",
                "Lobby 1",
                "Car Park 1",
                "Admin Block",
                "Jackson Hall",
                "Engineering Lab",
                "Old Hostels"
        };

        JPanel startLine = new JPanel();
        JComboBox<String> startLocationsComboBox = new JComboBox<>(startLocations);
        JLabel startLabel = new JLabel("Select Your Starting Point");

        startLine.add(startLabel);
        startLine.add(startLocationsComboBox);

        JPanel endLine = new JPanel();
        JComboBox<String> endLocationsComboBox = new JComboBox<>(startLocations);
        JLabel endLabel = new JLabel("Select Your End Point");
        JLabel endText = new JLabel();
        endLine.add(endLabel);
        endLine.add(endLocationsComboBox);
        endLine.add(endText);

        JPanel doneLine = new JPanel();
        JButton doneButton = new JButton("Done");
        JLabel doneText = new JLabel();
        doneLine.add(doneButton);
        doneLine.add(doneText);


        selectionPanel.add(startLine);
        selectionPanel.add(endLine);
        selectionPanel.add(doneLine);
        selectionPanel.setBounds(50, 200, 30, 10);


        doneButton.addActionListener(e -> {
            String selectedStartLocation = "You selected " + startLocationsComboBox.getItemAt(startLocationsComboBox.getSelectedIndex());
            doneText.setText(selectedStartLocation);
            startLocation = startLocationsComboBox.getItemAt(startLocationsComboBox.getSelectedIndex());
            endLocation = endLocationsComboBox.getItemAt(endLocationsComboBox.getSelectedIndex());

            for(int i: DijkstraAdjacencyMatrix.locations.keySet() ) {
                if(DijkstraAdjacencyMatrix.locations.get(i).equals(startLocation) ) {
                    startIndex = i;
                }
            }

            for(int j: DijkstraAdjacencyMatrix.locations.keySet()){
                if(DijkstraAdjacencyMatrix.locations.get(j).equals(endLocation)) {
                    endIndex = j;
                }
            }

            DijkstraAdjacencyMatrix.Graph.getShortestPath(startIndex, endIndex);
            doneText.setText(String.valueOf(DijkstraAdjacencyMatrix.pathway));
            DijkstraAdjacencyMatrix.pathway.clear();
        });
    }
}
