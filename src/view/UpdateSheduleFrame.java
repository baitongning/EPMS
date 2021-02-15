package view;

import dao.ScheduleDao;
import model.TbSchedule;
import tools.StringUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 修改员工信息画面
 * @author baito
 */
public class UpdateSheduleFrame extends JFrame {

	private JPanel contentPane;
	private JTextField idField;
	private JTextField nameField;
	private DateChooserJButton startButton;
	private DateChooserJButton endButton;
	private JTextField placeField;
	private JTextField reasonField;
	private JTextArea explainsField;
	private TbSchedule person = null;

	public UpdateSheduleFrame(String id,SheduleFrame sheduleFrame) {
		TbSchedule person = ScheduleDao.findScheduleById(id);
		//初始化时通过前页面传过来的recordNumber查询到这个TbPerson对象，然后初始化表单。
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(500, 250, 896, 611);
		setTitle("查看员工信息");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblt = new JLabel("员工编号：");
		lblt.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lblt.setBounds(300, 30, 98, 41);
		contentPane.add(lblt);

		idField = new JTextField();
		idField.setBounds(400, 30, 138, 41);
		idField.setFont(new Font("微软雅黑", Font.BOLD, 18));
		idField.setText(person.getPerson_id());
		idField.setEnabled(false);
		contentPane.add(idField);
		idField.setColumns(10);


		JLabel namelblt = new JLabel("员工姓名：");
		namelblt.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		namelblt.setBounds(300, 90, 98, 41);
		contentPane.add(namelblt);

		nameField = new JTextField();
		nameField.setBounds(400, 90, 138, 41);
		nameField.setFont(new Font("微软雅黑", Font.BOLD, 18));
		nameField.setText(person.getPerson_name());
		contentPane.add(nameField);
		nameField.setColumns(10);


		JLabel startTimeblt = new JLabel("出差开始时间：");
		startTimeblt.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		startTimeblt.setBounds(265, 150, 150, 41);
		contentPane.add(startTimeblt);

		startButton = new DateChooserJButton();
		startButton.setBounds(400, 150, 250, 41);
		startButton.setFont(new Font("微软雅黑", Font.BOLD, 18));
		startButton.setDate(person.getSchedule_start());
		contentPane.add(startButton);

		JLabel endTimeblt = new JLabel("出差结束时间：");
		endTimeblt.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		endTimeblt.setBounds(265, 210, 150, 41);
		contentPane.add(endTimeblt);

		endButton = new DateChooserJButton();
		endButton.setBounds(400, 210, 250, 41);
		endButton.setFont(new Font("微软雅黑", Font.BOLD, 18));
		endButton.setDate(person.getSchedule_end());
		contentPane.add(endButton);

		JLabel placelblt = new JLabel("出差地点：");
		placelblt.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		placelblt.setBounds(300, 270, 98, 41);
		contentPane.add(placelblt);

		placeField = new JTextField();
		placeField.setBounds(400, 270, 200, 41);
		placeField.setFont(new Font("微软雅黑", Font.BOLD, 18));
		placeField.setText(person.getSchedule_place());
		contentPane.add(placeField);
		placeField.setColumns(10);

		JLabel reasonlblt = new JLabel("出差事由：");
		reasonlblt.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		reasonlblt.setBounds(300, 330, 98, 41);
		contentPane.add(reasonlblt);

		reasonField = new JTextField();
		reasonField.setBounds(400, 330, 200, 41);
		reasonField.setFont(new Font("微软雅黑", Font.BOLD, 18));
		reasonField.setText(person.getSchedule_reason());
		contentPane.add(reasonField);
		reasonField.setColumns(10);

		JLabel explainslblt = new JLabel("备注：");
		explainslblt.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		explainslblt.setBounds(300, 390, 98, 41);
		contentPane.add(explainslblt);

		explainsField = new JTextArea(5, 10);
		explainsField.setBounds(400, 390, 350, 80);
		explainsField.setFont(new Font("微软雅黑", Font.BOLD, 18));
		explainsField.setText(person.getExplains());
		contentPane.add(explainsField);
		explainsField.setColumns(10);


		JButton addButton = new JButton("修正");
		addButton.setBounds(300, 500, 113, 41);
		contentPane.add(addButton);
		
		
		JButton exitButton = new JButton("退出");
		exitButton.setBounds(500, 500, 113, 41);
		contentPane.add(exitButton);
		
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean finish = true;
				if (StringUtil.isEmpty(idField.getText())) {
					finish = false;
					JOptionPane.showMessageDialog(null, "员工编号不能为空");
				}

				if (StringUtil.isEmpty(nameField.getText()) && finish) {
					finish = false;
					JOptionPane.showMessageDialog(null, "员工编号不能为空");
				}

				if (startButton.getDate().getTime() - endButton.getDate().getTime() >= 0 && finish) {
					finish = false;
					JOptionPane.showMessageDialog(null, "出差结束日要大于出差开始日");
				}
				if (finish) {
					//创建新的TbSchedule表对象，查询是否已存在该员工，按逻辑判断所有格式是否正确，set放数据，执行add，成功则提示
					TbSchedule findTbSchedule = ScheduleDao.findScheduleById(idField.getText().trim());

					if (findTbSchedule == null) {
						JOptionPane.showMessageDialog(null, "不存在员工号" + idField.getText().trim() + "！");

					} else {
						findTbSchedule.setPerson_id(idField.getText().trim());
						findTbSchedule.setPerson_name(nameField.getText().trim());
						findTbSchedule.setSchedule_start(startButton.getDate());
						findTbSchedule.setSchedule_end(endButton.getDate());
						findTbSchedule.setSchedule_place(placeField.getText().trim());
						findTbSchedule.setSchedule_reason(reasonField.getText().trim());
						findTbSchedule.setExplains(explainsField.getText().trim());

						int count = ScheduleDao.updateSchedule(findTbSchedule);
						if(count == 0){
							JOptionPane.showMessageDialog(null, "修改失败！请联系管理员");
						}else{
							JOptionPane.showMessageDialog(null, "修改成功！");
							dispose();
							sheduleFrame.refreshPersonButton_Do();
						}

					}
				}
			}
		});
	}
}
