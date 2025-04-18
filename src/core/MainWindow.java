import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;

public class MainWindow {

  private JFrame frame;
  private JTextArea inputText;
  private JTextArea outputText;
  private Button bottomButton;
  private JPanel panel;

  public MainWindow() {
    // frame = new JFrame("Main Window");
    inputText = new JTextArea();
    outputText = new JTextArea();
    bottomButton = new Button("Process");
    inputText.setRows(40);
    inputText.setColumns(30);
    outputText.setRows(40);
    outputText.setColumns(30);

    panel = new JPanel();
    initialize();
  }

  private void initialize() {
    frame = new JFrame("Transpiler");
    this.frame.setLayout(new BorderLayout());
    this.frame.setSize(800, 800);
    this.frame.setLocationRelativeTo(null);
    this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.frame.add(createScene(), BorderLayout.SOUTH);
    this.frame.setVisible(true);

  }

  private JPanel createScene() {
    JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

    mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    mainPanel.add(getPanelState("textArea"), BorderLayout.CENTER);
    buttonPanel.add(bottomButton);
    mainPanel.add(buttonPanel, BorderLayout.SOUTH);

    return mainPanel;
  }

  private JPanel getPanelState(String type) {
    switch (type) {
      case "textArea" -> {
        // ----------------------------------------- r, c, hzg, vtg
        JPanel textAreas = new JPanel(new GridLayout(1, 2, 10, 0));

        JScrollPane inputScroll = new JScrollPane(inputText);
        JScrollPane outputScroll = new JScrollPane(outputText);

        inputScroll.setMinimumSize(new Dimension(300, 400));
        outputScroll.setMinimumSize(new Dimension(300, 400));

        textAreas.add(inputScroll);
        textAreas.add(outputScroll);

        return textAreas;
      }
    }
    return null;
  }
}
