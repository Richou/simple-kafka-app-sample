package com.heanoria.reminders.kafkasample.producer.facades;

import com.heanoria.reminders.kafkasample.producer.processes.GenerateAlbumProcess;
import com.heanoria.reminders.kafkasample.producer.services.ProducerService;

public class SendAlbumFacade {

    private final GenerateAlbumProcess process;
    private final ProducerService service;

    public SendAlbumFacade(ProducerService service) {
        this.service = service;
        this.process = new GenerateAlbumProcess();
    }

    public void sendMessages() {
        for (int index = 0; index < 1; index++) {
            service.send(process.getRandomAlbum());
        }
    }
}
