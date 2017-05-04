package cn.how2j.lazycat.gui.dialog;

import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JProgressBar;

import cn.how2j.lazycat.gui.frame.MainFrame;
import cn.how2j.lazycat.gui.util.GUIUtil;

public class ProgressDialog extends JDialog implements Runnable {
	

	public static ProgressDialog instance = new ProgressDialog();
	
	public int current = 0;
	public boolean progressing = false;
	
	public JProgressBar pb = new JProgressBar();
	
	private ProgressDialog(){
//		super(MainFrame.instance);
//		this.setModal(true);
		this.setSize(400, 80);
		this.setTitle("Web应用重启进度");
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(null);
		this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		 //进度条最大100
        pb.setMaximum(100);
        //当前进度是50
        pb.setValue(0);
        //显示当前进度
//        pb.setStringPainted(true);
        
        pb.setSize(new Dimension(280, 10));
        pb.setLocation((this.getWidth()-pb.getWidth())/2, (this.getHeight()-25-pb.getHeight())/2);
        
        
		this.add(pb);
	}
	

	public void start(){
		
		if(progressing)
			return;
		progressing = true;
		current = 0;
		pb.setValue(current);
		new Thread(this).start();
		instance.setVisible(true);
 }
	
	public void stop(){
		
		progressing = false;
		instance.setVisible(false);
	}
	
	
	
	public static void main(String[] args) {
		
		instance.start();
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		while(true){
			if(!progressing)
				break;
			try {
				Thread.sleep(current*5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			pb.setValue(current++);
			if(current==80)
				progressing = false;
		}
		
	}

	public void pause() {
		// TODO Auto-generated method stub
		
		progressing = false;
	}

	public void rest() {
		
		int beginWith =pb.getValue();
		for (int i = beginWith; i < 100+1; i++) {
			ProgressDialog.instance.pb.setValue(i);
			try {
				Thread.sleep(1000/(100-beginWith));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ProgressDialog.instance.stop();
	}
	
}
