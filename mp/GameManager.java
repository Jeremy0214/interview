package src;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import java.lang.*;
import java.util.ArrayList;

public class GameManager extends JFrame implements KeyListener,ActionListener{ 
	// Character
	static final int chh = 500;
	static final int chw = 500;
	static final int chp = 300;
	// Monster
	static final int mposx = 1280;
	static final int mposy = 460;
	static final int mheight = 425;
	static final int mwidth = 229;
	static final int hitdist = 300;
	static final int damagedist = 5;
	static final int movedist = 2;
	static final int skilldist = 850;
	// Boss
	static final int bposx = 1000;
	static final int bposy = 100;
	static final int bwidth = 800;
	static final int bheight = 600;
	static final int bmovedist = 8;
	static boolean showBoss = false;

	static ArrayList<PlaySound> ps  = new ArrayList<PlaySound>(3); // 0: Background Music, 1: Hit Sound Effect, 2: Skill Sound Effect
	static ArrayList<ImageIcon> im  = new ArrayList<ImageIcon>(2); // 0: background, 1: Character, 2: npc/dialog
	static ArrayList<ImageIcon> skillIcon = new ArrayList<ImageIcon>(3); // skill image
	static ArrayList<ImageIcon> portalIcon = new ArrayList<ImageIcon>(2); // portal image
	static ArrayList<JLabel> jl     = new ArrayList<JLabel>(2);    // 0: background, 1: Character. 2: npc/dialog
	static ArrayList<JLabel> skillLabel    = new ArrayList<JLabel>(3);    // skill label
	static ArrayList<JLabel> portalLabel    = new ArrayList<JLabel>(2);	   // portal label
	static int portalno = 1;
	static boolean p1OrP2 = true; // true: portal 1, false: portal 2
	static boolean skill=false;
	static boolean turnRightOrLeft=true;//1=right 0=left
	boolean AtStirr=false;
	static boolean attack=false;
	boolean atJump=false;
	boolean atDown=false;
	int count=0,portalTo=0,count2=0;
	boolean map1=true;
	static boolean skill1=false;
	int count1=0;

	static GameManager frm = new GameManager();
	static JLabel myScript = new JLabel("Boss is coming!");


	static{
		ps.add(new PlaySound("music\\backGround\\01. Enter Hallownest.wav"));
		ps.add(new PlaySound("music\\monsterHit.wav"));

		im.add(new ImageIcon("image\\backGround\\bg1.jpg"));
		jl.add(new JLabel(im.get(0)));

		im.add(new ImageIcon("image\\character\\chst2.gif"));
		jl.add(new JLabel(im.get(1)));

		im.add(new ImageIcon("image\\backGround\\dialog.png"));
		jl.add(new JLabel(im.get(2)));

		//im.add(new ImageIcon("image\\monster\\boss.right.gif"));
		//jl.add(new JLabel(im.get(3)));

		skillIcon.add(new ImageIcon("image\\character\\skill.gif"));
		skillLabel.add(new JLabel(skillIcon.get(0)));

		portalIcon.add(new ImageIcon("image\\portal\\portal.game.pv.4.0.png"));
		portalLabel.add(new JLabel(portalIcon.get(0)));

		portalIcon.add(new ImageIcon("image\\portal\\portal.game.pv.4.0.png"));
		portalLabel.add(new JLabel(portalIcon.get(1)));

		portalIcon.add(new ImageIcon("image\\character\\npc.png"));
		portalLabel.add(new JLabel(portalIcon.get(2)));

		portalIcon.add(new ImageIcon("image\\portal\\portal.game.pv.4.0.png"));
		portalLabel.add(new JLabel(portalIcon.get(3)));

		portalIcon.add(new ImageIcon("image\\portal\\portal.game.pv.4.0.png"));
		portalLabel.add(new JLabel(portalIcon.get(4)));


		skillLabel.get(0).setBounds(100,0,972,731);

		portalLabel.get(0).setBounds(1100,480,127,178);
		portalLabel.get(1).setBounds(0,480,127,178);
		portalLabel.get(2).setBounds(900, 300, 441, 346);
		portalLabel.get(3).setBounds(0,480,127,178);
		portalLabel.get(4).setBounds(250,480,127,178);

		portalLabel.get(0).setVisible(true);
		portalLabel.get(1).setVisible(false);
		portalLabel.get(2).setVisible(false);
		portalLabel.get(3).setVisible(false);
		portalLabel.get(4).setVisible(false);

		jl.get(0).setBounds(0,0,1280,720);
		jl.get(1).setBounds(-200,chp,chh,chw);
		jl.get(2).setBounds(0,0,1280,720);
		myScript.setBounds(200, 200, 500, 100);
		//jl.get(3).setBounds(100,100,800,600);
		
		skillLabel.get(0).setVisible(false);

		myScript.setVisible(false);
		jl.get(0).setVisible(true);
		jl.get(1).setVisible(true);
		jl.get(2).setVisible(false);
		//jl.get(3).setVisible(false);

		frm.add(myScript);
		frm.add(jl.get(2));
	}


	//static ImageIcon backgroundd=new ImageIcon("image\\backGround\\bg1.jpg"); // BG IMAGE
	//static JLabel jlabBackGround=new JLabel(backgroundd);
	

	//static ImageIcon character=new ImageIcon("image\\character\\chst2.gif");

	//static JLabel jCharacter= new JLabel(character);
	//static ImageIcon skillIcon=new ImageIcon("image\\character\\skill.gif");
	//static JLabel jSkill=new JLabel(skillIcon);
	/* 
	static ImageIcon skillIcon2=new ImageIcon("image\\character\\TgqMhc.gif");
	static JLabel jSkill2=new JLabel(skillIcon2);
	*/

	//static ImageIcon portalIcon=new ImageIcon("image\\portal\\portal.game.pv.4.0.png");
	//static JLabel jPortal1=new JLabel(portalIcon);
	//static JLabel jPortal2=new JLabel(portalIcon);


	/* 
	static boolean skill2=false;
	int count3=0;
	
	int count4=0;
	static boolean skill3=false;
	boolean skill3Exist=false;
	boolean deathSkill3=false;
	static ImageIcon skill3Icon=new ImageIcon("image\\character\\8fXp4Jm.gif");
	static JLabel jSkill3=new JLabel(skill3Icon);
	
	static ImageIcon skillIcon4=new ImageIcon("image\\character\\skill4.gif");
	static JLabel jSkill4=new JLabel(skillIcon4);
	int count5=0;
	static boolean skill4=false;
	*/
	static Timer timer=new Timer(3,frm);
	static monster ms1= new monster("image\\monster\\ms.png");
	static Thread thread1=new Thread(ms1);
	static monster2 ms2= new monster2("image\\monster\\ms.png");
	static Thread thread2=new Thread(ms2);
	static monster3 ms3=new monster3("image\\monster\\ms.png");
	static Thread thread3=new Thread(ms3);
	static monster4 ms4=new monster4("image\\monster\\ms.png");
	static Thread thread4=new Thread(ms4);
	static monster5 ms5=new monster5("image\\monster\\ms.png");
	static Thread thread5=new Thread(ms5);
	static Boss bs = new Boss("image\\monster\\boss.right.gif");
	static Thread thread6=new Thread(bs);

	public static void main(String args[])
	{
		ps.get(0).start();
		frm.setTitle("Hollow Knight Mini");
		frm.setVisible(true);
		frm.setSize(1280,720);
		frm.setLayout(null);
		

		frm.add(jl.get(1));
		//frm.add(jl.get(3));
		frm.setResizable(false);
		frm.addKeyListener(frm);
		frm.add(skillLabel.get(0));
		frm.add(portalLabel.get(0));
		frm.add(portalLabel.get(1));
		frm.add(portalLabel.get(2));
		frm.add(portalLabel.get(3));
		frm.add(portalLabel.get(4));
		frm.add(jl.get(0));

		timer.start();
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		thread5.start();
		thread6.start();

		frm.addWindowListener(new WindowAdapter(){ 
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        }); 
		

		/*
		jSkill2.setBounds(110,-100,1200,900);
		frm.add(jSkill2);
		 */
		
		//jl.get(1).setBounds(-200,chp,chh,chw);
		//frm.add(jCharacter);
		
		/* 
		jSkill3.setBounds(0,0,1200,1276);
		frm.add(jSkill3);
		*/
		
		//jSkill.setBounds(200,0,972,731);
		//frm.add(jSkill);
		
		/*
		jSkill4.setBounds(50,100,1360,768);
		frm.add(jSkill4);
		 */
		

		//jPortal1.setBounds(1100,480,127,178);
		//frm.add(jPortal1);

		//jPortal2.setBounds(0,480,127,178);
		//frm.add(jPortal2);

		//jPortal1.setVisible(true);
		//jPortal2.setVisible(false);


		
		//background
		//jlabBackGround.setBounds(0,0,1280,720);
		//frm.add(jlabBackGround);

		

		
		//jSkill.setVisible(false);
		
		/*
		jSkill2.setVisible(false);
		jSkill3.setVisible(false);
		jSkill4.setVisible(false);
		*/

		//jlabBackGround.setVisible(false);
		//jl.get(1).setVisible(true);
		//jlabBackGround.setVisible(true);
		
		//close window

	}
	
	public void keyTyped(KeyEvent e) {
		//System.out.println("type");
	}
     
	public void keyPressed(KeyEvent e) {
		if(!atDown&&e.getKeyCode()==39&&!attack&&!AtStirr){
			//System.out.println(Integer.toString(jl.get(1).getX()));
			turnRightOrLeft=true;
			im.set(1, new ImageIcon("image\\character\\chr2.gif"));
			if(jl.get(1).getX()+7<1280 && jl.get(1).getY()==chp){
				jl.get(1).setLocation(jl.get(1).getX()+7,jl.get(1).getY());
			}
			else if(jl.get(1).getY()==218&&jl.get(1).getX()+5<717){
				jl.get(1).setLocation(jl.get(1).getX()+7,jl.get(1).getY());
			}

			if(portalno == 2){
				if(jl.get(1).getX()>700&&jl.get(1).getX()<1000){
					jl.get(2).setVisible(true);
					myScript.setVisible(true);
				}
				else{
					jl.get(2).setVisible(false);
					myScript.setVisible(false);
				}
			}
			else{
				jl.get(2).setVisible(false);
				myScript.setVisible(false);
			}
		}
		else if(!atDown&&e.getKeyCode()==37&&!attack&&!AtStirr){
			//System.out.println(Integer.toString(jl.get(1).getX()));
			turnRightOrLeft=false;
			//character=new ImageIcon("image\\character\\chr.gif");
			im.set(1, new ImageIcon("image\\character\\chr.gif"));
			if(jl.get(1).getX()-7>-170 && jl.get(1).getY()==chp){
				jl.get(1).setLocation(jl.get(1).getX()-7,jl.get(1).getY());
			}
			else if(jl.get(1).getY()==218&&jl.get(1).getX()-5>435){
				jl.get(1).setLocation(jl.get(1).getX()-7,jl.get(1).getY());
			}

			if(portalno == 2){
				if(jl.get(1).getX()>700&&jl.get(1).getX()<1000){
					jl.get(2).setVisible(true);
					myScript.setVisible(true);
				}
				else{
					jl.get(2).setVisible(false);
					myScript.setVisible(false);
				}
			}
			else{
				jl.get(2).setVisible(false);
				myScript.setVisible(false);
			}
		}
		else if(e.getKeyCode()==67&&!attack&&!AtStirr){
			if(!turnRightOrLeft)
				//character=new ImageIcon("image\\character\\cht1.gif");
				im.set(1, new ImageIcon("image\\character\\cht1.gif"));
			else
				//character=new ImageIcon("image\\character\\cht2.gif");
				im.set(1, new ImageIcon("image\\character\\cht2.gif"));
			attack=true;
		}
		else if(e.getKeyCode()==90&&!attack&&!AtStirr){
			skill=true;
			//PlaySkill h=new PlaySkill("music\\skill.wav");
			//h.start();
			PlaySound s = new PlaySound("music\\skill.wav");
			s.start();
			if(!turnRightOrLeft){
				//skillIcon=new ImageIcon("image\\character\\skill.gif");
				skillIcon.set(0, new ImageIcon("image\\character\\skill2.gif"));
				skillLabel.get(0).setLocation(jl.get(1).getX()-500, 0);
				//character=new ImageIcon("image\\character\\cht1.gif");
				//im.set(1, new ImageIcon("image\\character\\cht1.gif"));
			}
			else{
				//character=new ImageIcon("image\\character\\cht2.gif");
				//im.set(1, new ImageIcon("image\\character\\cht2.gif"));
				//skillIcon=new ImageIcon("image\\character\\skill2.gif");
				skillIcon.set(0, new ImageIcon("image\\character\\skill.gif"));
				skillLabel.get(0).setLocation(jl.get(1).getX(), 0);
			}
			//jSkill.setIcon(skillIcon);
			skillLabel.get(0).setIcon(skillIcon.get(0));
			//jSkill.setVisible(true);
			skillLabel.get(0).setVisible(true);
			attack=true;
			skill1=true;
		}
		else if(!atJump&&!atDown&&e.getKeyCode()==86&&!AtStirr){
			PlaySound s = new PlaySound("music\\Jump.wav");
			s.start();
			atJump=true;
		}
		//space
		else if(e.getKeyCode()==32){
			System.out.println("space");
			System.out.println(jl.get(1).getX());
			if(jl.get(1).getX()>-180&&jl.get(1).getX()<-130){
				if(portalno == 2){
					ms1.showMon = true;
					ms2.showMon = true;
					ms3.showMon = true;
					ms4.showMon = true;
					ms5.showMon = true;
					bs.showBoss = false;
					im.set(0, new ImageIcon("image\\backGround\\bg1.jpg"));
					jl.get(0).setIcon(im.get(0));
					jl.get(0).setLocation(0, 0);
					ps.get(0).stop();
					map1=false;
					ps.set(0, new PlaySound("music\\backGround\\01. Enter Hallownest.wav"));
					ps.get(0).start();
					portalno = 1;
					jl.get(1).setLocation(900, 300);
				}
				else if(portalno == 3){
					ms1.showMon = true;
					ms2.showMon = true;
					ms3.showMon = true;
					ms4.showMon = true;
					ms5.showMon = true;
					bs.showBoss = false;
					portalLabel.get(2).setVisible(false);
					im.set(0, new ImageIcon("image\\backGround\\bg2.jpg"));
					jl.get(0).setIcon(im.get(0));
					jl.get(0).setLocation(0, 0);
					map1=false;
					ps.get(0).stop();
					ps.set(0, new PlaySound("music\\backGround\\02. Dirtmouth.wav"));
					ps.get(0).start();
					portalno = 2;
					jl.get(1).setLocation(900, 300);
				}
			}
			else if(jl.get(1).getX()>850&&jl.get(1).getX()<980){
				if(portalno == 1){
					ms1.showMon = true;
					ms2.showMon = true;
					ms3.showMon = true;
					ms4.showMon = true;
					ms5.showMon = true;
					bs.showBoss = false;
					im.set(0, new ImageIcon("image\\backGround\\bg2.jpg"));
					jl.get(0).setIcon(im.get(0));
					jl.get(0).setLocation(0, 0);
					map1=false;
					ps.get(0).stop();
					ps.set(0, new PlaySound("music\\backGround\\02. Dirtmouth.wav"));
					ps.get(0).start();
					portalno = 2;
					jl.get(1).setLocation(-150, 300);
				}

			}
			else if(jl.get(1).getX()>700&&jl.get(1).getX()<1000){
				if(portalno == 2){
					ms1.showMon = false;
					ms2.showMon = false;
					ms3.showMon = false;
					ms4.showMon = false;
					ms5.showMon = false;
					bs.showBoss = false;
					portalLabel.get(2).setVisible(false);
					im.set(0, new ImageIcon("image\\backGround\\bg3.jpg"));
					jl.get(0).setIcon(im.get(0));
					jl.get(0).setLocation(0, 0);
					map1=false;
					ps.get(0).stop();
					ps.set(0, new PlaySound("music\\backGround\\03. Crossroads.wav"));
					ps.get(0).start();
					portalno = 3;
					jl.get(1).setLocation(-150, 300);
					jl.get(2).setVisible(false);
				}
			}
			else if(jl.get(1).getX()>300&&jl.get(1).getX()<600){
				if(portalno == 3){
					ms1.showMon = false;
					ms2.showMon = false;
					ms3.showMon = false;
					ms4.showMon = false;
					ms5.showMon = false;
					bs.showBoss = true;
					System.out.println("Boss section");
					im.set(0, new ImageIcon("image\\backGround\\bg4.jpg"));
					jl.get(0).setIcon(im.get(0));
					jl.get(0).setLocation(0, 0);
					map1=false;
					ps.get(0).stop();
					ps.set(0, new PlaySound("music\\backGround\\22. Dream Battle.wav"));
					ps.get(0).start();
					portalno = 4;
					jl.get(1).setLocation(50, 300);
				}
			}
			else if(jl.get(1).getX()>0&&jl.get(1).getX()<150){
				if(portalno == 4){
					ms1.showMon = false;
					ms2.showMon = false;
					ms3.showMon = false;
					ms4.showMon = false;
					ms5.showMon = false;
					bs.showBoss = false;
					portalLabel.get(2).setVisible(false);
					im.set(0, new ImageIcon("image\\backGround\\bg3.jpg"));
					jl.get(0).setIcon(im.get(0));
					jl.get(0).setLocation(0, 0);
					map1=false;
					ps.get(0).stop();
					ps.set(0, new PlaySound("music\\backGround\\03. Crossroads.wav"));
					ps.get(0).start();
					portalno = 3;
					jl.get(1).setLocation(500, 300);
				}
			}
		}
		//x
		/* 
		else if(!skill2&&e.getKeyCode()==88){
			skill=true;
			jSkill2.setVisible(true);
			skill2=true;
			hit=new PlaySkill("music\\skill2\\use1.wav");
			hit.start();
			attack=true;
			if(!turnRightOrLeft){
				character=new ImageIcon("image\\character\\cht1.gif");
			}
			else{
				character=new ImageIcon("image\\character\\cht2.gif");
			}
		}
		//a
		else if(!skill3&&e.getKeyCode()==65){
			jSkill3.setIcon(skill3Icon);
			jSkill3.setVisible(true);
			jSkill3.setLocation(jl.get(1).getX()-500,jl.get(1).getY()-800);
			hit=new PlaySkill("music\\skill3\\400011057.Use.wav");
			hit.start();
			skill3=true;
			if(!turnRightOrLeft){
				character=new ImageIcon("image\\character\\cht1.gif");
			}
			else{
				character=new ImageIcon("image\\character\\cht2.gif");
			}
		}
		else if(!skill4 && e.getKeyCode()==83){
			jSkill4.setVisible(true);
			skill4=true;
			if(!turnRightOrLeft){
				character=new ImageIcon("image\\character\\cht1.gif");
			}
			else{
				character=new ImageIcon("image\\character\\cht2.gif");
			}
		}
		*/
		jl.get(1).setIcon(im.get(1));
	}
     
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==67){	
			attack=false;
		}
		if(!attack&&!skill/*&&!skill4*/){
			if(turnRightOrLeft) { 
				im.set(1, new ImageIcon("image\\character\\chst2.gif"));
			}
			else {
				im.set(1, new ImageIcon("image\\character\\chst1.gif"));
			}
			jl.get(1).setIcon(im.get(1));
		}
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		//jump fall
		if(atJump){
			if(count<50){
				if(!turnRightOrLeft) // jump
					im.set(1, new ImageIcon("image\\character\\chj.gif"));
				else
					im.set(1, new ImageIcon("image\\character\\chj2.gif"));
				jl.get(1).setIcon(im.get(1));
				count++;
			}
			else{
				atJump=false;
				atDown=true;
				count=0;
			}
		}
		if(atDown){
			if(count<50){
				count++;
			}
			else{
				atDown=false;
				count=0;
				if(!turnRightOrLeft) // stand
					im.set(1, new ImageIcon("image\\character\\chst1.gif"));
				else
					im.set(1, new ImageIcon("image\\character\\chst2.gif"));
				jl.get(1).setIcon(im.get(1));
			}
		}
		//portal
		if(true){
			if(count2==20){
				if(portalno == 1){
					portalLabel.get(0).setVisible(true);
					portalLabel.get(1).setVisible(false);
					portalLabel.get(2).setVisible(false);
					portalLabel.get(3).setVisible(false);
					portalLabel.get(4).setVisible(false);
					portalIcon.set(0, new ImageIcon("image\\portal\\portal.game.pv.4."+portalTo+".png"));
					portalLabel.get(0).setIcon(portalIcon.get(0));
				}
				else if(portalno == 2){
					portalLabel.get(2).setLocation(900, 300);
					portalLabel.get(1).setVisible(true);
					portalLabel.get(0).setVisible(false);
					portalLabel.get(2).setVisible(true);
					portalLabel.get(3).setVisible(false);
					portalLabel.get(4).setVisible(false);
					portalIcon.set(1, new ImageIcon("image\\portal\\portal.game.pv.4."+portalTo+".png"));
					portalIcon.set(2, new ImageIcon("image\\character\\npc.png"));
					portalLabel.get(1).setIcon(portalIcon.get(1));
				}	
				else if(portalno == 3){
					portalLabel.get(2).setLocation(500, 300);
					portalLabel.get(1).setVisible(false);
					portalLabel.get(0).setVisible(false);
					portalLabel.get(2).setVisible(true);
					portalLabel.get(3).setVisible(true);
					portalLabel.get(4).setVisible(false);
					portalIcon.set(3, new ImageIcon("image\\portal\\portal.game.pv.4."+portalTo+".png"));
					portalIcon.set(2, new ImageIcon("image\\character\\npc.png"));
					portalLabel.get(3).setIcon(portalIcon.get(3));
				}
				else if(portalno == 4){
					portalLabel.get(1).setVisible(false);
					portalLabel.get(0).setVisible(false);
					portalLabel.get(2).setVisible(false);
					portalLabel.get(3).setVisible(false);
					portalLabel.get(4).setVisible(true);
					portalIcon.set(4, new ImageIcon("image\\portal\\portal.game.pv.4."+portalTo+".png"));
					portalLabel.get(4).setIcon(portalIcon.get(4));
				}
				portalTo++;
				if(portalTo>13)
					portalTo=0;
			}
			count2++;
			if(count2>20)
				count2=0;
		}
		
		if(skill1){
			count1++;
			if(count1>280){
				skill1=false;
				attack=false;
				skill=false;
				skillLabel.get(0).setVisible(false);
				if(!turnRightOrLeft)
					im.set(1, new ImageIcon("image\\character\\chst1.gif"));
				else
					im.set(1, new ImageIcon("image\\character\\chst2.gif"));
				jl.get(1).setIcon(im.get(1));
				count1=0;
			}
		}
		
		/* 
		if(skill2){
			count3++;
			if(count3>1650){
				skill2=false;
				count3=0;
				jSkill2.setVisible(false);
				skill=false;
				attack=false;
				
				if(!turnRightOrLeft)
					character=new ImageIcon("image\\character\\chst1.gif");
				else
					character=new ImageIcon("image\\character\\chst2.gif");
				jl.get(1).setIcon(character);
			}
		}

		if(skill3&&!skill3Exist&&!deathSkill3){
			count4++;
			if(count4>500){
				skill=true;
				skill3Exist=true;
				hit=new PlaySkill("music\\skill3\\400011057.Summoned.wav");
				hit.start();
				count4=0;
				if(!turnRightOrLeft)
					character=new ImageIcon("image\\character\\chst1.gif");
				else
					character=new ImageIcon("image\\character\\chst2.gif");
				jl.get(1).setIcon(character);
				skill3Icon=new ImageIcon("image\\character\\5XEfr6X.gif");
				jSkill3.setIcon(skill3Icon);
			}
		}
		if(skill3&&skill3Exist&&!deathSkill3){
			if(count4%350==0){
				hit.stop();
				hit=new PlaySkill("music\\skill3\\400011057.Attack1.wav");
				hit.start();
			}
			count4++;
			if(count4>2000){
				deathSkill3=true;
				hit=new PlaySkill("music\\skill3\\400011057.Die.wav");
				hit.start();
				count4=0;
				skill3Icon=new ImageIcon("image\\character\\8iiVRT3.gif");
				jSkill3.setIcon(skill3Icon);
			}
		}
		if(skill3&&skill3Exist&&deathSkill3){
			count4++;
			if(count4>500){
				deathSkill3=false;
				skill3Exist=false;
				skill3=false;
				skill=false;
				count4=0;
				skill3Icon=new ImageIcon("image\\character\\8fXp4Jm.gif");
				jSkill3.setIcon(skill3Icon);
				jSkill3.setVisible(false);
			}
		}
		if(skill4){
			if(count5==450){
				skill=true;
				attack=false;
			}
			if(count5==200){
				hit=new PlaySkill("music\\skill4\\Use.wav");
				hit.start();
			}
			count5++;
			if(count5>900){
				skill4=false;
				count5=0;
				jSkill4.setVisible(false);
				skill=false;
				attack=false;
				
				if(!turnRightOrLeft)
					character=new ImageIcon("image\\character\\chst1.gif");
				else
					character=new ImageIcon("image\\character\\chst2.gif");
				jl.get(1).setIcon(character);
			}
		}
		*/
	}	
}