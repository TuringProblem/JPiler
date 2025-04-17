import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.FlowLayout;
import javax.swing.JTextArea;

public class MainWindow {

  private JFrame frame;

  public MainWindow() {
    initialize();
  }

  private void initialize() {
    frame = new JFrame("Java Transpiler");
    this.frame.setLayout(new BorderLayout());
    this.frame.setSize(500, 1000);
    this.frame.setLocationRelativeTo(null);
    this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    JPanel panel = new JPanel(); // default a FlowLayout
    panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
    panel.setBackground(Color.lightGray);
    
    //JPanel cP = new JPanel(); // Center Panel
    //cP.setLayout(new FlowLayout(FlowLayout.left, 5, 5));
    frame.add(new JTextArea(), BorderLayout.CENTER);


    // Button component
    Button buttonOne = new Button("Click to print Text");
    //Button buttonTwo = new Button("Hi");

    panel.add(buttonOne);
    //panel.add(buttonTwo);


    //prefered size
    panel.setPreferredSize(new Dimension(250, 50)); // width, height


    frame.add(panel, BorderLayout.SOUTH);
    this.frame.setVisible(true);
  }
}
