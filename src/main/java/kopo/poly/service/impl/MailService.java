package kopo.poly.service.impl;

import kopo.poly.dto.MailDTO;
import kopo.poly.persistance.mapper.IFileMapper;
import kopo.poly.persistance.mapper.IUserMapper;
import kopo.poly.service.IMailService;
import kopo.poly.util.CmmUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

import static kopo.poly.util.PrivateUtil.mainEmailID;
import static kopo.poly.util.PrivateUtil.mainEmailPW;

@Slf4j
@Service("MailService")
public class MailService implements IMailService {

    public final IUserMapper Usermapper;

    @Autowired
    public MailService(IUserMapper mapper, IUserMapper Usermapper){
        this.Usermapper = Usermapper;
    }


    @Override
    public  int sendMail(MailDTO mDTO){

        log.info("메일 전송 시작");


        HtmlEmail email1 = new HtmlEmail();
        email1.setHostName("smtp.naver.com");
        email1.setSmtpPort(465);

        email1.setAuthentication(mainEmailID, mainEmailPW);

        email1.setSSLOnConnect(true);
        email1.setStartTLSEnabled(true);

        int res = 0;

        try{
            email1.setFrom(mainEmailID, "관리자", "utf-8"); //???
            email1.addTo(mDTO.getToMail(), "사용자", "utf-8"); //받는사람
            email1.setSubject(mDTO.getTitle()); //제목

            email1.setHtmlMsg((String)mDTO.getContents());  //내용
            email1.send();
            res = 1;
        } catch (EmailException e) {
            e.printStackTrace();
        }

        log.info("메일 전송 완료");
        return  res;
    }
}

