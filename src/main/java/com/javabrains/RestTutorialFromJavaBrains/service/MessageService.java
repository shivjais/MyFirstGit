package com.javabrains.RestTutorialFromJavaBrains.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.javabrains.RestTutorialFromJavaBrains.database.DatabaseClass;
import com.javabrains.RestTutorialFromJavaBrains.exception.DataNotFoundException;
import com.javabrains.RestTutorialFromJavaBrains.model.Message;

public class MessageService {

	private Map<Long,Message> messages = DatabaseClass.getMessages();
	
	/*public List<Message> getAllMessages(){
		List<Message> list=new ArrayList<>();
		Message m1=new Message(1L, "Hello World", "Shivam");
		Message m2=new Message(2L, "Hello Shiv", "Shivam");
		list.add(m1);
		list.add(m2);
		return list;
	}*/
	public MessageService(){
		messages.put(1L, new Message(1, "Hello World", "Shivam"));
		messages.put(2L, new Message(2, "Hello Shiv","Shivam"));
	}
	public List<Message> getAllMessages(){
		return new ArrayList<>(messages.values());
	}
	
	/*public Message getMessage(long id){
		
		return  messages.get(id);
	}*/
	
	public Message getMessage(long id){
		Message message=messages.get(id);
		if(message == null){
			throw new DataNotFoundException("No data found with id "+id);
		}
		return  message;
	}
	
	public Message addMessage(Message message){
		message.setId(messages.size() + 1);
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message updateMessage(Message message){
		if(message.getId() <=0){
			return null;
		}
		System.out.println(message.getId());
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message removeMessage(long id){
		return messages.remove(id);
	}
	
	public List<Message> getAllMessageForYear(int year){
		List<Message> messageForYear = new ArrayList<>();
		Calendar calendar=Calendar.getInstance();
		for (Message message : messages.values()) {
			calendar.setTime(message.getCreated());
			if(calendar.get(Calendar.YEAR)== year){
				messageForYear.add(message);
			}
		}
		return messageForYear;
	}
	
	public List<Message> getAllMessagePaginated(int start,int size){
		List<Message> list=new ArrayList<>(messages.values());
		return list.subList(start, size);
	}
}
