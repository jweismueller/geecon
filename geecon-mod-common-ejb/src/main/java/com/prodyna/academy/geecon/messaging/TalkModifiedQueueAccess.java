package com.prodyna.academy.geecon.messaging;

import javax.inject.Inject;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.slf4j.Logger;

public class TalkModifiedQueueAccess {

	@Inject
	private Logger log;

	@Inject
	private ConnectionFactory connectionFactory;

	@Inject
	private Queue queue;

	public void send(String msg) {
		try {
			Connection connection = connectionFactory.createConnection();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			MessageProducer messageProducer = session.createProducer(queue);
			connection.start();
			TextMessage message = session.createTextMessage();
			message.setText(msg);
			messageProducer.send(message);
			messageProducer.close();
			session.close();
			connection.close();
		} catch (JMSException e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}
}
