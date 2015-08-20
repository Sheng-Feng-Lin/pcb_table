import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class OS1_00156150 {
		LinkedList<String> linkedlist; //宣告型態為String的LinkedList
		StringTokenizer stoken;
		public String pid; 
		
		/*****************************************************************
		Description: 建構子, 初始化設定
		Parameters: void
		Return: void 
		Created Date: 2014/03/16 	
		******************************************************************/
		public OS1_00156150() {
			// TODO Auto-generated constructor stub
			linkedlist = new LinkedList();
			stoken = null;
			pid = null;
	
		}
		/*****************************************************************
		Description: 讀取檔案且parser檔案
		Parameters: File FileName
		Return: void 
		Created Date: 2014/03/16 	
		******************************************************************/
		public void loadfile(File FileName){
			
			//使用try...catch...做找不到檔案或是存取錯誤例外的處理
		    try(FileReader fr =new FileReader(FileName)){
		    	//讀取檔案裡面的內容至buuferedreader中
		    	BufferedReader br = new BufferedReader(fr);
				//parser檔案的內容, 當檔案裡有內容尚未被讀出十此while迴圈會繼續執行, 且將讀出的值放入LinkedList中 
				stoken = new StringTokenizer(br.readLine(), " ");
				while( stoken.hasMoreTokens()){
					linkedlist.add(stoken.nextToken());
				}
					//將LinkedList的內容轉為字串放入pid此字串中
					pid = linkedlist.toString();        
			}		
			catch(FileNotFoundException e){
					System.out.println("例外發生:找不到該檔案");
			}
			catch(final IOException e){
					System.out.println("例外發生:檔案存取錯誤");
			}			    
		}
		
		/*****************************************************************
		Description: dispatch queue
		Parameters: void
		Return: void 
		Created Date: 2014/03/16 	
		******************************************************************/ 
		public void dispatch(){
			linkedlist.removeFirst(); //queue為FIFO, 故會先remove queue head
			pid = linkedlist.toString();//將LinkedList的內容轉為字串放入pid此字串中
		}
		
		/*****************************************************************
		Description: add PCB to queue
		Parameters: String New_PCB
		Return: void 
		Created Date: 2014/03/16 	
		******************************************************************/ 
		public void add(String New_PCB){
			linkedlist.add(New_PCB); //將從GUI輸入的值加入至LinkedList中
			pid = linkedlist.toString();//將LinkedList的內容轉為字串放入pid此字串中
		}	
}
