package com.parviz.javachallenge;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	private static ArrayList<Contact> contacts;
	private static Scanner sc;
	private static int id = 0;

	public static void main(String[] args) {
		
		contacts = new ArrayList<>();
		System.out.println("Welcome to the contacts service!");
		showInitialOptions();
	}

	private static void showInitialOptions() {
		System.out.println("Please select one" + 
				"\n\t 1. Manage contacts" + 
				"\n\t 2. Messages" + 
				"\n\t 3. Quit");
		
		sc = new Scanner(System.in);
		int choice = sc.nextInt();
		
		switch(choice) {
		case 1:
			manageContacts();
			break;
		case 2:
			manageMessages();
			break;
		default:
			break;
		}
	}

	private static void manageMessages() {
		System.out.println("Please select one:" + 
				"\n\t 1. Show all messages" + 
				"\n\t 2. Send a new message" + 
				"\n\t 3. Go back");
		
		int choice = sc.nextInt();
		switch(choice) {
		case 1:
			showAllMessages();
			break;
		case 2:
			sendNewMessage();
			break;
		default:
			showInitialOptions();
			break;
		}
	}

	private static void sendNewMessage() {
		System.out.println("Who are you going to send a message?");
		String name = sc.next();
		
		if(name.equals("")) {
			System.out.println("Please enter the name of the contact");
			sendNewMessage();
		}
		else {
			boolean doesExist = false;
			for(Contact c: contacts) {
				if(c.getName().equals(name)) {
					doesExist = true;
				}
			}
			
			if(doesExist) {
				System.out.println("What are you going to say?");
				String text = sc.next();
				
				if(text.equals("")) {
					System.out.println("Please enter some message");
					sendNewMessage();
				}
				else {
					id++;
					Message newMessage = new Message(text, name, id);
					for(Contact c: contacts) {
						if(c.getName().equals(name)) {
							ArrayList<Message> newMessages = c.getMessages();
							newMessages.add(newMessage);
							c.setMessages(newMessages);
						}
					}
				}
			}
			else {
				System.out.println("There is no such contact");
			}
		}
		
		showInitialOptions();
	}

	private static void showAllMessages() {
		ArrayList<Message> allMessages = new ArrayList<>();
		for(Contact c: contacts) {
			allMessages.addAll(c.getMessages());
		}
		
		if(allMessages.size() > 0) {
			for(Message m: allMessages) {
				m.getDetails();
				System.out.println("***********");
			}
		}
		else {
			System.out.println("You don't have any messages");
		}
		
		showInitialOptions();
	}

	private static void manageContacts() {
		System.out.println("Please select one" + 
				"\n\t 1. Show all contacts " + 
				"\n\t 2. Add a new contact " + 
				"\n\t 3. Search for a contact " + 
				"\n\t 4. Delete a contact " + 
				"\n\t 5. Go back ");
		
		int choice = sc.nextInt();
		
		switch(choice) {
		case 1:
			showAllContacts();
			break;
		case 2:
			addNewContact();
			break;
		case 3:
			searchForContact();
			break;
		case 4:
			deleteContact();
			break;
		default:
			showInitialOptions();
			break;
		}
	}

	private static void deleteContact() {
		System.out.println("Please enter the contact's name:");
		String name = sc.next();
		
		if(name.equals("")) {
			System.out.println("Please enter the name properly!");
			deleteContact();
		}
		else {
			boolean doesExist = false;
			Contact contactToDelete = null;
			for(Contact c: contacts) {
				if(c.getName().equals(name)) {
					doesExist = true;
					contactToDelete = c;
				}
			}
			
			if(!doesExist) {
				System.out.println("There is no such contact in your phone");
			}
			else {
				contacts.remove(contactToDelete);
			}
		}
		
		showInitialOptions();
	}

	private static void searchForContact() {
		System.out.println("Please enter the contact name:");
		String name = sc.next();
		
		if(name.equals("")) {
			System.out.println("Please enter the contact name properly!");
			searchForContact();
		}
		else {
			boolean doesExist = false;
			for(Contact c: contacts) {
				if(c.getName().equals(name)) {
					doesExist = true;
					c.getDetails();
				}
			}
			
			if(!doesExist) {
				System.out.println("There is no such contact in your phone");
			}
			
		}
		
		showInitialOptions();
	}

	private static void addNewContact() {
		System.out.println("Adding a new contact..." + 
				"\nPlease enter the contact's name: ");
		String name = sc.next();
		System.out.println("Please enter the contact's number:");
		String number = sc.next();
		System.out.println("Please enter the contact's email:");
		String email = sc.next();
		
		if(name.equals("") || number.equals("") || email.equals("")) {
			System.out.println("Please enter all the information");
			addNewContact();
		}
		else {
			boolean doesExist = false;
			for(Contact c: contacts) {
				if(c.getName().equals(name)) {
					doesExist = true;
				}
			}
			
			if(doesExist) {
				System.out.println("Contact with the name " + name + " already exists");
				addNewContact();
			}
			else {
				Contact contact = new Contact(name, number, email);
				contacts.add(contact);
				System.out.println(name + " successfully added!");
			}
			
			
		}
		
		showInitialOptions();
	}

	private static void showAllContacts() {
		for(Contact c : contacts) {
			c.getDetails();
			System.out.println("**********");
		}
		
		showInitialOptions();
	}

}
