import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.FlowLayout;

public class MainWindow {

  private JFrame frame;

  public MainWindow() {
    initialize();
  }

  private void initialize() {
    frame = new JFrame("Java Transpiler");
    this.frame.setLayout(new BorderLayout());
    this.frame.setSize(500, 500);
    this.frame.setLocationRelativeTo(null);
    this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel panel = new JPanel(); // default a FlowLayout
    panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
    Button button = new Button("Click to print Text");
    panel.add(button);

    frame.add(panel, BorderLayout.CENTER);
    this.frame.setVisible(true);
  }
}
