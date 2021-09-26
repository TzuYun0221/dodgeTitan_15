package util;

import java.util.List;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

//�D���}�ʪ��t��
public class Animation {
	private List<BufferedImage> frames; 
	private int frameIndex = 0;
	private int deltaTime;	//�e��frame�M�o��frame���t�O
	private long previousTime;
	
	public Animation(int deltaTime) {
		this.deltaTime = deltaTime;
		frames = new ArrayList<BufferedImage>(); //�Ϥ����o��list
	}
	
	public void update() {
		//����˸m�W���@��
		if(System.currentTimeMillis() - previousTime > deltaTime) {
			frameIndex ++; 	//�ثe�ɶ���W�Ӯɶ��j��ɶ��t�A�i�J�U�@��frame
			
			if (frameIndex >= frames.size()) {	// 2�i�Ͻ��y���	
				frameIndex = 0;	
			}
			previousTime = System.currentTimeMillis();	
		} 
	}
	
	public void addFrame (BufferedImage frame) {
		frames.add(frame); //�[�Wframe
	}
	
	//��X�b����frame�W
	public BufferedImage getFrame() {
		if(frames.size() > 0) { 	
			return frames.get(frameIndex);	
		}
		return null;
	}
	
}
