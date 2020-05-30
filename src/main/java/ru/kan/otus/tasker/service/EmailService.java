package ru.kan.otus.tasker.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import ru.kan.otus.tasker.domain.Task;

import javax.mail.MessagingException;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;

@Service
public class EmailService {

    @Value("${mail.email}")
    private String email;

    @Value("${mail.password}")
    private String password;

    private static final String[] TASKS = {"update data", "write document", "health check"};

    private final MessageChannel sendMailChannel;

    public EmailService(@Qualifier("sendMailChannel") MessageChannel sendMailChannel) {
        this.sendMailChannel = sendMailChannel;
    }

    public void sendEmails(int emailCount) {
        for (int i = 0; i < emailCount; i++) {
            Message message = MessageBuilder.withPayload(createRandomTaskFromEmail()).build();
            sendMailChannel.send(message);
        }
    }

    private static String generateTaskTitle() {
        return new String(TASKS[RandomUtils.nextInt(0, TASKS.length)]);
    }

    public SimpleMailMessage createRandomTaskFromEmail() {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("New Task");
        String taskTitle = generateTaskTitle();
        mailMessage.setText("{\"title\":\"" + taskTitle + "\",\"description\":\"" + taskTitle + "\",\"status\":\"NEW\",\"priority\":\"LOW\"}");
        //отправляем сами себе
        mailMessage.setTo(this.email);
        mailMessage.setFrom(this.email);

        return mailMessage;
    }

    public Task taskFromEmail(javax.mail.Message message) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Task newTask = mapper.readValue(message.getContent().toString().replaceAll("(\\r|\\n)", ""), Task.class);
            System.out.println(newTask);
            return newTask;
        } catch (IOException | MessagingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getUrl() {
        return "imaps://"
                + URLEncoder.encode(this.email, Charset.defaultCharset())
                + ":"
                + URLEncoder.encode(this.password, Charset.defaultCharset())
                + "@imap.mail.ru:993/inbox";
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }
}
