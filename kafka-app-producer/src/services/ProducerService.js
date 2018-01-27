import kafka from "kafka-node";

export default class ProducerService {
    constructor() {
        const Producer = kafka.Producer;
        const client = new kafka.Client('localhost:2181');
        this.producer = new Producer(client);
    }

    sendMessages(topic, payloads) {
        this.producer.on('ready', () => {
            console.log('Producer is ready !');
            this.producer.send([{topic: topic, messages: payloads, groupId: 'musics-grp'}], (err, data) => {
                console.log(err || data);
                process.exit()
            })
        })
    }
}