package gui;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import DataBaseConn.Plan;
import DataBaseConn.Usage;
import DataBaseConn.conn;

public class ServicesActionListener implements ActionListener {
	Window window;
	private JButton exit;
	private JLabel logo;
	private JLabel service1;
	private JLabel service2;
	private JLabel service3;
	private JLabel forthismonth;
	String[] months = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
			"October", "November", "December" };

	public ServicesActionListener(Window window) {
		// TODO Auto-generated constructor stub
		this.window = window;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		window.frame.getContentPane().removeAll();
		window.frame.repaint();
		window.panel = new JPanel();
		window.panel.setBackground(Color.WHITE);
		window.frame.add(window.panel);
		window.panel.setLayout(null);
		/// code////
		List<Plan> plans = conn.GetAllPlans();
		int[] plansofuser = new int[plans.size()];

		List<Usage> allusages = new ArrayList<>();
		for (int i = 0; i < plans.size(); i++) {
			plansofuser[i] = -1;
			plansofuser[i] = conn.getFullplanid(conn.GetUserid(window.usertext.getText()), plans.get(i).getId());

			allusages.add(conn.getUsage(plansofuser[i]));

		}

		if (allusages.get(0) != null)
			forthismonth = new JLabel("For " + months[allusages.get(0).getMonth() - 1]);
		if (allusages.get(1) != null)
			forthismonth = new JLabel("For " + months[allusages.get(1).getMonth() - 1]);
		if (allusages.get(2) != null)
			forthismonth = new JLabel("For " + months[allusages.get(2).getMonth() - 1]);
		if ((allusages.get(0) == null) && (allusages.get(1) == null) && (allusages.get(2) == null)) {
			forthismonth = new JLabel("You have no plans");
			forthismonth.setBounds(10, 20, 300, 25);
			window.panel.add(forthismonth);
			//////////
			// Exit button parameters;
			this.exit = new JButton("Go back");
			this.exit.setBounds(10, 110, 100, 25);
			// Set color red when hoverd over button
			this.exit.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseEntered(java.awt.event.MouseEvent evt) {
					exit.setBackground(Color.RED);
				}

				public void mouseExited(java.awt.event.MouseEvent evt) {
					exit.setBackground(window.loginButton.getBackground());
				}
			});
			window.panel.add(this.exit);
			this.exit.addActionListener(new ExittoCustomerActionListener(this.window));
			// LOGO
			logo = new JLabel();
			this.logo.setBounds(0, 0, window.frame.getWidth(), window.frame.getHeight());
			BufferedImage img = null;
			try {
				img = ImageIO.read(new File("D:\\Programing test\\java\\MobileOperatorAlpha\\logo3.png"));

			} catch (IOException e2) {
				e2.printStackTrace();
			}
			Image dimg = img.getScaledInstance(logo.getWidth(), logo.getHeight(), Image.SCALE_SMOOTH);
			ImageIcon imageIcon = new ImageIcon(dimg);
			logo.setIcon(imageIcon);
			window.panel.add(logo);
			//////////////////////////////////////////////////
			window.frame.revalidate();
			window.frame.repaint();
			window.frame.setVisible(true);
			return;
		}
		forthismonth.setBounds(10, 20, 300, 25);
		window.panel.add(forthismonth);

		if (plansofuser[0] != -1) {
			if ((allusages.get(0).isActiveminutes()) && (allusages.get(0).isActivedata())
					&& (allusages.get(0).isActivesms()) && (allusages.get(0).isPayed())) {
				service1 = new JLabel("For plan " + plans.get(0).getName() + " "
						+ (plans.get(0).getMinutes() - allusages.get(0).getUsedminutes()) + " minutes left | "
						+ (plans.get(0).getSms() - allusages.get(0).getUsedsms()) + " SMS left | "
						+ (plans.get(0).getMobile_data() - allusages.get(0).getUseddata()) + " mobile data left");
				service1.setBounds(10, 40, 500, 25);
				window.panel.add(service1);
			}
			if ((allusages.get(0).isActiveminutes()) && !(allusages.get(0).isActivedata())
					&& (allusages.get(0).isActivesms()) && (allusages.get(0).isPayed())) {
				service1 = new JLabel("For plan " + plans.get(0).getName() + " "
						+ (plans.get(0).getMinutes() - allusages.get(0).getUsedminutes()) + " minutes left | "
						+ (plans.get(0).getSms() - allusages.get(0).getUsedsms()) + " SMS left | " + " no mobile data");
				service1.setBounds(10, 40, 500, 25);
				window.panel.add(service1);
			}
			if ((allusages.get(0).isActiveminutes()) && (allusages.get(0).isActivedata())
					&& !(allusages.get(0).isActivesms()) && (allusages.get(0).isPayed())) {
				service1 = new JLabel("For plan " + plans.get(0).getName() + " "
						+ (plans.get(0).getMinutes() - allusages.get(0).getUsedminutes()) + " minutes left | "
						+ " no more SMS | " + (plans.get(0).getMobile_data() - allusages.get(0).getUseddata())
						+ " mobile data left");
				service1.setBounds(10, 40, 500, 25);
				window.panel.add(service1);
			}
			if (!(allusages.get(0).isActiveminutes()) && (allusages.get(0).isActivedata())
					&& (allusages.get(0).isActivesms()) && (allusages.get(0).isPayed())) {
				service1 = new JLabel("For plan " + plans.get(0).getName() + " " + " No minutes left | "
						+ (plans.get(0).getSms() - allusages.get(0).getUsedsms()) + " SMS left | "
						+ (plans.get(0).getMobile_data() - allusages.get(0).getUseddata()) + " mobile data left");
				service1.setBounds(10, 40, 500, 25);
				window.panel.add(service1);
			}
			if (!(allusages.get(0).isPayed())) {
				service1 = new JLabel("Plan has expired");
				service1.setBounds(10, 40, 300, 25);
				window.panel.add(service1);
			}
			if (!(allusages.get(0).isActiveminutes()) && !(allusages.get(0).isActivedata())
					&& (allusages.get(0).isActivesms()) && (allusages.get(0).isPayed())) {
				service1 = new JLabel("For plan " + plans.get(0).getName() + " " + "No minutes left | "
						+ (plans.get(0).getSms() - allusages.get(0).getUsedsms()) + " SMS left | " + " no mobile data");
				service1.setBounds(10, 40, 500, 25);
				window.panel.add(service1);
			}
			if ((allusages.get(0).isActiveminutes()) && !(allusages.get(0).isActivedata())
					&& !(allusages.get(0).isActivesms()) && (allusages.get(0).isPayed())) {
				service1 = new JLabel("For plan " + plans.get(0).getName() + " "
						+ (plans.get(0).getMinutes() - allusages.get(0).getUsedminutes()) + " minutes left | "
						+ " no SMS left | " + " no mobile data left");
				service1.setBounds(10, 40, 500, 25);
				window.panel.add(service1);
			}
			if (!(allusages.get(0).isActiveminutes()) && (allusages.get(0).isActivedata())
					&& !(allusages.get(0).isActivesms()) && (allusages.get(0).isPayed())) {
				service1 = new JLabel("For plan " + plans.get(0).getName() + " " + " No minutes left | "
						+ (plans.get(0).getSms() - allusages.get(0).getUsedsms()) + " SMS left | "
						+ " no mobile data left");
				service1.setBounds(10, 40, 500, 25);
				window.panel.add(service1);
			}

			if (!(allusages.get(0).isActiveminutes()) && !(allusages.get(0).isActivedata())
					&& !(allusages.get(0).isActivesms()) && (allusages.get(0).isPayed())) {
				service1 = new JLabel("For plan " + plans.get(0).getName() + " " + "no minutes left | "
						+ " no SMS left | " + " no mobile data");
				service1.setBounds(10, 40, 500, 25);
				window.panel.add(service1);
			}
		}
//////////////////////plan2
		if (plansofuser[1] != -1) {
			if ((allusages.get(1).isActiveminutes()) && (allusages.get(1).isActivedata())
					&& (allusages.get(1).isActivesms()) && (allusages.get(1).isPayed())) {
				service2 = new JLabel("For plan " + plans.get(1).getName() + " "
						+ (plans.get(1).getMinutes() - allusages.get(1).getUsedminutes()) + " minutes left | "
						+ (plans.get(1).getSms() - allusages.get(1).getUsedsms()) + " SMS left | "
						+ (plans.get(1).getMobile_data() - allusages.get(1).getUseddata()) + " mobile data left");
				service2.setBounds(10, 60, 500, 25);
				window.panel.add(service2);
			}
			if ((allusages.get(1).isActiveminutes()) && !(allusages.get(1).isActivedata())
					&& (allusages.get(1).isActivesms()) && (allusages.get(1).isPayed())) {
				service2 = new JLabel("For plan " + plans.get(1).getName() + " "
						+ (plans.get(1).getMinutes() - allusages.get(1).getUsedminutes()) + " minutes left | "
						+ (plans.get(1).getSms() - allusages.get(1).getUsedsms()) + " SMS left | " + " no mobile data");
				service2.setBounds(10, 60, 500, 25);
				window.panel.add(service2);
			}
			if ((allusages.get(1).isActiveminutes()) && (allusages.get(1).isActivedata())
					&& !(allusages.get(1).isActivesms()) && (allusages.get(1).isPayed())) {
				service2 = new JLabel("For plan " + plans.get(1).getName() + " "
						+ (plans.get(1).getMinutes() - allusages.get(1).getUsedminutes()) + " minutes left | "
						+ " no more SMS | " + (plans.get(1).getMobile_data() - allusages.get(1).getUseddata())
						+ " mobile data left");
				service2.setBounds(10, 60, 500, 25);
				window.panel.add(service2);
			}
			if (!(allusages.get(1).isActiveminutes()) && (allusages.get(1).isActivedata())
					&& (allusages.get(1).isActivesms()) && (allusages.get(1).isPayed())) {
				service2 = new JLabel("For plan " + plans.get(1).getName() + " " + " No minutes left | "
						+ (plans.get(1).getSms() - allusages.get(1).getUsedsms()) + " SMS left | "
						+ (plans.get(1).getMobile_data() - allusages.get(1).getUseddata()) + " mobile data left");
				service2.setBounds(10, 60, 500, 25);
				window.panel.add(service2);
			}
			if (!(allusages.get(1).isPayed())) {
				service2 = new JLabel("Plan has expired");
				service2.setBounds(10, 60, 300, 25);
				window.panel.add(service2);
			}
			if (!(allusages.get(1).isActiveminutes()) && !(allusages.get(1).isActivedata())
					&& (allusages.get(1).isActivesms()) && (allusages.get(1).isPayed())) {
				service2 = new JLabel("For plan " + plans.get(1).getName() + " " + "No minutes left | "
						+ (plans.get(1).getSms() - allusages.get(1).getUsedsms()) + " SMS left | " + " no mobile data");
				service2.setBounds(10, 60, 500, 25);
				window.panel.add(service2);
			}
			if ((allusages.get(1).isActiveminutes()) && !(allusages.get(1).isActivedata())
					&& !(allusages.get(1).isActivesms()) && (allusages.get(1).isPayed())) {
				service2 = new JLabel("For plan " + plans.get(1).getName() + " "
						+ (plans.get(1).getMinutes() - allusages.get(1).getUsedminutes()) + " minutes left | "
						+ " no SMS left | " + " no mobile data left");
				service2.setBounds(10, 60, 500, 25);
				window.panel.add(service2);
			}
			if (!(allusages.get(1).isActiveminutes()) && (allusages.get(1).isActivedata())
					&& !(allusages.get(1).isActivesms()) && (allusages.get(1).isPayed())) {
				service2 = new JLabel("For plan " + plans.get(1).getName() + " " + " No minutes left | "
						+ (plans.get(1).getSms() - allusages.get(1).getUsedsms()) + " SMS left | "
						+ " no mobile data left");
				service2.setBounds(10, 60, 500, 25);
				window.panel.add(service2);
			}

			if (!(allusages.get(1).isActiveminutes()) && !(allusages.get(1).isActivedata())
					&& !(allusages.get(1).isActivesms()) && (allusages.get(1).isPayed())) {
				service2 = new JLabel("For plan " + plans.get(1).getName() + " " + "no minutes left | "
						+ " no SMS left | " + " no mobile data");
				service2.setBounds(10, 60, 500, 25);
				window.panel.add(service2);
			}
		}

/////////////////////plan3
		if (plansofuser[2] != -1) {
			if ((allusages.get(2).isActiveminutes()) && (allusages.get(2).isActivedata())
					&& (allusages.get(2).isActivesms()) && (allusages.get(2).isPayed())) {
				service3 = new JLabel("For plan " + plans.get(2).getName() + " "
						+ (plans.get(2).getMinutes() - allusages.get(2).getUsedminutes()) + " minutes left | "
						+ (plans.get(2).getSms() - allusages.get(2).getUsedsms()) + " SMS left | "
						+ (plans.get(2).getMobile_data() - allusages.get(2).getUseddata()) + " mobile data left");
				service3.setBounds(10, 80, 500, 25);
				window.panel.add(service3);
			}
			if ((allusages.get(2).isActiveminutes()) && !(allusages.get(2).isActivedata())
					&& (allusages.get(2).isActivesms()) && (allusages.get(2).isPayed())) {
				service3 = new JLabel("For plan " + plans.get(2).getName() + " "
						+ (plans.get(2).getMinutes() - allusages.get(2).getUsedminutes()) + " minutes left | "
						+ (plans.get(2).getSms() - allusages.get(2).getUsedsms()) + " SMS left | " + " no mobile data");
				service3.setBounds(10, 80, 500, 25);
				window.panel.add(service3);
			}
			if ((allusages.get(2).isActiveminutes()) && (allusages.get(2).isActivedata())
					&& !(allusages.get(2).isActivesms()) && (allusages.get(2).isPayed())) {
				service3 = new JLabel("For plan " + plans.get(2).getName() + " "
						+ (plans.get(2).getMinutes() - allusages.get(2).getUsedminutes()) + " minutes left | "
						+ " no more SMS | " + (plans.get(2).getMobile_data() - allusages.get(2).getUseddata())
						+ " mobile data left");
				service3.setBounds(10, 80, 500, 25);
				window.panel.add(service3);
			}
			if (!(allusages.get(2).isActiveminutes()) && (allusages.get(2).isActivedata())
					&& (allusages.get(2).isActivesms()) && (allusages.get(2).isPayed())) {
				service3 = new JLabel("For plan " + plans.get(2).getName() + " " + " No minutes left | "
						+ (plans.get(2).getSms() - allusages.get(2).getUsedsms()) + " SMS left | "
						+ (plans.get(2).getMobile_data() - allusages.get(2).getUseddata()) + " mobile data left");
				service3.setBounds(10, 80, 500, 25);
				window.panel.add(service3);
			}
			if (!(allusages.get(2).isPayed())) {
				service3 = new JLabel("Plan has expired");
				service3.setBounds(10, 80, 300, 25);
				window.panel.add(service3);
			}
			if (!(allusages.get(2).isActiveminutes()) && !(allusages.get(2).isActivedata())
					&& (allusages.get(2).isActivesms()) && (allusages.get(2).isPayed())) {
				service3 = new JLabel("For plan " + plans.get(2).getName() + " " + "No minutes left | "
						+ (plans.get(2).getSms() - allusages.get(2).getUsedsms()) + " SMS left | " + " no mobile data");
				service3.setBounds(10, 80, 500, 25);
				window.panel.add(service3);
			}
			if ((allusages.get(2).isActiveminutes()) && !(allusages.get(2).isActivedata())
					&& !(allusages.get(2).isActivesms()) && (allusages.get(2).isPayed())) {
				service3 = new JLabel("For plan " + plans.get(2).getName() + " "
						+ (plans.get(2).getMinutes() - allusages.get(1).getUsedminutes()) + " minutes left | "
						+ " no SMS left | " + " no mobile data left");
				service3.setBounds(10, 80, 500, 25);
				window.panel.add(service3);
			}
			if (!(allusages.get(2).isActiveminutes()) && (allusages.get(2).isActivedata())
					&& !(allusages.get(2).isActivesms()) && (allusages.get(2).isPayed())) {
				service3 = new JLabel("For plan " + plans.get(2).getName() + " " + " No minutes left | "
						+ (plans.get(0).getSms() - allusages.get(0).getUsedsms()) + " SMS left | "
						+ " no mobile data left");
				service3.setBounds(10, 80, 500, 25);
				window.panel.add(service3);
			}

			if (!(allusages.get(2).isActiveminutes()) && !(allusages.get(2).isActivedata())
					&& !(allusages.get(2).isActivesms()) && (allusages.get(2).isPayed())) {
				service3 = new JLabel("For plan " + plans.get(2).getName() + " " + "no minutes left | "
						+ " no SMS left | " + " no mobile data");
				service3.setBounds(10, 80, 500, 25);
				window.panel.add(service3);
			}
		}

		//////////
		// Exit button parameters;
		this.exit = new JButton("Go back");
		this.exit.setBounds(10, 110, 100, 25);
		// Set color red when hoverd over button
		this.exit.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				exit.setBackground(Color.RED);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				exit.setBackground(window.loginButton.getBackground());
			}
		});
		window.panel.add(this.exit);
		this.exit.addActionListener(new ExittoCustomerActionListener(this.window));
		// LOGO
		logo = new JLabel();
		this.logo.setBounds(0, 0, window.frame.getWidth(), window.frame.getHeight());
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("D:\\Programing test\\java\\MobileOperatorAlpha\\logo3.png"));

		} catch (IOException e2) {
			e2.printStackTrace();
		}
		Image dimg = img.getScaledInstance(logo.getWidth(), logo.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		logo.setIcon(imageIcon);
		window.panel.add(logo);
		//////////////////////////////////////////////////
		window.frame.revalidate();
		window.frame.repaint();
		window.frame.setVisible(true);

	}

}
