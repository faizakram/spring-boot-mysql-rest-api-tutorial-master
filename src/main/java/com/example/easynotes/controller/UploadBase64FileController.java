package com.example.easynotes.controller;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.easynotes.utils.UserInfo;


@RestController
public class UploadBase64FileController {


	
	
	@PostMapping("/image")
    public @ResponseBody String saveImage(@RequestBody UserInfo image) throws IOException  {     
       
		try
        {
            //This will decode the String which is encoded by using Base64 class
            byte[] imageByte=Base64.decodeBase64(image.getBase64File());
            new FileOutputStream("E://sample.jpg").write(imageByte);
            return "success ";
        }
        catch(Exception e)
        {
            return "error = "+e;
        }
    }

}
