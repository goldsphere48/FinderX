package com.Nezar.FinderX;

import java.io.IOException;
import java.io.StringWriter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlWriter;

public class GameStats {
	
	int currentNumberInArcade;
	int numOfArcadeModes;
	int currentMode;
	int currentPointsInFirstArcadeMode;
	int tmpScore;
	
	int bestNumbers;
	int bestFirstModePoints;
	int bestSecondModePoints;
	int bestThirdModePoints;
	
	FileHandle handle;
	
	boolean[] levelComplete;
	
	public GameStats(){
		tmpScore = 0;
		bestNumbers = 0;
		currentPointsInFirstArcadeMode = -1;
		bestFirstModePoints = -1;
		bestSecondModePoints= 0;
		bestThirdModePoints = 0;
		bestFirstModePoints = -1;
		numOfArcadeModes = 3;
	}
	
	public void save(){
			if (bestFirstModePoints < 0)
				bestFirstModePoints = currentPointsInFirstArcadeMode;
			else
				if(currentPointsInFirstArcadeMode < bestFirstModePoints)
					bestFirstModePoints = currentPointsInFirstArcadeMode;
		
		StringWriter writer = new StringWriter();
		XmlWriter xml = new XmlWriter(writer);
		try {
			xml.element("script").element("data").attribute("Numbers", bestNumbers)
				.element("Arcade").attribute("bestFirstModePoints", bestFirstModePoints).attribute("bestSecondModePoints", bestSecondModePoints).attribute("bestThirdModePoints", bestThirdModePoints)
				.pop()
			.pop()
			.pop();
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try{
			FileHandle handle = Gdx.files.local("data/user.xml");
			handle.writeString(writer.toString(), false);
		} catch(Exception e){System.out.println("error in the end");}
	}
	
	public void initSaveSystem(){
		handle = Gdx.files.local("data/user.xml");
		if(handle.file().exists()){
			try{
				XmlReader reader = new XmlReader();
				String inputXmlString = new String();
				inputXmlString = handle.readString();
				XmlReader.Element root = reader.parse(inputXmlString);
				bestNumbers = Integer.parseInt(root.getChildByName("data").getAttribute("Numbers"));
				bestFirstModePoints = Integer.parseInt(root.getChildByName("data").getChildByName("Arcade").getAttribute("bestFirstModePoints"));
				bestSecondModePoints = Integer.parseInt(root.getChildByName("data").getChildByName("Arcade").getAttribute("bestSecondModePoints"));
				bestThirdModePoints = Integer.parseInt(root.getChildByName("data").getChildByName("Arcade").getAttribute("bestThirdModePoints"));
				
			} catch(Throwable t){

			}
		}else{
			try {
				handle.file().mkdir();
				handle.file().createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
