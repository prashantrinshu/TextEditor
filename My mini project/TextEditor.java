import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    JFrame frame;
    JMenuBar menuBar;

    JMenu file, edit;
    JMenuItem newFile, openFile, saveFile;
    JMenuItem cut, copy, paste, selectAll, close;
    JTextArea textArea;
    TextEditor(){
        frame = new JFrame("Text Editor"); // creating a window frame
        
        //initialize menu bar
        menuBar = new JMenuBar();

        //initialize textArea
        textArea = new JTextArea();

        //initialize menu
        file = new JMenu("File");
        edit = new JMenu("Edit");

        // initialize file menu Item
        newFile = new JMenuItem("New Window");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");

        //add action listener to menu item to file
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        // adding file menu item to the file menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        // initialize edit menu Item
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");

        //adding action listener to the edit
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        // adding file menu item to the edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        // add menu to the menu bar
        menuBar.add(file);
        menuBar.add(edit);

        // set menu bar to frame
        frame.setJMenuBar(menuBar);

        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
        panel.add(textArea, BorderLayout.CENTER);
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.add(scrollPane);



        frame.add(panel);
        frame.setBounds(100,100,400,400);
        frame.setVisible(true); // making frame visible
        frame.setLayout(null);

    }
    public static void main(String[] args) {
        TextEditor texteditor = new TextEditor();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == cut){
            textArea.cut(); // perform cut operation
        }
        if(e.getSource() == copy){
            textArea.copy(); //perform copy operation
        }
        if(e.getSource() == paste){
            textArea.paste(); //perform paste operation
        }
        if(e.getSource() == selectAll){
            textArea.selectAll(); //perform select all operation
        }
        if(e.getSource() == close){
            System.exit(0);
        }
        if(e.getSource() == openFile){
            JFileChooser fileChooser = new JFileChooser("C");
            int chooseOption = fileChooser.showOpenDialog(null);
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                File file= fileChooser.getSelectedFile();
                String filePath = file.getPath();
                try{
                    FileReader fileReader = new FileReader(filePath);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate = "";
                    String output = "";
                    while((intermediate = bufferedReader.readLine())!= null){
                        output+= intermediate+"\n";

                    }
                    textArea.setText(output);
                }
                catch(IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }

    }
}
