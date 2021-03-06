
import java.io.File;  
import java.util.ArrayList;  
import java.util.List;  
  

import java.util.UUID;

import mapper.JobMapper;

import org.mybatis.generator.api.MyBatisGenerator;  
import org.mybatis.generator.config.Configuration;  
import org.mybatis.generator.config.xml.ConfigurationParser;  
import org.mybatis.generator.internal.DefaultShellCallback;  
import org.springframework.beans.factory.annotation.Autowired;

import pojo.Job;
  
  
public class GeneratorSqlmap {  
  
	@Autowired
    private JobMapper jobMapper;
    public static void generator() throws Exception{  
  
  
        List<String> warnings = new ArrayList<String>();  
        boolean overwrite = true;  
        //加载配置文件  
        File configFile = new File("src/main/resources/generatorConfig.xml");   
        ConfigurationParser cp = new ConfigurationParser(warnings);  
        Configuration config = cp.parseConfiguration(configFile);  
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);  
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,  
                callback, warnings);  
        myBatisGenerator.generate(null);  
  
  
    }   
    
    public static void main(String[] args) throws Exception {
    	  try {  
              generator();  
              System.out.println("成功");
          } catch (Exception e) {  
              e.printStackTrace();  
              System.out.println("失败");
          }  
           System.out.println("------------"); 
    	
    }  
}  