package com.driver;

import java.util.*;

public class Gmail extends Email {

    private int inboxCapacity; //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)

    private List<Mail> inbox = new ArrayList<>();
    private List<Mail> trash = new ArrayList<>();

    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity = inboxCapacity;
    }

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.

        if(inbox.size()==inboxCapacity)
        {
            Mail mail = inbox.get(0);
            trash.add(mail);
            inbox.remove(mail);
        }
        Mail mail = new Mail(new Date(), sender, message);
        inbox.add(mail);
    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
        for(Mail mail : inbox)
        {
            if(mail.getMessage().equals(message))
            {
                trash.add(mail);
                inbox.remove(mail);
                return;
            }
        }
    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox

        int inboxSize = inbox.size();
        if(inboxSize==0)
        {
            return null;
        }
        return inbox.get(inboxSize-1).getMessage();

    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox

        int inboxSize = inbox.size();
        if(inboxSize==0)
        {
            return null;
        }
        return inbox.get(0).getMessage();

    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date

        int cnt = 0;

        for(Mail mail : inbox)
        {
            if(mail.getDate().compareTo(start)>=0 && mail.getDate().compareTo(end)<=0)
            {
                cnt++;
            }
        }
        return cnt;


    }

    public int getInboxSize(){
        // Return number of mails in inbox

        return inbox.size();

    }

    public int getTrashSize(){
        // Return number of mails in Trash

        return trash.size();

    }

    public void emptyTrash(){
        // clear all mails in the trash

        trash.clear();

    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox

        return this.inboxCapacity;

    }

    public void setInboxCapacity(int inboxCapacity) {
        this.inboxCapacity = inboxCapacity;
    }

    public List<Mail> getInbox() {
        return inbox;
    }

    public void setInbox(List<Mail> inbox) {
        this.inbox = inbox;
    }

    public List<Mail> getTrash() {
        return trash;
    }

    public void setTrash(List<Mail> trash) {
        this.trash = trash;
    }
}
