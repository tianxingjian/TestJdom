package nc.ui.pub.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.vo.pub.BusinessException;

public class TxtFileReader {

	/** �������� */
	private FileInputStream fileIn = null;
	
	private String rBreak = "\\|";//Ĭ�ϵĲ��зָ��
	private String cBreak = ":";//Ĭ�ϵĲ��зָ��
	private int keyIndex = 0; //keyֵ������
	
	private String m_sFilePath;

	public void setrBreak(String rBreak) {
		this.rBreak = rBreak;
	}

	public void setcBreak(String cBreak) {
		this.cBreak = cBreak;
	}

	public void setKeyIndex(int keyIndex) {
		this.keyIndex = keyIndex;
	}

	public void closeFile() throws BusinessException{
		try {
			this.fileIn.close();
		} catch (IOException e) {
			throw new BusinessException("�ļ��ر�ʧ�ܣ�");
		}finally{
			this.fileIn =null;
		}
	}

	public void openFile(String sPath) throws Exception {
		m_sFilePath = sPath;
		File file = new File(m_sFilePath);
		if (!file.exists()) {
			throw new Exception("Ŀ���ļ�����ʧ�ܣ�" + m_sFilePath);
		}
		
		try {
			fileIn = new FileInputStream(m_sFilePath);
		} catch (Exception ne) {
			fileIn = null;
			throw new Exception("�ļ���ʧ�ܣ�");
		}
	}
	
	public Map<String, String[][]> readFile() throws BusinessException, UnsupportedEncodingException{
		
		if(fileIn == null){
			throw new BusinessException("�ļ���ʧ�ܣ�");
		}
		Map<String, String[][]> record = new HashMap<String, String[][]>();
		InputStreamReader reader = null;
		BufferedReader bufferReader = null;
		
		reader = new InputStreamReader(fileIn, "UTF-8");
		bufferReader = new BufferedReader(reader);
		String line;
		String[][] array = null;
		int count = 0;
		ImpDataVO dataVO = null;
		try {
			while ((line=bufferReader.readLine())!=null){
				dataVO = processLine(line);
				record.put(dataVO.getKey(), dataVO.getArray());
				count++;
			}
		} catch (IOException e) {
			throw new BusinessException("��[" + count + "]���ļ���ȡʧ�ܣ�");
		}finally{
			try {
				bufferReader.close();
			} catch (IOException e) {
				throw new BusinessException("�ļ��ر�ʧ�ܣ�");
			}finally{
				bufferReader = null;
			}
			try {
				reader.close();
			} catch (IOException e) {
				throw new BusinessException("�ļ��ر�ʧ�ܣ�");
			}finally{
				reader = null;
			}
			closeFile();
		}
		return record;
	}
	
	private ImpDataVO processLine(String lineStr){
		String keyStr = null;
		String[]tables = lineStr.split(rBreak);
		List<String[]> list = new ArrayList<String[]>();
		for(int i = 0; i < tables.length; i++){
			if(i == keyIndex){
				continue;
			}
			String[]rows = tables[i].split(cBreak);
			list.add(rows);
		}
		keyStr = tables[keyIndex];
		
		return new ImpDataVO(keyStr, list.toArray(new String[0][0]));
	}
	
	
}
