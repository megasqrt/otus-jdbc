package ru.kan.otus.tasker.integration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;

@IntegrationComponentScan
@SuppressWarnings({"resource", "Duplicates", "InfiniteLoopStatement"})
@ComponentScan
@Configuration
@EnableIntegration
public class Config {

    //TODO научиться приоритезировать задачи
    @Bean
    public QueueChannel taskChannel() {
        return MessageChannels.queue(100).get();
    }

    @Bean
    public PublishSubscribeChannel doneChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        //return Pollers.fixedRate(10000).maxMessagesPerPoll(2).get() ;
        return Pollers.fixedRate(100).get();
    }

    @Bean
    public IntegrationFlow taskFlow() {
        return IntegrationFlows.from("taskChannel")
                .split()
                .handle("taskmanagerService", "work")
                .aggregate()
                .channel("doneChannel")
                .get();
    }
}
