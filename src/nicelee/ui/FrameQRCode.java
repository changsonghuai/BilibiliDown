package nicelee.ui;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRootPane;

import com.google.zxing.WriterException;

import nicelee.util.QrCodeUtil;

public class FrameQRCode extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1659460151786653307L;
	String QRcodeStr;
	public FrameQRCode(String qrcode ) {
		QRcodeStr = qrcode;
	}
	
	public void initUI() {
		this.setUndecorated(true);
		this.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
		this.setTitle("请扫描二维码...");
		this.setSize(450, 500);
		// this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBackground(Color.WHITE);
		this.getContentPane().setBackground(Color.WHITE);
		//网址链接 ==> 二维码图片
		try {
			ImageIcon imgIcon = new ImageIcon(QrCodeUtil.createQrCode(QRcodeStr, 900));
			imgIcon = new ImageIcon(imgIcon.getImage().getScaledInstance(350, 350, Image.SCALE_SMOOTH));
			JLabel jLabel = new JLabel(imgIcon);
			this.add(jLabel);
			this.setVisible(true);
		} catch (WriterException e) {
			e.printStackTrace();
			this.dispose();
		}
		
		//关闭后将后台线程同时关闭
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Global.needToLogin = false;
				super.windowClosing(e);
			}
		});
	}
}
