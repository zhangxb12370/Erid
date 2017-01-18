package com.parsec.eridanus.controller.index;

import com.parsec.tool.FileUpLoadUtil;
import com.parsec.tool.ReturnJson;
import com.parsec.universal.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;


/**
 * 文件上传
 * @author 叶波
 * 2014-11-24
 */
@RestController
public class FileUploadController {

	Logger logger = LoggerFactory.getLogger(FileUploadController.class);
	/**
	 * 
	 * 上传文件.
	 * @param
	 * @return json对象
	 *
	 */
	@RequestMapping(value="admin/fileUpLoad",method = RequestMethod.POST, produces = {Constants.JSON_ENCODING})
	public ReturnJson upload(@RequestParam("file") MultipartFile file,HttpSession session) throws IOException, ServletException {
		ReturnJson ret = new ReturnJson();
		        try{
					String containerPath=session.getServletContext().getRealPath("");
					String originalFilename=file.getOriginalFilename();
					String offpix=originalFilename.substring(originalFilename.indexOf(".")+1);//获取后缀名
					String fileName= FileUpLoadUtil.fileName(); //获取文件名
					String imageUrl=FileUpLoadUtil.url(offpix,fileName); //获取访问相对地址
					String filePath=FileUpLoadUtil.filePath(containerPath,offpix,fileName);  //获取文件的绝对地址
					ret.setResult(imageUrl);
					File file_db=new File(filePath);
					file_db.createNewFile();
					file.transferTo(file_db);
					return ret.setSuccess("上传成功");
		        }catch(Exception e){
		            e.printStackTrace();
		            return ret.setFail("服务器异常");
		        }
	}

}
