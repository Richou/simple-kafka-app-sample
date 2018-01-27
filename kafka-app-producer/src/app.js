import ProducerService from "./services/ProducerService";

const musics = require('../assets/musics');

const producerService = new ProducerService();
const payloads = musics.map(music => JSON.stringify(music));
producerService.sendMessages('musics', payloads);