import java.awt.RenderingHints;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;

import javax.swing.JPanel;

/**
 * @author { @Override } | @since 20250417, 21:01
 *
 **/
public class RoundedPanel extends JPanel {
  private int radius = 26;

  pulbic RoundedPanel(LayoutManager layout) {
    super(layout);
    setOpaque(false);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g.create();
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2.setColor(getBackground());
    g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
    g2.dispose();
  }
}
