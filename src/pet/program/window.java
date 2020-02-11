package pet.program;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import pet.model.pet1;


public class window extends pet1{
	protected static JFrame jframe = new JFrame(getPet_Name());
	static boolean isplay = true;
	static JLabel jlabel = new JLabel();
	/*
	 * 窗口
	 */
	protected static void jframe() {

		// 设置背景透明
		jframe.setUndecorated(true);
		jframe.setBackground(new Color(0, 0, 0, 0));
		jframe.setVisible(true);

		// 设置窗口位置和大小
		jframe.setBounds(200, 200, 300, 300);
		jframe.setLocation((int) (Math.random() * 1000), (int) (Math.random() * 600));
		// 设置是否前端显示
		jframe.setAlwaysOnTop(true);

		// 设置是否支持窗口拉伸
		jframe.setResizable(false);
		// jframe.setResizable(true);

		// 设置关闭窗口布局
		jframe.setLayout(null);

		// 设置窗口关闭方式
		// jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		jlabelImg(jlabel,getPet_image(random(getpet_Action_number()-1)));
		jframe.add(jlabel);

		// 获取x,y坐标
		Point origin = new Point();
		jframe.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// 当鼠标按下的时候获得窗口当前的位置
				origin.x = e.getX();
				origin.y = e.getY();
				jlabelImg(jlabel, getPet_image(4));
				isplay = false;
				System.out.println("isplay:false");
			}

			public void mouseReleased(MouseEvent e) {
				jlabelImg(jlabel, getPet_image(random(getpet_Action_number()-1)));
				isplay = true;
				System.out.println("isplay:true");
			}
		});

		jframe.addMouseMotionListener(new MouseMotionAdapter() {
			// 拖动（mouseDragged 指的不是鼠标在窗口中移动，而是用鼠标拖动）
			public void mouseDragged(MouseEvent e) {
				// 当鼠标拖动时获取窗口当前位置
				Point p = jframe.getLocation();
				// 设置窗口的位置
				// 窗口当前的位置 + 鼠标当前在窗口的位置 - 鼠标按下的时候在窗口的位置
				int x = p.x + e.getX() - origin.x;
				int y = p.y + e.getY() - origin.y;
				jframe.setLocation(x, y);
			}
		});

	}

	protected static void play_Time() {
		try {
			Thread.sleep((random(5)+2)*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(isplay==true) {
			jlabelImg(jlabel, getPet_image(random(getpet_Action_number()-1)));
		}
	}
	
	public static int random(int action) {
		int i = new Random().nextInt(action);
		System.out.println(i);
		return i;
	}
	
	/*
	 * 系统托盘
	 */
	protected static void setTray() {
		// 判断系统是否支持系统托盘
		if (SystemTray.isSupported()) {
			// 获取当前系统的托盘
			SystemTray tray = SystemTray.getSystemTray();

			// 为托盘添加一个右键弹出菜单
			PopupMenu popMenu = new PopupMenu();

			MenuItem itemOpen = new MenuItem("Show~");
			itemOpen.addActionListener(e -> jframe.setVisible(true));

			MenuItem itemHide = new MenuItem("Hide");
			itemHide.addActionListener(e -> jframe.setVisible(false));

			MenuItem itemExit = new MenuItem("Exit！");
			itemExit.addActionListener(e -> System.exit(0));

			popMenu.add(itemOpen);
			popMenu.add(itemHide);
			popMenu.add(itemExit);

			// 设置托盘图标
			ImageIcon icon = new ImageIcon("pet\\run.gif");
			Image image = icon.getImage().getScaledInstance(icon.getIconWidth(), icon.getIconHeight(),
					Image.SCALE_DEFAULT);

			TrayIcon trayIcon = new TrayIcon(image, "MyPet~", popMenu);
			// 自适应图片尺寸
			trayIcon.setImageAutoSize(true);

			try {
				tray.add(trayIcon);
			} catch (AWTException e1) {
				e1.printStackTrace();
			}
		}
	}

	/*
	 * 图片传入参数
	 */
	protected static void jlabelImg(JLabel jLabel, String imgUrl) {
		ImageIcon icon = new ImageIcon(imgUrl);
		int picWidth = icon.getIconWidth(), pinHeight = icon.getIconHeight();
		icon.setImage(icon.getImage().getScaledInstance(picWidth, pinHeight, Image.SCALE_DEFAULT));
		jLabel.setBounds(0, 0, picWidth, pinHeight);
		jLabel.setIcon(icon);
	}

}
