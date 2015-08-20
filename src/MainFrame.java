import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class MainFrame extends JFrame {
	/*****************************************************************
	Description: 建立所需變數
	Created Date: 2014/03/16 	
	******************************************************************/
	private JPanel contentPane;
	private OS1_00156150 cpu_scheduler = new  OS1_00156150();
	private JTextField textField;
	private JTextField textField_1;
	JFileChooser fileChooser;
	FileNameExtensionFilter filter;
	/*****************************************************************
	Description: 主程式
	Created Date: 2014/03/16 	
	******************************************************************/
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				//使用try...catch...做例外的處理
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/*****************************************************************
	Description: MainFram 的建構子 , 也是建立GUI的地方
	Created Date: 2014/03/16 	
	******************************************************************/
	public MainFrame() {
		setTitle("OS1_00156150");
		
		/*
		 *初始化Frame
		*/
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 657, 571); // frame size 
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		/*
		 *建立Textfield 用於存放queue內容
		*/
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setBounds(65, 54, 566, 72);
		contentPane.add(textField);
		textField.setColumns(10);
		
		/*
		 *建立Textfield_1 用於存放queue head內容
		*/
		textField_1 = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField_1.setBounds(65, 211, 566, 72);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		/*
		 *建立Label顯示目前的textfield為Queue Head
		*/
		JLabel lblNewLabel = new JLabel("Queue Head");
		lblNewLabel.setFont(new Font("新細明體", Font.PLAIN, 24));
		lblNewLabel.setBounds(10, 167, 124, 34);
		contentPane.add(lblNewLabel);
		
		/*
		 *建立Label顯示目前的textfield為Queue 內容
		*/
		JLabel lblNewLabel_1 = new JLabel("Queue");
		lblNewLabel_1.setFont(new Font("新細明體", Font.PLAIN, 24));
		lblNewLabel_1.setBounds(10, 10, 99, 34);
		contentPane.add(lblNewLabel_1);
		
		/*
		 *建立功能為new的button
		*/
		JButton btnNewButton = new JButton("New");
		btnNewButton.setFont(new Font("新細明體", Font.PLAIN, 33));
		btnNewButton.setBounds(10, 388, 154, 118);
		contentPane.add(btnNewButton);
		
		/*
		 *建立功能為dispatch的button
		*/
		JButton btnNewButton_1 = new JButton("Dispatch");
		btnNewButton_1.setFont(new Font("新細明體", Font.PLAIN, 33));
		btnNewButton_1.setBounds(248, 388, 154, 118);
		contentPane.add(btnNewButton_1);
		
		/*
		 *建立功能為add的button
		*/
		JButton btnNewButton_2 = new JButton("Add");
		btnNewButton_2.setFont(new Font("新細明體", Font.PLAIN, 33));
		btnNewButton_2.setBounds(477, 388, 154, 118);
		contentPane.add(btnNewButton_2);
		
		//當點擊new會將選擇的檔案load進來 
		btnNewButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0){
				
				//建立檔案選擇的功能
				fileChooser = new JFileChooser();
				filter = new FileNameExtensionFilter("txt檔案", "txt");//選擇能開啟檔案的類型為.txt檔
				fileChooser.setFileFilter(filter);//設定過濾器
				int returnVal = fileChooser.showOpenDialog(null); 
					if(returnVal == JFileChooser.APPROVE_OPTION) 
						System.out.println("You chose to open this file: " +fileChooser.getSelectedFile().getName());
				//將選擇的檔案傳入至loadfile此function中
				cpu_scheduler.loadfile(fileChooser.getSelectedFile());
				textField.setFont(new Font("標楷體", Font.BOLD, 18));
				textField_1.setFont(new Font("標楷體", Font.BOLD, 18));
				textField.setText(cpu_scheduler.pid);
				textField_1.setText(cpu_scheduler.linkedlist.getFirst()+" "+cpu_scheduler.linkedlist.getLast()); //show queue head 
			}
		});
		
		//點擊disptch button then dispatch queue 
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				cpu_scheduler.dispatch();
				textField.setText(cpu_scheduler.pid);
				//當linkedlist中還有內容則print queue head
				if(cpu_scheduler.linkedlist.size()!=0)
					textField_1.setText(cpu_scheduler.linkedlist.getFirst()+" "+cpu_scheduler.linkedlist.getLast());//show queue head 
				else
					textField_1.setText(null);
			}
		});
		
		//點擊add button then add PCB to queue 
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//點擊add會跳出小視窗供使用者輸入值,在將值回傳至add function 加入至 linkedlist中
				cpu_scheduler.add((String) JOptionPane.showInputDialog(null,"Please input new PCB：\n","Add PCB",JOptionPane.PLAIN_MESSAGE,null,null,"在此輸入"));
				textField.setText(cpu_scheduler.pid);
				textField_1.setText(cpu_scheduler.linkedlist.getFirst()+" "+cpu_scheduler.linkedlist.getLast());//show queue head 
			}
		});
		
	}
}
