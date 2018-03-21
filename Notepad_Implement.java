import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
class Notepad_Implement
{
	public static void main(String args[])
	{
		new Notepad_code();
	}
}
class Notepad_code
{
	JFrame f=new JFrame("NOTEPAD IN JAVA");
	JMenuBar menubar_item=new JMenuBar();
	JMenu file_item=new JMenu("FILE");
	JMenuItem new_item=new JMenuItem("NEW");
	JMenuItem open_item=new JMenuItem("OPEN");
	JMenuItem save_item=new JMenuItem("SAVE");
	JMenuItem exit_item=new JMenuItem("EXIT");
	int approved;
	File choosed_file;
	JFileChooser jfc=new JFileChooser();
	JTextArea ta=new JTextArea();
	JScrollPane sp=new JScrollPane(ta);
	Notepad_code()
	{
		setup_frame();
		add_menubar_menuitems();
		exit_code();
		new_code();
		open_code();
		save_code();
	}
	void setup_frame()
	{
		f.setSize(1200,700);
		f.setLayout(null);
		f.setVisible(true);
		f.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent we)
			{
				System.exit(1);
			}
		});
		sp.setBounds(30,30,1100,600);
		f.add(sp);
		ta.setLineWrap(true);
	}
	void add_menubar_menuitems()
	{
		menubar_item.add(file_item);
		file_item.add(new_item);
		file_item.add(open_item);
		file_item.add(save_item);
		file_item.add(exit_item);
		f.setJMenuBar(menubar_item);
	}
	void exit_code()
	{
		exit_item.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				System.exit(1);
			}
		});
	}
	void new_code()
	{
		new_item.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{	
				ta.setText(" ");
			}
		});
	}
	void save_code()
	{
		save_item.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				approved=jfc.showSaveDialog(f);
				if(approved==JFileChooser.APPROVE_OPTION)
				{	
					try
					{
						choosed_file=jfc.getSelectedFile();
						BufferedWriter bw=new BufferedWriter(new FileWriter(choosed_file));
						ta.write(bw);
					}
					catch(Exception e)
					{		
					}
				}
			}
		});
	}
	void open_code()  
	{
		open_item.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				approved=jfc.showOpenDialog(f);
				if(approved==JFileChooser.APPROVE_OPTION)
				{
					try
					{
						choosed_file=jfc.getSelectedFile();
						ta.setText(null);
						String line;
						String newline="\n";
						BufferedReader br=new BufferedReader(new FileReader(choosed_file));
						line=br.readLine();
						while(line!=null)
						{
							ta.append(line+newline);
							line=br.readLine();	
						}
						ta.append(line);
					} 
					catch(IOException e)
					{
						Dialog not_found=new Dialog(f);
						JLabel not_found_label=new JLabel("Selected file not found");
						not_found.setSize(300,300);
						not_found.setLayout(null);
						not_found.setTitle("FILE NOT FOUND");
						not_found.setLocationRelativeTo(f);
						not_found_label.setBounds(50,100,200,100);						
						not_found.add(not_found_label);
						not_found.setVisible(true);
						not_found.addWindowListener(new WindowAdapter()
						{
							public void windowClosing(WindowEvent we)
							{
								not_found.dispose();
								f.revalidate();
								f.repaint();
							}
						}); 
					}  
				}
			}
		});
	}
}