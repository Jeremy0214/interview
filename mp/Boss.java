package src;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.html.HTMLDocument.HTMLReader.HiddenAction;

import java.util.Random;
import java.lang.*;
public class Boss extends GameManager implements Runnable{
	ImageIcon boss1;
	JLabel bossIcon;
	ImageIcon damageIcon=new ImageIcon("image\\boss.left.gif");
	JLabel ms_damege;
	
	PlaySound msh = new PlaySound("music\\monsterHit.wav");
	
	int RightOrLeft; // 1: right, 0: left
	int howMuchTime;
	int bosshealth=100;
	public Boss(String ptr){
		boss1=new ImageIcon(ptr);
		bossIcon=new JLabel(boss1);
		int whereBorn=(int)(Math.random()*bposx);
		bossIcon.setBounds(whereBorn,bposy,bwidth,bheight);
		frm.add(bossIcon);
		ms_damege = new JLabel(damageIcon);
		ms_damege.setBounds(0,0,292,254);
		frm.add(ms_damege);
		ms_damege.setVisible(false);
	}
	
	boolean lastAttackEnd=true;
	boolean decreas=false;


	public void monsterMove(){
		for(int i=0,count=0;i<howMuchTime;i++){
			if(skill) {break;}
			if(attack){ break; }
			if(RightOrLeft == 1 &&bossIcon.getX()+bmovedist<1340){
				boss1=new ImageIcon("image\\monster\\boss.right.gif");
				bossIcon.setIcon(boss1);
				bossIcon.setLocation(bossIcon.getX()+bmovedist,bposy);
			}
			else if(RightOrLeft == 0 &&bossIcon.getX()-bmovedist>0){
				boss1=new ImageIcon("image\\monster\\boss.left.gif");
				bossIcon.setIcon(boss1);
				bossIcon.setLocation(bossIcon.getX()-bmovedist,bposy);
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
			System.out.println("Boss1 " + bossIcon.getX() + " " + bossIcon.getY());
		}
	}

	public void monsterHit(){
		if((attack&&lastAttackEnd) || skill){
			if(skill) { bosshealth -= 5;}
			if((attack&&lastAttackEnd) || skill){
				if(skill) {bosshealth -= 5;}
				if( !turnRightOrLeft) // Left
				{
					if(skill){
						if( (bossIcon.getX() <= jl.get(1).getX() && bossIcon.getX() >= jl.get(1).getX()-skilldist)){
							System.out.println("left bosshealth " + bosshealth);
							System.out.println("left attack: " + jl.get(1).getX() + " " + Integer.toString(jl.get(1).getX()-skilldist));
							System.out.println("left monster X: " + bossIcon.getX());
							monsterDamage();
							lastAttackEnd=false;
							monsterDieAndReborn();
							bosshealth--;
							lastAttackEnd=true;
						}
					}
					else if( (bossIcon.getX() <= jl.get(1).getX() && bossIcon.getX() >= jl.get(1).getX()-hitdist)){
						System.out.println("left bosshealth " + bosshealth);
						System.out.println("left attack: " + jl.get(1).getX() + " " + Integer.toString(jl.get(1).getX()-hitdist));
						System.out.println("left monster X: " + bossIcon.getX());
						monsterDamage();
						lastAttackEnd=false;
						monsterDieAndReborn();
						bosshealth--;
						lastAttackEnd=true;
					}
				}
				else if(turnRightOrLeft) // right
				{
					if(skill){
						if( (bossIcon.getX() >= jl.get(1).getX() && bossIcon.getX() <= jl.get(1).getX()+skilldist)){
							System.out.println("right bosshealth " + bosshealth);
							System.out.println("right attack: " + jl.get(1).getX() + " " + Integer.toString(jl.get(1).getX()+skilldist));
							System.out.println("right monster X: " + bossIcon.getX());
							monsterDamage();
							lastAttackEnd=false;
							monsterDieAndReborn();
							bosshealth--;
							lastAttackEnd=true;
						}
					}
					else if( (bossIcon.getX() >= jl.get(1).getX() && bossIcon.getX() <= jl.get(1).getX()+hitdist)){
						System.out.println("right bosshealth " + bosshealth);
						System.out.println("right attack: " + jl.get(1).getX() + " " + Integer.toString(jl.get(1).getX()+hitdist));
						System.out.println("right monster X: " + bossIcon.getX());
						monsterDamage();
						lastAttackEnd=false;
						monsterDieAndReborn();
						bosshealth--;
						lastAttackEnd=true;
					}
				}
			}
			else{
				if(RightOrLeft == 1)
				{
					boss1=new ImageIcon("image\\monster\\boss.right.gif");
					bossIcon.setIcon(boss1);
				}
				else{
					boss1=new ImageIcon("image\\monster\\boss.left.gif");
					bossIcon.setIcon(boss1);
				}
			}
		}
	}

	public void monsterDamage(){
		if(attack && !skill){
			System.out.println("Normal attack");
			damageIcon=new ImageIcon("image\\damage.gif");
			ms_damege.setIcon(damageIcon);
			ms_damege.setLocation(bossIcon.getX()-130,bposy-230);
			ms_damege.setVisible(true);
		}
		if(skill){
			System.out.println("Skill attack");
			damageIcon=new ImageIcon("image\\critizen.gif");
			ms_damege.setIcon(damageIcon);
			ms_damege.setLocation(bossIcon.getX()-130,bposy-250);
			ms_damege.setVisible(true);
		}
	}

	public void monsterDieAndReborn(){
		if(bosshealth < 1){ //death 		
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
			if(RightOrLeft == 1) { boss1=new ImageIcon("image\\monster\\mdie2.gif"); }
			else{ boss1=new ImageIcon("image\\monster\\mdie1.gif"); }
			bossIcon.setIcon(boss1);
			try { 
				Thread.sleep(80); 
			} 
			catch(InterruptedException e){}
			try { 
				Thread.sleep(450); 
			} 
			catch(InterruptedException e){}
			//boss1_damege.setVisible(false);
			ms_damege.setVisible(false);
			bossIcon.setVisible(false);
			try {
				Thread.sleep(2550);
			} 
			catch(InterruptedException e){}
			int whereBorn=(int)(Math.random()*bposx);
			bossIcon.setLocation(whereBorn,bposy);
			if(RightOrLeft == 1) {boss1=new ImageIcon("image\\monster\\boss.right.gif");}
			else{ boss1=new ImageIcon("image\\monster\\boss.left.gif"); }
			bossIcon.setIcon(boss1);
			bosshealth=100;
			bossIcon.setVisible(true);
		}
		else{ //KB
			if(RightOrLeft == 1) {boss1=new ImageIcon("image\\monster\\boss.right.gif");}
			else{ boss1=new ImageIcon("image\\monster\\boss.left.gif"); }
			bossIcon.setIcon(boss1);
			//apw33=new PlayHit("music\\monsterHit.wav");
			//apw33.start();
			msh = new PlaySound("music\\monsterHit.wav");
			msh.start();
			for(int i=0;i<20;i++){
				if(RightOrLeft == 0){
					if(bossIcon.getX()-damagedist > 0) {bossIcon.setLocation(bossIcon.getX()-damagedist,bossIcon.getY());}
				else{
					if(bossIcon.getX()+damagedist < 1280 ) {bossIcon.setLocation(bossIcon.getX()+damagedist,bossIcon.getY());}
				}
				if(i==11)
					//boss1_damege.setVisible(false);
					ms_damege.setVisible(false);
				try { 
					Thread.sleep(100);
				}
				catch(InterruptedException e){}
			}
			if(RightOrLeft == 1) {boss1=new ImageIcon("image\\monster\\boss.right.gif");}
			else{ boss1=new ImageIcon("image\\monster\\boss.left.gif"); }
			bossIcon.setIcon(boss1);
		}
		showBoss = false;
	}

	}



	public void run(){
		while(true){
			if(showBoss){
				bossIcon.setVisible(true);
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
				System.out.println(showBoss);
				bossIcon.setVisible(false);
			}
		}
	}
}