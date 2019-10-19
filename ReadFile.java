import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class ReadFile extends JFrame //Jframe t make crap look manageable
{
    //there will be wo parts:: ADDRESS BAR at the top for URL and MAIN WINDOW for displaying the we page content
    private JTextField addressBar; //declaring the ADDRESS BAR object of JtextFild class
    private JEditorPane display; //declaring the MAIN WINDOW object of JEditorPan class

    //constructor
    public ReadFile()
    {
        super("Tudum Browser"); //title at the top
        addressBar= new JTextField("Enter a URL first...please!!"); //JtextField is a class. addressBar is its object. Here we are assigning memory to this object.
        //when a user writes a URL then presses 'enter'. This 'enter' is an event. so we add action-listener to wait and listen for the user to hit 'enter' event
        addressBar.addActionListener
        (
            new ActionListener() //class
            {
                public void actionPerformed(ActionEvent event) //this method will be called when user presses 'enter'
                {
                    //user will press 'enter' only after typing the URL
                    //loadCrap is user-defined method, it takes input string parameter. it wil get the URL and go to the webpage and read the HTML file and display in the Main window
                    loadCrap(event.getActionCommand()); //getActionCommand() will fetch the URL string from the addressBar and pass it to loadCrap(), i.e. return the command string ssociate with this action
                }
            }
        );
        add(addressBar, BorderLayout.NORTH); //will put addressBar to the top of the screen

        //now we will define the MAIN WINDOW which won't be editable. but we can add a url listener to it, which will sit on every url to see if any action is performed
        display=new JEditorPane();//JEditorPane is a class. display is its object. Here we are assigning memory to this object.
        display.setEditable(false); 
        //because we do not want the contents of webpage to be editable by user. it can set 'true' for aplications like notepad or textpad but not here
        display.addHyperlinkListener//it will sit on every URL and wait n listen for the user to click on it. then it will pass the UL to loadCrap()
        (
            new HyperlinkListener() //class
            {
                public void hyperlinkUpdate(HyperlinkEvent event) //update instead of click because there may be many events like rolling over it, rolling away from it etc and not just clicking on it
                {
                    // if clicked= ACTIVATED
                    //if rolled over= ENTERED
                    //if rolled away= EXITED
                  if(event.getEventType()==HyperlinkEvent.EventType.ACTIVATED)
                  {
                      loadCrap(event.getURL().toString());//getURL() is an object, so we need to convert it to string to pass into loadCrap()
                  }  
                }
            }
        );
       // display.setEditabe(false);
        add(new JScrollPane(display), BorderLayout.CENTER); //will put display on the center of the screen
        setSize(500,300);
        setVisible(true);
    }

    //loadCrap to read any html file and display that on the screen
    private void loadCrap(String userText)
    {
        try 
        {
            display.setPage(userText); //sets the current url being displayed
            addressBar.setText(userText);
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println("crappp!!!.. you didn't enter the correct URL!!");
        }
    }

}