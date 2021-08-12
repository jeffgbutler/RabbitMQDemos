# RabbitMQ Message Sender

This is a simple app that will send messages continuously to three different types of queues on a RabbitMQ instance:

1. A durable quorum queue
2. A durable classic queue
3. A non-durable classic queue

The app is intended for deployment to Cloud Foundry and will automatically connect to a RabbitMQ service instance
bound to the application. After the application is deployed, point a browser to the application's route. On the
home page you can enter a simple message, an interval, and then press "start". The application will continuously
send messages until you press the "stop" button.

For example, if you enter the message "Test Message" and an interval of 500, the application will send messages every
500 milliseconds in the form "Test Message (1)", "Test Message (2)", etc.
