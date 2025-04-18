import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import java.util.function.Supplier;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MainWindow {

  private JFrame frame;
  private JTextArea inputText;
  private JTextArea outputText;
  private Button bottomButton;
  private JPanel panel;

  public MainWindow() {
    inputText = new JTextArea(50, 30);
    outputText = new JTextArea(50, 30);
    bottomButton = new Button("Process");

    panel = new JPanel();
    initialize();
  }

  private void initialize() {
    frame = new JFrame("JPiler");
    this.frame.setLayout(new BorderLayout());
    this.frame.setSize(800, 800);
    this.frame.setLocationRelativeTo(null);
    this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel state = myStateSupplier.get();

    updateRenderedCode();

    this.frame.add(state, BorderLayout.SOUTH);
    this.frame.setVisible(true);

  }

  private Supplier<JPanel> myStateSupplier = () -> {
    JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

    // this is grabing the state from {@code getPanelState(String type)}
    mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    mainPanel.add(getPanelState("textArea"), BorderLayout.CENTER);

    buttonPanel.add(bottomButton);
    mainPanel.add(buttonPanel, BorderLayout.SOUTH);

    return mainPanel;
  };

  public void updateRenderedCode() {
    // event listener for when the button is clicked
    bottomButton.addActionListener(e -> {
      // compileAndShowByteCode(inputText.getText());

      System.out.println(inputText.getText());
    });

  }

  private void compileAndShowByteCode(String sourceCode) throws IOException, InterruptedException {
    String className = extractClassName(sourceCode);
    if (className == null) {
      outputText.setText("Error: Class name not found");
      return;
    }
    String fileName = className + ".java";

    Files.writeString(Paths.get(fileName), sourceCode);

    Process javacProcess = Runtime.getRuntime().exec("javac " + fileName);
    javacProcess.waitFor();

    if (javacProcess.exitValue() != 0) {
      String error = new String(javacProcess.getErrorStream().readAllBytes());
      outputText.setText("Error: " + error);
      return;
    }
    System.out.printf("Compiled successfully %s", fileName);

    // TODO: add javap -c classpath

  }

  private String extractClassName(String sourceCode) {
    for (String line : sourceCode.split("\\R")) {
      line = line.trim();
      if (line.startsWith("public class") || line.startsWith("class")) {
        String[] tokens = line.split("\\s+");
        // int idx = line.indexOf("class");
        if (tokens.length >= 3) {
          return tokens[2].split("\\{")[0].trim();
        }
      }
    }
    return null;
  }

  private JPanel getPanelState(String type) {
    switch (type) {
      case "textArea" -> {
        // ----------------------------------------- r, c, hzg, vtg
        JPanel textAreas = new JPanel(new GridLayout(1, 2, 10, 0));

        JScrollPane inputScroll = new JScrollPane(inputText);
        JScrollPane outputScroll = new JScrollPane(outputText);

        // inputScroll.setMinimumSize(new Dimension(300, 400));
        // outputScroll.setMinimumSize(new Dimension(300, 400));

        textAreas.add(inputScroll);
        textAreas.add(outputScroll);

        return textAreas;
      }
    }
    return null;
  }
}
