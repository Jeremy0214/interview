package src;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.html.HTMLDocument.HTMLReader.HiddenAction;

import java.util.Random;
import java.lang.*;
public class monster3 extends GameManager implements Runnable{
	static ImageIcon monster3;
	static JLabel monsterIcon;
	static ImageIcon damageIcon=new ImageIcon("image\\ms.left.gif");
	static JLabel ms_damege;
	static boolean showMon = true;
	PlaySound msh = new PlaySound("music\\monsterHit.wav");

	static{
		ms_damege = new JLabel(damageIcon);
		ms_damege.setBounds(0,0,292,254);
		frm.add(ms_damege);
		ms_damege.setVisible(false);
	}

	
	int RightOrLeft; // 1: right, 0: left
	int howMuchTime;
	static int health=2;
	public monster3(String ptr){
		monster3=new ImageIcon(ptr);
		monsterIcon=new JLabel(monster3);
		int whereBorn=(int)(Math.random()*mposx);
		monsterIcon.setBounds(whereBorn,mposy,mheight,mwidth);
		frm.add(monsterIcon);
	}
	
	boolean lastAttackEnd=true;
	boolean decreas=false;


	public void monsterMove(){
		for(int i=0,count=0;i<howMuchTime;i++){
			if(skill) {break;}
			if(attack){ break; }
			if(RightOrLeft == 1 &&monsterIcon.getX()+movedist<1340){
				monster3=new ImageIcon("image\\monster\\ms.right.png");
				monsterIcon.setIcon(monster3);
				monsterIcon.setLocation(monsterIcon.getX()+movedist,monsterIcon.getY());
			}
			else if(RightOrLeft == 0 &&monsterIcon.getX()-movedist>0){
				monster3=new ImageIcon("image\\monster\\ms.left.png");
				monsterIcon.setIcon(monster3);
				monsterIcon.setLocation(monsterIcon.getX()-movedist,monsterIcon.getY());
			}
			try {
				Thread.sleep(100);
			} catch(InterruptedException e){
			}
			if(!decreas){
				count++;
				if(count>=4)
					decreas=true;
			}
			else{
				count--;
				if(count<=0)
					decreas=false;
			}
			//System.out.println("monster3 " + monsterIcon.getX() + " " + monsterIcon.getY());
		}
	}

	public void monsterHit(){
		if((attack&&lastAttackEnd) || skill){
			if(skill) {health=1;}
			if((attack&&lastAttackEnd) || skill){
				if(skill) {health=1;}
				if( !turnRightOrLeft) // Left
				{
					if(skill){
						if( (monsterIcon.getX() <= jl.get(1).getX() && monsterIcon.getX() >= jl.get(1).getX()-skilldist)){
							System.out.println("left health " + health);
							System.out.println("left attack: " + jl.get(1).getX() + " " + Integer.toString(jl.get(1).getX()-skilldist));
							System.out.println("left monster X: " + monsterIcon.getX());
							monsterDamage();
							lastAttackEnd=false;
							monsterDieAndReborn();
							health--;
							lastAttackEnd=true;
						}
					}
					else if( (monsterIcon.getX() <= jl.get(1).getX() && monsterIcon.getX() >= jl.get(1).getX()-hitdist)){
						System.out.println("left health " + health);
						System.out.println("left attack: " + jl.get(1).getX() + " " + Integer.toString(jl.get(1).getX()-hitdist));
						System.out.println("left monster X: " + monsterIcon.getX());
						monsterDamage();
						lastAttackEnd=false;
						monsterDieAndReborn();
						health--;
						lastAttackEnd=true;
					}
				}
				else if(turnRightOrLeft) // right
				{
					if(skill){
						if( (monsterIcon.getX() >= jl.get(1).getX() && monsterIcon.getX() <= jl.get(1).getX()+skilldist)){
							System.out.println("right health " + health);
							System.out.println("right attack: " + jl.get(1).getX() + " " + Integer.toString(jl.get(1).getX()+skilldist));
							System.out.println("right monster X: " + monsterIcon.getX());
							monsterDamage();
							lastAttackEnd=false;
							monsterDieAndReborn();
							health--;
							lastAttackEnd=true;
						}
					}
					else if( (monsterIcon.getX() >= jl.get(1).getX() && monsterIcon.getX() <= jl.get(1).getX()+hitdist)){
						System.out.println("right health " + health);
						System.out.println("right attack: " + jl.get(1).getX() + " " + Integer.toString(jl.get(1).getX()+hitdist));
						System.out.println("right monster X: " + monsterIcon.getX());
						monsterDamage();
						lastAttackEnd=false;
						monsterDieAndReborn();
						health--;
						lastAttackEnd=true;
					}
				}
			}
			else{
				if(RightOrLeft == 1)
				{
					monster3=new ImageIcon("image\\monster\\ms.right.png");
					monsterIcon.setIcon(monster3);
				}
				else{
					monster3=new ImageIcon("image\\monster\\ms.left.png");
					monsterIcon.setIcon(monster3);
				}
			}
		}
	}

	public void monsterDamage(){
		if(attack&&!skill){
			System.out.println("Normal attack");
			damageIcon=new ImageIcon("image\\damage.gif");
			ms_damege.setIcon(damageIcon);
			ms_damege.setLocation(monsterIcon.getX()-130,monsterIcon.getY()-230);
			ms_damege.setVisible(true);
		}
		if(skill){
			System.out.println("Skill attack");
			damageIcon=new ImageIcon("image\\critizen.gif");
			ms_damege.setIcon(damageIcon);
			ms_damege.setLocation(monsterIcon.getX()-130,monsterIcon.getY()-250);
			ms_damege.setVisible(true);
		}
	}

	public void monsterDieAndReborn(){
		if(health==1){ //death 		
			if(skill1){
				//apw33.stop();
				//apw33=new PlayHit("music\\skillHit.wav");
				//apw33.start();
				msh.stop();
				msh = new PlaySound("music\\skillHit.wav");
				msh.start();
			}
			/* 
			if(skill2){
				apw33.stop();
				apw33=new PlayHit("music\\skill2\\hit1.wav");
				apw33.start();
			}
			if(skill3){
				apw33.stop();
				apw33=new PlayHit("music\\skill3\\400011057.Hit.wav");
				apw33.start();
			}
			if(skill4){
				apw33.stop();
				apw33=new PlayHit("music\\skill4\\Hit.wav");
				apw33.start();
			}
			*/
			//apw33=new PlayHit("music\\monsterDied.wav");
			//apw33.start();
			msh = new PlaySound("music\\monsterDied.wav");
			msh.start();
			if(RightOrLeft == 1) { monster3=new ImageIcon("image\\monster\\mdie2.gif"); }
			else{ monster3=new ImageIcon("image\\monster\\mdie1.gif"); }
			monsterIcon.setIcon(monster3);
			try { 
				Thread.sleep(80); 
			} 
			catch(InterruptedException e){}
			try { 
				Thread.sleep(450); 
			} 
			catch(InterruptedException e){}
			//monster3_damege.setVisible(false);
			ms_damege.setVisible(false);
			monsterIcon.setVisible(false);
			try {
				Thread.sleep(2550);
			} 
			catch(InterruptedException e){}
			int whereBorn=(int)(Math.random()*mposx);
			monsterIcon.setLocation(whereBorn,mposy);
			if(RightOrLeft == 1) {monster3=new ImageIcon("image\\monster\\ms.right.png");}
			else{ monster3=new ImageIcon("image\\monster\\ms.left.png"); }
			monsterIcon.setIcon(monster3);
			health=3;
			monsterIcon.setVisible(true);
		}
		else{ //KB
			if(RightOrLeft == 1) {monster3=new ImageIcon("image\\monster\\ms.right.png");}
			else{ monster3=new ImageIcon("image\\monster\\ms.left.png"); }
			monsterIcon.setIcon(monster3);
			//apw33=new PlayHit("music\\monsterHit.wav");
			//apw33.start();
			msh = new PlaySound("music\\monsterHit.wav");
			msh.start();
			for(int i=0;i<20;i++){
				if(RightOrLeft == 0){
					if(monsterIcon.getX()-damagedist > 0) {monsterIcon.setLocation(monsterIcon.getX()-damagedist,monsterIcon.getY());}
				else{
					if(monsterIcon.getX()+damagedist < 1280 ) {monsterIcon.setLocation(monsterIcon.getX()+damagedist,monsterIcon.getY());}
				}
				if(i==11)
					//monster3_damege.setVisible(false);
					ms_damege.setVisible(false);
				try { 
					Thread.sleep(100);
				}
				catch(InterruptedException e){}
			}
			if(RightOrLeft == 1) {monster3=new ImageIcon("image\\monster\\ms.right.png");}
			else{ monster3=new ImageIcon("image\\monster\\ms.left.png"); }
			monsterIcon.setIcon(monster3);
		}
	}

	}



	public void run(){
		while(true){

			if(showMon){
				monsterIcon.setVisible(true);
				RightOrLeft=(int)(Math.random()*2);
				howMuchTime=(int)(Math.random()*60);
				decreas=false;
				monsterMove();

				monsterHit();

				int sleepTime=(int)(Math.random()*4);
				for(int i=0;i<sleepTime;i++){
					try {
						if(attack||skill)
							break;
						Thread.sleep(1000);
					} catch(InterruptedException e){
					}
				}
			}
			else{
				monsterIcon.setVisible(false);
			}
		}
	}
}