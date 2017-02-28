package com.yicunyipin.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class ImageUploadAction extends ActionSupport {
	private File upload;
    private String uploadContentType;  
    private String uploadFileName;
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public String getUploadContentType() {
		return uploadContentType;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}  
	
	public String execute() throws Exception
	{//System.out.println("upload");
		
		HttpServletResponse response = ServletActionContext.getResponse();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
        response.setCharacterEncoding("GBK");  
        PrintWriter out = response.getWriter();  
        
  
        // CKEditor提交的很重要的一个参数  
        String callback = ServletActionContext.getRequest().getParameter("CKEditorFuncNum");   
        //System.out.println("upload1");
       // System.out.println("uploadContentType:"+uploadContentType);
        String expandedName = "";  //文件扩展名  
        if (uploadContentType.equals("image/pjpeg") || uploadContentType.equals("image/jpeg")) {  
            //IE6上传jpg图片的headimageContentType是image/pjpeg，而IE9以及火狐上传的jpg图片是image/jpeg  
            expandedName = ".jpg";  
        }else if(uploadContentType.equals("image/png") || uploadContentType.equals("image/x-png")){  
            //IE6上传的png图片的headimageContentType是"image/x-png"  
            expandedName = ".png";  
            //System.out.println("expandedName:"+expandedName);
        }else if(uploadContentType.equals("image/gif")){  
            expandedName = ".gif";  
        }else if(uploadContentType.equals("image/bmp")){  
            expandedName = ".bmp";  
        }else{  
            out.println("<script type=\"text/javascript\">");    
            out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",''," + "'文件格式不正确（必须为.jpg/.gif/.bmp/.png文件）');");   
            out.println("</script>");  
            return null;  
        }  
          
        if(upload.length() > 600*1024){  
            out.println("<script type=\"text/javascript\">");    
            out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",''," + "'文件大小不得大于600k');");   
            out.println("</script>");  
            return null;  
        }  
          
          
        InputStream is = new FileInputStream(upload);  
        
      String uploadPath = ServletActionContext.getServletContext()     
                .getRealPath("/admin/img/postImg");  

        String fileName = java.util.UUID.randomUUID().toString();  //采用时间+UUID的方式随即命名  
        fileName += expandedName;  
        //System.out.println(" fileName:"+ fileName);
        File toFile = new File(uploadPath, fileName);  
       
        OutputStream os = new FileOutputStream(toFile); 
        //System.out.println("2222");
        byte[] buffer = new byte[1024];     
        int length = 0;  
        while ((length = is.read(buffer)) > 0) {     
            os.write(buffer, 0, length);     
        }     
        is.close();  
        os.close();  
      //  System.out.println("111111111");
        // 返回“图像”选项卡并显示图片  
        out.println("<script type=\"text/javascript\">");    
        out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" +"/yicunyipin01/admin/img/postImg/" + fileName + "','')");    
        out.println("</script>");  
          
        return null;  
	}
    
    

}
