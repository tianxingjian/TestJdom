package cn.com;

import java.security.KeyStore.Entry;
import java.util.Iterator;
import java.util.Map;

import nc.ui.pub.file.TxtFileReader;

import org.junit.Test;

public class TxtFileReaderTest {

	@Test
	public void test() throws Exception {
		String path = "test/数据导入格式.txt";
		
		TxtFileReader reader = new TxtFileReader();
		reader.openFile(path);
		Map<String, String[][]> map = reader.readFile();
		Iterator<Map.Entry<String, String[][]>> entrys = map.entrySet().iterator();
		while(entrys.hasNext()){
			Map.Entry<String, String[][]> entry = entrys.next();
			System.out.println(entry.getKey() + "  - >  " + entry.getValue());
			String [][]tmp = entry.getValue();
			for(int i = 0; i < tmp.length; i ++){
				for(int j = 0; j < tmp[i].length; j++){
					System.out.print(tmp[i][j] + "\t");
				}
			}
		}
	}

}
