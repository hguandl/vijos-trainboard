package cn.edu.sustech.adoj.trainboard.web;

import cn.edu.sustech.adoj.trainboard.config.CallUrls;
import cn.edu.sustech.adoj.trainboard.model.cas.ServiceResponse;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CasLogin {
    private final CallUrls callUrls;
    private final HttpServletRequest request;

    private final XmlMapper xmlMapper = new XmlMapper();

    @Autowired
    public CasLogin(CallUrls callUrls, HttpServletRequest request) {
        this.callUrls = callUrls;
        this.request = request;
    }

    @GetMapping("/caslogin")
    public String casLogin(Model model) {
        String loginUrl = String.format("%s?service=%s", callUrls.getLoginBaseUrl(), callUrls.getServiceUrl());

        String ticket = request.getParameter("ticket");

        if (ticket == null) {
            return String.format("redirect:%s", loginUrl);
        }

        RestTemplate restTemplate = new RestTemplate();

        String url = String.format("%s?service=%s&ticket=%s",
                callUrls.getValidateUrl(), callUrls.getServiceUrl(), ticket);

        String xmlResponseString = restTemplate.getForObject(url, String.class);

        try {
            ServiceResponse xmlResponse = xmlMapper.readValue(xmlResponseString, ServiceResponse.class);
            model.addAttribute("user", xmlResponse.getAuthenticationSuccess().getAttributes());
            return "caslogin";
        } catch (Exception e) {
            return "redirect:/";
        }

    }
}
