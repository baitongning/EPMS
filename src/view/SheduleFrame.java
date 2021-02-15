package view;

import dao.*;
import model.*;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * 日程管理系统主画面
 *
 * @author btn
 */
public class SheduleFrame {

    public static JFrame frame;
    private JTextField nameOrIdField;
    private JTable table;
    private DefaultTableModel model;
    private String recordNumber;
    private JLabel statusLabel;
    private String userId;

    /**
     * 启动该画面.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SheduleFrame window = new SheduleFrame("T00001");
                    window.frame.setVisible(true);
                    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                    Dimension frameSize = window.frame.getSize();
                    if (frameSize.height > screenSize.height) {
                        frameSize.height = screenSize.height;
                    }
                    if (frameSize.width > screenSize.width) {
                        frameSize.width = screenSize.width;
                    }
                    window.frame.setLocation((screenSize.width - frameSize.width) / 2,
                            (screenSize.height - frameSize.height) / 2);
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public SheduleFrame() {
    }

    public SheduleFrame(String uid) {
        userId = uid;
        initialize();
    }


    /**
     * 初始化画面
     */
    private void initialize() {
        frame = new JFrame();
        frame.setTitle("人事日程管理系统");
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(
                SheduleFrame.class.getResource("/images/storage_128px.png")));
        frame.setBounds(100, 100, 1600, 900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 关闭窗口监听
        frame.setDefaultCloseOperation(frame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                if (JOptionPane.showConfirmDialog(null, "确认退出?", "提示",
                        JOptionPane.YES_NO_OPTION) == 0) {
                    System.exit(0);
                }
            }
        });

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(Color.WHITE);
        frame.getContentPane().add(menuBar, BorderLayout.NORTH);

        JMenu menu = new JMenu("文件");
        menu.setIcon(new ImageIcon(SheduleFrame.class
                .getResource("/images/base.png")));
        menuBar.add(menu);


        JMenuItem menuItem_1 = new JMenuItem("退出");
        menuItem_1.setMnemonic(KeyEvent.VK_Q);
        menuItem_1.setIcon(new ImageIcon(SheduleFrame.class
                .getResource("/images/exit.png")));
        menuItem_1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        menu.add(menuItem_1);
        menuItem_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
                Event.CTRL_MASK));
        menuItem_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(null, "确认退出?", "提示",
                        JOptionPane.YES_NO_OPTION) == 0) {
                    System.exit(0);
                }
            }
        });


        JMenu viewMenu = new JMenu("主题");
        menuBar.add(viewMenu);
        viewMenu.setIcon(new ImageIcon(SheduleFrame.class
                .getResource("/images/view.png")));
        ButtonGroup group = new ButtonGroup();
        JRadioButtonMenuItem v1 = new JRadioButtonMenuItem("Metal", true);
        v1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        v1.setIcon(new ImageIcon(SheduleFrame.class.getResource("/images/edit.png")));
        viewMenu.add(v1);
        v1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    UIManager
                            .setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
                    SwingUtilities.updateComponentTreeUI(frame);
                } catch (ClassNotFoundException | InstantiationException
                        | IllegalAccessException
                        | UnsupportedLookAndFeelException e1) {
                }
            }
        });
        JRadioButtonMenuItem v2 = new JRadioButtonMenuItem("Motif");
        v2.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        v2.setIcon(new ImageIcon(SheduleFrame.class.getResource("/images/edit.png")));
        viewMenu.add(v2);
        v2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    UIManager
                            .setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
                    SwingUtilities.updateComponentTreeUI(frame);
                } catch (ClassNotFoundException | InstantiationException
                        | IllegalAccessException
                        | UnsupportedLookAndFeelException e1) {
                }
            }
        });
        JRadioButtonMenuItem v3 = new JRadioButtonMenuItem("Windows");
        v3.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        v3.setIcon(new ImageIcon(SheduleFrame.class.getResource("/images/edit.png")));
        viewMenu.add(v3);
        v3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    UIManager
                            .setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                    SwingUtilities.updateComponentTreeUI(frame);
                } catch (ClassNotFoundException | InstantiationException
                        | IllegalAccessException
                        | UnsupportedLookAndFeelException e1) {
                }
            }
        });
        JRadioButtonMenuItem v4 = new JRadioButtonMenuItem("Nimbus");
        v4.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        v4.setIcon(new ImageIcon(SheduleFrame.class.getResource("/images/edit.png")));
        viewMenu.add(v4);
        v4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    UIManager
                            .setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
                    SwingUtilities.updateComponentTreeUI(frame);
                } catch (ClassNotFoundException | InstantiationException
                        | IllegalAccessException
                        | UnsupportedLookAndFeelException e1) {
                }
            }
        });
        JRadioButtonMenuItem v5 = new JRadioButtonMenuItem("beautyeye");
        v5.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        v5.setIcon(new ImageIcon(SheduleFrame.class.getResource("/images/edit.png")));
        viewMenu.add(v5);
        v5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
                    BeautyEyeLNFHelper
                            .launchBeautyEyeLNF();
                    UIManager.put("RootPane.setupButtonVisible", false);
                    SwingUtilities.updateComponentTreeUI(frame);
                } catch (Exception e1) {
                }
            }
        });
        group.add(v1);
        group.add(v2);
        group.add(v3);
        group.add(v4);
        group.add(v5);
        viewMenu.add(v1);
        viewMenu.add(v2);
        viewMenu.add(v3);
        viewMenu.add(v4);
        viewMenu.add(v5);

        JPanel panel6 = new JPanel();
        panel6.setLayout(new BorderLayout());


        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        frame.getContentPane().add(panel6, BorderLayout.CENTER);
        panel6.add(tabbedPane, BorderLayout.CENTER);


        JToolBar toolBar = new JToolBar();
        panel6.add(toolBar, BorderLayout.NORTH);

        JButton btnNewButton_2 = new JButton("导出");
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dealExportbtn();
            }

        });
        btnNewButton_2.setIcon(new ImageIcon(SheduleFrame.class.getResource("/images/saveHS.png")));
        toolBar.add(btnNewButton_2);


        JButton btnNewButton_5 = new JButton("计算器");
        btnNewButton_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new tools.JCalculator();
            }
        });
        btnNewButton_5.setIcon(new ImageIcon(SheduleFrame.class.getResource("/images/CalculatorHS.png")));
        toolBar.add(btnNewButton_5);

        JButton btnNewButton_6 = new JButton("记事本");
        btnNewButton_6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new tools.JNotepad();
            }
        });
        btnNewButton_6.setIcon(new ImageIcon(SheduleFrame.class.getResource("/images/edit.png")));
        toolBar.add(btnNewButton_6);

        //状态栏
        statusLabel = new JLabel("");
        statusLabel.setBorder(BorderFactory.createEmptyBorder(5, 30, 5, 5));
        statusLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        toolBar.add(statusLabel);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = df.format(new Date());
        statusLabel.setText("员工ID：" + userId + "      " + "用户权限：超级管理员" + "      " + "登陆时间：" + time);
        //tabbedPane, BorderLayout.CENTER

        JPanel panel = new JPanel();
        tabbedPane.addTab("员工日程管理", null, panel, null);
        panel.setLayout(new BorderLayout(0, 0));

        JPanel panel_3 = new JPanel();
        panel.add(panel_3, BorderLayout.NORTH);

        JButton addPersonButton = new JButton("增加");
        addPersonButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addPersonButton_Do();
            }
        });
        panel_3.add(addPersonButton);

        JButton delPersonButton = new JButton("删除");
        delPersonButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                delPersonButton_Do();
            }
        });
        panel_3.add(delPersonButton);

        JButton updatePersonButton = new JButton("修改");
        updatePersonButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updatePersonButton_Do();
            }
        });
        panel_3.add(updatePersonButton);

        JButton refreshPersonButton = new JButton("刷新");
        refreshPersonButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                refreshPersonButton_Do();
            }
        });
        panel_3.add(refreshPersonButton);

        JLabel label = new JLabel("      行高");
        panel_3.add(label);

        JSlider rowHeightSlider = new JSlider(JSlider.HORIZONTAL, 5, 100, 70);
        rowHeightSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                int height = ((JSlider) e.getSource()).getValue();
                table.setRowHeight(height);
                table.repaint();
            }
        });
        panel_3.add(rowHeightSlider);

        JLabel lblid = new JLabel("                   姓名");
        panel_3.add(lblid);

        nameOrIdField = new JTextField();
        panel_3.add(nameOrIdField);
        nameOrIdField.setColumns(10);

        JButton searchButton = new JButton("搜索");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchButton_Do();
            }
        });
        panel_3.add(searchButton);


        JScrollPane scrollPane = new JScrollPane();
        panel.add(scrollPane, BorderLayout.CENTER);

        table = new JTable() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setRowHeight(40);
        table.setBorder(BorderFactory.createEtchedBorder());
        table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //表数据居中
        DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
        cr.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, cr);

        //设置表头居中显示
        ((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        //添加单列排序功能
        table.setAutoCreateRowSorter(true);
        model = (DefaultTableModel) table.getModel();
        model.setColumnIdentifiers(new String[]{"ID", "员工卡号", "员工姓名", "出差剩余天数", "出差开始时间", "出差结束时间", "出差地点", "出差事由", "备注"});
        scrollPane.setViewportView(table);

        List<TbSchedule> schedule = ScheduleDao.findAllSchedule();
        for (TbSchedule p : schedule) {
            String start_time;
            String end_time;
            String days = null;
            try {
                start_time = new SimpleDateFormat("yyyy-MM-dd").format(p.getSchedule_start());
                end_time = new SimpleDateFormat("yyyy-MM-dd").format(p.getSchedule_end());

                Date date1 = new Date();
                int day = (int) ((p.getSchedule_end().getTime() - date1.getTime()) / (1000 * 3600 * 24));
                if (day > 0) {
                    days = "剩余" + day + "天";
                } else if (day == 0) {
                    days = "今天";
                } else if (day < 0) {
                    days = "已逾期" + Math.abs(day) + "天";
                }
            } catch (NullPointerException e1) {
                System.out.println("日期格式转换出错");
                e1.printStackTrace();
                start_time = "";
                end_time = "";
            }
            model.addRow(new String[]{String.valueOf(p.getId()), p.getPerson_id(), p.getPerson_name(), days, start_time, end_time,
                    p.getSchedule_place(), p.getSchedule_reason(), p.getExplains()});
        }

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        header.setPreferredSize(new Dimension(header.getWidth(), 25));
    }

    private void addPersonButton_Do() {
        AddSheduleFrame addSheduleFrame = new AddSheduleFrame(this);
        addSheduleFrame.setVisible(true);
    }


    private void delPersonButton_Do() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "请先从表格中选中一行员工信息！");
        } else {
            recordNumber = (String) table.getValueAt(row, 1);
            int value = JOptionPane.showConfirmDialog(null, "你确认删除选中员工吗？", "", JOptionPane.YES_NO_OPTION);
            if (value == JOptionPane.YES_OPTION) {
                ScheduleDao.deleteSchedule(recordNumber);
                refreshPersonButton_Do();
            } else {
            }
        }

    }

    private void updatePersonButton_Do() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "请先从表格中选中一行员工信息！");
        } else {
            recordNumber = (String) table.getValueAt(row, 1);
            UpdateSheduleFrame pl = new UpdateSheduleFrame(recordNumber,this);
            pl.setVisible(true);
        }
    }


    protected void refreshPersonButton_Do() {
        model.setRowCount(0);
        List<TbSchedule> schedule = ScheduleDao.findAllSchedule();
        for (TbSchedule p : schedule) {
            String start_time;
            String end_time;
            String days = null;
            try {
                start_time = new SimpleDateFormat("yyyy-MM-dd").format(p.getSchedule_start());
                end_time = new SimpleDateFormat("yyyy-MM-dd").format(p.getSchedule_end());

                Date date1 = new Date();
                int day = (int) ((p.getSchedule_end().getTime() - date1.getTime()) / (1000 * 3600 * 24));
                if (day > 0) {
                    days = "剩余" + day + "天";
                } else if (day == 0) {
                    days = "今天";
                } else if (day < 0) {
                    days = "已逾期" + Math.abs(day) + "天";
                }
            } catch (NullPointerException e1) {
                System.out.println("日期格式转换出错");
                e1.printStackTrace();
                start_time = "";
                end_time = "";
            }
            model.addRow(new String[]{String.valueOf(p.getId()), p.getPerson_id(), p.getPerson_name(), days, start_time, end_time,
                    p.getSchedule_place(), p.getSchedule_reason(), p.getExplains()});
        }
    }

    private void searchButton_Do() {

        model.setRowCount(0);
        List<TbSchedule> schedule = ScheduleDao.findAllScheduleByName("%" + nameOrIdField.getText().trim() + "%");
        for (TbSchedule p : schedule) {
            String start_time;
            String end_time;
            String days = null;
            try {
                start_time = new SimpleDateFormat("yyyy-MM-dd").format(p.getSchedule_start());
                end_time = new SimpleDateFormat("yyyy-MM-dd").format(p.getSchedule_end());

                Date date1 = new Date();
                int day = (int) ((p.getSchedule_end().getTime() - date1.getTime()) / (1000 * 3600 * 24));
                if (day > 0) {
                    days = "剩余" + day + "天";
                } else if (day == 0) {
                    days = "今天";
                } else if (day < 0) {
                    days = "已逾期" + Math.abs(day) + "天";
                }
            } catch (NullPointerException e1) {
                System.out.println("日期格式转换出错");
                e1.printStackTrace();
                start_time = "";
                end_time = "";
            }
            model.addRow(new String[]{String.valueOf(p.getId()), p.getPerson_id(), p.getPerson_name(), days, start_time, end_time,
                    p.getSchedule_place(), p.getSchedule_reason(), p.getExplains()});
        }

    }


    protected void dealExportbtn() {
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            fileChooser.showSaveDialog(frame);//显示保存对话框
            String fi = fileChooser.getSelectedFile().getAbsolutePath() + ".xls";
            System.out.println(fi);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fi, true), "GBK"));
            for (int i = 0; i < table.getColumnCount(); i++) {
                writer.write(table.getColumnName(i) + "\t");
            }
            writer.write("\n");
            for (int i = 0; i < table.getRowCount(); i++) {
                for (int j = 0; j < table.getColumnCount(); j++) {
                    try {
                        writer.write(table.getValueAt(i, j).toString() + "\t");
                    } catch (NullPointerException e) {
                        writer.write("" + "\t");
                        e.printStackTrace();
                    }
                }
                writer.write("\n");
            }
            writer.close();
            JOptionPane.showMessageDialog(null, "文件导出成功");
        } catch (Exception ee) {
            ee.printStackTrace();
        }

    }
}
