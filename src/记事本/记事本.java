package 记事本;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class 记事本 {

	public static void main(String[] args) {
		setOthers st=new setOthers();
		st.setwin();

	}
}

class setOthers implements ActionListener{
	JFrame jf;        //窗口
	JTextArea jt;     //文本框
	JMenu jm;         //文件菜单
	JMenuBar menbar;     //选项卡
	JMenuItem newitem;    //新建
	JMenuItem openitem;    //打开
	JMenuItem baocun;     //保存
	JMenuItem exititem;    //退出
	JMenu bjm;         //编辑菜单
	JMenuBar bmenbar;     //选项卡
	JMenuItem bfind;   //查找
	JMenuItem bnext;    //下一个
	
	
	//窗体
	void setwin() {
		jf=new JFrame("记事本");
		jf.setSize(600,500);             //大小
		jf.setLocationRelativeTo(jf);     //居中
		
		setOthers so=new setOthers();
		jf.getContentPane().add(new JScrollPane(so.setJPan()));   //面板加入文本框个滚动条
		jf.setJMenuBar(so.setJMen());
		
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //关窗口 程序结束运行 释放内存
}	
	//可编辑文本框
	JTextArea setJPan() {
		jt=new JTextArea(); 
		jt.setEditable(true);
		jt.setOpaque(true);
		return jt;
	}
	
	String themePath=null;
	//选项卡
	JMenuBar setJMen() {
		File file=new File(".");
		try {
		themePath = file.getCanonicalPath();//获取当前路径
		} catch (IOException e) {
			e.printStackTrace();
		}
		themePath+=(File.separator+"theme");//获取图片文件夹	
	
		jm=new JMenu("文件");  //定义文件菜单
		menbar=new JMenuBar();  
		bjm=new JMenu("编辑");   //定义编辑菜单
		bmenbar=new JMenuBar();
		
		newitem=new JMenuItem("新建",
				new ImageIcon(themePath+File.separator+"xinjian.png"));
		openitem=new JMenuItem("打开",
				new ImageIcon(themePath+File.separator+"dakai.png"));
		baocun=new JMenuItem("保存",
				new ImageIcon(themePath+File.separator+"baocun.png"));
		exititem=new JMenuItem("退出",
				new ImageIcon(themePath+File.separator+"guanbi.png"));
		newitem.setMnemonic('N');   //新建快捷键
		newitem.setAccelerator(KeyStroke.getKeyStroke('N',
				java.awt.Event.CTRL_MASK));
		openitem.setMnemonic('O');   //打开快捷键
		openitem.setAccelerator(KeyStroke.getKeyStroke('O',
				java.awt.Event.CTRL_MASK));
		baocun.setMnemonic('S');   //保存快捷键
		baocun.setAccelerator(KeyStroke.getKeyStroke('S',
				java.awt.Event.CTRL_MASK));
		exititem.setMnemonic('X');   //退出快捷键
		exititem.setAccelerator(KeyStroke.getKeyStroke('X',
				java.awt.Event.CTRL_MASK));
		
		bfind=new JMenuItem("查找",
				new ImageIcon(themePath+File.separator+"chazhao.png"));
		bnext=new JMenuItem("替换",
				new ImageIcon(themePath+File.separator+"tihuan.png"));
		bfind.setMnemonic('F');   //查找快捷键
		bfind.setAccelerator(KeyStroke.getKeyStroke('F',
				java.awt.Event.CTRL_MASK));
		bnext.setMnemonic('R');   //替换快捷键
		bnext.setAccelerator(KeyStroke.getKeyStroke('R',
				java.awt.Event.CTRL_MASK));		
		
		jm.add(newitem);
		jm.add(openitem);
		jm.add(baocun);
		jm.addSeparator();
		jm.add(exititem);
		
		bjm.add(bfind);
		bjm.add(bnext);
		
		menbar.add(jm);
	    menbar.add(bjm);
		
		newitem.addActionListener(this);
		openitem.addActionListener(this);
		baocun.addActionListener(this);
		exititem.addActionListener(this);
		bfind.addActionListener(this);
		bnext.addActionListener(this);
	
		return menbar;
	}
	
	
	//选项卡事件
	@Override              
	public void actionPerformed(ActionEvent event) {
		Object obj=event.getSource();
		if(obj instanceof JMenuItem) {
			JMenuItem b=(JMenuItem)obj;
	    //新建事件
		if(b==newitem) {
			if(isBao()==false&&jt.getText().length()>=1) {
			     tishi();
				}
			else {
				setBao(false);
				jt.setText("");}
		};
		//打开事件
		if(b==openitem) {
			if(isBao()==false&&jt.getText().length()>=1) {
			     tishi();
				}
			else {
				setBao(false);
			File file=new File(choosedaoruPath());
			InputStream input;
			try {
				input=new FileInputStream(file);
				byte byt[]=new byte[(int)file.length()];
				for(int i=0;i<byt.length;i++) {
					byt[i]=(byte)input.read();
				}
				jt.replaceRange(new String(byt), 0, 0);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			}
			setBao(false);
		}
		//保存事件
		if(b==baocun) {
			baocun();
		}
		
		//退出事件
		if(b==exititem) {
			if(isBao()==false&&jt.getText().length()>=1) {
			     tishi();
				}
			else {
			System.exit(0);}
		}
		
		//查找事件
		if(b==bfind) {
			if(jt.getText().length()<1) {
				kong();
				}
			if(jt.getText().length()>=1) {
				setWeizhi(0);
				JFrame cfra=new JFrame("查找");
				cfra.setAlwaysOnTop(true);
				
				JLabel jlc=new JLabel("查找内容");
				JTextArea jtc=new JTextArea("");
				JButton jbx=new JButton("下一个");
				JButton jbq=new JButton("取消");
				
				cfra.setSize(318, 122);
				cfra.setLocationRelativeTo(jt);
				cfra.setLayout(null);
				
				jlc.setBounds(12, 6, 65, 20);
				jtc.setBounds(87, 6, 196, 20);
				jbx.setBounds(12, 52, 115, 27);
				jbq.setBounds(160, 52, 115, 27);
				
				cfra.add(jlc);
				cfra.add(jtc);
				cfra.add(jbx);
				cfra.add(jbq);
				cfra.setVisible(true);
				
				jbx.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String find=jtc.getText();
						if(find=="") {}
						if(find!="") {
						jt.requestFocus();
						String bfind=jt.getText();
						for(int i=getWeizhi();i<=bfind.length()-find.length();i++) {

							if(bfind.substring(i, i+find.length()).equals(find)) {
								jt.setSelectionStart(i);
								jt.setSelectionEnd(i+find.length());
								setWeizhi(i+1);
								break;
							}
						}	
					}
				}
			});
				jbq.addActionListener(new ActionListener() {		
					@Override
					public void actionPerformed(ActionEvent e) {
						cfra.setVisible(false);
					}
				});
			}
			
		}
		//替换事件
		if(b==bnext) {
			if(jt.getText().length()<1) {
				kong();
				}
			if(jt.getText().length()>=1) {
				setWeizhi(0);
				JFrame cfra=new JFrame("替换");
				cfra.setAlwaysOnTop(true);
				
				JLabel jlc=new JLabel("替换内容：");
				JTextArea jtc=new JTextArea("");
				JLabel jlcw=new JLabel("替换为：");
				JTextArea jtct=new JTextArea("");
				JButton jbx=new JButton("替换");
				JButton jbq=new JButton("取消");
				
				cfra.setSize(318, 155);
				cfra.setLocationRelativeTo(jt);
				cfra.setLayout(null);
				
				jlc.setBounds(7, 12, 72, 16);
				jtc.setBounds(89, 12, 191, 16);
				jlcw.setBounds(7, 38, 72, 16);
				jtct.setBounds(89, 38, 191, 16);
				jbx.setBounds(7, 81, 118, 30);
				jbq.setBounds(146, 81, 134, 30);
				
				cfra.add(jlc);
				cfra.add(jtc);
				cfra.add(jlcw);
				cfra.add(jtct);
				cfra.add(jbx);
				cfra.add(jbq);
				cfra.setVisible(true);
				
				jbx.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String find=jtc.getText();
						String tofind=jtct.getText();
						if(find==""||tofind=="") {}
						if(find!=""&&tofind!="") {
						jt.requestFocus();
						String bfind=jt.getText();
						for(int i=0;i<=bfind.length()-find.length();i++) {
							if(bfind.substring(i, i+find.length()).equals(find)) {
								jt.replaceRange(tofind, i, (i+find.length()));
							}
						}
						}
						cfra.setVisible(false);
					}
				});
				jbq.addActionListener(new ActionListener() {		
					@Override
					public void actionPerformed(ActionEvent e) {
						cfra.setVisible(false);
					}
				});
			}
			
		}
		
	}
	}
	
	
	private int weizhi=0;  //查找纪录上次位置

	public int getWeizhi() {
		return weizhi;
	}
	public void setWeizhi(int weizhi) {
		this.weizhi = weizhi;
	}
	private boolean bao=false;//判断是否已经保存
	
	public boolean isBao() {
		return bao;
	}
	public void setBao(boolean bao) {
		this.bao = bao;
	}

	//提示和选择文件目录等
	//未保存提示
	JLabel tishi;
	void tishi() {
		JFrame fra=new JFrame("提示");
		fra.setAlwaysOnTop(true);
		fra.setSize(200, 120);
		fra.setLocationRelativeTo(jt);
		fra.setLayout(null);
		
		tishi=new JLabel("尚未保存，请先保存");
		JButton right=new JButton("保存");
		JButton no=new JButton("取消");
		
		right.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			fra.setVisible(false);
            baocun();
			}
		});
		no.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			fra.setVisible(false);	
			}
		});
		
		tishi.setBounds(45,10, 150, 20);
		right.setBounds(15,45, 60, 20);
		no.setBounds(115,45, 60, 20);
		
		fra.add(tishi);
		fra.add(right);
		fra.add(no);
		fra.setVisible(true);;
	}
	//文档为空提示
	void kong() {
			JFrame fra=new JFrame("提示");
			fra.setAlwaysOnTop(true);
			fra.setSize(200, 120);
			fra.setLocationRelativeTo(jt);
			fra.setLayout(null);
			
			tishi=new JLabel("文本为空");
			JButton right=new JButton("确定");
			JButton no=new JButton("取消");
			
			right.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
		        fra.setVisible(false);
				}
			});
			no.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
				fra.setVisible(false);	
				}
			});
			
			tishi.setBounds(45,10, 150, 20);
			right.setBounds(15,45, 60, 20);
			no.setBounds(115,45, 60, 20);
			
			fra.add(tishi);
			fra.add(right);
			fra.add(no);
			fra.setVisible(true);;
	}
	
	
private String filepath=null;  //打开文档目录
private String baocunPath=null;   //保存文档目录
   public String getBaocunPath() { 
		return baocunPath;
	}
	public void setBaocunPath(String baocunPath) {
		this.baocunPath = baocunPath;
	}
public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	
//选择打开文档
String choosedaoruPath() {
	FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "txt 文件", "txt");
	int result = 0;
	JFileChooser fileChooser = new JFileChooser();
	FileSystemView fsv = FileSystemView.getFileSystemView();  
	System.out.println(fsv.getHomeDirectory());                //得到桌面路径
	fileChooser.setCurrentDirectory(fsv.getHomeDirectory());   //设置当前目录为桌面
	fileChooser.setDialogTitle("请选则要导入的文件...");
	fileChooser.setApproveButtonText("确定");
	fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);  //只显示文件
	fileChooser.setFileFilter(filter);
	result = fileChooser.showOpenDialog(fileChooser);
	if (JFileChooser.APPROVE_OPTION == result) {                    //确定导入纪录数据  
		       filepath=fileChooser.getSelectedFile().getPath();   
	   }
	return filepath;
}
//选择保存文档目录
String choosebaocun() {
	JFileChooser chooser = new JFileChooser();
	chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); //仅显示目录
	chooser.showOpenDialog(null);   //弹出一个文件选择器对话框。 
	baocunPath = chooser.getSelectedFile().getPath();
	return baocunPath;
}

void baocun() {
	JFrame fra=new JFrame("另存为...");
	
	JButton luj=new JButton("选择文件夹");
	JButton cun=new JButton("保存");
	JButton quxiao=new JButton("取消");
	JLabel jl=new JLabel("输入文件名：");
	JTextArea ming=new JTextArea("输入文件名");
	JTextArea lujin=new JTextArea("C:Users\\Administrator\\Desktop");
	lujin.setEditable(false);

	fra.setSize(386, 138);
	fra.setLocationRelativeTo(jt);
	fra.setLayout(null);
	
	luj.setBounds(7,6, 120, 21);
	lujin.setBounds(153,6,233,21);
	jl.setBounds(18, 37, 89, 21);
	ming.setBounds(153, 37, 233, 21);
	cun.setBounds(7, 73, 168, 30);
	quxiao.setBounds(204, 73, 167, 30);

	fra.add(luj);
	fra.add(lujin);
	fra.add(jl);
	fra.add(ming);
	fra.add(cun);
	fra.add(quxiao);
	fra.setVisible(true);
	
	//选择文件路径
	luj.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent event) {
		lujin.setText(choosebaocun());
		}
	});
	//取消
	quxiao.addActionListener(new ActionListener() {			
		@Override
		public void actionPerformed(ActionEvent e) {
			fra.setVisible(false);
		}
	});
	//保存
	cun.addActionListener(new ActionListener() {			
		@Override
		public void actionPerformed(ActionEvent e) {
			String datapath=lujin.getText();
			BufferedWriter bw;
			datapath+=(File.separator+ming.getText()+".txt");
			//JTextArea的换行字符使用String类的spilt()方法进行分割
			String[] write=jt.getText().split("\n");  
			File file1=new File(datapath);
			System.out.println(datapath);
			//创建文件
			if(file1.exists()) {    //判断文件是否存在
				file1.delete();     //删除文件
			}
			try {
				file1.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();}
			//写入文件
			try {
				bw = new BufferedWriter(new FileWriter(datapath));
				for (String str : write) {
					bw.write(str);
					bw.newLine();
					bw.flush();
					}
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			setBao(true);
			fra.setVisible(false);
		}
	});
}
}