package com.github.mayangbo666.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.OutputStream;

@Controller
public class UploadController extends BaseController {

    @RequestMapping("/upload2")
    public void upload2(MultipartFile myFile, HttpServletRequest request, HttpServletResponse response) {
        try {
            String path = WebUtils.getRealPath(request.getServletContext(), "/upload/" + getUserId(request.getSession()));
            myFile.transferTo(new File(path));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (OutputStream os = response.getOutputStream()) {
            String script = "<script>parent.reloadAvatar();</script>";
            os.write(script.getBytes());
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
