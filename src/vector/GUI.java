package vector;

import vector.util.CanvasMouse; // Assessing interface for mouse event handlers
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;

import static java.awt.Color.*;

/**
 * GUI class controls the what is output to the window. It contains one canvas object that is read to
 * determine what is printed to the window.
 */
public class GUI extends CanvasMouse  {

    JFrame frame;
    VectorCanvas canvas;

    GUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("VectorTool");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(700+20, 800));
        frame.setLocation(970,50);
        frame.getContentPane().setLayout(new BorderLayout());

        showMenuBar();
        showToolPalette();
        showCanvas();

        frame.pack();
        frame.setVisible(true);
    }

    private void open() {
        System.out.println("New");
    }

    private void newFile() {

    }

    private void save() {

    }

    private void saveAs() {

    }

    private JMenuItem createMenuItem(String text, ActionListener e) {
        JMenuItem newMenuItem = new JMenuItem(text);
        newMenuItem.setMnemonic(KeyEvent.VK_N);
        newMenuItem.addActionListener(e);
        return newMenuItem;
    }

    public void showMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        fileMenu.add(createMenuItem("New", (event) -> newFile()));
        fileMenu.add(createMenuItem("Open", (event) -> open()));
        fileMenu.add(createMenuItem("Save", (event) -> save()));
        fileMenu.add(createMenuItem("Save As...", (event) -> saveAs()));

        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);
    }

    private JButton palletButton() {
        JButton output = new JButton("");
        output.setPreferredSize(new Dimension(20,20));
        return output;
    }

    private JButton[] shapeButton(){
        JButton plot = new JButton("PLOT");
      //  plot.setPreferredSize(new Dimension(45,35));
        JButton line = new JButton("LINE");
     //   line.setPreferredSize(new Dimension(45,35));
        JButton rectangle = new JButton("BOX");
       // rectangle.setPreferredSize(new Dimension(45,35));
        JButton ellipse = new JButton("CIRCLE");
     //   ellipse.setPreferredSize(new Dimension(45,35));
        JButton polygon = new JButton("POLY");
       // polygon.setPreferredSize(new Dimension(45,35));
        return new JButton[]{plot, line, rectangle, ellipse, polygon};
    }
    private JButton[] toolButton(){
        JButton zoomPlus = new JButton("PLUS");
      //  zoomPlus.setPreferredSize(new Dimension(45,55));
        JButton zoomMinus = new JButton("MINUS");
       // zoomMinus.setPreferredSize(new Dimension(45,55));
        JButton undo = new JButton("UNDO");
      //  undo.setPreferredSize(new Dimension(45,55));
        return new JButton[]{zoomPlus, zoomMinus, undo};
    }
    private ArrayList<JButton> colourButton(){
        Color[]colourBackground = { RED, BLUE, GREEN, WHITE, BLACK, YELLOW, ORANGE, PINK, CYAN, GRAY};//, blue, green, white, black, yellow, orange, pink, cyan, clear};

        JButton pen = new JButton("PEN");
       // pen.setPreferredSize(new Dimension(45,20));
        JButton fill = new JButton("FILL");
      //  fill.setPreferredSize(new Dimension(45,20));
        JButton picker = new JButton("PICKER");
      //  picker.setPreferredSize(new Dimension(45,20));
        JButton red = new JButton();
        JButton blue = new JButton();
        JButton green = new JButton();
        JButton white = new JButton();
        JButton black = new JButton();
        JButton yellow = new JButton();
        JButton orange = new JButton();
        JButton pink = new JButton();
        JButton cyan= new JButton();
        JButton clear = new JButton();

        JButton[] colourNames = {red, blue, green, white, black, yellow, orange, pink, cyan, clear};
        ArrayList<JButton> colourButtonArray = new ArrayList<>(Arrays.asList(pen,fill,picker));

        int counter = 0;
        for(JButton i : colourNames){
            i.setPreferredSize(new Dimension(20,20));
            i.setBackground(colourBackground[counter]);
            colourButtonArray.add(i);
            counter ++;
        }
       return colourButtonArray;
    }

    private void showToolPalette(){

        //frame.add(mainPanel);
        JPanel pallet = new JPanel(new GridLayout(3,1));
        JPanel shapePallet = new JPanel(  );
        //shapePallet.setMaximumSize(new Dimension(500,100));
        JPanel toolPallet = new JPanel();
        JPanel colourPallet = new JPanel();

        pallet.setBackground(Color.lightGray);
      //  pallet.setLayout(new GridBagLayout());
        GridBagConstraints palletConstraints = new GridBagConstraints();
        pallet.setPreferredSize(new Dimension(50, 100));


        //shapePallet.setPreferredSize(new Dimension(50,100));
        shapePallet.setBackground(Color.BLACK);
     //   shapePallet.setLayout(new GridLayout(5,1));
       // toolPallet.setPreferredSize(new Dimension(50,300));
        toolPallet.setBackground(Color.GREEN);
      //  toolPallet.setLayout(new GridLayout(3,1));
       // colourPallet.setPreferredSize(new Dimension(50,300));
        colourPallet.setBackground(RED);
        //colourPallet.setLayout(new GridLayout(3,1));

JButton x = new JButton();
        JButton y = new JButton();
        JButton z = new JButton();
//shapePallet.add(x);
//        toolPallet.add(y);
//        colourPallet.add(z);

        for(JButton button : shapeButton()){
            shapePallet.add(button);
        }
        for(JButton button : toolButton()){
            toolPallet.add(button);
        }
        for(JButton button : colourButton()){
            colourPallet.add(button);
        }
      //  shapePallet.setPreferredSize(new Dimension(50,20));
        palletConstraints.fill = GridBagConstraints.VERTICAL;
        palletConstraints.gridx =0;
        palletConstraints.gridy =0 ;
      //  palletConstraints.ipady = 2;
       // palletConstraints.weighty =0.5;
//        palletConstraints.gridwidth = 50;
      //  palletConstraints.gridheight = 2;
        pallet.add(shapePallet, palletConstraints);

      //  toolPallet.setPreferredSize(new Dimension(50,60));
      //  palletConstraints.gridheight = 10;
        palletConstraints.gridx = 0;
        palletConstraints.gridy = 1;
       // palletConstraints.gridheight = 2;
        pallet.add(toolPallet, palletConstraints);
     //   colourPallet.setPreferredSize(new Dimension(70,20));
        palletConstraints.gridx = 0;
        palletConstraints.gridy = 2;
     //   palletConstraints.gridheight = 2;
        pallet.add(colourPallet, palletConstraints);

/*     pallet.add(palletButton());
        pallet.add(palletButton());
        pallet.add(palletButton());
        pallet.add(palletButton());
        pallet.add(palletButton());
        pallet.add(palletButton());*/
        frame.getContentPane().add(pallet, BorderLayout.WEST);
        //mainPanel.add(pallet, BorderLayout.EAST);
    }

    public void showCanvas() {
        CanvasMouse mouseEvent = new CanvasMouse(); // interface for mouse events
        canvas = new VectorCanvas();

        frame.getContentPane().add(canvas, BorderLayout.CENTER);
        canvas.setSideWidth(500);
        attachCanvas(canvas); // specify canvas to be used
        canvas.addMouseListener(mouseEvent); // listen for still mouse events
        canvas.addMouseMotionListener(mouseEvent); // listen for moving mouse events

    }
}
