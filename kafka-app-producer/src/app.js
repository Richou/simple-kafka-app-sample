import ProducerService from "./services/ProducerService";

const producerService = new ProducerService();
producerService.sendMessages('musics', ['coucou', 'oui']);