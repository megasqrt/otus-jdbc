package ru.kan.otus.tasker.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.jpa.dsl.Jpa;
import org.springframework.integration.jpa.support.PersistMode;
import org.springframework.integration.mail.dsl.Mail;
import org.springframework.messaging.MessageChannel;
import ru.kan.otus.tasker.domain.Task;
import ru.kan.otus.tasker.repositories.TaskRepository;
import ru.kan.otus.tasker.service.EmailService;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Objects;

@Configuration
public class IntegrationMailConfig {

    @Autowired
    private EmailService emailService;
    @Autowired
    private EntityManager entityManagerFactory;
    @Autowired
    private TaskRepository taskRepository;

    @Bean("jpaOutputAdapterChannel")
    public MessageChannel jpaOutputAdapterChannel() {
        return new DirectChannel();
    }

    @Bean("sendMailChannel")
    public MessageChannel sendMailChannel() {
        return new DirectChannel();
    }

    @Bean
    public IntegrationFlow mailListener(EntityManagerFactory entityManagerFactory) {
        return IntegrationFlows.from(Mail.imapInboundAdapter(
                emailService.getUrl()
                ).javaMailProperties(p -> {
                    p.put("mail.debug", "false");
                    p.put("mail.imaps.ssl.trust", "*");
                }),
                e -> e.poller(Pollers.fixedDelay(1000).maxMessagesPerPoll(1)))
                .<Message>handle((payload, headers) -> (payload))
                .<Message>filter(m -> {
                    try {
                        return m.getSubject().equals("New Task");
                    } catch (MessagingException e) {
                        e.printStackTrace();
                        return false;
                    }
                })
                .handle(emailService, "taskFromEmail")
                .<Task>filter(Objects::nonNull)
                .handle(Jpa.outboundAdapter(entityManagerFactory)
                                .entityClass(Task.class)
                                .persistMode(PersistMode.PERSIST),
                        e -> e.transactional(true))
                .get();
    }

    @Bean
    public IntegrationFlow sendMailFlow() {
        return IntegrationFlows.from("sendMailChannel")
                .handle(Mail.outboundAdapter("smtp.mail.ru")
                                .port(25)
                                .credentials(emailService.getEmail(), emailService.getPassword())
                                .javaMailProperties(p -> {
                                    p.put("mail.debug", "true");
                                    p.put("mail.smtp.ssl.trust", "*");
                                    p.put("mail.smtp.starttls.enable", "true");
                                }),
                        e -> e.id("sendMailEndpoint"))
                .get();
    }
}
