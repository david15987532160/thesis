package com.phamanh.thesis;

import com.phamanh.thesis.models.ResultModel;
import com.phamanh.thesis.models.UserModel;
import com.phamanh.thesis.models.UserModelReq;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

@RestController
public class TranslateController {
//    @GetMapping("/hello")
//    public ResultModel test(
//            @RequestParam("name") String name,
//            @RequestParam("age") int age
//    ) {
////        Object userModel = UserModel.builder().name(name).age(age).build();
//        return ResultModel.builder()
////                .data(userModel)
//                .build();
//    }


//    @PostMapping("/test_hello2")
//    public ResultModel sayHello(
//            UserModelReq req
//    ) {
//        int age = req.getAge();
//        String name = req.getName();
//
//        StringBuilder pyBuilder = new StringBuilder("/usr/bin/python3 /home/phamanh/Documents/thesis/test.py -s ");
//
////        String py = "";
////
////        String[] cmd = new String[]{py, name};
//
//        pyBuilder.append(URLEncoder.encode(name.trim(), StandardCharsets.UTF_8).replace("+", "%20"));
//
//        System.out.println(pyBuilder.toString());
//
//        try {
//            Process p = Runtime.getRuntime().exec(pyBuilder.toString());
//            p.waitFor();
//
//            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
//            StringBuilder sb = new StringBuilder();
//            String line1 = null;
//            while ((line1 = reader.readLine()) != null) {
//                sb.append(line1);
//            }
//            return ResultModel.builder().data(sb.toString()).build();
//        } catch (Exception e) {
//            return ResultModel.builder().error(e.getMessage()).build();
//        }
//    }

    @PostMapping("/test_hello")
    public String translate(
            String res
    ) {
        String result = res;

        StringBuilder pyBuilder = new StringBuilder("/usr/bin/python3 /home/phamanh/Documents/thesis/en_vi.py -s ");

//        String py = "";
//
//        String[] cmd = new String[]{py, name};

        pyBuilder.append(URLEncoder.encode(result.trim(), StandardCharsets.UTF_8).replace("+", "%20"));

        System.out.println(pyBuilder.toString());

        try {
            Process p = Runtime.getRuntime().exec(pyBuilder.toString());
            p.waitFor();

            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line1 = null;
            while ((line1 = reader.readLine()) != null) {
                sb.append(line1);
            }
//            return ResultModel.builder().data(sb.toString()).build();
            return sb.toString();
        } catch (Exception e) {
//            return ResultModel.builder().error(e.getMessage()).build();
            return e.getMessage();
        }
    }

    @GetMapping("/translate")
    public String translate_GET(
            @RequestParam("q") String q
    ) {
        // Do somethings...

        if (!q.equals("we were poor")) {
//            return ResultModel
//                    .builder()
//                    .code(111)
//                    .error("Cannot translate.")
//                    .build();

            return "Cant translate.";
        }

//        return ResultModel.builder().data("Xin chao.").build();
//        return ResultModel.builder().data("Xin chao.").build();

        return "chúng tôi đã từng nghèo";
    }
}
