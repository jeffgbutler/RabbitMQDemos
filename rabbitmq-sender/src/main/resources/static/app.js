new Vue({
    el: "#app",
    data: {
        message: '',
        interval: 1000,
        started: false,
        messageNumber: 0,
        responses: []
    },
    computed: {
        startEnabled: function() {
            return this.message.length > 0 && !this.started;
        }
    },
    methods: {
        start: function() {
            if (this.started) {
                return;
            }
            this.started = true;
            this.sendMessage();
        },
        stop: function() {
            this.started = false;
        },
        reset: function() {
            this.responses = [];
            this.messageNumber = 0;
        },
        scheduleUpdate: function() {
            setTimeout(() => this.sendMessage(), this.interval);
        },
        sendMessage: function() {
            this.messageNumber += 1;
            let theMessage = `${this.message} (${this.messageNumber})`;
            fetch(`/api/sendMessage?message=${theMessage}`, {method: 'GET'})
                .then(res => {
                    if (!res.ok) {
                        throw new Error(`Received response ${res.status}`)
                    }
                    return res.text();
                })
                .then((data) => {
                    this.addMessage(theMessage, data);
                })
                .catch((err) => {
                    this.addMessage(theMessage, err);
                })
                .then(() => {
                    if (this.started) {
                        this.scheduleUpdate();
                    }
                });
        },
        addMessage: function (theMessage, theResponse) {
            this.responses.unshift({
                message: theMessage,
                response: theResponse
            });
            this.responses = this.responses.slice(0, 20);
        }
    }
});
