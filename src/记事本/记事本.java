package ���±�;

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

public class ���±� {

	public static void main(String[] args) {
		setOthers st=new setOthers();
		st.setwin();

	}
}

class setOthers implements ActionListener{
	JFrame jf;        //����
	JTextArea jt;     //�ı���
	JMenu jm;         //�ļ��˵�
	JMenuBar menbar;     //ѡ�
	JMenuItem newitem;    //�½�
	JMenuItem openitem;    //��
	JMenuItem baocun;     //����
	JMenuItem exititem;    //�˳�
	JMenu bjm;         //�༭�˵�
	JMenuBar bmenbar;     //ѡ�
	JMenuItem bfind;   //����
	JMenuItem bnext;    //��һ��
	
	
	//����
	void setwin() {
		jf=new JFrame("���±�");
		jf.setSize(600,500);             //��С
		jf.setLocationRelativeTo(jf);     //����
		
		setOthers so=new setOthers();
		jf.getContentPane().add(new JScrollPane(so.setJPan()));   //�������ı����������
		jf.setJMenuBar(so.setJMen());
		
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //�ش��� ����������� �ͷ��ڴ�
}	
	//�ɱ༭�ı���
	JTextArea setJPan() {
		jt=new JTextArea(); 
		jt.setEditable(true);
		jt.setOpaque(true);
		return jt;
	}
	
	String themePath=null;
	//ѡ�
	JMenuBar setJMen() {
		File file=new File(".");
		try {
		themePath = file.getCanonicalPath();//��ȡ��ǰ·��
		} catch (IOException e) {
			e.printStackTrace();
		}
		themePath+=(File.separator+"theme");//��ȡͼƬ�ļ���	
	
		jm=new JMenu("�ļ�");  //�����ļ��˵�
		menbar=new JMenuBar();  
		bjm=new JMenu("�༭");   //����༭�˵�
		bmenbar=new JMenuBar();
		
		newitem=new JMenuItem("�½�",
				new ImageIcon(themePath+File.separator+"xinjian.png"));
		openitem=new JMenuItem("��",
				new ImageIcon(themePath+File.separator+"dakai.png"));
		baocun=new JMenuItem("����",
				new ImageIcon(themePath+File.separator+"baocun.png"));
		exititem=new JMenuItem("�˳�",
				new ImageIcon(themePath+File.separator+"guanbi.png"));
		newitem.setMnemonic('N');   //�½���ݼ�
		newitem.setAccelerator(KeyStroke.getKeyStroke('N',
				java.awt.Event.CTRL_MASK));
		openitem.setMnemonic('O');   //�򿪿�ݼ�
		openitem.setAccelerator(KeyStroke.getKeyStroke('O',
				java.awt.Event.CTRL_MASK));
		baocun.setMnemonic('S');   //�����ݼ�
		baocun.setAccelerator(KeyStroke.getKeyStroke('S',
				java.awt.Event.CTRL_MASK));
		exititem.setMnemonic('X');   //�˳���ݼ�
		exititem.setAccelerator(KeyStroke.getKeyStroke('X',
				java.awt.Event.CTRL_MASK));
		
		bfind=new JMenuItem("����",
				new ImageIcon(themePath+File.separator+"chazhao.png"));
		bnext=new JMenuItem("�滻",
				new ImageIcon(themePath+File.separator+"tihuan.png"));
		bfind.setMnemonic('F');   //���ҿ�ݼ�
		bfind.setAccelerator(KeyStroke.getKeyStroke('F',
				java.awt.Event.CTRL_MASK));
		bnext.setMnemonic('R');   //�滻��ݼ�
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
	
	
	//ѡ��¼�
	@Override              
	public void actionPerformed(ActionEvent event) {
		Object obj=event.getSource();
		if(obj instanceof JMenuItem) {
			JMenuItem b=(JMenuItem)obj;
	    //�½��¼�
		if(b==newitem) {
			if(isBao()==false&&jt.getText().length()>=1) {
			     tishi();
				}
			else {
				setBao(false);
				jt.setText("");}
		};
		//���¼�
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
		//�����¼�
		if(b==baocun) {
			baocun();
		}
		
		//�˳��¼�
		if(b==exititem) {
			if(isBao()==false&&jt.getText().length()>=1) {
			     tishi();
				}
			else {
			System.exit(0);}
		}
		
		//�����¼�
		if(b==bfind) {
			if(jt.getText().length()<1) {
				kong();
				}
			if(jt.getText().length()>=1) {
				setWeizhi(0);
				JFrame cfra=new JFrame("����");
				cfra.setAlwaysOnTop(true);
				
				JLabel jlc=new JLabel("��������");
				JTextArea jtc=new JTextArea("");
				JButton jbx=new JButton("��һ��");
				JButton jbq=new JButton("ȡ��");
				
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
		//�滻�¼�
		if(b==bnext) {
			if(jt.getText().length()<1) {
				kong();
				}
			if(jt.getText().length()>=1) {
				setWeizhi(0);
				JFrame cfra=new JFrame("�滻");
				cfra.setAlwaysOnTop(true);
				
				JLabel jlc=new JLabel("�滻���ݣ�");
				JTextArea jtc=new JTextArea("");
				JLabel jlcw=new JLabel("�滻Ϊ��");
				JTextArea jtct=new JTextArea("");
				JButton jbx=new JButton("�滻");
				JButton jbq=new JButton("ȡ��");
				
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
	
	
	private int weizhi=0;  //���Ҽ�¼�ϴ�λ��

	public int getWeizhi() {
		return weizhi;
	}
	public void setWeizhi(int weizhi) {
		this.weizhi = weizhi;
	}
	private boolean bao=false;//�ж��Ƿ��Ѿ�����
	
	public boolean isBao() {
		return bao;
	}
	public void setBao(boolean bao) {
		this.bao = bao;
	}

	//��ʾ��ѡ���ļ�Ŀ¼��
	//δ������ʾ
	JLabel tishi;
	void tishi() {
		JFrame fra=new JFrame("��ʾ");
		fra.setAlwaysOnTop(true);
		fra.setSize(200, 120);
		fra.setLocationRelativeTo(jt);
		fra.setLayout(null);
		
		tishi=new JLabel("��δ���棬���ȱ���");
		JButton right=new JButton("����");
		JButton no=new JButton("ȡ��");
		
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
	//�ĵ�Ϊ����ʾ
	void kong() {
			JFrame fra=new JFrame("��ʾ");
			fra.setAlwaysOnTop(true);
			fra.setSize(200, 120);
			fra.setLocationRelativeTo(jt);
			fra.setLayout(null);
			
			tishi=new JLabel("�ı�Ϊ��");
			JButton right=new JButton("ȷ��");
			JButton no=new JButton("ȡ��");
			
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
	
	
private String filepath=null;  //���ĵ�Ŀ¼
private String baocunPath=null;   //�����ĵ�Ŀ¼
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
	
//ѡ����ĵ�
String choosedaoruPath() {
	FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "txt �ļ�", "txt");
	int result = 0;
	JFileChooser fileChooser = new JFileChooser();
	FileSystemView fsv = FileSystemView.getFileSystemView();  
	System.out.println(fsv.getHomeDirectory());                //�õ�����·��
	fileChooser.setCurrentDirectory(fsv.getHomeDirectory());   //���õ�ǰĿ¼Ϊ����
	fileChooser.setDialogTitle("��ѡ��Ҫ������ļ�...");
	fileChooser.setApproveButtonText("ȷ��");
	fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);  //ֻ��ʾ�ļ�
	fileChooser.setFileFilter(filter);
	result = fileChooser.showOpenDialog(fileChooser);
	if (JFileChooser.APPROVE_OPTION == result) {                    //ȷ�������¼����  
		       filepath=fileChooser.getSelectedFile().getPath();   
	   }
	return filepath;
}
//ѡ�񱣴��ĵ�Ŀ¼
String choosebaocun() {
	JFileChooser chooser = new JFileChooser();
	chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); //����ʾĿ¼
	chooser.showOpenDialog(null);   //����һ���ļ�ѡ�����Ի��� 
	baocunPath = chooser.getSelectedFile().getPath();
	return baocunPath;
}

void baocun() {
	JFrame fra=new JFrame("���Ϊ...");
	
	JButton luj=new JButton("ѡ���ļ���");
	JButton cun=new JButton("����");
	JButton quxiao=new JButton("ȡ��");
	JLabel jl=new JLabel("�����ļ�����");
	JTextArea ming=new JTextArea("�����ļ���");
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
	
	//ѡ���ļ�·��
	luj.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent event) {
		lujin.setText(choosebaocun());
		}
	});
	//ȡ��
	quxiao.addActionListener(new ActionListener() {			
		@Override
		public void actionPerformed(ActionEvent e) {
			fra.setVisible(false);
		}
	});
	//����
	cun.addActionListener(new ActionListener() {			
		@Override
		public void actionPerformed(ActionEvent e) {
			String datapath=lujin.getText();
			BufferedWriter bw;
			datapath+=(File.separator+ming.getText()+".txt");
			//JTextArea�Ļ����ַ�ʹ��String���spilt()�������зָ�
			String[] write=jt.getText().split("\n");  
			File file1=new File(datapath);
			System.out.println(datapath);
			//�����ļ�
			if(file1.exists()) {    //�ж��ļ��Ƿ����
				file1.delete();     //ɾ���ļ�
			}
			try {
				file1.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();}
			//д���ļ�
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