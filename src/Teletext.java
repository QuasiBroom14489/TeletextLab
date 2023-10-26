/**
 * Teletext main clas. Displays a rolling list of headlines
 */
 
import java.awt.Container;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Teletext extends JFrame
{
  private static String[] headlines =
  {
          
  };

  public Teletext()
  {
    super("Headlines");
    setSize(600, 320);
    
    TeletextList msgList = new TeletextList(headlines);
    TeletextDisplay display = new TeletextDisplay(msgList);
    TeletextInput inputPanel = new TeletextInput(msgList);
    Container c = getContentPane();
    c.add(display, BorderLayout.CENTER);
    c.add(inputPanel, BorderLayout.SOUTH);

    Timer t = new Timer(1, display);
    t.start();
  }


  /******************************************************************/
  /***************                        main       ****************/
  /******************************************************************/

  public static void main(String[] args)
  {

    Teletext window = new Teletext();
    window.setDefaultCloseOperation(EXIT_ON_CLOSE);
    window.setLocation(100, 100);
    window.setVisible(true);
  }
}
